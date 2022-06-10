package Programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Programmers_86052 {

    public static void main(String[] args) {
        new Solution().solution(new String[]{"R","R"});
    }

    static class Solution {
        private static final int DIR_TYPE = 4;
        int mapRow, mapCol;
        char[][] map;
        int[][][] visited;
        ArrayList<Integer> answer = new ArrayList<>();
        private int[] dRow = {0, -1, 0, 1};
        private int[] dCol = {1, 0, -1, 0};

        public int[] solution(String[] grid) {

            makeMap(grid);
            findCycles();

            Collections.sort(answer);
            return answer.stream().mapToInt(i -> i).toArray();
        }

        private void makeMap(String[] grid) {
            mapRow = grid.length;
            mapCol = grid[0].length();
            map = new char[mapRow][mapCol];
            visited = new int[mapRow][mapCol][DIR_TYPE];

            for (int row = 0; row < mapRow; row++) {
                String line = grid[row];

                for (int col = 0; col < mapCol; col++) {
                    map[row][col] = line.charAt(col);
                }
            }
        }

        private void findCycles() {
            for (int row = 0; row < mapRow; row++) {
                for (int col = 0; col < mapCol; col++) {
                    for (int dir = 0; dir < DIR_TYPE; dir++) {
                        findCycle(row, col, dir);
                    }
                }
            }
        }

        private void findCycle(int row, int col, int dir) {
            Queue<Location> routeQueue = new LinkedList<>();
            int count = 0;

            if (visited[row][col][dir] > 0) {
                return;
            }

            while (true) {
                if (visited[row][col][dir] == 0) {
                    routeQueue.add(new Location(row, col, dir));
                    visited[row][col][dir] = count;
                    row = updateRow(row, dir);
                    col = updateCol(col, dir);
                    dir = getNextDir(map[row][col], dir);
                    count++;
                } else if (visited[row][col][dir] > 0) {
                    int cycleSize = count - visited[row][col][dir];
                    answer.add(cycleSize);
                    unMarkVisited(routeQueue, count - cycleSize - 1);
                    break;
                }
            }

        }

        private void unMarkVisited(Queue<Location> routeQueue, int count) {
            for (int i = 0; i < count; i++) {
                Location poll = routeQueue.poll();
                visited[poll.row][poll.col][poll.dir] = 0;
            }
        }

        private int getNextDir(char nodeType, int dir) {
            if (nodeType == 'S') {
                return dir;
            } else if (nodeType == 'L') {
                return getLeftDir(dir);
            } else {
                return getRightDir(dir);
            }
        }

        private int getLeftDir(int dir) {
            int nextDir = (dir - 1) % DIR_TYPE;
            if (nextDir < 0) {
                nextDir += DIR_TYPE;
            }
            return nextDir;
        }

        private int getRightDir(int dir) {
            int nextDir = (dir + 1) % DIR_TYPE;
            if (nextDir < 0) {
                nextDir += DIR_TYPE;
            }
            return nextDir;
        }

        private int updateRow(int row, int dir) {
            int nextRow = (row + dRow[dir]) % mapRow;
            if (nextRow < 0) {
                nextRow += mapRow;
            }

            return nextRow;
        }

        private int updateCol(int col, int dir) {
            int nextCol = (col + dCol[dir]) % mapCol;
            if (nextCol < 0) {
                nextCol += mapCol;
            }

            return nextCol;
        }

        private class Location {
            int row, col, dir;

            public Location(int row, int col, int dir) {
                this.row = row;
                this.col = col;
                this.dir = dir;
            }
        }
    }
}
