package Programmers;

public class Programmers_12924 {
    public static void main(String[] args) {
        new Solution().solution(1);
    }

    static class Solution {
        public int solution(int n) {
            int answer = 0;
            int sum = 0;
            int addIndex = 1;
            int removeIndex = 1;

            while (addIndex <= n + 1) {
                if (sum < n) {
                    sum = addNumber(sum, addIndex);
                    addIndex++;
                } else if (sum > n) {
                    sum = removeNumber(sum, removeIndex);
                    removeIndex++;
                } else {
                    answer++;
                    sum = removeNumber(sum, removeIndex);
                    removeIndex++;
                }
            }

            return answer;
        }

        private int addNumber(int sum, int firstIndex) {
            return sum + firstIndex;
        }

        private int removeNumber(int sum, int lastIndex) {
            return sum - lastIndex;
        }
    }
}
