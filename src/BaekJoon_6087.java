import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_6087 {
    private static final int INT_MAX = 987654321;
    private static int mapRow, mapCol;
    private static char[][] map;
    private static Area[][] dp;
    private static ArrayList<Location> laser = new ArrayList<>();
    private static int[] dRow = {0, 0, 1, -1};
    private static int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        initializeDP();
        int minMirror = findMinMirror();
        System.out.println(minMirror);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        mapCol = Integer.parseInt(tokenizer.nextToken());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        map = new char[mapRow][mapCol];
        dp = new Area[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();
            for (int col = 0; col < mapCol; col++) {
                char input = line.charAt(col);
                map[row][col] = input;
                if (input == 'C') {
                    laser.add(new Location(row, col));
                }
            }
        }
    }

    private static void initializeDP() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                dp[row][col] = new Area();
            }
        }
    }

    private static int findMinMirror() {
        //bfs를 통해 최소 거울 개수를 얻는다.
        Location start = laser.get(0);
        int minMirror = 987654321;

        Queue<Laser> laserQueue = new LinkedList<>();
        laserQueue.add(new Laser(start.row, start.col));

        while (!laserQueue.isEmpty()) {
            Laser poll = laserQueue.poll();
            int row = poll.row;
            int col = poll.col;
            int curMirror = poll.mirror;
            int curDir = poll.dir;

            if (isGoal(row, col)) {
                if (curMirror < minMirror) {
                    minMirror = curMirror;
                }
                continue;
            }

            if (curMirror >= minMirror) {
                continue;
            }

            for (int nextDir = 0; nextDir < 4; nextDir++) {
                int tempRow = row + dRow[nextDir];
                int tempCol = col + dCol[nextDir];

                if (isInArea(tempRow, tempCol)) {
                    if (map[tempRow][tempCol] == '*') {
                        continue;
                    }

                    Area area = dp[tempRow][tempCol];

                    int tempMirror = curMirror;
                    if (curDir != nextDir) {
                        tempMirror = curMirror + 1;
                    }

                    if(area.changeMirror(nextDir, tempMirror)) // 이거보다 효율적인 거 있으면 스킵
                    {
                        laserQueue.add(new Laser(tempRow, tempCol, tempMirror, nextDir));
                    }

                }
            }
        }
        return minMirror;
    }

    private static boolean isGoal(int row, int col) {
        Location goal = laser.get(1);
        return row == goal.row && col == goal.col;
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }

    private static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class Laser extends Location {
        int mirror, dir;
        public Laser(int row, int col) {
            super(row, col);
            mirror = -1;
            dir = -1;
        }

        public Laser(int row, int col, int mirror, int dir) {
            super(row, col);
            this.mirror = mirror;
            this.dir = dir;
        }
    }

    private static class Area {
        int[] minMirror = new int[4];   //방향별로 최소값

        public Area() {
            for (int i = 0; i < 4; i++) {
                minMirror[i] = INT_MAX;
            }
        }

        public boolean changeMirror(int dir, int mirrorAmount) {
            boolean change = false;
            if (minMirror[dir] > mirrorAmount) {
                minMirror[dir] = mirrorAmount;
                change = true;
            }

            for (int tempDir = 0; tempDir < 4; tempDir++) {
                if (minMirror[tempDir] > mirrorAmount + 1) {
                    minMirror[tempDir] = mirrorAmount + 1;
                    change = true;
                }
            }
            return change;
        }
    }
}
