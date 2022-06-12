package Programmers;

public class Programmers_12905 {
    public static void main(String[] args) {
        new Solution().solution(new int[][]{{0, 1, 1, 0}, {1, 1, 1, 1}, {0, 0, 1, 1}});
    }

    static class Solution
    {
        int[][] dp;
        int max = 0;
        int mapRow, mapCol;

        public int solution(int [][]board)
        {
            mapRow = board.length;
            mapCol = board[0].length;
            dp = new int[mapRow][mapCol];
            makeDP(board);
            return (int) Math.pow(max, 2);
        }

        private void makeDP(int[][] board) {
            for (int row = 0; row < mapRow; row++) {
                for (int col = 0; col < mapCol; col++) {
                    if (Math.min(row, col) < 1) {
                        if (board[row][col] == 1) {
                            dp[row][col] = 1;
                            max = Math.max(max, 1);
                        }
                    } else {
                        int size = getMin(row, col);

                        if (board[row][col] == 1) {
                            dp[row][col] = size + 1;
                            max = Math.max(max, size + 1);
                        }
                    }
                }
            }
        }

        private int getMin(int row, int col) {
            int min = Math.min(dp[row][col - 1], dp[row - 1][col]);
            min = Math.min(min, dp[row - 1][col - 1]);
            return min;
        }
    }
}
