package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Programmers_67259 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new int[][]{{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}});
    }

    static class Solution {
        private static final int DIR_TYPE = 4;
        int[][][] costDP;
        boolean[][][] visited;
        private int[] dRow = {0, 0, 1, -1};
        private int[] dCol = {1, -1, 0, 0};
        int boardSize;

        public int solution(int[][] board) {
            int answer;
            boardSize = board[0].length;

            visited = new boolean[boardSize][boardSize][DIR_TYPE];
            costDP = new int[boardSize][boardSize][DIR_TYPE];

            fillCostDPWithBFS(board);
            answer = getMinCost();
            return answer;
        }

        private void fillCostDPWithBFS(int[][] board) {
            Queue<Location> locationQueue = new LinkedList<>();
            locationQueue.add(new Location(0, 0, 0));
            for (int dir = 0; dir < 4; dir++) {
                visited[0][0][dir] = true;
            }

            while (!locationQueue.isEmpty()) {
                Location curLocation = locationQueue.poll();

                for (int nextDir = 0; nextDir < DIR_TYPE; nextDir++) {
                    int nextRow = curLocation.row + dRow[nextDir];
                    int nextCol = curLocation.col + dCol[nextDir];
                    int curDir = curLocation.dir;
                    int nextCost = curLocation.cost + 100;

                    if (isNotAvailable(board, nextRow, nextCol)) {
                        continue;
                    }

                    if (isCorner(nextDir, curDir)) {
                        nextCost += 500;
                    }

                    if (visited[nextRow][nextCol][nextDir]) {
                        if (nextCost < costDP[nextRow][nextCol][nextDir]) {
                            costDP[nextRow][nextCol][nextDir] = nextCost;
                            locationQueue.add(new Location(nextRow, nextCol, nextCost, nextDir));
                        }
                    } else {
                        costDP[nextRow][nextCol][nextDir] = nextCost;
                        visited[nextRow][nextCol][nextDir] = true;
                        locationQueue.add(new Location(nextRow, nextCol, nextCost, nextDir));
                    }
                }
            }
        }
        private boolean isNotAvailable(int[][] board, int row, int col) {
            return isOutOfArea(row, col) || isWall(board[row][col]);
        }

        private boolean isCorner(int nextDir, int curDir) {
            return curDir != -1 && curDir != nextDir;
        }

        private boolean isOutOfArea(int row, int col) {
            return row < 0 || boardSize <= row || col < 0 || boardSize <= col;
        }

        private boolean isWall(int i) {
            return i == 1;
        }

        private int getMinCost() {
            int min = Integer.MAX_VALUE;

            for (int dir = 0; dir < DIR_TYPE; dir++) {
                int cost = costDP[boardSize - 1][boardSize - 1][dir];
                if (cost != 0) {
                    min = Math.min(min, cost);
                }
            }

            return min;
        }

        private class Location {
            int row, col, cost, dir;

            public Location(int row, int col, int cost) {
                this.row = row;
                this.col = col;
                this.cost = cost;
                this.dir = -1;
            }

            public Location(int row, int col, int cost, int dir) {
                this.row = row;
                this.col = col;
                this.cost = cost;
                this.dir = dir;
            }
        }
    }
}
