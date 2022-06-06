package BaekJoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon_16234 {
    private static int mapSize, minDiff, maxDiff, total =0;
    private static int[][] map = new int[52][52];
    private static int[][] visited = new int[52][52];
    private static ArrayList<Integer> union = new ArrayList<>();
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        initialize();
        getInput();
        System.out.println(getDayOfMigration());
    }

    private static void initialize() {
        for (int i = 0; i < 52; i++) {
            Arrays.fill(map[i], -1);
        }
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        mapSize = scanner.nextInt();
        minDiff = scanner.nextInt();
        maxDiff = scanner.nextInt();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i + 1][j + 1] = scanner.nextInt();
                // surround map by 0 cause exception handling
            }
        }
    }


    private static int getDayOfMigration() {
        boolean flag = true;
        int count = 0;

        while (flag)
            if (count >= 2000|| !migrate()) {
                flag = false;
            } else {
                count++;
            }
        return count;
    }

    private static boolean migrate() {
        boolean movable = false;
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                checkUnion(i + 1, j + 1);
                if (makeUnion()) {
                    movable = true;
                }
                resetUnion();

            }
        }
        resetCache();
        return movable;
    }




    private static void checkUnion(int row, int col) {
        if (visited[row][col] == 1) {
            return;
        }
        int tempRow, tempCol;
        visited[row][col] = 1;
//        total += map[row][col];
        union.add(makeMapNumber(row, col));

        for (int i = 0; i < 4; i++) {
            tempRow = row + dx[i];
            tempCol = col + dy[i];
            if (map[tempRow][tempCol] != -1) {
                if (canUnion(Math.abs(map[row][col] - map[tempRow][tempCol]))) {
                    checkUnion(tempRow, tempCol);
                }
            }
        }
    }


    private static boolean canUnion(int diff) {
        boolean union = false;
        if (diff >= minDiff) {
            if (diff <= maxDiff) {
                union = true;
            }
        }
        return union;
    }

    private static int makeMapNumber(int i, int j) {
        return 100 * i + j;
    }


    private static void resetUnion() {
        union.clear();
    }

    private static void resetCache() {
        for (int i = 0; i < 52; i++) {
            Arrays.fill(visited[i], 0);
        }
    }

    private static boolean makeUnion() {
        if (union.size() < 2) {
            return false;
        }
        migrateUnion();
        return true;
    }

    private static void migrateUnion() {
        int total = 0;
        for (Integer i : union) {
            total += map[i / 100][i % 100];
        }
        int divide = total / union.size();
        for (Integer i : union) {
            map[i / 100][i % 100] = divide;
        }
    }
}
