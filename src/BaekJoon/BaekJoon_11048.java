package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11048 {
    private static int mapRow, mapCol;
    private static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        System.out.println(dp[mapRow - 1][mapCol - 1]);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapRow][mapCol];
        dp = new int[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void makeDP() {
        makeInitialValues();
        makeAllValues();
    }

    private static void makeInitialValues() {
        initializeFirstRow();
        initializeFirstCol();
    }

    private static void initializeFirstRow() {
        int sum = 0;
        for (int row = 0; row < mapRow; row++) {
            sum += map[row][0];
            dp[row][0] = sum;
        }
    }

    private static void initializeFirstCol() {
        int sum = 0;
        for (int col = 0; col < mapCol; col++) {
            sum += map[0][col];
            dp[0][col] = sum;
        }
    }

    private static void makeAllValues() {
        for (int row = 1; row < mapRow; row++) {
            for (int col = 1; col < mapCol; col++) {
                int temp = Math.max(dp[row - 1][col], dp[row][col - 1]);
                temp = Math.max(temp, dp[row - 1][col - 1]);

                dp[row][col] = temp + map[row][col];
            }
        }
    }
}
