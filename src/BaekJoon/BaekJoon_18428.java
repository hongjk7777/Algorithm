package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_18428 {
    private static int mapSize;
    private static char[][] map;
    private static ArrayList<Location> teachers = new ArrayList<>();
    private static int[] dRow = {0, 0, 1, -1};
    private static int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        if (canAvoidWatch(3)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        mapSize = Integer.parseInt(tokenizer.nextToken());
        map = new char[mapSize][mapSize];

        for (int row = 0; row < mapSize; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapSize; col++) {
                char input = tokenizer.nextToken().charAt(0);
                map[row][col] = input;

                if (input == 'T') {
                    teachers.add(new Location(row, col));
                }
            }
        }
    }

    private static boolean canAvoidWatch(int obstacleNum) {
        boolean canAvoid = false;
        if (obstacleNum == 0) {
            return canAvoidTeachers();
        }

        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                char area = map[row][col];
                if (area == 'X') {
                    map[row][col] = 'O';
                    if (canAvoidWatch(obstacleNum - 1)) {
                        canAvoid = true;
                        break;
                    }

                    map[row][col] = 'X';
                }
            }

            if (canAvoid) {
                break;
            }
        }
        return canAvoid;
    }

    private static boolean canAvoidTeachers() {
        int size = teachers.size();
        boolean ableToAvoid = true;

        for (int num = 0; num < size; num++) {
            if (!canAvoidTeacher(num)) {
                ableToAvoid = false;
                break;
            }
        }
        return ableToAvoid;
    }

    private static boolean canAvoidTeacher(int teacherNum) {
        Location teacher = teachers.get(teacherNum);
        int row = teacher.row;
        int col = teacher.col;
        boolean ableToAvoid = true;

        for (int dir = 0; dir < 4; dir++) {
            int tempRow = row + dRow[dir];
            int tempCol = col + dCol[dir];

            while (isInArea(tempRow, tempCol)) {
                if (map[tempRow][tempCol] == 'O') {
                    break;
                } else if (map[tempRow][tempCol] == 'S') {
                    ableToAvoid = false;
                    break;
                }

                tempRow += dRow[dir];
                tempCol += dCol[dir];
            }
        }

        return ableToAvoid;
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }

    private static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
