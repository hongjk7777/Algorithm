import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
/*
 아쉬운 점: map을 사용했더라면 조금 더 일반적인 풀이가 가능했다.
          자료구조에 대해 조금 더 생각해보고 풀어보자
 */
public class BaekJoon_17140 {

    private static int targetRow, targetCol, targetVal, rowSize, colSize;
    private static int[][] map = new int[100][100];
    private static int[] numberCount = new int[100];
    // 나온 횟수 * 100 + 해당 숫자- 1 을 해
    // %100으로 숫자를 나타내고 /100으로 나온 횟수를 나타낸다.

    public static void main(String[] args) {
        int count;
        getInput();
        resetNumberCount();
        for (count = 0; count < 101; count++) {
            if (map[targetRow - 1][targetCol - 1] == targetVal) {
                break;
            }

            if (rowSize >= colSize) {
                calculateRow();
            } else {
                calculateCol();
            }

        }

        if (count >= 101) {
            count = -1;
        }

        System.out.println(count);
    }


    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        targetRow = scanner.nextInt();
        targetCol = scanner.nextInt();
        targetVal = scanner.nextInt();
        rowSize = 3;
        colSize = 3;

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
    }


    private static void resetNumberCount() {
        for (int i = 0; i < 100; i++) {
            numberCount[i] = i;
        }
    }


    private static void calculateRow() {
        int tempMaxCol = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (map[i][j] != 0) {
                    numberCount[map[i][j] - 1] += 100;
                }
            }
            Arrays.sort(numberCount);

            for (int j = 0; j < 100; j++) {
                map[i][j] = 0;
            }
            int colNum = 0;
            for (int j = 0; j < 100; j++) {
                if (numberCount[j] >= 100) {
                    map[i][colNum] = (numberCount[j] % 100) + 1;
                    colNum++;
                    map[i][colNum] = (numberCount[j] / 100);
                    colNum++;
                    if (colNum >= 100) {
                        break;
                    }
                }
            }
            if (colNum > tempMaxCol) {
                tempMaxCol = colNum;
            }
            resetNumberCount();

        }
        colSize = tempMaxCol;
    }


    private static void calculateCol() {
        int tempMaxRow = 0;
        for (int i = 0; i < colSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                if (map[j][i] != 0) {
                    numberCount[map[j][i] - 1] += 100;
                }
            }
            Arrays.sort(numberCount);

            for (int j = 0; j < 100; j++) {
                map[j][i] = 0;
            }
            int rowNum = 0;
            for (int j = 0; j < 100; j++) {
                if (numberCount[j] >= 100) {
                    map[rowNum][i] = (numberCount[j] % 100) + 1;
                    rowNum++;
                    map[rowNum][i] = (numberCount[j] / 100);
                    rowNum++;
                    if (rowNum >= 100) {
                        break;
                    }
                }
            }
            if (rowNum > tempMaxRow) {
                tempMaxRow = rowNum;
            }
            resetNumberCount();

        }
        rowSize = tempMaxRow;
    }

}
