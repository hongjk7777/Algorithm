import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_9663 {
    private static int mapSize, answer = 0;
    private static int[][] checked;

    public static void main(String[] args) throws IOException {
        getInput();
        findNQueensAnswer(0);
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        checked = new int[mapSize][mapSize];
    }

    private static void findNQueensAnswer(int row) {
        if (row == mapSize) {
            answer++;
            return;
        }

        for (int col = 0; col < mapSize; col++) {
            if (checked[row][col] == 0) {
                checkAreas(row, col);
                findNQueensAnswer(row + 1);
                uncheckArea(row, col);
            }
        }
    }

    private static void checkAreas(int row, int col) {
        checked[row][col]++;
        checkHorizontal(row, col + 1);
        checkVertical(row + 1, col);
        checkSWDiagonal(row + 1, col - 1);
        checkSEDiagonal(row + 1, col + 1);
    }

    private static void checkHorizontal(int row, int col) {
        while (isInArea(row, col)) {
            checked[row][col]++;
            col++;
        }
    }

    private static void checkVertical(int row, int col) {
        while (isInArea(row, col)) {
            checked[row][col]++;
            row++;
        }
    }

    private static void checkSWDiagonal(int row, int col) {
        while (isInArea(row, col)) {
            checked[row][col]++;
            row++;
            col--;
        }
    }

    private static void checkSEDiagonal(int row, int col) {
        while (isInArea(row, col)) {
            checked[row][col]++;
            row++;
            col++;
        }
    }

    private static void uncheckArea(int row, int col) {
        checked[row][col]--;
        uncheckHorizontal(row, col + 1);
        uncheckVertical(row + 1, col);
        uncheckSWDiagonal(row + 1, col - 1);
        uncheckSEDiagonal(row + 1, col + 1);
    }

    private static void uncheckHorizontal(int row, int col) {
        while (isInArea(row, col)) {
            checked[row][col]--;
            col++;
        }
    }

    private static void uncheckVertical(int row, int col) {
        while (isInArea(row, col)) {
            checked[row][col]--;
            row++;
        }
    }

    private static void uncheckSWDiagonal(int row, int col) {
        while (isInArea(row, col)) {
            checked[row][col]--;
            row++;
            col--;
        }
    }

    private static void uncheckSEDiagonal(int row, int col) {
        while (isInArea(row, col)) {
            checked[row][col]--;
            row++;
            col++;
        }
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }
}
