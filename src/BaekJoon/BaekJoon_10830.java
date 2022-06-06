package BaekJoon;/*
느낀 점
1. 다른 사람의 풀이를 보면 연산자 정의를 했는데 나도 한 번 해보자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_10830 {
    private static int matrixSize;
    private static long powNum;
    private static int[][] matrix;

    public static void main(String[] args) throws IOException {
        getInput();
        matrix = powMatrix(powNum);
        printMatrix();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        matrixSize = Integer.parseInt(tokenizer.nextToken());
        powNum = Long.parseLong(tokenizer.nextToken());
        matrix = new int[matrixSize][matrixSize];

        for (int row = 0; row < matrixSize; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < matrixSize; col++) {
                matrix[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static int[][] powMatrix(long powNum) {
        int[][] ret = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            ret[i][i] = 1;
        }

        while (powNum > 0) {
            if (powNum % 2 == 1) {
                ret = multiplyMatrix(ret, matrix);
                powNum--;
            }

            matrix = multiplyMatrix(matrix, matrix);
            powNum /= 2;
        }

        return ret;
    }

    private static int[][] multiplyMatrix(int[][] front, int[][] back) {
        int[][] ret = new int[matrixSize][matrixSize];

        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++) {
                int sum = 0;
                for (int i = 0; i < matrixSize; i++) {
                    sum += front[row][i] * back[i][col];
                    sum %= 1000;
                }

                ret[row][col] = sum;
            }
        }

        return ret;
    }

    private static void printMatrix() {
        for (int row = 0; row < matrixSize; row++) {
            for (int col = 0; col < matrixSize; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}
