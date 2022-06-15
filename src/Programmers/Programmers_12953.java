package Programmers;

public class Programmers_12953 {
    public static void main(String[] args) {
        new Solution().solution(new int[]{99, 97, 95, 93, 91, 89, 87, 85, 83, 81, 79, 77, 75, 73, 71});
    }

    static class Solution {
        public int solution(int[] arr) {
            return getAllOfLCM(arr);
        }

        private int getAllOfLCM(int[] arr) {
            int lcm = arr[0];
            for (int i = 1; i <= arr.length - 1; i++) {
                lcm = getLcm(lcm, arr[i]);
            }
            return lcm;
        }

        private int getLcm(int num1, int num2) {
            return num1 * num2 / getGCD(num1, num2);
        }

        private int getGCD(int num1, int num2) {
            int min = Math.min(num1, num2);
            int gcd = 1;
            for (int i = 2; i <= min; i++) {
                if (num1 % i == 0 && num2 % i == 0) {
                    gcd = i;
                }
            }
            return gcd;
        }
    }
}
