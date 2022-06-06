package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11058 {
    private static int n;
    private static final long[] dp = new long[101];

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        System.out.println(dp[n]);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
    }

    private static void makeDP() {

        for (int i = 1; i <= 100; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1] + 1);

            long temp = dp[i];
            for (int j = i + 3; j <= 100; j++) {
                temp += dp[i];
                dp[j] = Math.max(dp[j], temp);
            }
        }
    }
}
