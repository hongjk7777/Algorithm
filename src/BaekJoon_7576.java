import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_7576 {
    private static int mapRow, mapCol;
    private static int[][] map;
    private static final int[] dRow = {0, 0, 1, -1};
    private static final int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        int time = getTimeToRipe();
        if (isAllRiped()) {
            System.out.println(time);
        } else {
            System.out.println(-1);
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static int getTimeToRipe() {
        Queue<Location> queue = new LinkedList<>();
        addRipedTomatoes(queue);
        int time = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Location cur = queue.poll();
                int curRow = cur.row;
                int curCol = cur.col;

                for (int dir = 0; dir < 4; dir++) {
                    int tempRow = curRow + dRow[dir];
                    int tempCol = curCol + dCol[dir];

                    if (!isInArea(tempRow, tempCol)) {
                        continue;
                    }

                    if (map[tempRow][tempCol] == 0) {
                        map[tempRow][tempCol] = 1;
                        queue.add(new Location(tempRow, tempCol));
                    }
                }
            }
            time++;
        }

        return time;
    }

    private static void addRipedTomatoes(Queue<Location> queue) {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (map[row][col] == 1) {
                    queue.add(new Location(row, col));
                }
            }
        }
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }

    private static boolean isAllRiped() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (map[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
