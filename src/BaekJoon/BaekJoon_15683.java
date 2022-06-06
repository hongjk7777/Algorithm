package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon_15683 {

    private static int column, row;
    private static int cctvAmount = 0;
    private static int minBlindArea = 987654321;
    private static int[][] cctv = new int[8][3];    // 0 = cctv X, 1 = cctv Y, 2 = cctv Num
    private static int[][] map = new int[8][8];
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        getInput();
        getMinBlindArea(0, map);
        System.out.println(minBlindArea);
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        row = scanner.nextInt();
        column = scanner.nextInt();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int input = scanner.nextInt();
                //if cctv store cctv[]
                if (0 < input && input < 6) {
                    cctv[cctvAmount][0] = i;
                    cctv[cctvAmount][1] = j;
                    cctv[cctvAmount][2] = input;
                    cctvAmount++;
                }
                map[i][j] = input;
            }
        }
    }

    private static void getMinBlindArea(int index, int[][] map) {
        //end condition
        if (index == cctvAmount) {
            int result = countBlindArea(map);
            if (result < minBlindArea) {
                minBlindArea = result;
            }
            return;
        }

        int cctvNum = cctv[index][2];

        for (int cctvDir = 0; cctvDir < getDiffAngleAmount(cctvNum); cctvDir++) {
            int[][] cloneMap = new int[8][8];
            makeCloneMap(map, cloneMap);
            cloneMap = checkWatchArea(cctv[index], cctvDir, cloneMap);
            getMinBlindArea(index + 1, cloneMap);
        }

    }



    private static int countBlindArea(int[][] map) {
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int getDiffAngleAmount(int cctvNum) {
        if (cctvNum == 1 || cctvNum == 3 || cctvNum == 4) {
            return 4;
        }
        if (cctvNum == 2) {
            return 2;
        }
        if (cctvNum == 5) {
            return 1;
        }
        return 0;
    }

    private static void makeCloneMap(int[][] map, int[][] cloneMap) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cloneMap[i][j] = map[i][j];
            }
        }
    }

    private static int[][] checkWatchArea(int[] cctv, int dir, int[][] map) {
        int cctvNum = cctv[2];
        int leftDir = (dir + 3) % 4;
        int rightDir = (dir + 1) % 4;
        int reverseDir = (dir + 2) % 4;
        int[] currentXY = new int[]{cctv[0], cctv[1]};  //0 = x, 1 = y

        if (cctvNum == 1) {
            watchDir(currentXY, dir, map);
        }

        if (cctvNum == 2) {
            watchDir(currentXY, dir, map);
            watchDir(currentXY, reverseDir, map);
        }

        if (cctvNum == 3) {
            watchDir(currentXY, dir, map);
            watchDir(currentXY, rightDir, map);
        }

        if (cctvNum == 4) {
            watchDir(currentXY, dir, map);
            watchDir(currentXY, rightDir, map);
            watchDir(currentXY, reverseDir, map);
        }

        if (cctvNum == 5) {
            watchDir(currentXY, dir, map);
            watchDir(currentXY, rightDir, map);
            watchDir(currentXY, leftDir, map);
            watchDir(currentXY, reverseDir, map);
        }


        return map;
    }

    private static void watchDir(int[] xy, int cctvDir, int[][] map) {
        int currentX = xy[0];
        int currentY = xy[1];
        while (isWatchable(currentX, currentY, map)) {
            if (map[currentX][currentY] == 0) {
                map[currentX][currentY] = -1;
            }
            currentX += dx[cctvDir];
            currentY += dy[cctvDir];

        }
    }

    private static boolean isWatchable(int x, int y, int[][] map) {
        if (x < 0 || x >= row || y < 0 || y >= column) {
            return false;
        }
        return map[x][y] != 6;
    }


}
