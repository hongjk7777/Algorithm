package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_5557 {
    private static int numAmount;
    private static int[] num;
    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        System.out.println(dp[numAmount - 2][num[numAmount - 1]]);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        numAmount = Integer.parseInt(tokenizer.nextToken());
        num = new int[numAmount];
        dp = new long[numAmount][21];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numAmount; i++) {
            num[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        dp[0][num[0]] = 1;

        for (int i = 1; i < numAmount - 1; i++) {
            for (int curSum = 0; curSum <= 20; curSum++) {
                long temp = dp[i - 1][curSum];
                if (temp > 0) {
                    if (isInLimit(curSum + num[i])) {
                        dp[i][curSum + num[i]] += temp;
                    }
                    if (isInLimit(curSum - num[i])) {
                        dp[i][curSum - num[i]] += temp;
                    }
                }
            }
        }
    }

    private static boolean isInLimit(int num) {
        return 0 <= num && num <= 20;
    }
}
