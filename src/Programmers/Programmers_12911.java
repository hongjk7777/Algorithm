package Programmers;

public class Programmers_12911 {
    public static void main(String[] args) {
        new Solution().solution(15);
    }

    static class Solution {
        public int solution(int n) {
            String binary = getBinary(n);
            String nextBinary = getNextBinary(binary);
            return getNum(nextBinary);
        }

        private String getBinary(int n) {
            StringBuilder builder = new StringBuilder();

            while (n > 0) {
                if ((n & 1) == 1) {
                    builder.append(1);
                } else {
                    builder.append(0);
                }
                n >>= 1;
            }

            return builder.reverse().toString();
        }

        private String getNextBinary(String binary) {
            boolean change = false;
            StringBuilder nextBinary = new StringBuilder();
            for (int i = binary.length() - 1; i >= 0; i--) {
                if (binary.charAt(i) == '1') {
                    change = true;
                    if (i == 0) {
                        nextBinary.append("1").append("0").append(getRightAlign(binary.substring(1)));
                        break;
                    }
                } else {
                    if (change) {
                        nextBinary.append(binary, 0, i).append("1")
                                .append("0").append(getRightAlign(binary.substring(i + 2)));
                        break;
                    }
                }
            }
            return nextBinary.toString();
        }

        private int getNum(String binary) {
            int sum = 0;
            int temp = 1;

            for (int i = binary.length() - 1; i >= 0; i--) {
                if (binary.charAt(i) == '1') {
                    sum += temp;
                }
                temp <<= 1;
            }

            return sum;
        }

        private String getRightAlign(String str) {
            StringBuilder builder = new StringBuilder();
            int count1 = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '1') {
                    count1++;
                }
            }

            builder.append("0".repeat(Math.max(0, str.length() - count1)));
            builder.append("1".repeat(Math.max(0, count1)));

            return builder.toString();
        }
    }
}
