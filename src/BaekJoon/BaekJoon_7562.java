package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_7562 {
    private static int mapSize, startRow, startCol, goalRow, goalCol;
    private static boolean[][] visited;
    private static final int[] dRow = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] dCol = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        int testcaseAmount = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < testcaseAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            mapSize = Integer.parseInt(tokenizer.nextToken());
            visited = new boolean[mapSize][mapSize];

            tokenizer = new StringTokenizer(reader.readLine());
            startRow = Integer.parseInt(tokenizer.nextToken());
            startCol = Integer.parseInt(tokenizer.nextToken());

            tokenizer = new StringTokenizer(reader.readLine());
            goalRow = Integer.parseInt(tokenizer.nextToken());
            goalCol = Integer.parseInt(tokenizer.nextToken());

            int answer = getMinTime();
            System.out.println(answer);
        }
    }

    private static int getMinTime() {
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(startRow, startCol));
        visited[startRow][startCol] = true;
        int time = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Location poll = queue.poll();
                int curRow = poll.row;
                int curCol = poll.col;

                if (isGoal(curRow, curCol)) {
                    return time;
                }

                for (int dir = 0; dir < 8; dir++) {
                    int tempRow = curRow + dRow[dir];
                    int tempCol = curCol + dCol[dir];

                    if (!isInArea(tempRow, tempCol)) {
                        continue;
                    }

                    if (!visited[tempRow][tempCol]) {
                        visited[tempRow][tempCol] = true;
                        queue.add(new Location(tempRow, tempCol));
                    }
                }
            }
            time++;
        }

        return time;
    }

    private static boolean isGoal(int row, int col) {
        return row == goalRow && col == goalCol;
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }

    private static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
