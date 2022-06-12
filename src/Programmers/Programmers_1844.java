package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_1844 {
    public static void main(String[] args) {
        new Solution().solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}});
    }

    static class Solution {
        int mapRow, mapCol;
        boolean[][] visited;
        private int[] dRow = {0, 0, 1, -1};
        private int[] dCol = {1, -1, 0, 0};

        public int solution(int[][] maps) {
            initialize(maps);

            return getMinDistWithBFS(maps);
        }

        private void initialize(int[][] maps) {
            mapRow = maps.length;
            mapCol = maps[0].length;
            visited = new boolean[mapRow][mapCol];
        }

        private int getMinDistWithBFS(int[][] maps) {
            Queue<Location> queue = new LinkedList<>();
            queue.add(new Location(0, 0, 1));
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                Location curLocation = queue.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int nextRow = curLocation.row + dRow[dir];
                    int nextCol = curLocation.col + dCol[dir];
                    int nextDist = curLocation.dist + 1;

                    if (!isAvailable(maps, nextRow, nextCol)) {
                        continue;
                    }

                    if (!visited[nextRow][nextCol]) {
                        if (isGoal(nextRow, nextCol)) {
                            return nextDist;
                        }
                        visited[nextRow][nextCol] = true;
                        queue.add(new Location(nextRow, nextCol, nextDist));
                    }
                }
            }

            return -1;
        }

        private boolean isAvailable(int[][] maps, int row, int col) {
            return isInArea(row, col) && isNotWall(maps, row, col);
        }

        private boolean isInArea(int row, int col) {
            return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
        }

        private boolean isNotWall(int[][] maps, int row, int col) {
            return maps[row][col] != 0;
        }

        private boolean isGoal(int row, int col) {
            return row == mapRow - 1 && col == mapCol - 1;
        }

        private class Location {
            int row, col, dist;

            public Location(int row, int col, int dist) {
                this.row = row;
                this.col = col;
                this.dist = dist;
            }
        }
    }
}
