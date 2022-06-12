package Programmers;

public class Programmers_62048 {
    public static void main(String[] args) {
        new Solution().solution(8, 12);
    }

    static class Solution {
        public long solution(int w, int h) {
            long min = Math.min(w, h);
            long max = Math.max(w, h);
            int gcd = getGCD(w, h);

            return max * min - (min + max - gcd);
        }

        private int getGCD(int num1, int num2) {
            int gcd = 1;
            for (int i = 2; i <= Math.max(num1, num2); i++) {
                if (num1 % i == 0 && num2 % i == 0) {
                    gcd = i;
                }
            }

            return gcd;
        }

    }
}
