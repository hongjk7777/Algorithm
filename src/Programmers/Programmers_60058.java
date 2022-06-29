package Programmers;
import java.util.Stack;

public class Programmers_60058 {
    public static void main(String[] args) {
        new Solution().solution("");
    }
    static class Solution {
        public String solution(String p) {
            if(isCorrect(p)){
                return p;
            } else {
                return getCorrectStr(p);
            }
        }

        private boolean isCorrect(String p) {
            Stack<Character> stack = new Stack<>();

            for(int i = 0; i < p.length(); i++) {
                if(p.charAt(i) == '(') {
                    stack.add('(');
                } else {
                    if(stack.size() == 0){
                        return false;
                    } else {
                        stack.pop();
                    }
                }
            }

            return stack.size() == 0;
        }

        private String getCorrectStr(String p) {
            if(p.length() == 0){
                return "";
            }

            StringBuilder builder = new StringBuilder();
            int idx = 0;
            for(; idx < p.length(); idx++){
                builder.append(p.charAt(idx));
                if(isBalanced(builder.toString())){
                    break;
                }
            }

            String u = builder.toString();
            String v = p.substring(idx + 1);

            if(isCorrect(u)){
                return u + getCorrectStr(v);
            } else {
                return "(" + getCorrectStr(v) + ")" + reverseBracket(u.substring(1, u.length() - 1));
            }
        }

        private boolean isBalanced(String p) {
            int leftBracket = 0;
            int rightBracket = 0;

            for(int i = 0; i < p.length(); i++) {
                if(p.charAt(i) == '('){
                    leftBracket++;
                } else {
                    rightBracket++;
                }
            }

            return leftBracket == rightBracket;
        }

        private String reverseBracket(String p) {
            StringBuilder builder = new StringBuilder();

            for(int i = 0; i < p.length(); i++) {
                if(p.charAt(i) == '(') {
                    builder.append(')');
                } else {
                    builder.append('(');
                }
            }

            return builder.toString();
        }
    }
}
