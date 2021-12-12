import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_20057 {

    private static final int LEFT = 0, DOWN = 1, RIGHT=2, UP = 3;
    private static int mapSize, tornadoRow, tornadoCol, tornadoDir, spinNum = 1, totalSand = 0;
    private static int[][] map;
    private static int[] dRow = {0, 1, 0, -1};
    private static int[] dCol = {-1, 0, 1, 0};
    private static int[][][] spreadingSands =
            {
                    //LEFT
                    {       {0,0,2,0,0},
                            {0,10,7,1,0},
                            {5,-1,0,0,0},
                            {0,10,7,1,0},
                            {0,0,2,0,0}},
                    //DOWN
                    {       {0,0,0,0,0},
                            {0,1,0,1,0},
                            {2,7,0,7,2},
                            {0,10,-1,10,0},
                            {0,0,5,0,0}},
                    //RIGHT
                    {       {0,0,2,0,0},
                            {0,1,7,10,0},
                            {0,0,0,-1,5},
                            {0,1,7,10,0},
                            {0,0,2,0,0}},
                    //UP
                    {       {0,0,5,0,0},
                            {0,10,-1,10,0},
                            {2,7,0,7,2},
                            {0,1,0,1,0},
                            {0,0,0,0,0}}
            };

    public static void main(String[] args) throws IOException {
        getInput();
        startTornado();
        int spreadSand = countLeftSand();
        System.out.println(totalSand - spreadSand);
    }



    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapSize][mapSize];
        tornadoRow = mapSize / 2;
        tornadoCol = mapSize / 2;
        tornadoDir = LEFT;

        for (int i = 0; i < mapSize; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < mapSize; j++) {
                int input = Integer.parseInt(tokenizer.nextToken());
                map[i][j] = input;
                totalSand += input;
            }
        }
    }

    private static void startTornado() {
        boolean end = false;

        while (!end) {
            int row = tornadoRow + dRow[tornadoDir];
            int col = tornadoCol + dCol[tornadoDir];

            if (isIncorrectArea(row, col)) {
                changeDir();
                continue;
            } else {
                if (isInArea(row, col)) {
                    tornadoRow = row;
                    tornadoCol = col;
                }
            }
            moveSand();
            if (isEndArea(row, col)) {
                end = true;
            }
        }

    }


    private static boolean isIncorrectArea(int row, int col) {
        int center = mapSize / 2;
        return !(row >= center - spinNum && center + spinNum >= row
                && col >= center - spinNum && center + spinNum >= col);
    }

    private static void changeDir() {
        if (tornadoDir == UP) {
            spinNum++;
        }
        tornadoDir++;
        tornadoDir %= 4;
    }

    private static void moveSand() {
        int row = tornadoRow;
        int col = tornadoCol;
        double sandAmount = map[row][col];
        map[row][col] = 0;
        int spreadSandAmount = 0;

        //모래가 날릴 때 row, col -2~2 범위기 때문에
        //-2로 시작하였고, 배열 index가 음수는 될 수 없어서 i + 2, j + 2로 함
        for (int i = -2; i < 3; i++) {
            for (int j = -2; j < 3; j++) {
                double percent = spreadingSands[tornadoDir][i + 2][j + 2];
                percent /= 100;
                int moveAmount = (int) (percent *sandAmount);
                spreadSandAmount += moveAmount;
                if (isInArea(row + i, col + j)) {
                    map[row + i][col + j] += moveAmount;
                }
            }
        }
        if (isInArea(row + dRow[tornadoDir], col + dCol[tornadoDir])) {
            map[row + dRow[tornadoDir]][col + dCol[tornadoDir]] += sandAmount - spreadSandAmount;
        }
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }

    private static boolean isEndArea(int row, int col) {
        return row == 0 && col == 0;
    }

    private static int countLeftSand() {
        int sum = 0;
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                sum +=map[i][j];
            }
        }
        return sum;
    }
}
