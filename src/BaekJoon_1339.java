/*
아쉬운 점
1. 문제 분류에 너무 집착해서 풀다 보니 쉬운 길을 돌아갔다
ㄴ 문제 분류로만 풀려고 하지 말자
2. 문제에는 알파벳 J까지 쓴다고 한 게 아니라 10개를 쓴다 했는데
내맘대로 J까지로 정해서 풀다가 꽤 많이 틀렸다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1339 {
    public static final int ALPHABETNUM = 26;
    private static int wordAmount;
    private static final long[] alphabetSum = new long[ALPHABETNUM];
    private static String[] words;

    public static void main(String[] args) throws IOException {
        getInput();
        makeAlphabetSums();
        long answer = getMaxSum();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        wordAmount = Integer.parseInt(tokenizer.nextToken());
        words = new String[wordAmount];

        for (int i = 0; i < wordAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            words[i] = tokenizer.nextToken();
        }
    }

    private static void makeAlphabetSums() {
        for (int i = 0; i < wordAmount; i++) {
            makeAlphabetSum(words[i]);
        }
    }

    private static void makeAlphabetSum(String word) {
        for (int i = 0; i < word.length(); i++) {
            char digit = word.charAt(i);
            int digitValue = digit - 'A';
            alphabetSum[digitValue] += Math.pow(10, word.length() - 1 - i);
        }
    }

    private static long getMaxSum() {
        long sum = 0;
        Arrays.sort(alphabetSum);

        int index = ALPHABETNUM - 1;
        int num = 9;
        while (index >= 0 && alphabetSum[index] > 0) {
            sum += alphabetSum[index] * num;
            index--;
            num--;
        }

        return sum;
    }
}
