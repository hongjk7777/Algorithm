package BaekJoon;/*
생각노트
-배열을 떼어서 돌린 다음에 돌려주자

개선해야 할 점
1. spinClockWise를 안 좋게 구현하여 spinReverse라는 함수까지 만들었는데 그럴 필요 없이
spinClockWise를 두 번 할 수 있도록 고쳐야 함.
2. 반복문의 범위가 다른 사람들이나 내가 나중에 봤을 때 이해하기가 너무 난해함 직관적이게 바꿀 필요 ㅇ
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_23291 {
    private static int fishbowlAmount, diffLimit, max = -1, min = 987654321, answer = 0;
    private static int[][] fishbowl, tempBowl, spinBowl;
    private static int[] dRow = {0, 0, -1, 1};
    private static int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        cleanUpFishbowl();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        fishbowlAmount = Integer.parseInt(tokenizer.nextToken());
        diffLimit = Integer.parseInt(tokenizer.nextToken());
        fishbowl = new int[fishbowlAmount][fishbowlAmount];
        tempBowl = new int[fishbowlAmount][fishbowlAmount];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < fishbowlAmount; i++) {
            int input = Integer.parseInt(tokenizer.nextToken());
            fishbowl[fishbowlAmount - 1][i] = input;

            if (input < min) {
                min = input;
            } else if (input > max) {
                max = input;
            }
        }
    }


    private static void cleanUpFishbowl() {
        while (isOverLimit()) {
            addFish();
            castFirstMagic();
            castSecondMagic();
            answer++;
        }
    }

    private static boolean isOverLimit() {
        return max - min > diffLimit;
    }

    private static void addFish() {
        for (int i = 0; i < fishbowlAmount; i++) {
            if (fishbowl[fishbowlAmount - 1][i] == min) {
                fishbowl[fishbowlAmount - 1][i]++;
            }
        }
        min++;
    }

    private static void castFirstMagic() {
        int castNum = 1, leftest = 0;
        int rowSize = 1;
        int colSize = 1;

        while (leftest + rowSize + colSize - 1 < fishbowlAmount) {
            for (int row = 0; row < rowSize; row++) {
                for (int col = 0; col < colSize; col++) {
                    tempBowl[rowSize - row - 1][col] =
                            fishbowl[fishbowlAmount - row - 1][leftest + col];
                }
            }

            spinClockWise(rowSize,colSize);

            int lastLeft = leftest;
            leftest += colSize;
            castNum++;

            for (int row = 0; row < rowSize; row++) {
                for (int col = 0; col < colSize; col++) {
                    fishbowl[fishbowlAmount - (colSize - col - 1) - 2][leftest + row] =
                            spinBowl[col][row];
                    fishbowl[fishbowlAmount - row - 1][lastLeft + col] = 0;
                }
            }
            rowSize = (castNum / 2) + 1;
            colSize = ((castNum - 1) / 2) + 1;
        }


        moveFish(rowSize, leftest);
        rearrangeFish(rowSize, leftest);
    }

    private static void spinClockWise(int row, int col) {
        spinBowl = new int[col][row];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                spinBowl[j][row - 1 - i] = tempBowl[i][j];
                tempBowl[i][j] = 0;
            }
        }
    }

    private static void moveFish(int rowSize, int leftest) {
        int startRow = fishbowlAmount - rowSize;
        int startCol = leftest;

        for (int row = startRow; row < fishbowlAmount; row++) {
            for (int col = startCol; col < fishbowlAmount; col++) {
                int firstFish = fishbowl[row][col];
                int lossFish = 0;
                if (firstFish == 0) {
                    continue;
                }

                for (int dir = 0; dir < 4; dir++) {
                    int tempRow = row + dRow[dir];
                    int tempCol = col + dCol[dir];

                    if (!isInArea(tempRow, tempCol)) {
                        continue;
                    }
                    int anotherFish = fishbowl[tempRow][tempCol];

                    if (anotherFish == 0) {
                        continue;
                    }

                    int diff = firstFish - anotherFish;
                    if (diff >= 5) {
                        tempBowl[tempRow][tempCol] += diff / 5;
                        lossFish += diff / 5;
                    }
                }

                tempBowl[row][col] += firstFish - lossFish;
            }
        }

        for (int i = 0; i < fishbowlAmount; i++) {
            for (int j = 0; j < fishbowlAmount; j++) {
                fishbowl[i][j] = tempBowl[i][j];
                tempBowl[i][j] = 0;
            }

        }
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < fishbowlAmount && 0 <= col && col < fishbowlAmount;
    }

    private static void rearrangeFish(int rowSize, int leftest) {
        int startRow = fishbowlAmount - rowSize;
        int startCol = leftest;
        int curCol = 0;
        max = 0;
        min = 987654321;

        for (int col = startCol; col < fishbowlAmount; col++) {
            for (int row = fishbowlAmount - 1; row >= startRow; row--) {
                if (fishbowl[row][col] != 0) {
                    int fish = fishbowl[row][col];

                    fishbowl[fishbowlAmount - 1][curCol] = fish;
                    checkMinMax(fish);

                    if (row == fishbowlAmount - 1 && col == curCol) {
                        curCol++;
                        continue;
                    }
                    curCol++;
                    fishbowl[row][col] = 0;
                }
            }
        }
    }

    private static void checkMinMax(int fishAmount) {
        if (fishAmount < min) {
            min = fishAmount;
        }
        if (fishAmount > max) {
            max = fishAmount;
        }
    }

    private static void castSecondMagic() {
        int leftest = 0;
        int rowSize = 1;
        int colSize = fishbowlAmount / 2;

        for (int castNum = 0; castNum < 2; castNum++) {
            for (int row = 0; row < rowSize; row++) {
                for (int col = 0; col < colSize; col++) {
                    tempBowl[rowSize - row - 1][col] =
                            fishbowl[fishbowlAmount - row - 1][leftest + col];
                }
            }

            spinReverse(rowSize,colSize);

            int lastLeft = leftest;
            leftest += colSize;

            for (int row = 0; row < rowSize; row++) {
                for (int col = 0; col < colSize; col++) {
                    fishbowl[fishbowlAmount - castNum - 1 - rowSize + row][leftest + col] =
                            spinBowl[row][col];
                    fishbowl[fishbowlAmount - row - 1][lastLeft + col] = 0;
                }
            }
            rowSize*=2;
            colSize = fishbowlAmount / 4;
        }



        moveFish(rowSize, leftest);
        rearrangeFish(rowSize, leftest);
    }

    private static void spinReverse(int row, int col) {
        spinBowl = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                spinBowl[row - 1 - i][col - 1 - j] = tempBowl[i][j];
                tempBowl[i][j] = 0;
            }
        }
    }


}
