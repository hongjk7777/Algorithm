import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1736 {
    private static int mapRow, mapCol, robotAmount = 0;
    private static boolean[][] cleanArea;

    public static void main(String[] args) throws IOException {
        getInput();
        calculateRobotAmount();
        System.out.println(robotAmount);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        cleanArea = new boolean[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapCol; col++) {
                int input = Integer.parseInt(tokenizer.nextToken());
                cleanArea[row][col] = input != 1;
            }
        }
    }

    private static void calculateRobotAmount() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                if (!cleanArea[row][col]) {
                    startClean(row, col);
                    robotAmount++;
                }
            }
        }
    }

    private static void startClean(int curRow, int curCol) {
        cleanArea[curRow][curCol] = true;

        for (int row = curRow; row < mapRow; row++) {
            for (int col = curCol; col < mapCol; col++) {
                if (!cleanArea[row][col]) {
                    cleanArea[row][col] = true;
                    curCol = col;
                }
            }
        }
    }
}
