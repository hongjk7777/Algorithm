package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11055 {
    private static int n, max = 0;
    private static int[] sequence, dp;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        sequence = new int[n + 1];
        dp = new int[n + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= n; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + sequence[i]);
                }
            }
            updateMax(dp[i]);
        }
    }

    private static void updateMax(int temp) {
        max = Math.max(max, temp);
    }

    private static void printAnswer() {
        System.out.println(max);
    }
}
