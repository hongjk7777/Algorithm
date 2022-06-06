package BaekJoon;/*
아쉬웠던 점
1. 쉬운 문제라고 너무 예외체크를 안 했음
2. dfs할 때 startNode를 체크를 자주 안 해서 이로 인한 예외나 오류가 자주 생김
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_2667 {
    private static int n;
    private static int[][] map;
    private static boolean[][] visited;
    private static ArrayList<Integer> complexSizeList = new ArrayList<>();
    private static final int[] dRow = {0, 0, 1, -1};
    private static final int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        findComplexes();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        initialize();

        for (int row = 0; row < n; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();
            for (int col = 0; col < n; col++) {
                map[row][col] =
                        Integer.parseInt(String.valueOf(line.charAt(col)));
            }
        }
    }

    private static void initialize() {
        map = new int[n][n];
        visited = new boolean[n][n];
    }

    private static void findComplexes() {
        int complexNum = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (map[row][col] == 1 && !visited[row][col]) {
                    complexSizeList.add(1);
                    visited[row][col] = true;
                    dfs(row, col, complexNum);
                    complexNum++;
                }
            }
        }
        Collections.sort(complexSizeList);
    }

    private static void dfs(int row, int col, int complexNum) {
        for (int dir = 0; dir < 4; dir++) {
            int tempRow = row + dRow[dir];
            int tempCol = col + dCol[dir];

            if (!isInArea(tempRow, tempCol)) {
                continue;
            }

            if (map[tempRow][tempCol] == 1 && !visited[tempRow][tempCol]) {
                visited[tempRow][tempCol] = true;
                addComplexSize(complexNum);
                dfs(tempRow, tempCol, complexNum);
            }
        }
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < n && 0 <= col && col < n;
    }

    private static void addComplexSize(int complexNum) {
        int curSize = complexSizeList.get(complexNum);
        complexSizeList.set(complexNum, curSize + 1);
    }

    private static void printAnswer() {
        System.out.println(complexSizeList.size());
        for (int size : complexSizeList) {
            System.out.println(size);
        }
    }
}
