import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1912 {
    private static final int INT_MIN = -987654321;
    private static int n, max = INT_MIN;
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
        dp = new int[n + 1];
        sequence = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = sequence[i - 1];
            } else {
                dp[i] = dp[i - 1] + sequence[i - 1];
            }

            updateMax(dp[i]);
        }
    }

    private static void updateMax(int temp) {
        if (max < temp) {
            max = temp;
        }
    }

    private static void printAnswer() {
        System.out.println(max);
    }
}
