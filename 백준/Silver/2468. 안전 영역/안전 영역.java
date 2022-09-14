import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_HEIGHT = 100;
    private static int size;
    private static int[][] map;
    private static boolean[][] checked;
    private static int[] dRow = new int[]{0, 0, 1, -1};
    private static int[] dCol = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        int maxSafeAreas = 0;
        for (int rainHeight = 0; rainHeight < MAX_HEIGHT; rainHeight++) {
            maxSafeAreas = Math.max(maxSafeAreas, getSafeAreas(rainHeight));
        }

        System.out.println(maxSafeAreas);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        map = new int[size][size];

        for (int tempRow = 0; tempRow < size; tempRow++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int tempCol = 0; tempCol < size; tempCol++) {
                String value = tokenizer.nextToken();
                map[tempRow][tempCol] = Integer.parseInt(value);
            }
        }
    }


    private static int getSafeAreas(int rainHeight) {
        checked = new boolean[map.length][map[0].length];
        checkUnsafeAreas(rainHeight);
        int count = 0;

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (!checked[row][col]) {
                    count++;
                    dfs(row, col);
                }
            }
        }

        return count;
    }

    private static void checkUnsafeAreas(int rainHeight) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (map[row][col] <= rainHeight) {
                    checked[row][col] = true;
                }
            }
        }
    }

    private static void dfs(int row, int col) {
        checked[row][col] = true;
        for (int dir = 0; dir < 4; dir++) {
            int nextRow = row + dRow[dir];
            int nextCol = col + dCol[dir];
            if (isInArea(nextRow, nextCol) && !checked[nextRow][nextCol]) {
                dfs(nextRow, nextCol);
            }
        }
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < size && 0 <= col && col < size;
    }
}
