import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_20061 {
    private static int blockAmount, type, blockRow, blockCol, score = 0;
    private static int[][] map = new int[10][10];
    private static ArrayList<Block> startBlockList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        playGame();
        System.out.println(score);
        System.out.println(getBlockArea());
    }



    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());
        blockAmount = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < blockAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());

            type = Integer.parseInt(tokenizer.nextToken());
            blockRow = Integer.parseInt(tokenizer.nextToken());
            blockCol = Integer.parseInt(tokenizer.nextToken());

            startBlockList.add(new Block(type, blockRow, blockCol));
        }
    }

    private static void playGame() {
        for (int i = 0; i < blockAmount; i++) {
            placeBlock(i);

        }
    }

    private static void placeBlock(int blockNum) {
        pushBlockBlueArea(blockNum);
        pushBlockGreenArea(blockNum);
    }

    private static void pushBlockBlueArea(int blockNum) {
        Block block = startBlockList.get(blockNum);
        int row = block.row;
        int col = block.col;
        int type = block.type;

        if (type == 1) {
            while (col < 10 && map[row][col] == 0) {
                col++;
            }
            col--;
            map[row][col] = blockNum + 1;
        }

        else if (type == 2) {
            while (col + 1 < 10 && map[row][col + 1] == 0) {
                col++;
            }
            col--;
            map[row][col] = blockNum + 1;
            map[row][col + 1] = blockNum + 1;
        }

        else {
            while (col < 10 &&
                    (map[row + 1][col] == 0 && map[row][col] == 0)) {
                col++;
            }
            col--;
            map[row + 1][col] = blockNum + 1;
            map[row][col] = blockNum + 1;
        }

        countScore(row, col);
        int blueCount = countPushBlueArea();

        if (blueCount > 0) {
            deleteBlueArea(blueCount);
        }

    }

    private static int countPushBlueArea() {
        int count = 0;
        for (int tempCol = 4; tempCol < 6; tempCol++) {
            for (int tempRow = 0; tempRow < 4; tempRow++) {
                if (map[tempRow][tempCol] != 0) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    private static int countPushGreenArea() {
        int count = 0;
        for (int tempRow = 4; tempRow < 6; tempRow++) {
            for (int tempCol = 0; tempCol < 4; tempCol++) {
                if (map[tempRow][tempCol] != 0) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    private static void deleteBlueArea(int blueCount) {
        for (int i = 0; i < blueCount; i++) {
            for (int tempCol = 9; tempCol > 4; tempCol--) {
                for (int tempRow = 0; tempRow < 4; tempRow++) {
                    map[tempRow][tempCol] = map[tempRow][tempCol - 1];
                    map[tempRow][tempCol - 1] = 0;
                }
            }
        }
    }

    private static void deleteGreenArea(int greenCount) {
        for (int i = 0; i < greenCount; i++) {
            for (int tempRow = 9; tempRow > 4; tempRow--) {
                for (int tempCol = 0; tempCol < 4; tempCol++) {
                    map[tempRow][tempCol] = map[tempRow - 1][tempCol];
                    map[tempRow - 1][tempCol] = 0;
                }
            }
        }
    }


    private static void pushBlockGreenArea(int blockNum) {
        Block block = startBlockList.get(blockNum);
        int row = block.row;
        int col = block.col;
        int type = block.type;

        if (type == 1) {
            while (row < 10 && map[row][col] == 0) {
                row++;
            }
            row--;
            map[row][col] = blockNum + 1;
        } else if (type == 2) {
            while (row < 10 &&
                    (map[row][col] == 0 && map[row][col + 1] == 0)) {
                row++;
            }
            row--;
            map[row][col] = blockNum + 1;
            map[row][col + 1] = blockNum + 1;
        } else {
            while (row + 1 < 10 && map[row + 1][col] == 0) {
                row++;
            }
            row--;
            map[row][col] = blockNum + 1;
            map[row + 1][col] = blockNum + 1;
        }

        row--;

        countScore(row, col);

        int greenCount = countPushGreenArea();
        if (greenCount > 0) {
            deleteGreenArea(greenCount);
        }
    }

    private static void countScore(int row, int col) {
        boolean ableToScore;
        int maxCol;
        int maxRow;

        do {
            ableToScore = false;
            maxCol = 0;
            maxRow = 0;

            if (col > 4) {  // blue area
                for (int tempCol = 0; tempCol < 4; tempCol++) {
                    int blockCount = 0;
                    for (int tempRow = 0; tempRow < 4; tempRow++) {
                        if (map[tempRow][6 + tempCol] != 0) {
                            blockCount ++;
                        }
                    }
                    if (blockCount == 4) {
                        clearLine(row, tempCol);
                        maxCol = tempCol + 6;
                        ableToScore = true;
                        score++;
                    }
                }
            } else {        // green area
                for (int tempRow = 0; tempRow < 4; tempRow++) {
                    int blockCount = 0;
                    for (int tempCol = 0; tempCol < 4; tempCol++) {
                        if (map[tempRow + 6][tempCol] != 0) {
                            blockCount ++;
                        }
                    }
                    if (blockCount == 4) {
                        clearLine(tempRow, col);
                        maxRow = tempRow + 6;
                        ableToScore = true;
                        score++;
                    }
                }
            }

            if (maxRow > 0) {
                pushGreenArea(maxRow);
            }

            if (maxCol > 0) {
                pushBlueArea(maxCol);
            }

        } while (ableToScore);

    }

    private static void pushBlueArea(int col) {
        for (int tempCol = col - 1; tempCol >= 4; tempCol--) {
            for (int tempRow = 0; tempRow < 4; tempRow++) {
                if (map[tempRow][tempCol + 1] == 0) {
                    if (map[tempRow][tempCol] > 0) {
                        if (tempRow != 3 && map[tempRow][tempCol] == map[tempRow + 1][tempCol]) {
                            //type 3
                            map[tempRow][tempCol + 1] = map[tempRow][tempCol];
                            map[tempRow + 1][tempCol + 1] = map[tempRow + 1][tempCol];
                            map[tempRow][tempCol] = 0;
                            map[tempRow + 1][tempCol] = 0;
                            tempRow++;
                        } else if (map[tempRow][tempCol] == map[tempRow][tempCol - 1]) {
                            //type 2
                            map[tempRow][tempCol + 1] = map[tempRow][tempCol];
                            map[tempRow][tempCol] = map[tempRow][tempCol - 1];
                            map[tempRow][tempCol - 1] = 0;
                        } else {
                            //type 1
                            map[tempRow][tempCol + 1] = map[tempRow][tempCol];
                            map[tempRow][tempCol] = 0;
                        }
                    }
                }
            }
        }
    }

    private static void pushGreenArea(int row) {
        for (int tempRow = row - 1; tempRow >= 4; tempRow--) {
            for (int tempCol = 0; tempCol < 4; tempCol++) {
                if (map[tempRow + 1][tempCol] == 0) {
                    if (map[tempRow][tempCol] > 0) {
                        if (map[tempRow][tempCol] == map[tempRow - 1][tempCol]) {
                            //type 3
                            map[tempRow + 1][tempCol] = map[tempRow][tempCol];
                            map[tempRow][tempCol] = map[tempRow - 1][tempCol];
                            map[tempRow - 1][tempCol] = 0;
                        } else if (tempCol != 3 && map[tempRow][tempCol] == map[tempRow][tempCol + 1]) {
                            //type 2
                            map[tempRow + 1][tempCol + 1] = map[tempRow][tempCol];
                            map[tempRow + 1][tempCol] = map[tempRow][tempCol + 1];
                            map[tempRow][tempCol] = 0;
                            map[tempRow][tempCol + 1] = 0;
                            tempCol++;
                        } else {
                            //type 1
                            map[tempRow + 1][tempCol] = map[tempRow][tempCol];
                            map[tempRow][tempCol] = 0;
                        }
                    }
                }
            }
        }
    }

    private static void clearLine(int row, int col) {
        if (col > 4) {  //blue Area
            for (int tempRow = 0; tempRow < 4; tempRow++) {
                map[tempRow][6 + col] = 0;
            }
        }

        else {          //green Area
            for (int tempCol = 0; tempCol < 4; tempCol++) {
                map[6 + row][tempCol] = 0;
            }
        }

    }

    private static int getBlockArea() {
        int count = 0;

        for (int tempRow = 6; tempRow < 10; tempRow++) {
            for (int tempCol = 0; tempCol < 4; tempCol++) {
                if (map[tempRow][tempCol] > 0) {
                    count++;
                }
            }
        }

        for (int tempCol = 6; tempCol < 10; tempCol++) {
            for (int tempRow = 0; tempRow < 4; tempRow++) {
                if (map[tempRow][tempCol] > 0) {
                    count++;
                }
            }
        }

        return count;
    }

    static class Block {
        int type, row, col;

        public Block(int type, int row, int col) {
            this.type = type;
            this.row = row;
            this.col = col;
        }
    }
}
