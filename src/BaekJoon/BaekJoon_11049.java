package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11049 {
    private static final int INF_MAX = 987654321;
    private static int matrixAmount;
    private static int[][] dp;
    private static Matrix[] matrices;

    public static void main(String[] args) throws IOException {
        getInput();
        findMinMultiply();
        printMinMultiply();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        matrixAmount = Integer.parseInt(tokenizer.nextToken());
        matrices = new Matrix[matrixAmount];
        dp = new int[matrixAmount + 1][matrixAmount + 1];

        for (int i = 0; i < matrixAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int row = Integer.parseInt(tokenizer.nextToken());
            int col = Integer.parseInt(tokenizer.nextToken());
            matrices[i] = new Matrix(row, col);
        }
    }

    private static void findMinMultiply() {
        for (int multiplySize = 1; multiplySize < matrixAmount; multiplySize++) {
            for (int start = 1; start <= matrixAmount - multiplySize; start++) {
                int end = start + multiplySize;
                dp[start][end] = INF_MAX;

                for (int index = start; index < end; index++) {
                    int multiplyAmount =
                            matrices[start - 1].row * matrices[index - 1].col * matrices[end - 1].col;
                    dp[start][end] = Math.min(dp[start][end],
                            dp[start][index] + dp[index + 1][end] + multiplyAmount);
                }
            }
        }
    }

    private static void printMinMultiply() {
        System.out.println(dp[1][matrixAmount]);
    }

    private static class Matrix {
        int row, col;

        public Matrix(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
