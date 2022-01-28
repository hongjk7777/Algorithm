import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11057 {
    private static final int MOD = 10007;
    private static int n;
    //dp[i][j]는 i번 size까지 왔고 j가 마지막 수일 때 경우의 수
    public static int[][] dp;

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
        dp = new int[n + 1][10];
    }

    private static void makeDP() {
        initializeDP();

        for (int i = 2; i <= n; i++) {
            for (int lastNum = 0; lastNum < 10; lastNum++) {
                for (int curNum = lastNum; curNum < 10; curNum++) {
                    dp[i][curNum] =
                            (dp[i][curNum] + dp[i - 1][lastNum]) % MOD;
                }
            }
        }
    }

    private static void initializeDP() {
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }
    }

    private static void printAnswer() {
        int answer = getAnswer();
        System.out.println(answer);
    }

    private static int getAnswer() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += dp[n][i];
        }
        return (sum % MOD);
    }
}
