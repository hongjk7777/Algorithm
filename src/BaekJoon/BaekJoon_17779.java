package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class BaekJoon_17779 {

    private static int mapSize, min = 987654321, totalPopulation = 0;
    private static int[][] map;
    private static int[] area = new int[5];

    public static void main(String[] args) throws IOException {
        getInput();
        gerrymander();
        System.out.println(min);
    }



    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < mapSize; j++) {
                int input = Integer.parseInt(tokenizer.nextToken());
                map[i][j] = input;
                totalPopulation += input;
            }
        }
    }

    private static void gerrymander() {
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                divideArea(row, col);
            }
        }
    }

    private static void divideArea(int row, int col) {
        int d1Max = col;
        int d2Max;
//        int d2Max = mapSize- 1 - col;
        for (int d1 = 1; d1 <= d1Max; d1++) {
            d2Max = Math.min(mapSize- 1 - col, mapSize - 1 - row - d1);
            for (int d2 = 1; d2 <= d2Max; d2++) {
                int diff = calculateDiff(row, col, d1, d2);
                if (diff < min) {
                    min = diff;
                }
            }
        }
    }

    private static int calculateDiff(int row, int col, int d1, int d2) {
        int max = 0, min = 987654321;

//        findAreaNum();
        findArea1(row, col, d1);
        findArea2(row, col, d2);
        findArea3(row, col, d1, d2);
        findArea4(row, col, d1, d2);
        findArea5();

        for (int i = 0; i < 5; i++) {
            if (max < area[i]) {
                max = area[i];
            }
        }

        for (int i = 0; i < 5; i++) {
            if (min > area[i]) {
                min = area[i];
            }
        }

        resetArea();

        return max - min;

    }



    private static void findArea1(int row, int col, int d1) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col + 1; j++) {
                area[0] += map[i][j];
            }
//            area[0] += col + 1;
        }
        for (int i = row; i < row + d1; i++) {
            for (int j = 0; j < col - (i - row); j++) {
                area[0] += map[i][j];
            }
//            area[0] += col - i;
        }
    }

    private static void findArea2(int row, int col, int d2) {
        for (int i = 0; i < row; i++) {
            for (int j = col + 1; j < mapSize; j++) {
                area[1] += map[i][j];
            }
//            area[1] += mapSize - col - 1;
        }
        for (int i = row; i <= row + d2; i++) {
            for (int j = col + 1 + (i - row); j < mapSize; j++) {
                area[1] += map[i][j];
            }
//            area[1] += mapSize - col - 1 - i;
        }
    }

    private static void findArea3(int row, int col, int d1, int d2) {
        int temp = row + d1 + d2;
        for (int i = row + d1; i <= temp; i++) {
            for (int j = 0; j < col - d1 + d2 - (temp - i); j++) {
                area[2] += map[i][j];
            }
//            area[2] += col - d1 + d2 - (temp - i);
        }

        for (int i = temp + 1; i < mapSize; i++) {
            for (int j = 0; j < col - d1 + d2; j++) {
                area[2] += map[i][j];

            }
//            area[2] += col - d1 + d2;
        }
    }

    private static void findArea4(int row, int col, int d1, int d2) {
        int temp = row + d1 + d2;
        for (int i = row + d2 + 1; i <= temp; i++) {
            for (int j = col - d1 + d2 + (temp - i) + 1; j < mapSize; j++) {
                area[3] += map[i][j];
            }
//            area[3] += (mapSize - 1 - (col - d1 + d2)) - (temp - i);
        }

        for (int i = temp + 1; i < mapSize; i++) {
            for (int j = col - d1 + d2; j < mapSize; j++) {
                area[3] += map[i][j];
            }
//            area[3] += mapSize - (col - d1 + d2);
        }
    }

    private static void findArea5() {
        int areaPopulation = totalPopulation;
        for (int i = 0; i < 4; i++) {
            areaPopulation -= area[i];
        }
        area[4] = areaPopulation;
    }

    private static void resetArea() {
        for (int i = 0; i < 5; i++) {
            area[i] = 0;
        }
    }
}
