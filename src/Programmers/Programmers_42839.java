package Programmers;

import java.util.Arrays;

public class Programmers_42839 {
    public static void main(String[] args) {
        new Solution().solution("");
    }

    static class Solution {
        private static final int MAX = 10000000;
        private boolean[] isPrime = new boolean[MAX];
        private boolean[] checked = new boolean[MAX];
        private int[] curStr;
        private int answer = 0;

        public int solution(String numbers) {
            Arrays.fill(isPrime, true);
            findPrime();

            curStr = new int[numbers.length()];
            dfs(0, numbers);
            return answer;
        }

        private void findPrime() {
            isPrime[0] = false;
            isPrime[1] = false;

            for(int i = 2; i <= Math.sqrt(MAX); i++) {
                if(!isPrime[i]) {
                    continue;
                }

                for(int j = i*i; j < MAX; j+=i) {
                    isPrime[j] = false;
                }
            }
        }

        private void dfs(int start, String numbers) {
            if(start == numbers.length()) {
                String str = getCurStr(numbers);

                if(str.equals("")){
                    return;
                }

                int num = Integer.parseInt(str);
                if(!checked[num]) {
                    if(isPrime[num]){
                        answer++;
                    }
                    checked[num] = true;
                }

                return;
            }

            dfs(start + 1, numbers);

            for(int i = 0; i < numbers.length(); i++) {
                if(curStr[i] == 0) {
                    curStr[i] = start + 1;
                    dfs(start + 1, numbers);
                    curStr[i] = 0;
                }
            }

        }

        private String getCurStr(String numbers) {
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < numbers.length(); i++){
                if(curStr[i] > 0) {
                    builder.append(numbers.charAt(curStr[i] - 1));
                }
            }

            return builder.toString();
        }


    }
}
