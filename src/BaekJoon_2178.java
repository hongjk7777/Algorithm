import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_2178 {
    private static int n, m;
    private static int[][] map;
    private static boolean[][] visited;
    private static final int[] dRow = {0, 0, 1, -1};
    private static final int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getMinDist();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int row = 0; row < n; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();
            for (int col = 0; col < m; col++) {
                map[row][col] =
                        Integer.parseInt(String.valueOf(line.charAt(col)));
            }
        }
    }

    private static int getMinDist() {
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(0, 0));
        visited[0][0] = true;
        int dist = 1;

        while (!queue.isEmpty()) {
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                Location temp = queue.poll();
                int curRow = temp.row;
                int curCol = temp.col;

                if (isGoal(curRow, curCol)) {
                    return dist;
                }

                for (int dir = 0; dir < 4; dir++) {
                    int nextRow = curRow + dRow[dir];
                    int nextCol = curCol + dCol[dir];

                    if (!isInArea(nextRow, nextCol)) {
                        continue;
                    }

                    if (map[nextRow][nextCol] == 1 &&
                            !visited[nextRow][nextCol]) {
                        visited[nextRow][nextCol] = true;
                        queue.add(new Location(nextRow, nextCol));
                    }
                }
            }
            dist++;
        }

        return dist;
    }

    private static boolean isGoal(int row, int col) {
        return row == (n - 1) && col == (m - 1);
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < m;
    }

    private static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
