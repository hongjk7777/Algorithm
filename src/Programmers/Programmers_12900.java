package Programmers;

public class Programmers_12900 {
    public static void main(String[] args) {
        new Solution().solution(4);
    }

    static class Solution {
        private static final int MOD = 1000000007;
        int[] dp;
        public int solution(int n) {
            dp = new int[n + 1];
            makeDP(n);
            System.out.println(dp[n]);
            return dp[n];
        }

        private void makeDP(int size) {
            dp[0] = 1;
            dp[1] = 1;
            for (int i = 2; i <= size; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
            }
        }

    }
}
