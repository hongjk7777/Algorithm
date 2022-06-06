package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16935 {
    private static int mapRow, mapCol, operatorAmount;
    private static int[] operators;
    private static int[][] map, tempMap;

    public static void main(String[] args) throws IOException {
        getInput();
        executeOperators();
        printMap();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        operatorAmount = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapRow][mapCol];
        operators = new int[operatorAmount];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < operatorAmount; i++) {
            operators[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void executeOperators() {
        for (int i = 0; i < operatorAmount; i++) {
            executeOperator(operators[i]);
        }
    }

    private static void executeOperator(int operationNum) {
        if (operationNum == 1) {
            executeOperation1();
        } else if (operationNum == 2) {
            executeOperation2();
        } else if (operationNum == 3) {
            executeOperation3();
        } else if (operationNum == 4) {
            executeOperation4();
        } else if (operationNum == 5) {
            executeOperation5();
        } else if (operationNum == 6) {
            executeOperation6();
        }
    }

    private static void executeOperation1() {
        copyMapToTempMap();
        makeMapUpsideDown();
    }

    private static void copyMapToTempMap() {
        tempMap = new int[mapRow][mapCol];
        for (int row = 0; row < mapRow; row++) {
            if (mapCol >= 0) System.arraycopy(map[row], 0, tempMap[row], 0, mapCol);
        }
    }

    private static void makeMapUpsideDown() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = tempMap[mapRow - 1 - row][col];
            }
        }
    }
    
    private static void executeOperation2() {
        copyMapToTempMap();
        makeMapReverseLeftRight();
    }

    private static void makeMapReverseLeftRight() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = tempMap[row][mapCol - 1 - col];
            }
        }
    }

    private static void executeOperation3() {
        copyMapToTempMap();
        spinMapClockWise90();
        changeRowCol();
    }

    private static void spinMapClockWise90() {
        map = new int[mapCol][mapRow];
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                map[col][row] = tempMap[mapRow - 1 - row][col];
            }
        }
    }

    private static void changeRowCol() {
        int temp = mapRow;
        mapRow = mapCol;
        mapCol = temp;
    }

    private static void executeOperation4() {
        copyMapToTempMap();
        spinMapClockwise270();
        changeRowCol();
    }

    private static void spinMapClockwise270() {
        map = new int[mapCol][mapRow];
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                map[col][row] = tempMap[row][mapCol - 1 - col];
            }
        }
    }

    private static void executeOperation5() {
        copyMapToTempMap();
        divideAndSpinMapClockWise90();
    }

    private static void divideAndSpinMapClockWise90() {
        for (int row = 0; row < mapRow / 2; row++) {
            for (int col = 0; col < mapCol / 2; col++) {
                map[row][col] = tempMap[mapRow / 2 + row][col];
                map[mapRow / 2 + row][col] = tempMap[mapRow / 2 + row][mapCol / 2 + col];
                map[row][mapCol / 2 + col] = tempMap[row][col];
                map[mapRow / 2 + row][mapCol / 2 + col] = tempMap[row][mapCol / 2 + col];
            }
        }
    }

    private static void executeOperation6() {
        copyMapToTempMap();
        divideAndSpinMapClockWise270();
    }

    private static void divideAndSpinMapClockWise270() {
        for (int row = 0; row < mapRow / 2; row++) {
            for (int col = 0; col < mapCol / 2; col++) {
                map[row][col] = tempMap[row][mapCol / 2 + col];
                map[mapRow / 2 + row][col] = tempMap[row][col];
                map[row][mapCol / 2 + col] = tempMap[mapRow / 2 + row][mapCol / 2 + col];
                map[mapRow / 2 + row][mapCol / 2 + col] = tempMap[mapRow / 2 + row][col];
            }
        }
    }

    private static void printMap() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                builder.append(map[row][col]).append(" ");
            }
            builder.append("\n");
        }
        System.out.println(builder);
    }
}
