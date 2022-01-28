import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_13398 {
    private static final int MIN_INT = -987654321;
    private static int n;
    private static int[] sequence;
    //dp[i][j]는 i번 원소를 사용하고 j번 건너뛰었을 때 최댓값
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
        sequence = new int[n + 1];
        dp = new long[n + 1][2];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= n; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        for (int i = 1; i <= n; i++) {
            //건너뛰지 않을 경우 최댓값
            dp[i][0] = sequence[i];
            if (dp[i - 1][0] > 0) {
                dp[i][0] += dp[i - 1][0];
            }

            //1개 건너뛸 경우 최댓값
            dp[i][1] = MIN_INT;
            if (dp[i - 1][1] > 0) {
                dp[i][1] = dp[i - 1][1] + sequence[i];
            }
            if (i != 1) {
                //i == 1일 경우 음수 수열일 경우 답이 항상 0
                dp[i][1] = Math.max(dp[i][1], dp[i - 1][0]);
            }

        }
    }

    private static void printAnswer() {
        long max = getMax();
        System.out.println(max);
    }

    private static long getMax() {
        long max = MIN_INT;
        for (int i = 1; i <= n; i++) {
            long temp = Math.max(dp[i][0], dp[i][1]);
            max = Math.max(max, temp);
        }
        return max;
    }
}
