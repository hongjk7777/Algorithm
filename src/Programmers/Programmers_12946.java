package Programmers;

public class Programmers_12946 {
    public static void main(String[] args) {
        new Solution().solution(2);
    }

    static class Solution {
        int[][] moves;
        int count = 0;

        public int[][] solution(int n) {
            moves = new int[getArrSize(n)][2];
            addMoves(n, 0, 2);
            return moves;
        }

        private int getArrSize(int n) {
            return (int) (Math.pow(2, n) - 1);
        }

        private void addMoves(int n, int start, int dest) {
            int path = getPath(start, dest);

            if (n > 1) {
                addMoves(n - 1, start, path);
            }

            moves[count][0] = start + 1;
            moves[count][1] = dest + 1;
            count++;

            if (n > 1) {
                addMoves(n - 1, path, dest);
            }
        }

        private int getPath(int start, int dest) {
            boolean[] checked = new boolean[3];
            checked[start] = true;
            checked[dest] = true;

            for (int i = 0; i < 3; i++) {
                if (!checked[i]) {
                    return i;
                }
            }
            return 0;
        }
    }
}
