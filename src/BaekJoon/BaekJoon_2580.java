package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_2580 {
    private static final int SIZE = 9;
    private static final int[][] sudoku = new int[SIZE][SIZE];
    private static final ArrayList<Location> blanks = new ArrayList<>();
    private static boolean complete = false;

    public static void main(String[] args) throws IOException {
        getInput();
        dfs(0);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        for (int row = 0; row < SIZE; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < SIZE; col++) {
                int value = Integer.parseInt(tokenizer.nextToken());
                if (value == 0) {
                    blanks.add(new Location(row, col));
                }
                sudoku[row][col] = value;
            }
        }
    }

    private static void dfs(int depth) {

        if (depth == blanks.size()) {
            printSudoku();
            complete = true;
            return;
        }

        Location blank = blanks.get(depth);
        ArrayList<Integer> possibleNum;
        possibleNum = getPossibleNum(blank);

        for (int num : possibleNum) {
            if (!complete) {
                sudoku[blank.row][blank.col] = num;
                dfs(depth + 1);
            }
            sudoku[blank.row][blank.col] = 0;
        }
    }

    private static void printSudoku() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                builder.append(sudoku[row][col]).append(" ");
            }
            builder.append("\n");
        }

        System.out.println(builder);
    }

    private static ArrayList<Integer> getPossibleNum(Location blank) {
        ArrayList<Integer> possibleNum = new ArrayList<>();
        boolean[] checked = new boolean[SIZE + 1];
        int row = blank.row;
        int col = blank.col;

        for (int tempRow = 0; tempRow < SIZE; tempRow++) {
            checked[sudoku[tempRow][col]] = true;
        }
        for (int tempCol = 0; tempCol < SIZE; tempCol++) {
            checked[sudoku[row][tempCol]] = true;
        }

        //속해있는 사각형의 왼쪽 위로 가기 위해 하는 작업
        row = (row / 3) * 3;
        col = (col / 3) * 3;
        for (int dRow = 0; dRow < 3; dRow++) {
            for (int dCol = 0; dCol < 3; dCol++) {
                checked[sudoku[row + dRow][col + dCol]] = true;
            }
        }

        for (int i = 1; i <= SIZE; i++) {
            if (!checked[i]) {
                possibleNum.add(i);
            }
        }

        return possibleNum;
    }

    private static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
