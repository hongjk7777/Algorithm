package Programmers;

import java.util.ArrayList;

public class Programmers_68645 {
    public static void main(String[] args) {
        new Solution().solution(1000);
    }

    static class Solution {
        int mapSize;
        int[][] arr;
        boolean[][] visited;

        public int[] solution(int n) {
            mapSize = n;
            arr = new int[mapSize][mapSize];
            visited = new boolean[mapSize][mapSize];
            makeArr(mapSize);
            return getAnswer();
        }

        private void makeArr(int n) {
            int row = 0, col = 0, count = 1;
            arr[row][col] = count++;
            visited[row][col] = true;

            while (count <= mapSize * (mapSize + 1) / 2){
                while (isAvailable(row + 1, col)) {
                    visited[row + 1][col] = true;
                    arr[++row][col] = count++;
                }
                while (isAvailable(row, col + 1)) {
                    visited[row][col + 1] = true;
                    arr[row][++col] = count++;
                }
                while (isAvailable(row - 1, col - 1)) {
                    visited[row - 1][col - 1] = true;
                    arr[--row][--col] = count++;
                }
            }
        }

        private boolean isAvailable(int row, int col) {
            return isInArea(row) && isInArea(col) && isUnVisited(row, col);
        }

        private boolean isInArea(int loc) {
            return 0 <= loc && loc < mapSize;
        }

        private boolean isUnVisited(int row, int col) {
            return !visited[row][col];
        }

        private int[] getAnswer() {
            ArrayList<Integer> answer = new ArrayList<>();

            for (int row = 0; row < mapSize; row++) {
                for (int col = 0; col <= row; col++) {
                    answer.add(arr[row][col]);
                }
            }

            return answer.stream().mapToInt(i -> i).toArray();
        }

    }
}
