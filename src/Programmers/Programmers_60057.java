package Programmers;

public class Programmers_60057 {
    public static void main(String[] args) {
        new Solution().solution("avaba");
    }

    static class Solution {
        public int solution(String s) {
            int answer = Integer.MAX_VALUE;
            for (int len = 1; len <= s.length(); len++) {
                answer = Math.min(answer, getMinLen(s, len));
            }
            return answer;
        }

        private int getMinLen(String s, int size) {
            int len = s.length();
            for (int i = 0; i < s.length(); i+= size) {
                int count = getSameCount(s, size, i);
                if (count > 0) {
                    len = len - count * size + getDigit(count + 1);
                    i += size * count;
                }
            }
            return len;
        }

        private int getSameCount(String s, int size, int index) {
            if (index + size > s.length()) {
                return 0;
            }

            int count = 0;
            String str = s.substring(index, index + size);
            index += size;

            while (index + size <= s.length()) {
                if (str.equals(s.substring(index, index + size))) {
                    count++;
                } else {
                    return count;
                }

                index += size;
            }
            return count;
        }

        private int getDigit(int num) {
            int count = 1;
            num /= 10;
            while (num > 0) {
                count++;
                num /= 10;
            }
            return count;
        }
    }
}
