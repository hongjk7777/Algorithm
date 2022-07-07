package Programmers;
import java.lang.StringBuilder;

public class Programmers_70129 {
    public static void main(String[] args) {
        new Solution().solution("");
    }

    static class Solution {
        private int removed0 = 0;

        public int[] solution(String s) {
            int changeCount = 0;

            while(s.length() > 1){
                s = getBinaryChange(s);
                changeCount++;
            }

            return new int[]{changeCount, removed0};
        }

        private String getBinaryChange(String s) {
            StringBuilder builder = new StringBuilder();

            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '1'){
                    builder.append('1');
                } else {
                    removed0++;
                }
            }

            return getBinaryStr(builder.toString().length());
        }

        private String getBinaryStr(int length) {
            StringBuilder builder = new StringBuilder();

            while (length > 0){
                if((length & 1) == 1) {
                    builder.append("1");
                } else {
                    builder.append("0");
                }
                length /= 2;
            }

            return builder.reverse().toString();
        }
    }
}
