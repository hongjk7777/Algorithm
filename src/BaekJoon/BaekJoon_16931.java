package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16931 {
    private static int mapRow, mapCol, maxHeight;
    private static int[][] map;
    private static final int[] dRow = {0, 0, 1, -1};
    private static final int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getSurfaceArea();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapCol; col++) {
                int input = Integer.parseInt(tokenizer.nextToken());
                map[row][col] = input;
                maxHeight = Math.max(maxHeight, input);
            }
        }
    }

    private static int getSurfaceArea() {
        int sum = 0;

        for (int curHeight = 1; curHeight <= maxHeight; curHeight++) {
            sum = getSurfaceAreaAtHeight(curHeight);
        }
        sum += mapRow * mapCol * 2;

        return sum;
    }

    private static int getSurfaceAreaAtHeight(int curHeight) {
        int tempSum = 0;
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (map[row][col] < curHeight) {
                    continue;
                }

                for (int dir = 0; dir < 4; dir++) {
                    int tempRow = row + dRow[dir];
                    int tempCol = col + dCol[dir];
                    if (!isInArea(tempRow, tempCol)) {
                        tempSum++;
                        continue;
                    }

                    if (map[tempRow][tempCol] < curHeight) {
                        tempSum++;
                    }
                }
            }
        }
        return tempSum;
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }
}
