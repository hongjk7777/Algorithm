package Programmers;

import java.util.HashMap;
import java.util.Map;

public class Programmers_49994 {
    public static void main(String[] args) {
        new Solution().solution("LULLLLLLU");
    }

    static class Solution {
        Map<String, Boolean> visitedMap = new HashMap<>();
        int curRow = 0, curCol = 0;

        public int solution(String dirs) {
            for (int i = 0; i < dirs.length(); i++) {
                char dir = dirs.charAt(i);
                move(dir);
            }
            return visitedMap.size();
        }

        private void move(int dir) {
            if (dir == 'L') {
                moveLeft();
            } else if (dir == 'R') {
                moveRight();
            } else if (dir == 'U') {
                moveUp();
            } else {
                moveDown();
            }
        }

        private void moveLeft() {
            if (curRow > -5) {
                checkVisited(curRow, curCol, curRow - 1, curCol);
                curRow--;
            }
        }

        private void moveRight() {
            if (curRow < 5) {
                checkVisited(curRow, curCol, curRow + 1, curCol);
                curRow++;
            }
        }

        private void moveUp() {
            if (curCol < 5) {
                checkVisited(curRow, curCol, curRow, curCol + 1);
                curCol++;
            }
        }

        private void moveDown() {
            if (curCol > -5) {
                checkVisited(curRow, curCol, curRow, curCol - 1);
                curCol--;
            }
        }

        private void checkVisited(int curRow, int curCol, int nextRow, int nextCol) {
            String route1 = String.valueOf(curRow) + curCol +
                    nextRow + nextCol;
            String route2 = String.valueOf(nextRow) + nextCol +
                    curRow + curCol;

            if (!visitedMap.containsKey(route1) && !visitedMap.containsKey(route2)) {
                visitedMap.put(route1, true);
            }
        }
    }
}
