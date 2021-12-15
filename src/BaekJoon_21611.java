import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
생각 노트
1. 단계는 총 4단계 구슬 파괴(구슬 이동) -> 구슬 폭발(구슬 이동) -> 구슬 변화


나올 수 있는 예외
1.
 */
public class BaekJoon_21611 {
    private static final int LEFT = 0, DOWN = 1, RIGHT = 2, UP = 3;
    private static int mapSize, castAmount, sharkRow, sharkCol, score = 0;
    private static int[][] map;
    private static ArrayList<Blizzard> blizzardList = new ArrayList<>();
    private static ArrayList<Location> locationList = new ArrayList<>();
    private static int[] dRow = {0, 1, 0, -1};
    private static int[] dCol = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        initialize();

        for (int castNum = 0; castNum < castAmount; castNum++) {
            makeBlizzard(castNum);
        }

        System.out.println(score);
    }


    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        castAmount = Integer.parseInt(tokenizer.nextToken());

        map = new int[mapSize][mapSize];
        sharkRow = mapSize / 2;
        sharkCol = mapSize / 2;

        for (int row = 0; row < mapSize; row++) {
            tokenizer = new StringTokenizer(reader.readLine());

            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 0; i < castAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());

            int dir;
            int input = Integer.parseInt(tokenizer.nextToken());
            //이거 방향 정수 수정해야됨
            if (input == 1) {
                dir = UP;
            } else if (input == 2) {
                dir = DOWN;
            } else if (input == 3) {
                dir = LEFT;
            } else {
                dir = RIGHT;
            }
            int range = Integer.parseInt(tokenizer.nextToken());

            blizzardList.add(new Blizzard(dir, range));
        }
    }

    private static void initialize() {
        int row = sharkRow;
        int col = sharkCol;
        int dir = LEFT;

        int limit = 1;

        while (!(row == 0 && col == 0)) {
            int tempRow = row + dRow[dir];
            int tempCol = col + dCol[dir];

            if (!isInArea(tempRow, tempCol) || isOverLimit(tempRow, tempCol, limit)) {
                dir = changeDir(dir);

                //한 바퀴 돌면 넓혀야 함
                if (dir == LEFT) {
                    limit++;
                }
            } else {
                row = tempRow;
                col = tempCol;
            locationList.add(new Location(row, col));
            }
        }

    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }

    private static boolean isOverLimit(int row, int col, int limit) {
        int center = mapSize / 2;
        return !(center - limit <= row && row <= center + limit &&
                center - limit <= col && col <= center + limit);
    }

    private static int changeDir(int dir) {
        return (dir + 1) % 4;
    }

    private static void makeBlizzard(int castNum) {
        Blizzard blizzard = blizzardList.get(castNum);
        deleteBeads(castNum);
        explodeBeads();
        transformBeads();
    }

    private static void deleteBeads(int castNum) {
        Blizzard blizzard = blizzardList.get(castNum);
        int dir = blizzard.dir;
        int range = blizzard.range;
        int row = sharkRow;
        int col = sharkCol;

        for (int i = 0; i < range; i++) {
            int tempRow = row + (i + 1) * dRow[dir];
            int tempCol = col + (i + 1) * dCol[dir];

            map[tempRow][tempCol] = 0;
        }

        moveBeads();
    }

    private static void moveBeads() {
        int blankAmount = 0;

        for (int i = 0; i < mapSize * mapSize - 1; i++) {
            Location location = locationList.get(i);
            int row = location.row;
            int col = location.col;

            if (map[row][col] == 0) {
                blankAmount++;
            } else if (blankAmount != 0){
                Location newLocation = locationList.get(i - blankAmount);
                int newRow = newLocation.row;
                int newCol = newLocation.col;

                map[newRow][newCol] = map[row][col];
                map[row][col] = 0;
            }

        }
    }

    private static void explodeBeads() {
        int sameBeads = 0;
        int lastBeadsNum = 0;
        boolean end = false;
        ArrayList<Location> tempLocationList = new ArrayList<>();

        //이 함수는 4개 연속하는 것만 리턴하는 함수를 써서 개선하는 게 좋을 듯
        while (!end) {
            end = true;
            for (int i = 0; i < mapSize * mapSize - 1; i++) {
                Location location = locationList.get(i);
                int row = location.row;
                int col = location.col;

                if (i != mapSize * mapSize - 2) {
                    location = locationList.get(i + 1);
                }
                int nextRow = location.row;
                int nextCol = location.col;

                if (lastBeadsNum == 0) {
                    //첫 시작 시
                    lastBeadsNum = map[row][col];
                    sameBeads++;
                    tempLocationList.add(new Location(row, col));
                }

                if (map[nextRow][nextCol] == lastBeadsNum) {
                    sameBeads++;
                    tempLocationList.add(new Location(nextRow, nextCol));
                } else {
                    if (sameBeads >= 4) {
                        score += lastBeadsNum * tempLocationList.size();

                        for (Location tempLocation : tempLocationList) {
                            map[tempLocation.row][tempLocation.col] = 0;
                        }
                        end = false;
                    }

                    tempLocationList.clear();
                    lastBeadsNum = 0;
                    sameBeads = 0;
                }
            }
            tempLocationList.clear();
            lastBeadsNum = 0;
            sameBeads = 0;
            moveBeads();
        }
    }

    private static void transformBeads() {
        ArrayList<Integer> beadsNumList = new ArrayList<>();
        ArrayList<Location> tempLocationList = new ArrayList<>();
        int locationIndex = 0;
        int lastBeadsNum = 0;
        int sameBeads = 0;
        boolean end = false;

        for (Location location : locationList) {
            int row = location.row;
            int col = location.col;

            if (map[row][col] == 0) {
                break;
            }
            beadsNumList.add(map[row][col]);
        }

        clearBeads();

        for (int i = 0; i < beadsNumList.size(); i++) {
            Integer beadsNum = beadsNumList.get(i);
            int curNum = lastBeadsNum;

            if (curNum == 0) {
                lastBeadsNum = beadsNum;
                sameBeads++;
            }

            if (i == beadsNumList.size() - 1) {
                if (beadsNum != lastBeadsNum) {
                    putTransformedBeads(locationIndex, lastBeadsNum, sameBeads);
                    Location tempLocation;
                    locationIndex += 2;
                    if (locationIndex >= mapSize * mapSize - 2) {
                        break;
                    }
                    tempLocation = locationList.get(locationIndex);
                    map[tempLocation.row][tempLocation.col] = 1;
                    if (locationIndex + 1 > mapSize * mapSize - 2) {
                        break;
                    }
                    tempLocation = locationList.get(locationIndex + 1);
                    map[tempLocation.row][tempLocation.col] = beadsNum;
                } else if (curNum != 0){
                    sameBeads++;
                    putTransformedBeads(locationIndex, lastBeadsNum, sameBeads);
                    if (locationIndex > mapSize * mapSize - 2) {
                        break;
                    }
                }
                break;
            }

            if (beadsNum != lastBeadsNum) {
                putTransformedBeads(locationIndex, lastBeadsNum, sameBeads);
                locationIndex += 2;
                if (locationIndex > mapSize * mapSize - 2) {
                    break;
                }
                lastBeadsNum = beadsNum;
                sameBeads = 1;
            } else if (curNum != 0){
                sameBeads++;
            }
        }
    }

    private static void putTransformedBeads(int locationIndex, int lastBeadsNum, int sameBeads) {
        Location tempLocation = locationList.get(locationIndex);
        map[tempLocation.row][tempLocation.col] = sameBeads;
        if (locationIndex + 1 > mapSize * mapSize - 2) {
            return;
        }
        tempLocation = locationList.get(locationIndex + 1);
        map[tempLocation.row][tempLocation.col] = lastBeadsNum;
    }

    private static void clearBeads() {
        for (Location location : locationList) {
            map[location.row][location.col] = 0;
        }
    }

    private static class Blizzard {
        int dir, range;

        public Blizzard(int dir, int range) {
            this.dir = dir;
            this.range = range;
        }
    }

    private static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
