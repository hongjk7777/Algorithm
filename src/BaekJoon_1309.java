import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1309 {
    public static final int MOD = 9901;
    private static int n;
    //dp[i][j]는 i번 우리까지 왔을 때
    // 이전 우리의 사자에 따른(ex j = 1 이전에 사자 있음) 경우의 수 최대값
    private static int[][] dp;

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
        dp = new int[n + 1][2];
    }

    private static void makeDP() {
        dp[1][0] = 1;
        dp[1][1] = 2;
        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][0]) % MOD;
            dp[i][1] = (dp[i - 1][0] * 2 + dp[i - 1][1]) % MOD;
        }
    }

    private static void printAnswer() {
        int sum = (dp[n][0] + dp[n][1]) % MOD;
        System.out.println(sum);
    }
}
