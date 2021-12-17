/*
문제점
1. 항상 느끼는 거지만 문제에 이해가 잘 안될 때 나 혼자 멋대로 생각하지 말고 문제를 읽자.
2. 그리고 이번 구현에서 백트래킹을 하려다가 삼중 for문으로 했는데
   앞으로는 그냥 항상 코드가 읽기 쉬운 쪽으로 하자.
   이번에 삼중 for문으로 풀어 가독성이 굉장히 떨어짐
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_23290 {
    private static int fishAmount, castAmount;
    private static int[][] smellMap = new int[4][4];
    private static Shark shark;
    private static ArrayList<Integer>[][] fishMap = new ArrayList[4][4];
    private static ArrayList<Integer>[][] tempFishMap = new ArrayList[4][4];
    private static ArrayList<Integer>[][] copyFishMap = new ArrayList[4][4];
    private static int[] d8Row = {0, -1, -1, -1, 0, 1, 1, 1};
    private static int[] d8Col = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] d4Row = {-1, 0, 1, 0};
    private static int[] d4Col = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        initialize();
        getInput();
        for (int i = 0; i < castAmount; i++) {
            startMagic();
        }
        System.out.println(countFish());
    }

    private static void initialize() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                fishMap[row][col] = new ArrayList<>();
                tempFishMap[row][col] = new ArrayList<>();
                copyFishMap[row][col] = new ArrayList<>();
            }
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        fishAmount = Integer.parseInt(tokenizer.nextToken());
        castAmount = Integer.parseInt(tokenizer.nextToken());

        for (int fishNum = 0; fishNum < fishAmount; fishNum++) {
            tokenizer = new StringTokenizer(reader.readLine());

            int row = Integer.parseInt(tokenizer.nextToken()) - 1;
            int col = Integer.parseInt(tokenizer.nextToken()) - 1;
            int dir = Integer.parseInt(tokenizer.nextToken()) - 1;

            fishMap[row][col].add(dir);
        }

        tokenizer = new StringTokenizer(reader.readLine());

        int sharkRow = Integer.parseInt(tokenizer.nextToken()) - 1;
        int sharkCol = Integer.parseInt(tokenizer.nextToken()) - 1;

        shark = new Shark(sharkRow, sharkCol);
    }

    private static void startMagic() {
        copyFish();
        moveFish();
        moveShark();
        reduceSmell();
        appearCopyFish();
    }

    private static void copyFish() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                copyFishMap[row][col] = new ArrayList<>(fishMap[row][col]);
            }
        }
    }

    private static void moveFish() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int size = fishMap[row][col].size();
                for (int i = 0; i < size; i++) {
                    int dir = fishMap[row][col].get(i);
                    int firstDir = dir;
                    boolean moved = true;

                    while (!isAbleToMove(row, col, dir)) {
                        dir = (dir + 7) % 8;

                        if (firstDir == dir) {
                            moved = false;
                            break;
                        }
                    }

                    if (moved) {
                        tempFishMap[row + d8Row[dir]][col + d8Col[dir]].add(dir);
                    } else {
                        tempFishMap[row][col].add(dir);
                    }
                }
            }
        }

        copyMap();
    }

    private static boolean isAbleToMove(int row, int col, int dir) {
        int tempRow = row + d8Row[dir];
        int tempCol = col + d8Col[dir];

        if (!isInArea(tempRow,tempCol)|| isSharkArea(tempRow, tempCol) ||
                smellMap[tempRow][tempCol] > 0 ) {
            return false;
        }

        return true;
    }

    private static boolean isSharkArea(int tempRow, int tempCol) {
        return shark.row == tempRow && shark.col == tempCol;
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < 4 && 0 <= col && col < 4;
    }

    private static void copyMap() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                fishMap[row][col] = tempFishMap[row][col];
                tempFishMap[row][col] = new ArrayList<>();
            }
        }
    }

    //재귀로 구현하는 게 더 보기 편할듯
    private static void moveShark() {
        int priority = 10000;
        int eatenFish = 0;
        int row = shark.row;
        int col = shark.col;
        int firstRow, firstCol, secondRow, secondCol, thirdRow, thirdCol, firstFish, secondFish, thirdFish;
        int[] moveDir = new int[3];

        for (int firstDir = 0; firstDir < 4; firstDir++) {
            firstRow = row + d4Row[firstDir];
            firstCol = col + d4Col[firstDir];
            if (!isInArea(firstRow, firstCol)) {
                continue;
            }
            firstFish = fishMap[firstRow][firstCol].size();

            for (int secondDir = 0; secondDir < 4; secondDir++) {
                secondRow = row + d4Row[firstDir] + d4Row[secondDir];
                secondCol = col + d4Col[firstDir] + d4Col[secondDir];
                if (!isInArea(secondRow, secondCol)) {
                    continue;
                }
                secondFish = fishMap[secondRow][secondCol].size();

                for (int thirdDir = 0; thirdDir < 4; thirdDir++) {
                    thirdRow = row + d4Row[firstDir] + d4Row[secondDir] + d4Row[thirdDir];
                    thirdCol = col + d4Col[firstDir] + d4Col[secondDir] + d4Col[thirdDir];
                    if (!isInArea(thirdRow, thirdCol)) {
                        continue;
                    }
                    if ((thirdRow == firstRow && thirdCol == firstCol) || (thirdRow == secondRow && thirdCol == secondCol)) {
                        thirdFish = 0;
                    } else {
                        thirdFish = fishMap[thirdRow][thirdCol].size();
                    }

                    int tempFishSum = firstFish + secondFish + thirdFish;
                    int tempPriority = firstDir * 100 + secondDir * 10 + thirdDir;

                    if (tempFishSum > eatenFish) {
                        eatenFish = tempFishSum;
                        priority = tempPriority;
                        saveDir(moveDir, firstDir, secondDir, thirdDir);
                    } else if (tempFishSum == eatenFish) {
                        if (tempPriority < priority) {
                            //먹은 고기는 똑같으므로 생략
                            priority = tempPriority;
                            saveDir(moveDir, firstDir, secondDir, thirdDir);
                        }
                    }
                }
            }
        }

        moveAndEatFish(row, col, moveDir);

    }

    private static void saveDir(int[] moveDir, int firstDir, int secondDir, int thirdDir) {
        moveDir[0] = firstDir;
        moveDir[1] = secondDir;
        moveDir[2] = thirdDir;
    }

    private static void moveAndEatFish(int row, int col, int[] moveDir) {
        for (int i = 0; i < 3; i++) {
            int dir = moveDir[i];
            row += d4Row[dir];
            col += d4Col[dir];

            if (fishMap[row][col].size() > 0) {
                smellMap[row][col] = 3;
            }
            fishMap[row][col].clear();
        }

        shark.row = row;
        shark.col = col;
    }

    private static void reduceSmell() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (smellMap[row][col] != 0) {
                    smellMap[row][col]--;
                }
            }
        }
    }

    private static void appearCopyFish() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                fishMap[row][col].addAll(copyFishMap[row][col]);
                copyFishMap[row][col].clear();
            }
        }
    }

    private static int countFish() {
        int sum = 0;

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                sum += fishMap[row][col].size();
            }
        }

        return sum;
    }

    private static class Shark {
        int row, col;

        public Shark(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
