package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_16948 {
    private static int mapSize, startRow, startCol, goalRow, goalCol;
    private static boolean[][] visited;
    private static final int[] dRow = {-2, -2, 0, 0, 2, 2};
    private static final int[] dCol = {-1, 1, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getMinWayToGoal();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[mapSize][mapSize];

        tokenizer = new StringTokenizer(reader.readLine());
        startRow = Integer.parseInt(tokenizer.nextToken());
        startCol = Integer.parseInt(tokenizer.nextToken());
        goalRow = Integer.parseInt(tokenizer.nextToken());
        goalCol = Integer.parseInt(tokenizer.nextToken());
    }

    private static int getMinWayToGoal() {
        int moveCount = 0;
        boolean reached = false;
        Queue<Location> locations = new LinkedList<>();
        locations.add(new Location(startRow, startCol));
        visited[startRow][startCol] = true;

        while (!locations.isEmpty()) {
            int size = locations.size();

            for (int i = 0; i < size; i++) {

                Location poll = locations.poll();
                int curRow = poll.row;
                int curCol = poll.col;

                if (isGoal(curRow, curCol)) {
                    reached = true;
                    locations.clear();
                    break;
                }

                for (int dir = 0; dir < 6; dir++) {
                    int tempRow = curRow + dRow[dir];
                    int tempCol = curCol + dCol[dir];

                    if (!isInMap(tempRow, tempCol) || visited[tempRow][tempCol]) {
                        continue;
                    }

                    visited[tempRow][tempCol] = true;
                    locations.add(new Location(tempRow, tempCol));
                }
            }

            if (!reached) {
                moveCount++;
            }
        }

        if (!reached) {
            moveCount = -1;
        }

        return moveCount;
    }

    private static boolean isInMap(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }

    private static boolean isGoal(int tempRow, int tempCol) {
        return tempRow == goalRow && tempCol == goalCol;
    }

    private static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
