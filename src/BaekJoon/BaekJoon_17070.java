package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_17070 {
    private static final int HORIZONTAL = 0, DIAGONAL = 1, VERTICAL = 2;
    private static int mapSize, answer = 0;
    private static int[][] map;
    private static final int[] dRow = {0, 1, 1};
    private static final int[] dCol = {1, 1, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        dfs(0, 1, HORIZONTAL);
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapSize][mapSize];

        for (int row = 0; row < mapSize; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void dfs(int row, int col, int lastDir) {
        if (isGoal(row, col)) {
            answer++;
            return;
        }

        int tempRow, tempCol;
        for (int nextDir = 0; nextDir < 3; nextDir++) {
            tempRow = row + dRow[nextDir];
            tempCol = col + dCol[nextDir];
            if (isInArea(tempRow, tempCol) &&
                    ableToMove(tempRow, tempCol, lastDir, nextDir)) {
                dfs(tempRow, tempCol, nextDir);
            }
        }
    }

    private static boolean isGoal(int row, int col) {
        return row == mapSize - 1 && col == mapSize - 1;
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }

    private static boolean ableToMove(int row, int col, int lastDir,  int nextDir) {
        if (isUnableDir(lastDir, nextDir)) {
            return false;
        }

        return isAbleToPlacePipe(row, col, nextDir);
    }

    private static boolean isUnableDir(int lastDir, int nextDir) {
        return lastDir == HORIZONTAL && nextDir == VERTICAL || lastDir == VERTICAL && nextDir == HORIZONTAL;
    }

    private static boolean isAbleToPlacePipe(int row, int col, int nextDir) {
        if (nextDir == DIAGONAL) {
            return map[row][col] == 0 && map[row - 1][col] == 0
                    && map[row][col - 1] == 0;
        } else {
            return map[row][col] == 0;
        }
    }
}
