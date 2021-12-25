/*
            생각노트
            1. 자기자리에 냄새 뿌리기(한 턴 지날때마다 어떻게 카운트를 -?)
            ㄴ map 에 반영(어떻게?) -> smellMap 따로!
            ㄴ 냄새엔 상어의 정보와 count 총 두 개가 있어야함!
            2. 인접한 칸 중 아무 냄새가 없는 거 먼저 찾음 -> 여러 개면 선호방향따라서
            3. 인접한 칸이 냄새가 다 나면 자기냄새가 있는 칸 찾음 -> 여러 개면 선호방향
            4. 이동한 다음에 상어가 겹치면 번호가 작은 애가 살아남음
            ㄴ map 에 있는 걸로 하면 됨 아니면 또 다른 map?
            5. 이동한 다음에는 방향이 이동한 방향!
            6. 냄새 줄이기
            7. 종료 조건 = 상어 1마리
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_19237 {
    private static int mapSize, sharkAmount, smellDuration, aliveSharkAmount;
    private static int[][] map, nextMap;
    private static Smell[][] smellMap;
    private static Shark[] sharks;
    private static boolean gameEnd = false;
    private static int[] dRow = {-1, 1, 0, 0};
    private static int[] dCol = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        getInput();

        int answer = getTimeToEnd();
        answer = checkLimit(answer);

        System.out.println(answer);
    }



    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        mapSize = Integer.parseInt(tokenizer.nextToken());
        sharkAmount = Integer.parseInt(tokenizer.nextToken());
        smellDuration = Integer.parseInt(tokenizer.nextToken());

        map = new int[mapSize][mapSize];
        nextMap = new int[mapSize][mapSize];
        smellMap = new Smell[mapSize][mapSize];
        sharks = new Shark[sharkAmount];
        aliveSharkAmount = sharkAmount;

        for (int row = 0; row < mapSize; row++) {
            tokenizer = new StringTokenizer(reader.readLine());

            for (int col = 0; col < mapSize; col++) {
                int input = Integer.parseInt(tokenizer.nextToken());
                map[row][col] = input;
//                nextMap[row][col] = input;

                if (input != 0) {
                    sharks[input - 1] = new Shark(row, col, input);
                    smellMap[row][col] = new Smell(input);
                }
            }
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int sharkNum = 0; sharkNum < sharkAmount; sharkNum++) {
            int sharkDir = Integer.parseInt(tokenizer.nextToken());
            sharks[sharkNum].dir = sharkDir - 1;
        }

        for (int sharkNum = 0; sharkNum < sharkAmount; sharkNum++) {
            for (int dir = 0; dir < 4; dir++) {
                tokenizer = new StringTokenizer(reader.readLine());

                for (int i = 0; i < 4; i++) {
                    int preferredDir = Integer.parseInt(tokenizer.nextToken());
                    sharks[sharkNum].preferredDir[dir][i] = preferredDir - 1;
                }
            }
        }

    }

    private static int getTimeToEnd() {
        int time = 0;
        while (!gameEnd && time <=1000) {
            moveSharks();
            if (aliveSharkAmount == 1) {
                gameEnd = true;
            }
            time++;
        }

        return time;
    }

    private static void moveSharks() {
        for (int sharkNum = 0; sharkNum < sharkAmount; sharkNum++) {
            Shark shark = sharks[sharkNum];
            if (shark.isAlive()) {
                shark.move();
            }
        }
        copyMap(nextMap, map);

        reduceSmell();

        for (int sharkNum = 0; sharkNum < sharkAmount; sharkNum++) {
            Shark shark = sharks[sharkNum];
            if (shark.isAlive()) {
                shark.makeSmell();
            }
        }
    }

    private static void copyMap(int[][] src, int[][] dest) {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                dest[i][j] = src[i][j];
                src[i][j] = 0;
            }
        }
    }

    private static void reduceSmell() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                Smell smell = smellMap[i][j];
                if (smell != null) {
                    if (!smell.reduce()) {
                        smellMap[i][j] = null;
                    }
                }
            }
        }
    }


    private static int checkLimit(int answer) {
        if (answer > 1000) {
            return -1;
        } else {
            return answer;
        }
    }

    private static class Shark {
        int row;
        int col;
        int dir;
        int num;
        int[][] preferredDir = new int[4][4];
        boolean alive = true;


        public Shark(int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num;
        }

        public boolean isAlive() {
            return alive;
        }

        public void move() {
            int nextRow = row, nextCol = col, nextDir = -1;

            //다음 갈 칸 찾기
            for (int i = 0; i < 4; i++) {
                int tempDir = preferredDir[dir][i];
                int tempRow = row + dRow[tempDir];
                int tempCol = col + dCol[tempDir];

                if (!isInArea(tempRow, tempCol)) {
                    continue;
                }

                if (smellMap[tempRow][tempCol] == null) {
                    nextRow = tempRow;
                    nextCol = tempCol;
                    nextDir = tempDir;
                    break;
                } else if (smellMap[tempRow][tempCol].sharkNum == this.num) {
                    //냄새나는 칸 중 가장 우선도 높은 칸 저장
                    if (nextDir < 0) {
                        nextRow = tempRow;
                        nextCol = tempCol;
                        nextDir = tempDir;
                    }
                }
            }

            int area = nextMap[nextRow][nextCol];
            if (area != 0) {
                if (area > this.num) {
                    sharks[area - 1].die();
                } else {
                    this.die();
                }
            }

            if (this.isAlive()) {
                this.row = nextRow;
                this.col = nextCol;
                this.dir = nextDir;
                nextMap[row][col] = this.num;
            }
            //이거 다끝난담에 뿌려야 할텐데
//            smellMap[row][col] = new Smell(num);


        }


        private boolean isInArea(int tempRow, int tempCol) {
            return 0 <= tempRow && tempRow < mapSize &&
                    0 <= tempCol && tempCol < mapSize;
        }

        public void die() {
            map[row][col] = 0;
            aliveSharkAmount--;
            alive = false;
        }



        public void makeSmell() {
            smellMap[row][col] = new Smell(num);
        }
    }

    private static class Smell {
        int sharkNum, count;

        public Smell(int sharkNum) {
            this.sharkNum = sharkNum;
            this.count = smellDuration;
        }

        public boolean reduce() {
            if (count > 0) {
                count--;
            }
            return count != 0;
        }
    }
}
