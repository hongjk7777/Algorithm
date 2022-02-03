/*
아쉬운 점
1. 누가봐도 bfs문제였는데 갈피를 잡는 데 시간이 걸림
ㄴ 관련된 다양한 문제를 더 풀어보자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_1261 {
    private static int mapCol, mapRow;
    private static int[][] map;
    private static int[][] dp;
    private static boolean[][] visited;
    private static final int[] dRow = {0, 0, 1, -1};
    private static final int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapRow][mapCol];
        dp = new int[mapRow][mapCol];
        visited = new boolean[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();

            for (int col = 0; col < mapCol; col++) {
                map[row][col] = Integer.parseInt(String.valueOf(line.charAt(col)));
            }
        }
    }

    private static void makeDP() {
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Location curLocation = queue.poll();
            int curRow = curLocation.row;
            int curCol = curLocation.col;
            int crashedWall = dp[curRow][curCol];
            for (int dir = 0; dir < 4; dir++) {
                int tempRow = curRow + dRow[dir];
                int tempCol = curCol + dCol[dir];

                if (overLimit(tempRow, tempCol)) {
                    continue;
                }

                if (!visited[tempRow][tempCol]) {
                    dp[tempRow][tempCol] = crashedWall + map[tempRow][tempCol];
                    visited[tempRow][tempCol] = true;
                    queue.add(new Location(tempRow, tempCol));
                }
                else if (dp[tempRow][tempCol] >
                        crashedWall + map[tempRow][tempCol]) {
                    dp[tempRow][tempCol] = crashedWall + map[tempRow][tempCol];
                    queue.add(new Location(tempRow, tempCol));
                }

            }
        }
    }

    private static boolean overLimit(int row, int col) {
        return !isInArea(row, col);
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }

    private static void printAnswer() {
        System.out.println(dp[mapRow - 1][mapCol - 1]);
    }

    private static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
