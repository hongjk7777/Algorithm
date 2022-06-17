package Programmers;

public class Programmers_12902 {
    public static void main(String[] args) {
        new Solution().solution(5000);
    }

    static class Solution {
        private static final int MOD = 1000000007;
        long[] dp;
        public int solution(int n) {
            dp = new long[n + 1];
            makeDP(n);
            return (int) dp[n];
        }

        private void makeDP(int size) {
            dp[0] = 1;

            for (int i = 2; i <= size; i += 2) {
                dp[i] = (3 * dp[i - 2]) % MOD;

                int temp = i - 4;
                while (temp >= 0) {
                    dp[i] = (dp[i] + 2 * dp[temp]) % MOD;
                    temp -= 2;
                }
            }
        }
    }
}
