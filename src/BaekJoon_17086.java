/*
배운 점
1. 문제를 완전 잘못 읽어서 처음에 틀렸다.
ㄴ 제발 좀 열심히 읽자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_17086 {
    private static final int INT_MAX = 987654321;
    private static int mapRow, mapCol;
    private static final ArrayList<Location> sharksLocation = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getMaxSafeDist();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapCol; col++) {
                int shark = Integer.parseInt(tokenizer.nextToken());
                if (shark == 1) {
                    sharksLocation.add(new Location(row, col));
                }
            }
        }
    }

    private static int getMaxSafeDist() {
        int max = 0;
        int size = sharksLocation.size();

        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                int minDist = INT_MAX;
                for (Location shark : sharksLocation) {
                    int dist = shark.getDist(new Location(row, col));
                    minDist = Math.min(minDist, dist);
                }
                max = Math.max(max, minDist);
            }
        }
        return max;
    }

    private static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getDist(Location other) {
            int rowDiff = Math.abs(row - other.row);
            int colDiff = Math.abs(col - other.col);
            return Math.max(rowDiff, colDiff);
        }
    }
}
