package Programmers;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_42586 {
    public static void main(String[] args) {
        new Solution().solution(new int[]{95, 90, 99, 99, 80, 99}, new int[]{1, 1, 1, 1, 1, 1});
    }

    static class Solution {
        Queue<Integer> queue = new LinkedList<>();
        public int[] solution(int[] progresses, int[] speeds) {
            addProgresses2Queue(progresses);

            return getAnswer(speeds);
        }

        private void addProgresses2Queue(int[] progresses) {
            for (int progress : progresses) {
                queue.add(progress);
            }
        }

        private int[] getAnswer(int[] speeds) {
            int time = 1;
            int completed = 0;
            ArrayList<Integer> answer = new ArrayList<>();
            while (!queue.isEmpty()) {
                int count = 0;
                while (!queue.isEmpty() && queue.peek() + time * speeds[completed] >= 100) {
                    queue.poll();
                    completed++;
                    count++;
                }
                if (count > 0) {
                    answer.add(count);
                }
                time++;
            }

            return answer.stream().mapToInt(i -> i).toArray();
        }
    }
}
