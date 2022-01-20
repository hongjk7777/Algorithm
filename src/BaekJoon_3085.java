/*
아쉬웠던 점
1. 충분히 생각할 수 있는 예외를 생각 못해 틀림
ㄴ 다음부터는 예외 사항이 생길 수 있다 생각되면 머리로만 생각하는 버릇을 없애자
메모장이나 그림판, 수기로 쓰든지 하자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_3085 {
    private static int mapSize, answer = 0;
    private static char[][] map;
    private static int[] dRow = {0, 0, 1, -1};
    private static int[] dCol = {1, -1, 0, 0};
    private static char[] color = {'C', 'P', 'Z', 'Y'};

    public static void main(String[] args) throws IOException {
        getInput();
        findMaxCandyToEat();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        map = new char[mapSize][mapSize];

        for (int row = 0; row < mapSize; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();

            for (int col = 0; col < mapSize; col++) {
                map[row][col] = line.charAt(col);
            }
        }
    }

    private static void findMaxCandyToEat() {
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                for (int dir = 0; dir < 4; dir++) {
                    if (canSwap(row, col, dir)) {
                        swap(row, col, dir);
                        int temp = getMaxCandy();
                        if (answer < temp) {
                            answer = temp;
                        }
                        swap(row, col, dir);
                    }
                }
            }
        }
    }

    private static boolean canSwap(int row, int col, int dir) {
        int tempRow = row + dRow[dir];
        int tempCol = col + dCol[dir];

        return isInArea(tempRow, tempCol) && !isSameColor(row, col, tempRow, tempCol);
    }

    private static boolean isInArea(int tempRow, int tempCol) {
        return 0 <= tempRow && tempRow < mapSize && 0 <= tempCol && tempCol < mapSize;
    }

    private static boolean isSameColor(int row, int col, int tempRow, int tempCol) {
        return map[row][col] == map[tempRow][tempCol];
    }

    private static void swap(int row, int col, int dir) {
        int tempRow = row + dRow[dir];
        int tempCol = col + dCol[dir];

        char temp = map[row][col];
        map[row][col] = map[tempRow][tempCol];
        map[tempRow][tempCol] = temp;
    }

    private static int getMaxCandy() {
        int max = 0;
        max = getRowMaxCandy(max);
        max = getColMaxCandy(max);

        return max;
    }

    private static int getRowMaxCandy(int max) {
        for (int row = 0; row < mapSize; row++) {
            for (int colorNum = 0; colorNum < 4; colorNum++) {
                int sum = 0;
                boolean start = false;
                for (int col = 0; col < mapSize; col++) {
                    if (map[row][col] == color[colorNum]) {
                        sum++;
                        start = true;
                    } else if (start){
                        max = checkMax(max, sum);
                        sum = 0;
                        start = false;
                    }
                }

                max = checkMax(max, sum);
            }
        }
        return max;
    }

    private static int checkMax(int max, int sum) {
        if (sum > max) {
            max = sum;
        }
        return max;
    }

    private static int getColMaxCandy(int max) {
        for (int col = 0; col < mapSize; col++) {
            for (int colorNum = 0; colorNum < 4; colorNum++) {
                int sum = 0;
                boolean start = false;
                for (int row = 0; row < mapSize; row++) {
                    if (map[row][col] == color[colorNum]) {
                        sum++;
                        start = true;
                    } else if (start){
                        max = checkMax(max, sum);
                        sum = 0;
                        start = false;
                    }
                }

                max = checkMax(max, sum);
            }
        }
        return max;
    }

}
