import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon_17143 {

    private static int mapRow, mapCol, sharkAmount, answer;
    private static int[][] map = new int[100][100];
    private static Shark topShark;
    private static ArrayList<Shark> sharkList = new ArrayList<>();
    private static int[] dRow = {-1, 1, 0, 0};
    private static int[] dCol = {0, 0, 1, -1};

    public static void main(String[] args) {
        getInput();
        for (int fisherCol = 0; fisherCol < mapCol; fisherCol++) {
            catchShark();
            map = moveShark(fisherCol);
        }

        System.out.println(answer);
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        mapRow = scanner.nextInt();
        mapCol = scanner.nextInt();
        sharkAmount = scanner.nextInt();
        for (int i = 0; i < sharkAmount; i++) {
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            int speed = scanner.nextInt();
            int dir = scanner.nextInt() - 1;
            int size = scanner.nextInt();
            Shark shark = new Shark(row, col, speed, dir, size);
            if (col == 0) {
                compareHigher(shark);
            }
            sharkList.add(shark);
            map[row][col] = sharkList.size();
        }
    }

    private static int[][] moveShark(int fisherCol) {
        int[][] nextMap = new int[100][100];

        int sharkNum = sharkList.size();
        for (int i = 0; i < sharkNum; i++) {
            Shark shark = sharkList.get(i);
            if (!shark.isAlive()) {
                continue;
            }
            int dir = shark.dir;
            int speed = shark.speed;
            int row = shark.row;
            int col = shark.col;

            map[row][col] = 0;
            shark = calculateRowCol(shark);

            row = shark.row;
            col = shark.col;

            if (nextMap[row][col] > 0) {
                Shark anotherShark = sharkList.get(nextMap[row][col] - 1);
                i--;
                if (enterSameArea(shark, anotherShark)) {
                    nextMap[row][col] = i;
                }
            } else {
                nextMap[row][col] = i + 1;
            }
            
            if (shark.col == fisherCol + 1) {
                compareHigher(shark);
            }

        }
        return nextMap;
    }



    private static Shark calculateRowCol(Shark shark) {
        int row = shark.row;
        int col = shark.col;

        for (int i = 0; i < shark.speed; i++) {
            row += dRow[shark.dir];
            col += dCol[shark.dir];
            if (row < 0 && shark.dir < 2) {
                row = 1;
                if (shark.dir == 0) {
                    shark.dir = 1;
                } else {
                    shark.dir = 0;
                }
            }
            if (row == mapRow && shark.dir < 2) {
                row = mapRow - 2;
                if (shark.dir == 0) {
                    shark.dir = 1;
                } else {
                    shark.dir = 0;
                }
            }

            if (col < 0 && shark.dir > 1) {
                col = 1;
                if (shark.dir == 2) {
                    shark.dir = 3;
                } else {
                    shark.dir = 2;
                }
            }
            if (col == mapCol && shark.dir > 1) {
                col = mapCol - 2;
                if (shark.dir == 2) {
                    shark.dir = 3;
                } else {
                    shark.dir = 2;
                }
            }
        }
        shark.row = row;
        shark.col = col;

        return shark;
    }

    private static void resetMap(int[][] nextMap) {
        for (int i = 0; i < 100; i++) {
            Arrays.fill(nextMap[i], 0);
        }
    }


    private static boolean enterSameArea(Shark shark, Shark anotherShark) {
        if (shark.size > anotherShark.size) {
            anotherShark.isCaught();
            return true;
        } else {
            shark.isCaught();
            return false;
        }
    }

    private static void compareHigher(Shark shark) {
        if (topShark == null) {
            topShark = shark;
        }
        if (shark.row < topShark.row) {
            topShark = shark;
        }
    }

    private static void catchShark() {
        if (topShark != null) {
            map[topShark.row][topShark.col] = 0;
            answer += topShark.size;
            topShark.isCaught();
            topShark = null;
        }
    }

    

    private static class Shark {
        public boolean dead = false;
        int row, col, speed, dir, size;
        boolean move;

        public Shark(int row, int col, int speed, int dir, int size) {
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }

        public void isCaught() {
            dead = true;
        }

        public boolean isAlive() {
            return !dead;
        }
    }
}
