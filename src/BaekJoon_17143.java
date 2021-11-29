import java.util.*;
/*
나혼자 풀지 못하고 인터넷 보고 품
문제점 : 자료구조를 잘못 선택함
알게된 점: 방향을 바꿀때 상우하좌를 순서대로 하면 d = (d+2) % 4로 if 문 분기없이 가능
 */
public class BaekJoon_17143 {

    private static int mapRow, mapCol, sharkAmount, answer;
    private static int[][] map = new int[100][100];
    private static int[][] anotherMap = new int[100][100];
    private static Queue<Shark> sharkQueue = new LinkedList<>();
    private static int[] dRow = {-1, 0, 1, 0};
    private static int[] dCol = {0, -1, 0, 1};
    private static int sharkCount = 0;

    public static void main(String[] args) {
        getInput();
        for (int fisherCol = 0; fisherCol < mapCol; fisherCol++) {
            if (sharkAmount == 0 || sharkAmount == sharkCount) {
                break;
            }
            if (fisherCol % 2 == 0) {
                catchShark(fisherCol, map);
                moveSharks(map, anotherMap);
            } else {
                catchShark(fisherCol, anotherMap);
                moveSharks(anotherMap, map);
            }
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
            int dir = scanner.nextInt();
            if (dir == 1) {
                dir = 0;
            } else if (dir == 4) {
                dir = 1;
            }
            int size = scanner.nextInt();
            Shark shark = new Shark(row, col, speed, dir, size);
            sharkQueue.add(shark);
            map[row][col] = size;
        }
    }

    private static void catchShark(int fisherCol, int[][] map) {
        for (int row = 0; row < mapRow; row++) {
            if (map[row][fisherCol] != 0) {
                answer += map[row][fisherCol];
                map[row][fisherCol] = 0;
                sharkCount++;
                break;
            }

        }

    }

    private static void moveSharks(int[][] now, int[][] next) {
        int size = sharkQueue.size();
        for (int i = 0; i < size; i++) {
            Shark shark = sharkQueue.poll();
            if (now[shark.row][shark.col] != shark.size) {
                continue;
            }
            moveShark(shark, next);
            now[shark.row][shark.col] = 0;
        }
    }

    private static void moveShark(Shark shark, int[][] next) {
        int row = shark.row, col = shark.col, speed = shark.speed,
                size = shark.size, dir = shark.dir;
        if (dir == 0 || dir == 2) {
            speed %= 2 * (mapRow - 1);
        } else {
            speed %= 2 * (mapCol - 1);
        }

        for (int i = 0; i < speed; i++) {
            if (row + dRow[dir] < 0 || row + dRow[dir] >= mapRow ||
                    col + dCol[dir] < 0 || col + dCol[dir] >= mapCol) {
                dir = (dir + 2) % 4;
            }
            row += dRow[dir];
            col += dCol[dir];
        }

        if (next[row][col] > size) return;

        next[row][col] = size;
        sharkQueue.add(new Shark(row, col, speed, dir, size));
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

        public Shark(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void isCaught() {
            dead = true;
        }

        public boolean isAlive() {
            return !dead;
        }
    }
}
