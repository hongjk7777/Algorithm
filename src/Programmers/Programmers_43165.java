package Programmers;

public class Programmers_43165 {
    public static void main(String[] args) {
        new Solution().solution(new int[]{}, 0);
    }

    static class Solution {
        private int answer = 0;
        public int solution(int[] numbers, int target) {
            dfs(0, 0, numbers, target);
            return answer;
        }

        public void dfs(int depth, int sum, int[] numbers, int target) {
            if(depth == numbers.length) {
                if(sum == target){
                    answer++;
                }
                return;
            }

            dfs(depth + 1, sum + numbers[depth], numbers, target);
            dfs(depth + 1, sum - numbers[depth], numbers, target);
        }
    }
}
