import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1149 {
    private static final int MAX_INT = 987654321;
    private static int n;
    private static int[][] cost, dp;

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
        cost = new int[n][3];
        dp = new int[n + 1][3];

        for (int row = 0; row < n; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < 3; col++) {
                cost[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void makeDP() {
        for (int houseNum = 1; houseNum <= n; houseNum++) {
            for (int curColor = 0; curColor < 3; curColor++) {
                dp[houseNum][curColor] = MAX_INT;

                for (int lastColor = 0; lastColor < 3; lastColor++) {
                    if (curColor != lastColor) {
                        dp[houseNum][curColor] =
                                Math.min(dp[houseNum][curColor], dp[houseNum - 1][lastColor] + cost[houseNum - 1][curColor]);
                    }
                }
            }
        }
    }

    private static void printAnswer() {
        int min = Math.min(dp[n][0], dp[n][1]);
        min = Math.min(min, dp[n][2]);

        System.out.println(min);
    }

}
