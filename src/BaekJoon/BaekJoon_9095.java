package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_9095 {
    private static int testcaseAmount;
    private static int[] n, dp = new int[11];

    public static void main(String[] args) throws IOException {
        getInput();
        calculateDP();
        printSumWays();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        testcaseAmount = Integer.parseInt(tokenizer.nextToken());
        n = new int[testcaseAmount];

        for (int i = 0; i < testcaseAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            n[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void calculateDP() {
        dp[0] = 1;
        dp[1] = 1;
        for (int n = 2; n <= 10; n++) {
            for (int j = 1; j <= 3; j++) {
                if (n - j < 0) {
                    continue;
                }
                dp[n] += dp[n - j];
            }
        }
    }

    private static void printSumWays() {
        for (int testcaseNum = 0; testcaseNum < testcaseAmount; testcaseNum++) {
            printSumWay(testcaseNum);
        }
    }

    private static void printSumWay(int testcaseNum) {
        int temp = n[testcaseNum];
        System.out.println(dp[temp]);
    }
}
