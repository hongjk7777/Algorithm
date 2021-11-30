import java.util.*;

public class BaekJoon_14502 {

    private static int row, col, max = 0;
    private static int[][] map = new int[8][8];
    private static int[][] visited = new int[8][8];
    private static int[] dRow = {1, -1, 0, 0};
    private static int[] dCol = {0, 0, 1, -1};
    private static ArrayList<Virus> virusList = new ArrayList<>();

    public static void main(String[] args) {
        getInput();
        dfs(3);
        System.out.println(max);
    }


    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        row = scanner.nextInt();
        col = scanner.nextInt();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int input = scanner.nextInt();
                if (input == 2) {
                    virusList.add(new Virus(i, j));
                    visited[i][j] = 1;
                }
                map[i][j] = input;
            }
        }
    }

    private static void dfs(int count) {

        if (count == 0) {
            int safeArea = bfsSafeArea();
            if (max < safeArea) {
                max = safeArea;
            }
            reset();
            return;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(count - 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static int bfsSafeArea() {
        int[][] tempMap = new int[8][8];

        copyMapTo(tempMap);

        Queue<Virus> virusQueue = new LinkedList<>(virusList);

        while (!virusQueue.isEmpty()) {
            Virus virus = virusQueue.poll();
            int row = virus.row;
            int col = virus.col;

            for (int i = 0; i < 4; i++) {
                int tempRow = row + dRow[i];
                int tempCol = col + dCol[i];

                if (isInArea(tempRow, tempCol) && visited[tempRow][tempCol] != 1 && tempMap[tempRow][tempCol] == 0) {
                    virusQueue.add(new Virus(tempRow, tempCol));
                    visited[tempRow][tempCol] = 1;
                    tempMap[tempRow][tempCol] = 2;
                }
            }
        }

        return countSafeArea(tempMap);
    }

    private static void copyMapTo(int[][] tempMap) {
        for (int i = 0; i < 8; i++) {
            System.arraycopy(map[i], 0, tempMap[i], 0, 8);
        }
    }

    private static int countSafeArea(int[][] tempMap) {
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (tempMap[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isInArea(int tempRow, int tempCol) {
        return (0 <= tempRow && tempRow < row && 0 <= tempCol && tempCol < col);
    }


    private static void reset() {
        for (int i = 0; i < 8; i++) {
            Arrays.fill(visited[i], 0);
        }

        for (Virus virus : virusList) {
            visited[virus.row][virus.col] = 1;
        }
    }

    private static class Virus {
        private int row, col;

        public Virus(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
