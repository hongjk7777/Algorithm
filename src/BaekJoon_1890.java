import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1890 {
    private static int mapSize;
    private static int[][] map;
    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        System.out.println(dp[mapSize - 1][mapSize - 1]);
    }

    private static void makeDP() {
        dp[0][0] = 1;

        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                int jumpSize = map[row][col];

                if (jumpSize == 0) {
                    continue;
                }

                if (row + jumpSize < mapSize) {
                    dp[row + jumpSize][col] += dp[row][col];
                }
                if (col + jumpSize < mapSize) {
                    dp[row][col + jumpSize] += dp[row][col];
                }
            }
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapSize][mapSize];
        dp = new long[mapSize][mapSize];

        for (int row = 0; row < mapSize; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }
}
