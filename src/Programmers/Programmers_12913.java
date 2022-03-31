package Programmers;

public class Programmers_12913 {
    public static void main(String[] args) {
        new Solution().solution(new int[][]{{1, 2, 3, 5}, {5, 6, 7, 8}, {4, 3, 2, 1}});
    }

    static class Solution {
        private static final int COL_NUM = 4;
        int[][] dp;

        int solution(int[][] land) {
            dp = new int[land.length + 1][4];
            makeDP(land);

            return getAnswer();
        }

        private void makeDP(int[][] land) {

            for (int row = 0; row < land.length; row++) {
                for (int curCol = 0; curCol < COL_NUM; curCol++) {
                    for (int nextCol = 0; nextCol < COL_NUM; nextCol++) {
                        if (nextCol != curCol) {
                            dp[row + 1][nextCol] = Math.max(dp[row + 1][nextCol], dp[row][curCol] + land[row][nextCol]);
                        }
                    }
                }
            }
        }

        private int getAnswer() {
            int max = 0;
            for (int col = 0; col < COL_NUM; col++) {
                max = Math.max(max, dp[dp.length - 1][col]);
            }
            return max;
        }
    }
}
