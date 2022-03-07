/*
아쉬웠던 부분
1. 맨 처음엔 boolean 배열을 통해 풀려고 했는데 멍청한 짓이었다. 내 맘속으로도 시간이 오래
걸릴 거라고 생각하면서 풀이를 멈추지 않았다. 그 후엔 ArrayList를 풀려고 했다. 그러나 당연하게도
arrayList는 중간 문자열 삭제가 O(n) 시간이 걸리기엔 시간 초과가 나왔고, 그 후 중간 문자열만
생각하고 linkedList로 바꿔서 get에서 똑같은 문제로 결국 스택으로 돌아왔다. 사실 생각해보면
굉장히 단순하게도 자료구조의 기본적인 특성을 내가 알고는 있지만 적용을 제대로 못함을 깨달았다.
앞으로도 문자열과 자료구조 문제를 계속해서 풀어보자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BaekJoon_9935 {
    private static String bombStr;
    private static final Stack<Character> str = new Stack<>();
    private static final Stack<Character> checkedStr = new Stack<>();

    public static void main(String[] args) throws IOException {
        getInput();
        startBomb();
        printStr();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        String input = tokenizer.nextToken();

        for (int i = input.length() - 1; i >= 0; i--) {
            str.push(input.charAt(i));
        }

        tokenizer = new StringTokenizer(reader.readLine());
        bombStr = tokenizer.nextToken();
    }

    private static void startBomb() {
        while (!str.isEmpty()) {
            if (isBomb()) {
                removeBomb();

                for (int i = 0; i < bombStr.length() - 1; i++) {
                    if (checkedStr.isEmpty()) {
                        break;
                    }

                    str.push(checkedStr.pop());
                }
            } else {
                checkedStr.push(str.pop());
            }
        }
    }

    private static boolean isBomb() {
        boolean bomb = true;

        int index;
        for (index = 0; index < bombStr.length(); index++) {
            if (str.isEmpty()) {
                bomb = false;
                for (int i = 0; i < index; i++) {
                    str.push(checkedStr.pop());
                }
                break;
            }

            Character pop = str.pop();
            checkedStr.push(pop);

            if (!pop.equals(bombStr.charAt(index))) {
                bomb = false;
                for (int i = 0; i <= index; i++) {
                    str.push(checkedStr.pop());
                }
                break;
            }
        }

        return bomb;
    }

    private static void removeBomb() {
        for (int i = 0; i < bombStr.length(); i++) {
            checkedStr.pop();
        }
    }

    private static int updateCurIndex(int curIndex) {
        curIndex -= bombStr.length() - 1;

        if (curIndex < 0) {
            curIndex = 0;
        }
        return curIndex;
    }

    private static void printStr() {
        StringBuilder builder = new StringBuilder();

        while (!checkedStr.isEmpty()) {
            builder.append(checkedStr.pop());
        }
        builder.reverse();

        if (builder.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(builder);
        }
    }
}
