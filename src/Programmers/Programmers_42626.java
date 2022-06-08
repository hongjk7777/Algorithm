package Programmers;

import java.util.PriorityQueue;

public class Programmers_42626 {
    public static void main(String[] args) {
        new Solution().solution(new int[] {1, 2, 3, 9, 10, 12}, 100000);
    }

    static class Solution {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();

        public int solution(int[] scoville, int K) {

            addElements(scoville);

            return getAnswer(K);
        }

        private void addElements(int[] scoville) {
            for (int value : scoville) {
                priorityQueue.add((long) value);
            }
        }

        private int getAnswer(int goal) {
            int count = 0;

            while (priorityQueue.peek() < goal) {
                if (priorityQueue.size() == 1) {
                    return -1;
                }

                Long value1 = priorityQueue.poll();
                Long value2 = priorityQueue.poll();
                priorityQueue.add(value1 + value2 * 2);
                count++;
            }

            return count;
        }
    }
}
