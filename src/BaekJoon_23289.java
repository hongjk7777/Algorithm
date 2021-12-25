/*
내가 한 실수
1. 문제가 테케에 다 통과한다고 신나서 생각해볼만한
예외들을 시행해보지 않음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_23289 {
    private static final int LEFT = 0, UP = 1, RIGHT = 2, DOWN = 3;
    private static int mapRow, mapCol, targetDegree, wallAmount;
    private static int[][] map, tempRightWalls, tempUpWalls, tempMap, heated;
    private static Wall[][] wallMap;
    private static ArrayList<Heater> heaters = new ArrayList<>();
    private static ArrayList<Location> testLocations = new ArrayList<>();
    private static int[] dRow = {0, -1, 0, 1};
    private static int[] dCol = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        makeWallMap();

        int chocolateNum;
        for (chocolateNum = 1; chocolateNum <= 100; chocolateNum++) {
            operateHeaters();
            adjustDegree();
            reduceOuterAHeat();
            if (reachTargetDegree()) {
                break;
            }
        }

        System.out.println(chocolateNum);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        targetDegree = Integer.parseInt(tokenizer.nextToken());

        map = new int[mapRow][mapCol];
        tempMap = new int[mapRow][mapCol];
        tempRightWalls = new int[mapRow][mapCol];
        tempUpWalls = new int[mapRow][mapCol];
        wallMap = new Wall[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());

            for (int col = 0; col < mapCol; col++) {
                int input = Integer.parseInt(tokenizer.nextToken());
                if (input > 0 && input < 5) {
                    if (input == 1) {
                        input = RIGHT;
                    } else if (input == 2) {
                        input = LEFT;
                    } else if (input == 3) {
                        input = UP;
                    } else {
                        input = DOWN;
                    }
                    heaters.add(new Heater(row, col, input));
                } else if (input == 5) {
                    testLocations.add(new Location(row, col));
                }
            }
        }

        tokenizer = new StringTokenizer(reader.readLine());
        wallAmount = Integer.parseInt(tokenizer.nextToken());

        for (int wallNum = 0; wallNum < wallAmount; wallNum++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int wallRow = Integer.parseInt(tokenizer.nextToken()) - 1;
            int wallCol = Integer.parseInt(tokenizer.nextToken()) - 1;
            int wallDir = Integer.parseInt(tokenizer.nextToken());

            if (wallDir == 0) {
                wallDir = UP;
                tempUpWalls[wallRow][wallCol] = wallDir;
            } else {
                wallDir = RIGHT;
                tempRightWalls[wallRow][wallCol] = wallDir;
            }

        }
    }


    private static void makeWallMap() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                boolean left = false;
                boolean right = false;
                boolean up = false;
                boolean down = false;

                for (int dir = 0; dir < 4; dir++) {
                    int tempRow = row + dRow[dir];
                    int tempCol = col + dCol[dir];


                    if (dir == LEFT) {
                        if (!isInArea(tempRow, tempCol)) {
                            continue;
                        }
                        if (tempRightWalls[tempRow][tempCol] == RIGHT) {
                            left = true;
                        }
                    } else if (dir == RIGHT){
                        if (tempRightWalls[row][col] == RIGHT) {
                            right = true;
                        }
                    } else if (dir == UP){
                        if (tempUpWalls[row][col] == UP) {
                            up = true;
                        }
                    } else {
                        if (!isInArea(tempRow, tempCol)) {
                            continue;
                        }
                        if (tempUpWalls[tempRow][tempCol] == UP) {
                            down = true;
                        }

                    }
                }

                wallMap[row][col] = new Wall(left, right, up, down);
            }
        }
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }

    private static void operateHeaters() {

        for (Heater heater : heaters) {
            operateHeater(heater);
        }
    }

    private static void operateHeater(Heater heater) {
        int row = heater.row;
        int col = heater.col;
        int dir = heater.dir;
        Queue<Location> locationQueue = new LinkedList<>();
        heated = new int[mapRow][mapCol];

        row += dRow[dir];
        col += dCol[dir];

        locationQueue.add(new Location(row, col, 4));
        heated[row][col] = 1;
        map[row][col] += 5;

        while (!locationQueue.isEmpty()) {
            Location location = locationQueue.poll();
            row = location.row;
            col = location.col;
            int heat = location.heat;
            int tempRow;
            int tempCol;
            int sideDir = (dir + 1) % 4;
            int anotherSideDir = (dir + 3) % 4;

            if (heat == 0) {
                break;
            }

            if (!wallMap[row][col].isWall(dir)) {
                tempRow = location.row + dRow[dir];
                tempCol = location.col + dCol[dir];

                if (isInArea(tempRow, tempCol) && heated[tempRow][tempCol] == 0) {
                    locationQueue.add(new Location(tempRow, tempCol, heat - 1));
                    heated[tempRow][tempCol] = 1;
                    map[tempRow][tempCol] += heat;
                }
            }

            if (!wallMap[row][col].isWall(sideDir)) {
                tempRow = location.row + dRow[sideDir];
                tempCol = location.col + dCol[sideDir];

                if (isInArea(tempRow, tempCol)) {
                    if (!wallMap[tempRow][tempCol].isWall(dir)) {
                        tempRow += dRow[dir];
                        tempCol += dCol[dir];

                        if (isInArea(tempRow, tempCol) && heated[tempRow][tempCol] == 0) {
                            locationQueue.add(new Location(tempRow, tempCol, heat - 1));
                            heated[tempRow][tempCol] = 1;
                            map[tempRow][tempCol] += heat;
                        }
                    }
                }
            }

            if (!wallMap[row][col].isWall(anotherSideDir)) {
                tempRow = location.row + dRow[anotherSideDir];
                tempCol = location.col + dCol[anotherSideDir];

                if (isInArea(tempRow, tempCol)) {
                    if (!wallMap[tempRow][tempCol].isWall(dir)) {
                        tempRow += dRow[dir];
                        tempCol += dCol[dir];

                        if (isInArea(tempRow, tempCol) && heated[tempRow][tempCol] == 0) {
                            locationQueue.add(new Location(tempRow, tempCol, heat - 1));
                            heated[tempRow][tempCol] = 1;
                            map[tempRow][tempCol] += heat;
                        }
                    }
                }
            }
        }

    }

    private static void adjustDegree() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                int degree = map[row][col];
                int lostDegree = 0;

                if (degree < 4) {
                    tempMap[row][col] += degree;
                    continue;
                }
                for (int dir = 0; dir < 4; dir++) {
                    int tempRow = row + dRow[dir];
                    int tempCol = col + dCol[dir];

                    if (!isInArea(tempRow, tempCol)) {
                        continue;
                    }

                    int tempDegree = map[tempRow][tempCol];


                    if (dir == LEFT) {
                        if (wallMap[row][col].leftWall) {
                            continue;
                        }
                    } else if (dir == RIGHT) {
                        if (wallMap[row][col].rightWall) {
                            continue;
                        }
                    } else if (dir == UP) {
                        if (wallMap[row][col].upWall) {
                            continue;
                        }
                    } else{
                        if (wallMap[row][col].downWall) {
                            continue;
                        }
                    }
//                    if (wallMap[])

                    if (degree > tempDegree) {
                        int movingDegree = (degree - tempDegree) / 4;
                        tempMap[tempRow][tempCol] += movingDegree;
                        lostDegree += movingDegree;
                    }
                }

                tempMap[row][col] += degree - lostDegree;
            }
        }

        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = tempMap[row][col];
                tempMap[row][col] = 0;
            }
        }
    }

    private static void reduceOuterAHeat() {
        for (int i = 0; i < mapRow; i++) {
            if (map[i][0] > 0) {
                map[i][0]--;
            }
        }

        for (int i = 0; i < mapRow; i++) {
            if (map[i][mapCol - 1] > 0) {
                map[i][mapCol - 1]--;
            }
        }

        for (int i = 1; i < mapCol - 1; i++) {
            if (map[0][i] > 0) {
                map[0][i]--;
            }
        }

        for (int i = 1; i < mapCol - 1; i++) {
            if (map[mapRow - 1][i] > 0) {
                map[mapRow - 1][i]--;
            }
        }
    }

    private static boolean reachTargetDegree() {
        boolean end = true;

        for (Location location : testLocations) {
            if (map[location.row][location.col] < targetDegree) {
                end = false;
                break;
            }
        }
        return end;
    }

    private static class Heater {
        int row, col, dir;

        public Heater(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }

    private static class Location {
        int row;
        int col;
        int heat;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Location(int row, int col, int heat) {
            this.row = row;
            this.col = col;
            this.heat = heat;
        }
    }

    private static class Wall {
        boolean leftWall, rightWall, upWall, downWall;

        public Wall(boolean leftWall, boolean rightWall, boolean upWall, boolean downWall) {
            this.leftWall = leftWall;
            this.rightWall = rightWall;
            this.upWall = upWall;
            this.downWall = downWall;
        }

        public boolean isWall(int dir) {
            if (dir == LEFT) {
                return leftWall;
            } else if (dir == UP) {
                return upWall;
            } else if (dir == RIGHT) {
                return rightWall;
            } else {
                return downWall;
            }
        }
    }
}
