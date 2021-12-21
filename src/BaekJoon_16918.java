import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16918 {
    private static int mapRow, mapCol, limitTime;
    private static int[][] map, tempMap;
    private static int[] dRow = {0, 0, 1, -1};
    private static int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        for (int time = 1; time < limitTime; time++) {
            takeSecond(time);
        }
        printMap();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        limitTime = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();
            for (int col = 0; col < mapCol; col++) {
                char input = line.charAt(col);

                if (input == '.') {
                    map[row][col] = 0;
                } else {
                    map[row][col] = 2;
                }
            }
        }
    }


    private static void takeSecond(int time) {
        if (time % 2 == 1) {
            reduceTimeLimit();
        } else {
            tempMap = new int[mapRow][mapCol];
            detonateBomb();
            copyTempMapToMap();
        }

    }

    private static void reduceTimeLimit() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (map[row][col] == 0) {
                    map[row][col] = 3;
                } else {
                    map[row][col]--;
                }
            }
        }
    }


    private static void detonateBomb() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (map[row][col] == 1) {
                    for (int dir = 0; dir < 4; dir++) {
                        int tempRow = row + dRow[dir];
                        int tempCol = col + dCol[dir];

                        if (!isInArea(tempRow, tempCol)) {
                            continue;
                        }

                        tempMap[tempRow][tempCol] = -1;
                    }
                    tempMap[row][col] = -1;
                } else {
                    if (tempMap[row][col] != -1) {
                        tempMap[row][col] = map[row][col] - 1;
                    }
                }
            }
        }


    }

    private static void copyTempMapToMap() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (tempMap[row][col] == -1) {
                    map[row][col] = 0;
                } else {
                    map[row][col] = tempMap[row][col];
                }
            }
        }
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }

    private static void printMap() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                int area = map[row][col];

                if (area == 0) {
                    System.out.print('.');
                } else {
                    System.out.print('O');
                }
            }
            System.out.println();
        }
    }
}
