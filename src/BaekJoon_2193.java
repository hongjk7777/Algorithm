import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2193 {
    private static int n;

    //dp[i][j]는 j를 첫 수로 가지는 크기 j의 이친수(1번 조건 무시)
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
        dp = new long[n + 1][2];
    }

    private static void makeDP() {
        initializeDP();
        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }
    }

    private static void initializeDP() {
        dp[1][0] = 1;
        dp[1][1] = 1;
    }

    private static void printAnswer() {
        System.out.println(dp[n][1]);
    }
}
