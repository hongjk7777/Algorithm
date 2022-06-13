package Programmers;

import java.util.ArrayList;

public class Programmers_87390 {
    public static void main(String[] args) {
        new Solution().solution(10000000, 10000000000000L, 10000000500000L);
    }

    static class Solution {
        ArrayList<Integer> answer = new ArrayList<>();
        int mapSize;
        public int[] solution(int n, long left, long right) {
            mapSize = n;
            addAnswers(left, right);
            return answer.stream().mapToInt(i -> i).toArray();
        }

        private void addAnswers(long left, long right) {
            for (long i = left; i <= right; i++) {
                answer.add(getValueIndex(i));
            }
        }

        private int getValueIndex(long i) {
            long row = i / mapSize;
            long col = i % mapSize;

            return (int) Math.max(row + 1, col + 1);
        }
    }
}
