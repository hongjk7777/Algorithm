package Programmers;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_1829 {
    public static void main(String[] args) {
        new Solution().solution(0, 0, new int[][]{});
    }

    static class Solution {
        private int numberOfArea = 0;
        private int maxSizeOfOneArea = 0;
        private int pictureRow, pictureCol;
        private boolean[][] visited;
        private int[] dRow = new int[]{0, 0, 1, -1};
        private int[] dCol = new int[]{1, -1, 0, 0};

        public int[] solution(int m, int n, int[][] picture) {
            pictureRow = picture.length;
            pictureCol = picture[0].length;
            visited = new boolean[pictureRow][pictureCol];
            checkUnpaintableArea(picture);
            findAllArea(picture);
            return getArray(numberOfArea, maxSizeOfOneArea);
        }

        public void checkUnpaintableArea(int[][] picture) {
            for (int row = 0; row < pictureRow; row++) {
                for (int col = 0; col < pictureCol; col++) {
                    if (picture[row][col] == 0) {
                        visited[row][col] = true;
                    }
                }
            }
        }

        public void findAllArea(int[][] picture){
            for (int row = 0; row < pictureRow; row++){
                for (int col = 0; col < pictureCol; col++){
                    if(visited[row][col]){
                        continue;
                    }

                    int color = picture[row][col];
                    int areaSize = getAreaSize(row, col, color, picture);

                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, areaSize);
                }
            }
        }

        public int getAreaSize(int row, int col, int color, int[][] picture){
            int areaSize = 0;

            Queue<Location> queue = new LinkedList<>();
            queue.add(new Location(row, col));
            visited[row][col] = true;
            areaSize++;

            while(!queue.isEmpty()) {
                Location curLocation = queue.poll();
                int curRow = curLocation.row;
                int curCol = curLocation.col;

                for (int dir = 0; dir < 4; dir++) {
                    int tempRow = curRow + dRow[dir];
                    int tempCol = curCol + dCol[dir];

                    if(isAbleArea(tempRow, tempCol, color, picture)) {
                        queue.add(new Location(tempRow, tempCol));
                        visited[tempRow][tempCol] = true;
                        areaSize++;
                    }
                }
            }

            return areaSize;
        }

        public boolean isAbleArea(int row, int col, int color, int[][] picture) {
            return isInArea(row, col) && !visited[row][col] && picture[row][col] == color;
        }

        public boolean isInArea(int row, int col) {
            return 0 <= row && row < pictureRow && 0 <= col && col < pictureCol;
        }

        public int[] getArray(int num1, int num2){
            int[] answer = new int[2];
            answer[0] = numberOfArea;
            answer[1] = maxSizeOfOneArea;

            return answer;
        }

        class Location {
            int row, col;

            public Location(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }
    }
}
