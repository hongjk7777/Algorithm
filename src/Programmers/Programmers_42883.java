package Programmers;

import java.util.Stack;

public class Programmers_42883 {
    public static void main(String[] args) {
        new Solution().solution("4177252841", 4);
    }

    static class Solution {
        public String solution(String number, int k) {
            return getMaxStr(number, k);
        }

        private String getMaxStr(String number, int k) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < number.length(); i++) {
                int nextNum = Integer.parseInt(String.valueOf(number.charAt(i)));
                while (isRemovable(k, stack, nextNum)) {
                    stack.pop();
                    k--;
                }
                stack.add(nextNum);
            }

            return makeStackToStr(stack, k);
        }

        private boolean isRemovable(int k, Stack<Integer> stack, int nextNum) {
            return !stack.isEmpty() && stack.peek() < nextNum && k > 0;
        }

        private String makeStackToStr(Stack<Integer> stack, int k) {
            StringBuilder builder = new StringBuilder();

            while (stack.size() > 0) {
                builder.append(stack.pop());
            }

            // k가 남아있을 수도 있으니 뒤에 있는 수들이 더 작기 때문에 뒤부터 지움
            return builder.reverse().substring(0, builder.length() - k);
        }
    }
}
