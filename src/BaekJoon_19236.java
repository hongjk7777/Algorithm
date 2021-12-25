import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_19236 {
    private static int fishNum, fishDir, max = 0, start;
    private static Shark shark;
    private static Fish[][] fishMap = new Fish[4][4];
    private static FishLocation[] fishLocationList = new FishLocation[16];
    private static int[] dRow = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dCol = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        boolean eatable = true;
        getInput();
        enterShark();

        eatFish(0);

        System.out.println(max + start);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        for (int i = 0; i < 4; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 4; j++) {
                fishNum = Integer.parseInt(tokenizer.nextToken());
                fishDir = Integer.parseInt(tokenizer.nextToken()) - 1;
                fishMap[i][j] = new Fish(fishNum, fishDir);
                fishLocationList[fishNum - 1] = new FishLocation(i, j);
            }
        }
    }

    private static void enterShark() {
        Fish fish = fishMap[0][0];
        shark = new Shark(0, 0, fish.dir);
        fish.getEaten();
        start = fish.num;
    }

    public static void eatFish(int sum) {
        boolean eat = false;
        int curRow, curCol, curDir;

        Fish[][] tempFishList = new Fish[4][4];
        FishLocation[] tempList = new FishLocation[16];
        moveFishes();
        copyFish(fishMap, tempFishList);
        copyLocation(fishLocationList, tempList);

        for (int i = 0; i < 3; i++) {
            curRow = shark.row;
            curCol = shark.col;
            curDir = shark.dir;

            shark.row = curRow + (i + 1) * dRow[curDir];
            shark.col = curCol + (i + 1) * dCol[curDir];


            if (isInArea(shark.row, shark.col)) {
                Fish fish = fishMap[shark.row][shark.col];
                if (fish.isAlive()) {
                    fish.getEaten();
                    shark.dir = fish.dir;
                    eat = true;
                    eatFish(sum + fish.num);
                }
            }
            copyFish(tempFishList, fishMap);
            copyLocation(tempList, fishLocationList);
            shark.row = curRow;
            shark.col = curCol;
            shark.dir = curDir;
        }

        if (!eat) {
            if (max < sum) {
                max = sum;
            }
        }

    }

    private static void copyLocation(FishLocation[] src, FishLocation[] dest) {
        for (int i = 0; i < 16; i++) {
            dest[i] = new FishLocation(src[i].row, src[i].col);
        }
    }

    private static void copyFish(Fish[][] src, Fish[][] dest) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                dest[i][j] = new Fish(src[i][j]);
            }
//            System.arraycopy(src[i], 0, dest[i], 0, 4);
        }
    }

    private static void moveFishes() {
        for (int i = 0; i < 16; i++) {
            FishLocation fishLocation = fishLocationList[i];
            int row = fishLocation.row;
            int col = fishLocation.col;
            fishMap[row][col].move(row, col);
        }
    }

    private static boolean isInArea(int curRow, int curCol) {
        return 0 <= curRow && curRow < 4 && 0 <= curCol && curCol < 4;
    }



    private static class Fish {
        int num, dir;
        boolean alive = true;

        public Fish(int num, int dir) {
            this.num = num;
            this.dir = dir;
        }

        public Fish(Fish anotherFish) {
            this.num = anotherFish.num;
            this.dir = anotherFish.dir;
            this.alive = anotherFish.alive;
        }

        public void getEaten() {
//            max += num;
            alive = false;
        }

        public boolean isAlive() {
            return alive;
        }

        public void move(int curRow, int curCol) {
            boolean movable = false;
            int nextRow, nextCol, firstDir = dir;
            if (!isAlive()) {
                return;
            }

            for (int i = 0; i < 8; i++) {
                dir = (firstDir + i) % 8;
                nextRow = curRow + dRow[dir];
                nextCol = curCol + dCol[dir];
                if (isInArea(nextRow, nextCol) && !isSharkArea(nextRow, nextCol)) {
                    Fish anotherFish = fishMap[nextRow][nextCol];
//                    if (anotherFish.isAlive()) {
                        //change fish
                    Fish fish = new Fish(this);

                    fishMap[curRow][curCol] = anotherFish;
                    fishMap[nextRow][nextCol] = fish;
                    fishLocationList[num - 1].change(nextRow, nextCol);
                    fishLocationList[anotherFish.num - 1].change(curRow, curCol);

                    movable = true;
                    break;
//                    }
                }


            }

            if (!movable) {
                dir = firstDir;
            }

        }

        private boolean isSharkArea(int curRow, int curCol) {
            return curRow == shark.row && curCol == shark.col;
        }

    }



    private static class Shark {
        int row, col, dir;

        public Shark(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }


    }

    private static class FishLocation {
        int row;
        int col;

        public FishLocation(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void change(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
