package BaekJoon;/*
아쉬웠던 점
1. max의 초기값을 잘못 설정해 틀림
ㄴ 앞으로는 초기값을 설정할 때 음수가 나올 수 있는지도 확인해보다
ㄴ 아니면 그냥 무조건 -987654321로 초기화하는 것도 좋을 듯!
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_18290 {
    private static final int MIN_INT = -987654321;
    private static int n, m, k, max = MIN_INT;
    private static int[][] map;
    private static boolean[][] checked;
    private static int[] dRow = {0, 0, 1, -1};
    private static int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        findMaxSum();
        System.out.println(max);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        map = new int[n][m];
        checked = new boolean[n][m];

        for (int row = 0; row < n; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < m; col++) {
                map[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void findMaxSum() {
        dfs(0, 0);
    }

    private static void dfs(int sum, int checkedNum) {
        if (checkedNum == k) {
            if (sum > max) {
                max = sum;
            }
            return;
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (checked[row][col]) {
                    continue;
                }

                if (!isAdjacent(row, col)) {
                    int temp = map[row][col];
                    checked[row][col] = true;
                    dfs(sum + temp, checkedNum + 1);
                    checked[row][col] = false;
                }
            }
        }
    }

    private static boolean isAdjacent(int row, int col) {
        boolean adjacent = false;
        for (int dir = 0; dir < 4; dir++) {
            int tempRow = row + dRow[dir];
            int tempCol = col + dCol[dir];

            if (isInArea(tempRow, tempCol)) {
                if (checked[tempRow][tempCol]) {
                    adjacent = true;
                }
            }
        }
        return adjacent;
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < m;
    }
}
