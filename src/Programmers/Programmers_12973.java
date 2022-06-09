package Programmers;

import java.util.Stack;

public class Programmers_12973 {
    public static void main(String[] args) {
        new Solution().solution("baabaa");
    }

    static class Solution {
        Stack<Character> stack = new Stack<>();

        public int solution(String s) {
            addStr2StackWithRemoving(s);
            return getAnswer();
        }

        private void addStr2StackWithRemoving(String s) {
            stack.add(s.charAt(0));

            for (int i = 1; i < s.length(); i++) {
                char nextChar = s.charAt(i);

                if (stack.size() == 0 || stack.peek() != nextChar) {
                    stack.add(nextChar);
                } else {
                    stack.pop();
                }
            }
        }

        private int getAnswer() {
            if (stack.size() > 0) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
