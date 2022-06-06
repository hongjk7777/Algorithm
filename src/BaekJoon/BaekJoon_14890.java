package BaekJoon;

import java.util.Scanner;

public class BaekJoon_14890 {

    private static int size;
    private static int length;
    private static int[] line = new int[100];
    private static int[][] map = new int[100][100];
    private static int[] road = new int[100];


    public static void main(String[] args) {
        getInput();
        System.out.println(getEnableRoadNum());
    }


    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        size = scanner.nextInt();
        length = scanner.nextInt();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
    }


    private static int getEnableRoadNum() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                line[j] = map[i][j];
            }
            if (canBuildRoad(line)) {
                count++;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                line[j] = map[j][i];
            }
            if (canBuildRoad(line)) {
                count++;
            }
        }

        return count;
    }

    private static boolean canBuildRoad(int[] line) {
        boolean able = true;
        for (int i = 0; i < road.length; i++) {
            road[i] = 0;
        }
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                if (Math.abs(line[i - 1] - line[i]) > 1) {
                    able = false;
                    break;
                }
                if (line[i - 1] - line[i] == -1) {
                    if (!isSameHeight(i - length, i -1, line) || isOverlap(i - length, i)) {
                        able = false;
                        break;
                    }
                    for (int j = i - length; j < i; j++) {
                        road[j] = 1;
                    }
                }

                if (line[i - 1] - line[i] == 1) {
                    if (!isSameHeight(i, i + length - 1, line) || isOverlap(i, i + length)) {
                        able = false;
                        break;
                    }
                    for (int j = i; j < i + length; j++) {
                        road[j] = 1;
                    }
                }
            }
        }
        return able;
    }


    private static boolean isSameHeight(int start, int end, int[] line) {
        int height = line[end];
        if (start >= 0) {
            for (int i = start; i < end; i++) {
                if (line[i] != height) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    private static boolean isOverlap(int start, int end) {
        for (int i = start; i < end; i++) {
            if (road[i] == 1) {
                return true;
            }
        }
        return false;
    }
}
