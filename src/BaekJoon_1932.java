import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1932 {
    private static int mapSize;
    private static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        getInput();
        findMaxRoute();
        printMaxRoute();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        mapSize = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapSize + 1][mapSize + 1];
        dp = new int[mapSize + 1][mapSize + 1];

        for (int i = 0; i < mapSize; i++) {
            tokenizer = new StringTokenizer(reader.readLine());

            for (int j = 1; j <= i + 1; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void findMaxRoute() {
        dp[0][1] = map[0][1];
        for (int i = 1; i < mapSize; i++) {
            for (int j = 1; j <= i + 1; j++) {
                if (j == 1) {
                    dp[i][j] = dp[i - 1][j] + map[i][j];
                } else if (j == i + 1) {
                    dp[i][j] = dp[i - 1][j - 1] + map[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + map[i][j];
                }
            }
        }
    }

    private static void printMaxRoute() {
        int max = 0;
        for (int i = 1; i <= mapSize; i++) {
            int temp = dp[mapSize - 1][i];
            if (temp > max) {
                max = temp;
            }
        }

        System.out.println(max);
    }
}
