package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_14391 {
    private static final int VERTICAL = 0, WIDTH = 1;
    private static int mapRow, mapCol, max = 0;
    private static int[][] paperMap;
    private static int[][] cutWays;
    private static boolean[][] checked;

    public static void main(String[] args) throws IOException {
        getInput();
        findAllWaysToCut();
        System.out.println(max);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        paperMap = new int[mapRow][mapCol];
        cutWays = new int[mapRow][mapCol];
        checked = new boolean[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();
            for (int col = 0; col < mapCol; col++) {
                paperMap[row][col] = Integer.parseInt(String.valueOf(line.charAt(col)));
            }
        }
    }

    private static void findAllWaysToCut() {
        int max = (int) Math.pow(2, mapRow * mapCol);
        for (int way = 0; way < max; way++) {
            fillCutWays(way);
            int temp = getSum();
            updateMax(temp);
        }
    }

    private static void fillCutWays(int way) {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                cutWays[row][col] = way & 1;
                way = way >> 1;
            }
        }
    }

    private static int getSum() {
        initializeChecked();

        int sum = 0;
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (!checked[row][col]){
                    int tempSum = paperMap[row][col];
                    checked[row][col] = true;

                    if (cutWays[row][col] == WIDTH){
                        tempSum = getWidthTempSum(row, col, tempSum);
                    } else {
                        tempSum = getVerticalTempSum(row, col, tempSum);
                    }
                    sum += tempSum;
                }
            }
        }

        return sum;
    }

    private static void initializeChecked() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                checked[row][col] = false;
            }
        }
    }

    private static int getVerticalTempSum(int tempRow, int tempCol, int tempSum) {
        tempRow++;
        while (isInArea(tempRow, tempCol) && cutWays[tempRow][tempCol] == VERTICAL){
            checked[tempRow][tempCol] = true;
            tempSum *= 10;
            tempSum += paperMap[tempRow][tempCol];
            tempRow++;
        }
        return tempSum;
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }

    private static int getWidthTempSum(int tempRow, int tempCol, int tempSum) {
        tempCol++;
        while (isInArea(tempRow, tempCol) && cutWays[tempRow][tempCol] == WIDTH){
            checked[tempRow][tempCol] = true;
            tempSum *= 10;
            tempSum += paperMap[tempRow][tempCol];
            tempCol++;
        }
        return tempSum;
    }

    private static void updateMax(int temp) {
        max = Math.max(max, temp);
    }
}
