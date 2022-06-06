package BaekJoon;/*
생각노트
1. 택시가 bfs로 가장 가까운 손님 찾기(row, col 제일 작은 곳)
2. 목적지도 bfs로 거리를 확인하고 남은 연료 확인
2. 한 칸 움직일 때마다 연료 확인
3. 목적지에 도착하면 손님 태우고 쓴 연료에 두 배 받기
ㄴ 손님의 위치, 도착지 위치를 어떻게 표현
ㄴ 도착지는 손님을 정하면 1개로 압축됨
ㄴ map에 2로 표현함(그러면 2를 발견하면 해당 손님은 어케 찾음?)
ㄴ guestMap을 만들거나 모든 손님을 비교하면서 찾아야함.
ㄴ guestMap을 만드는 게 나을듯!

미흡한 점
1. bfs 구현시 옆에 꺼 되서 무한됨
2. 예외 생각이 미흡(경계값은 무조건 해야됨)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_19238 {

    private static int mapSize, guestAmount, fuelAmount, startRow, startCol, endGuest = 0;
    private static int[][] map, visited;
    private static Guest[][] guestMap;
    private static Taxi taxi;
    private static final int[] dRow = {-1, 1, 0, 0};
    private static final int[] dCol = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        getInput();
        startTaxi();
        System.out.println(fuelAmount);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        guestAmount = Integer.parseInt(tokenizer.nextToken());
        fuelAmount = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapSize][mapSize];
        visited = new int[mapSize][mapSize];
        guestMap = new Guest[mapSize][mapSize];

        for (int row = 0; row < mapSize; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        tokenizer = new StringTokenizer(reader.readLine());
        startRow = Integer.parseInt(tokenizer.nextToken()) - 1;
        startCol = Integer.parseInt(tokenizer.nextToken()) - 1;
        taxi = new Taxi(startRow, startCol);

        for (int i = 0; i < guestAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int guestRow = Integer.parseInt(tokenizer.nextToken()) - 1;
            int guestCol = Integer.parseInt(tokenizer.nextToken()) - 1;
            int destRow = Integer.parseInt(tokenizer.nextToken()) - 1;
            int destCol = Integer.parseInt(tokenizer.nextToken()) - 1;

            map[guestRow][guestCol] = 2;
            guestMap[guestRow][guestCol] = new Guest(guestRow, guestCol, destRow, destCol);
        }
    }

    private static void startTaxi() {
        Guest guest;
        while (fuelAmount >= 0 && endGuest < guestAmount) {
//            if (guest == null) {
                guest = findGuest();
                resetVisited();

                if (guest == null) {
                    fuelAmount = -1;
                    break;
                } else if (guest.distToTaxi > fuelAmount) {
                    fuelAmount = -1;
                } else {
                    taxi.move(guest.row, guest.col);
                    fuelAmount -= guest.distToTaxi;
                }
//            } else {
                int dist = findRoadToDist(guest);
                resetVisited();
                if (dist > fuelAmount) {
                    fuelAmount = -1;
                } else if (dist == -1) {
                    fuelAmount = -1;
                } else {
                    taxi.move(guest.destRow, guest.destCol);
                    fuelAmount += dist;
                    endGuest++;
                    guest.finish();
                }
//            }

        }
    }

    private static Guest findGuest() {
        //bfs로 찾음
        //distToTaxi 에 택시까지의 거리 적어야 함
        Queue<Taxi> taxiQueue = new LinkedList<>();
        Guest guest = null;
        int endDist = 987654321;

        taxiQueue.add(taxi);

        visited[taxi.row][taxi.col] = 1;
        if (map[taxi.row][taxi.col] == 2) {
            guest = guestMap[taxi.row][taxi.col];
            if (!guest.isFinished()) {
                guest.distToTaxi = 0;
                return guest;
            }
            guest = null;
        }

        while (!taxiQueue.isEmpty()) {
            Taxi tempTaxi = taxiQueue.poll();
            int row = tempTaxi.row;
            int col = tempTaxi.col;
            int dist = tempTaxi.dist;

            if (endDist < dist + 1) {
                break;
            }



            for (int i = 0; i < 4; i++) {
                int tempRow = row + dRow[i];
                int tempCol = col + dCol[i];


                if (!isInArea(tempRow, tempCol)) {
                    continue;
                }

                if (visited[tempRow][tempCol] != 0) {
                    continue;
                }

                if (map[tempRow][tempCol] == 2) {
                    if (!guestMap[tempRow][tempCol].isFinished()) {
                        if (guest != null) {
                            if (checkPriority(guest.row, guest.col, tempRow, tempCol)) {
                                guest = guestMap[tempRow][tempCol];
                                endDist = dist + 1;
                                guest.distToTaxi = dist + 1;
                            }
                        } else {
                            guest = guestMap[tempRow][tempCol];
                            endDist = dist + 1;
                            guest.distToTaxi = dist + 1;
                        }
                    }
                }
                visited[tempRow][tempCol] = 1;
                taxiQueue.add(new Taxi(tempRow, tempCol, dist + 1));

            }
        }

        return guest;
    }



    private static boolean isInArea(int tempRow, int tempCol) {
        if (0 <= tempRow && tempRow < mapSize && 0 <= tempCol && tempCol < mapSize) {
            return map[tempRow][tempCol] != 1;
        }
        return false;
    }

    private static boolean checkPriority(int row, int col, int tempRow, int tempCol) {
        if (row < tempRow) {
            return false;
        } else if (row > tempRow) {
            return true;
        } else {
            return col >= tempCol;
        }
    }

    private static void resetVisited() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                visited[i][j] = 0;
            }
        }
    }

    private static int findRoadToDist(Guest guest) {
        Queue<Taxi> taxiQueue = new LinkedList<>();
        taxiQueue.add(taxi);
        int destRow = guest.destRow;
        int destCol = guest.destCol;
        int totalDist = 0;
        boolean able = false;

        while (!taxiQueue.isEmpty()) {
            Taxi tempTaxi = taxiQueue.poll();
            int row = tempTaxi.row;
            int col = tempTaxi.col;
            int dist = tempTaxi.dist;

            if (row == destRow && col == destCol) {
                totalDist = dist;
                able = true;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int tempRow = row + dRow[i];
                int tempCol = col + dCol[i];

                if (!isInArea(tempRow, tempCol)) {
                    continue;
                }

                if (visited[tempRow][tempCol] != 0) {
                    continue;
                }

                visited[tempRow][tempCol] = 1;
                taxiQueue.add(new Taxi(tempRow, tempCol, dist + 1));
            }
        }

        if (able) {
            return totalDist;
        } else {
            return -1;
        }
    }

    private static class Guest {
        int row, col, destRow, destCol;
        int distToTaxi;
        boolean finished;

        public Guest(int guestRow, int guestCol, int destRow, int destCol) {
            this.row = guestRow;
            this.col = guestCol;
            this.destRow = destRow;
            this.destCol = destCol;
            this.finished = false;
        }

        public void finish() {
            finished = true;
        }

        public boolean isFinished() {
            return finished;
        }
    }

    private static class Taxi {
        int row, col, dist;

        public Taxi(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }

        public Taxi(int row, int col) {
            this.row = row;
            this.col = col;
            this.dist = 0;
        }

        public void move(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
