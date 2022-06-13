package Programmers;

import java.util.Stack;

public class Programmers_12909 {
    public static void main(String[] args) {
        new Solution().solution("(()(");
    }

    static class Solution {
        boolean solution(String s) {
            return isCorrect(s);
        }

        private boolean isCorrect(String s) {
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.add('(');
                } else {
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        stack.pop();
                    }
                }
            }

            return stack.size() == 0;
        }
    }
}
