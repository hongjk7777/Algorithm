/*
느낀 점: 아직 bfs, dfs가 미숙한 듯
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_1189 {
    private static int mapRow, mapCol, limitDist;
    private static int[][] visited;
    private static char[][] map;
    private static int[] dRow = {0, 0, 1, -1};
    private static int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getWayAmount(1 , mapRow - 1, 0);
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        limitDist = Integer.parseInt(tokenizer.nextToken());
        map = new char[mapRow][mapCol];
        visited = new int[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = line.charAt(col);
            }
        }
    }

    private static int getWayAmount(int dist, int curRow, int curCol) {
        int wayAmount = 0;
        visited[mapRow - 1][0] = 1;

        if (dist == limitDist) {
            if (isGoal(curRow, curCol)) {
                wayAmount++;
            }
            return wayAmount;
        }

        for (int dir = 0; dir < 4; dir++) {
            int tempRow = curRow + dRow[dir];
            int tempCol = curCol + dCol[dir];

            if (isInArea(tempRow, tempCol)) {
                if (map[tempRow][tempCol] == '.' && visited[tempRow][tempCol] == 0) {
                    visited[tempRow][tempCol] = 1;
                    wayAmount += getWayAmount(dist + 1, tempRow, tempCol);
                    visited[tempRow][tempCol] = 0;
                }
            }

        }

        return wayAmount;
    }

    private static boolean isGoal(int row, int col) {
        return row == 0 && col == mapCol - 1;
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }

    private static class Location {
        int row, col, dist;

        public Location(int row, int col, int dist, int[][] visited) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
}
