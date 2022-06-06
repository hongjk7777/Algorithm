package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_21608 {
    private static int mapSize;
    private static Student[] students;
    private static int[] order;
    private static int[][] map;
    private static int[] dRow = {0, 0, 1, -1};
    private static int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        initialize();
        decideSeats();
        int answer = getSatisfaction();
        System.out.println(answer);
    }


    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapSize][mapSize];
        order = new int[mapSize * mapSize];
        students = new Student[mapSize * mapSize];

        for (int studentNum = 0; studentNum < mapSize * mapSize; studentNum++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int number = Integer.parseInt(tokenizer.nextToken()) - 1;
            int favoriteNum1 = Integer.parseInt(tokenizer.nextToken()) - 1;
            int favoriteNum2 = Integer.parseInt(tokenizer.nextToken()) - 1;
            int favoriteNum3 = Integer.parseInt(tokenizer.nextToken()) - 1;
            int favoriteNum4 = Integer.parseInt(tokenizer.nextToken()) - 1;

            order[studentNum] = number;
            students[number] = new Student(number, new int[]{favoriteNum1, favoriteNum2, favoriteNum3, favoriteNum4});
        }
    }

    private static void initialize() {
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = -1;
            }
        }
    }

    private static void decideSeats() {
        for (int studentNum = 0; studentNum < mapSize * mapSize; studentNum++) {
            decideSeat(studentNum);
        }

    }

    private static void decideSeat(int studentNum) {
        int index = order[studentNum];
        Student student = students[index];
        Seat curSeat = null;

        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                if (map[row][col] != -1) {
                    continue;
                }

                int favoriteCount = 0;
                int emptyCount = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int tempRow = row + dRow[dir];
                    int tempCol = col + dCol[dir];

                    if (!isInArea(tempRow, tempCol)) {
                        continue;
                    }

                    int number = map[tempRow][tempCol];

                    if (number == -1) {
                        emptyCount++;
                    }

                    if (student.isFavorite(number)) {
                        favoriteCount++;
                    }

                }

                Seat newSeat = new Seat(row, col, favoriteCount, emptyCount);

                if (curSeat == null) {
                    curSeat = newSeat;
                } else {
                    if (isBetterSeat(curSeat, newSeat)) {
                        curSeat = newSeat;
                    }
                }
            }
        }

        assert curSeat != null;
        int row = curSeat.row;
        int col = curSeat.col;

        map[row][col] = student.number;
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }

    //개선필요
    private static boolean isBetterSeat(Seat curSeat, Seat newSeat) {
        if (curSeat.satisfaction > newSeat.satisfaction) {
            return false;
        } else if (curSeat.satisfaction < newSeat.satisfaction) {
            return true;
        } else {
            if (curSeat.emptyArea > newSeat.emptyArea) {
                return false;
            } else if (curSeat.emptyArea < newSeat.emptyArea) {
                return true;
            } else {
                if (curSeat.row < newSeat.row) {
                    return false;
                } else if (curSeat.row > newSeat.row) {
                    return true;
                } else {
                    return curSeat.col >= newSeat.col;
                }
            }
        }
    }

    private static int getSatisfaction() {
        int sum = 0;

        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                int number = map[row][col];
                Student student = students[number];
                int count = 0;

                for (int dir = 0; dir < 4; dir++) {
                    int tempRow = row + dRow[dir];
                    int tempCol = col + dCol[dir];

                    if (!isInArea(tempRow, tempCol)) {
                        continue;
                    }

                    int newNumber = map[tempRow][tempCol];

                    if (student.isFavorite(newNumber)) {
                        count++;
                    }
                }

                if (count == 1) {
                    sum += 1;
                } else if (count == 2) {
                    sum += 10;
                } else if (count == 3) {
                    sum += 100;
                } else if (count == 4) {
                    sum += 1000;
                }
            }
        }
        return sum;
    }

    private static class Student {
        int number;
        int[] favoriteNumber;

        public Student(int number, int[] favoriteNumber) {
            this.number = number;
            this.favoriteNumber = favoriteNumber;
        }

        public boolean isFavorite(int number) {
            boolean favorite = false;
            for (int i = 0; i < 4; i++) {
                if (favoriteNumber[i] == number) {
                    favorite = true;
                    break;
                }
            }

            return favorite;
        }
    }

    private static class Seat {
        int row, col;
        int satisfaction, emptyArea;

        public Seat(int row, int col, int satisfaction, int emptyArea) {
            this.row = row;
            this.col = col;
            this.satisfaction = satisfaction;
            this.emptyArea = emptyArea;
        }
    }
}
