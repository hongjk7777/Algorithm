/*
배운 점
1. kmp알고리즘의 작동 원리를 이해하고 실제로 문제를 풀어 볼 수 있었다.
ㄴ kmp알고리즘은 찾으려는 문자열과 전체 문자열의 길이를 각각 n, h 라 했을 때
   O(n + h)의 시간 복잡도를 갖으므로 굉장히 빠르게 동작한다.

의문점
1. pi배열은 왜 이름이 pi인지 모르겠다.
ㄴ 찾아본 결과 아마도 prefix function을 의미하는 듯하다
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_11585 {
    private static int stringSize, casesToEatMeats = 0;
    private static int[] pi;
    private static ArrayList<Character> roulette = new ArrayList<>();
    private static final ArrayList<Character> goal = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        makePartial();
        addAllCasesToRoulette();
        findCasesToEatMeats();
        printReducedFraction(stringSize, casesToEatMeats);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        stringSize = Integer.parseInt(tokenizer.nextToken());
        pi = new int[stringSize];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < stringSize; i++) {
            goal.add(tokenizer.nextToken().charAt(0));
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < stringSize; i++) {
            roulette.add(tokenizer.nextToken().charAt(0));
        }
    }

    //찾는 단어를 kmp 알고리즘 을 통해 pi를 만들자
    private static void makePartial() {
        int begin = 1, matched = 0;

        while (begin + matched < stringSize) {
            if (goal.get(begin + matched).equals(goal.get(matched))) {
                matched++;
                pi[begin + matched - 1] = matched;
            } else {
                if (matched == 0) {
                    begin++;
                }
                else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
    }

    //i부터 i + stringSize - 1 까지 확인 했을 때
    // 룰렛이 가질 수 있는 모든 경우의 수를 갖도록 한다.
    private static void addAllCasesToRoulette() {
        for (int i = 0; i < stringSize - 1; i++) {
            roulette.add(roulette.get(i));
        }
    }

    private static void findCasesToEatMeats() {
        //kmp를 사용해서 알맞은 문자열 개수 확인
        int begin = 0, matched = 0;
        while (begin + matched < roulette.size()) {
            if (roulette.get(begin + matched).equals(goal.get(matched))) {
                matched++;

                if (matched == stringSize) {
                    casesToEatMeats++;
                    begin += stringSize - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }

            }
        }
    }

    private static void printReducedFraction(int denominator, int numerator) {
        for (int i = 2; i <= stringSize; i++) {
            while (denominator % i == 0 && numerator % i == 0) {
                denominator /= i;
                numerator /= i;
            }
        }

        System.out.println(numerator + "/" + denominator);
    }
}
