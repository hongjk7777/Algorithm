import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2225 {
    private static final int MOD = 1000000000;
    private static int n, k;
    private static long[][] dp;

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
        k = Integer.parseInt(tokenizer.nextToken());
        dp = new long[k + 1][n + 1];
    }

    private static void makeDP() {
        dp[0][0] = 1;

        for (int sumAmount = 1; sumAmount <= k; sumAmount++) {
            for (int curSum = 0; curSum <= n; curSum++) {
                for (int temp = 0; temp <= n; temp++) {
                    if (curSum - temp < 0) {
                        break;
                    }
                    dp[sumAmount][curSum] =
                            (dp[sumAmount][curSum] + dp[sumAmount - 1][curSum - temp]) % MOD;
                }
            }
        }
    }

    private static void printAnswer() {
        System.out.println(dp[k][n]);
    }
}
