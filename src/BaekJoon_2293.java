import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2293 {
    private static int coinAmount, goalValue;
    private static int[] coinValue;
    private static final int MAX_COIN = 100, MAX_VALUE = 10000;
    private static final int[][] dp = new int[MAX_COIN + 1][MAX_VALUE + 1];

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        System.out.println(dp[coinAmount][goalValue]);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        coinAmount = Integer.parseInt(tokenizer.nextToken());
        goalValue = Integer.parseInt(tokenizer.nextToken());
        coinValue = new int[coinAmount];

        for (int i = 0; i < coinAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            coinValue[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        dp[0][0] = 1;

        for (int i = 0; i < coinAmount; i++) {
            int value = coinValue[i];
            for (int j = 0; j <= MAX_VALUE; j++) {
                dp[i + 1][j] = dp[i][j];

                if (j - value >= 0) {
                    dp[i + 1][j] += dp[i + 1][j - value];
                }
            }
        }
    }
}
