import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_10026 {
    private static int mapSize;
    private static int[][] map;
    private static boolean[][] visited;
    private static final int[] dRow = {0, 0, 1, -1};
    private static final int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        System.out.print(getRGBArea() + " ");
        changeG2R();
        System.out.print(getRBArea());
    }


    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapSize][mapSize];

        for (int row = 0; row < mapSize; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = line.charAt(col);
            }
        }
    }

    private static int getRGBArea() {
        int area = 0;

        visited = new boolean[mapSize][mapSize];
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                if (!visited[row][col]) {
                    checkSameArea(new Location(row, col));
                    area++;
                }
            }
        }
        return area;
    }

    private static void checkSameArea(Location curLocation) {
        int startRow = curLocation.row;
        int startCol = curLocation.col;
        int color = map[startRow][startCol];

        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(startRow, startCol));
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            Location current = queue.poll();
            int row = current.row;
            int col = current.col;

            for (int dir = 0; dir < 4; dir++) {
                int nextRow = row + dRow[dir];
                int nextCol = col + dCol[dir];

                if (isNotArea(nextRow, nextCol)) {
                    continue;
                }

                if (!visited[nextRow][nextCol] && map[nextRow][nextCol] == color) {
                    queue.add(new Location(nextRow, nextCol));
                    visited[nextRow][nextCol] = true;
                }
            }
        }
    }

    private static boolean isNotArea(int row, int col) {
        return !isInArea(row, col);
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }

    private static void changeG2R() {
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                if (map[row][col] == 'G') {
                    map[row][col] = 'R';
                }
            }
        }
    }

    private static int getRBArea() {
        int area = 0;
        visited = new boolean[mapSize][mapSize];

        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                if (!visited[row][col]) {
                    checkSameArea(new Location(row, col));
                    area++;
                }
            }
        }
        return area;
    }

    private static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
