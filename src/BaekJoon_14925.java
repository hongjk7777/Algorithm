import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_14925 {
    private static int mapRow, mapCol;
    private static int[][] map, rowDP, colDP;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printMax();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapRow][mapCol];
        rowDP = new int[mapRow][mapCol];
        colDP = new int[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void makeDP() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {

                if (map[row][col] != 0) {
                    continue;
                }

                rowDP[row][col] = 1;
                colDP[row][col] = 1;

                if (isInMap(row - 1, col)) {
                    rowDP[row][col] = Math.min(rowDP[row][col], rowDP[row - 1][col] + 1);
                    colDP[row][col] = Math.min(colDP[row][col], colDP[row - 1][col]);
                }

                if (isInMap(row, col - 1)) {
                    rowDP[row][col] = Math.min(rowDP[row][col], rowDP[row][col - 1]);
                    colDP[row][col] = Math.min(colDP[row][col], colDP[row][col - 1] + 1);
                }

                if (isInMap(row - 1, col - 1)) {
                    rowDP[row][col] = Math.min(rowDP[row][col], rowDP[row - 1][col - 1] + 1);
                    colDP[row][col] = Math.min(colDP[row][col], colDP[row - 1][col - 1] + 1);
                }

            }
        }
    }
    private static boolean isInMap(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }

    private static void printMax() {
        int max = getMax();

        System.out.println(max);
    }

    private static int getMax() {
        int max = 0;
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                int squareSize = Math.min(rowDP[row][col], colDP[row][col]);
                max = Math.max(max, squareSize);
            }
        }
        return max;
    }
}
