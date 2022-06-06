package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1987 {
    private static int mapRow, mapCol, max = 0;
    private static char[][] map;
    private static final boolean[] checked = new boolean[26];
    private static final int[] dRow = {0, 0, 1, -1};
    private static final int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        dfs(0, 0, 1);
        System.out.println(max);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        map = new char[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = line.charAt(col);
            }
        }
    }

    private static void dfs(int row, int col, int depth) {
        int curAlphabet = map[row][col] - 'A';
        checked[curAlphabet] = true;
        updateMax(depth);

        for (int dir = 0; dir < 4; dir++) {
            int tempRow = row + dRow[dir];
            int tempCol = col + dCol[dir];

            if (isInMap(tempRow, tempCol)) {
                int nextAlphabet = map[tempRow][tempCol] - 'A';
                if (!checked[nextAlphabet]) {
                    dfs(tempRow, tempCol, depth + 1);
                }
            }
        }
        checked[curAlphabet] = false;
    }

    private static void updateMax(int depth) {
        max = Math.max(max, depth);
    }

    private static boolean isInMap(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }
}
