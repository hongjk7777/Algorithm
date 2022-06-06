package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_17085 {
    private static int mapRow, mapCol, max = 0;
    private static char[][] map;
    private static final int[] crossWidth = {1, 5, 9, 13, 17, 21, 25, 29};

    public static void main(String[] args) throws IOException {
        getInput();
        dfs(0, 1);
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

    private static void dfs(int depth, int value) {
        if (depth == 2) {
            max = Math.max(max, value);
            return;
        }

        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                for (int size = 0; size <= 7; size++) {
                    if (canMakeCross(row, col, size)) {
                        makeCross(row, col, size);
                        dfs(depth + 1, (value * crossWidth[size]));
                        eraseCross(row, col, size);
                    }
                }
            }
        }
    }

    private static boolean canMakeCross(int row, int col, int size) {
        if (!isInMap(row, col, size)) {
            return false;
        }

        for (int dRow = -size; dRow <= size; dRow++) {
            if (map[row + dRow][col] == '.') {
                return false;
            }
        }

        for (int dCol = -size; dCol <= size; dCol++) {
            if (map[row][col + dCol] == '.') {
                return false;
            }
        }

        return true;
    }

    private static boolean isInMap(int row, int col, int size) {
        return 0 <= row - size && row + size < mapRow &&
                0 <= col - size && col + size < mapCol;
    }

    private static void makeCross(int row, int col, int size) {
        for (int dRow = -size; dRow <= size; dRow++) {
            map[row + dRow][col] = '.';
        }

        for (int dCol = -size; dCol <= size; dCol++) {
            map[row][col + dCol] = '.';
        }
    }

    private static void eraseCross(int row, int col, int size) {
        for (int dRow = -size; dRow <= size; dRow++) {
            map[row + dRow][col] = '#';
        }

        for (int dCol = -size; dCol <= size; dCol++) {
            map[row][col + dCol] = '#';
        }
    }
}
