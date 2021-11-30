import java.util.Scanner;

public class BaekJoon_17140 {

    private static int targetRow, targetCol, targetVal, rowSize, colSize;
    private static int[][] map = new int[100][100];
    private static int[] numberCount = new int[100];

    public static void main(String[] args) {
        int count;
        getInput();
        for (count = 0; count < 100; count++) {
            if (rowSize >= colSize) {
                calculateRow();
            } else {
//                calculateCol();
            }
            count++;

            if (map[targetRow][targetCol] == targetVal) {
                break;
            }
        }

        if (count == 100) {
            count = -1;
        }

        System.out.println(count);
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        targetRow = scanner.nextInt();
        targetCol = scanner.nextInt();
        targetVal = scanner.nextInt();
        rowSize = targetRow;
        colSize = targetCol;

        for (int i = 0; i < targetRow; i++) {
            for (int j = 0; j < targetCol; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
    }

    private static void calculateRow() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (map[i][j] == 0) {
                    continue;
                } else {
                    numberCount[map[i][j] - 1]++;
                }
            }
        }
    }
}
