package Programmers;

import java.util.Stack;

public class Programmers_76502 {
    public static void main(String[] args) {
        new Solution().solution("}}}");
    }

    static class Solution {

        char[] openBrackets = {'[', '{', '('};
        char[] closeBrackets = {']', '}', ')'};

        public int solution(String s) {
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                String rotateStr = getRotateStr(s, i);
                if (isCorrectBrackets(rotateStr)) {
                    count++;
                }
            }
            return count;
        }

        private String getRotateStr(String s, int rotateSize) {
            return s.substring(rotateSize) + s.substring(0, rotateSize);
        }

        private boolean isCorrectBrackets(String str) {
            Stack<Character> bracketStack = new Stack<>();

            for (int i = 0; i < str.length(); i++) {
                char curChar = str.charAt(i);
                if (isOpenBracket(curChar)) {
                    bracketStack.add(curChar);
                } else {
                    if (bracketStack.isEmpty()) {
                        return false;
                    } else if (isSameBracket(bracketStack.peek(), curChar)) {
                        bracketStack.pop();
                    } else {
                        return false;
                    }
                }
            }

            return bracketStack.isEmpty();
        }

        private boolean isOpenBracket(char c) {
            return c == '(' || c == '{' || c == '[';
        }

        private boolean isSameBracket(char openBracket, char closeBracket) {
            if (openBracket == '('){
                return closeBracket == ')';
            } else if (openBracket == '[') {
                return closeBracket == ']';
            } else {
                return closeBracket == '}';
            }
        }

    }
}
