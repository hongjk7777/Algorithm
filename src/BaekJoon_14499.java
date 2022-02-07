import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_14499 {
    private static int mapRow, mapCol, moveAmount;
    private static Dice dice;
    private static int[] movement;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        getInput();
        moveAndPrintUpsideNum();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        int diceRow = Integer.parseInt(tokenizer.nextToken());
        int diceCol = Integer.parseInt(tokenizer.nextToken());
        moveAmount = Integer.parseInt(tokenizer.nextToken());

        dice = new Dice(diceRow, diceCol);
        map = new int[mapRow][mapCol];
        movement = new int[moveAmount];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < moveAmount; i++) {
            movement[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void moveAndPrintUpsideNum() {
        StringBuilder builder = new StringBuilder();
        for (int num = 0; num < moveAmount; num++) {
            int upside = moveAndGetUpsideNum(num);

            //이동범위가 올바를 경우 upside != -1이다
            if (upside != -1) {
                builder.append(upside).append("\n");
            }
        }

        System.out.println(builder);
    }

    private static int moveAndGetUpsideNum(int num) {
        int dir = movement[num];
        int startRow = dice.row;
        int startCol = dice.col;

        dice.roll(dir);

        if (isNotMove(startRow, startCol)) {
            return -1;
        }

        int curRow = dice.row;
        int curCol = dice.col;

        if (map[curRow][curCol] == 0) {
            map[curRow][curCol] = dice.downside;
        } else {
            dice.downside = map[curRow][curCol];
            map[curRow][curCol] = 0;
        }

        return dice.upside;
    }

    private static boolean isNotMove(int startRow, int startCol) {
        return dice.row == startRow && dice.col == startCol;
    }

    private static class Dice {
        private static final int EAST = 1, WEST = 2, NORTH = 3, SOUTH = 4;
        int eastside = 0, westside = 0, northside = 0,
                southside = 0, upside = 0, downside = 0;
        int row, col;

        public Dice(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void roll(int dir) {
            switch (dir) {
                case EAST: rollToEast();
                    break;
                case WEST: rollToWest();
                    break;
                case NORTH: rollToNorth();
                    break;
                case SOUTH: rollToSouth();
                    break;
            }
        }

        public void rollToEast() {
            int tempEast = eastside;
            int tempWest = westside;
            int tempUp = upside;
            int tempDown = downside;

            if (overAreaLimit(this.row, this.col + 1)) {
                return;
            }
            this.col++;

            eastside = tempUp;
            downside = tempEast;
            westside = tempDown;
            upside = tempWest;
        }

        private boolean overAreaLimit(int row, int col) {
            return 0 > row || row >= mapRow || 0 > col || col >= mapCol;
        }

        public void rollToWest() {
            int tempEast = eastside;
            int tempWest = westside;
            int tempUp = upside;
            int tempDown = downside;

            if (overAreaLimit(this.row, this.col - 1)) {
                return;
            }
            this.col--;

            eastside = tempDown;
            upside = tempEast;
            westside = tempUp;
            downside = tempWest;
        }

        public void rollToSouth() {
            int tempSouth = southside;
            int tempNorth = northside;
            int tempUp = upside;
            int tempDown = downside;

            if (overAreaLimit(this.row + 1, this.col)) {
                return;
            }
            this.row++;

            southside = tempUp;
            downside = tempSouth;
            northside = tempDown;
            upside = tempNorth;
        }

        public void rollToNorth() {
            int tempSouth = southside;
            int tempNorth = northside;
            int tempUp = upside;
            int tempDown = downside;

            if (overAreaLimit(this.row - 1, this.col)) {
                return;
            }
            this.row--;

            southside = tempDown;
            upside = tempSouth;
            northside = tempUp;
            downside = tempNorth;
        }
    }
}
