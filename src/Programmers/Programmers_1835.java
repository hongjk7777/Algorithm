package Programmers;

public class Programmers_1835 {
    public static void main(String[] args) {
        new Solution().solution(0, new String[]{});
    }

    static class Solution {
        private final int MAX_FRIEND = 8;
        private char[] kakaoFriends = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        private int[] curIndex = new int[MAX_FRIEND];
        private char[] curLine = new char[MAX_FRIEND];
        private boolean[] checked = new boolean[MAX_FRIEND];
        private int answer = 0;

        public int solution(int n, String[] data) {
            makeLine(0, data);
            return answer;
        }

        public void makeLine(int depth, String[] data) {
            if(depth == MAX_FRIEND) {
                if(ableLine(data)){
                    answer++;
                }
                return;
            }

            char friend = kakaoFriends[depth];
            for(int i = 0; i < MAX_FRIEND; i++) {
                if(!checked[i]) {
                    curLine[i] = friend;
                    checked[i] = true;
                    curIndex[getFriendNum(friend)] = i;
                    makeLine(depth + 1, data);
                    checked[i] = false;
                }
            }
        }

        public boolean ableLine(String[] data) {
            for (String condition : data) {
                if (!meetCondition(condition)) {
                    return false;
                }
            }

            return true;
        }

        public boolean meetCondition(String condition) {
            int friend1 = getFriendNum(condition.charAt(0));
            int friend2 = getFriendNum(condition.charAt(2));
            char op = condition.charAt(3);
            int value = Integer.parseInt(String.valueOf(condition.charAt(4)));

            int interval = Math.abs(curIndex[friend1] - curIndex[friend2]) - 1;

            return isCorrect(interval, op, value);
        }

        public int getFriendNum(char friend) {
            for(int i = 0; i < MAX_FRIEND; i++) {
                if(kakaoFriends[i] == friend){
                    return i;
                }
            }
            return -1;
        }

        public boolean isCorrect(int interval, char op, int value) {
            if(op == '>') {
                return interval > value;
            } else if(op == '<') {
                return interval < value;
            } else {
                return interval == value;
            }
        }
    }
}
