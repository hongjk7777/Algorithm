import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1952 {
    private static int mapRow, mapCol;
    private static boolean[][] visited;
    private static final int[] dRow = {0, 1, 0, -1};
    private static final int[] dCol = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getChangedNum();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[mapRow][mapCol];
    }

    private static int getChangedNum() {
        int dir = 0;
        int curRow = 0, curCol = 0;
        int change = 0;
        int mapSize = mapRow * mapCol;
        visited[0][0] = true;

        int visitedArea = 1;
        while (visitedArea < mapSize) {
            int nextRow = curRow + dRow[dir];
            int nextCol = curCol + dCol[dir];

            if (isInArea(nextRow, nextCol) && !visited[nextRow][nextCol]) {
                curRow = nextRow;
                curCol = nextCol;
                visitedArea++;
                visited[curRow][curCol] = true;
            } else {
                change++;
                dir = changeDir(dir);
            }
        }

        return change;
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }

    private static int changeDir(int dir) {
        dir++;
        if (dir > 3) {
            dir = 0;
        }
        return dir;
    }
}
