package Programmers;

public class Programmers_42842 {
    public static void main(String[] args) {
        new Solution().solution(0, 0);
    }

    static class Solution {
        public int[] solution(int brown, int yellow) {
            int width = brown + yellow;

            for(int col = 3; col <= width / 3; col++) {
                for(int row = 3; row <= col; row++) {
                    int yellowNum = (row - 2) * (col - 2);
                    int brownNum = row * col - yellowNum;

                    if(brownNum == brown && yellowNum == yellow){
                        return new int[]{col, row};
                    }
                }
            }

            return new int[]{};
        }
    }
}
