package Programmers;

public class Programmers_12899 {

    public static void main(String[] args) {
        new Solution().solution(500000000);
    }

    static class Solution {
        StringBuilder answer = new StringBuilder();
        public String solution(int n) {
            makeAnswer(n);
            return answer.toString();
        }

        private void makeAnswer(long n) {
            int size = 0;
            while (n > 0) {
                size++;
                n -= Math.pow(3, size);
            }

            n += Math.pow(3, size);

            while (size > 0) {
                long pow3 = (long) Math.pow(3, size);

                if (n <= pow3 / 3) {
                    answer.append(1);
                } else if (n <= (pow3 * 2) / 3) {
                    answer.append(2);
                    n -= pow3 / 3;
                } else {
                    answer.append(4);
                    n -= (pow3 * 2) / 3;
                }

                size--;
            }
        }
    }
}
