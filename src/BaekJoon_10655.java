import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_10655 {
    private static final int INT_MAX = 987654321;
    private static int checkPointAmount, maxDist;
    private static ArrayList<Location> checkPoints = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        calculMaxDist();
        int answer = getMinDist();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        checkPointAmount = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < checkPointAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int row = Integer.parseInt(tokenizer.nextToken());
            int col = Integer.parseInt(tokenizer.nextToken());
            checkPoints.add(new Location(row, col));
        }
    }

    private static void calculMaxDist() {
        Location start = checkPoints.get(0);
        int distSum = 0;
        for (int i = 1; i < checkPointAmount; i++) {
            Location toGo = checkPoints.get(i);
            distSum += getDist(start, toGo);
            start = toGo;
        }

        maxDist = distSum;
    }

    private static int getDist(Location start, Location toGo) {
        int startRow = start.row;
        int startCol = start.col;
        int toGoRow = toGo.row;
        int toGoCol = toGo.col;

        return Math.abs(startRow - toGoRow) + Math.abs(startCol - toGoCol);
    }

    private static int getMinDist() {
        int minDist = INT_MAX;
        for (int skipNum = 1; skipNum < checkPointAmount - 1; skipNum++) {
            int tempDist = getDistSum(skipNum);
            if (minDist > tempDist) {
                minDist = tempDist;
            }
        }
        return minDist;
    }

    private static int getDistSum(int skipNum) {
        int skipDist = getDist(checkPoints.get(skipNum - 1), checkPoints.get(skipNum)) +
                getDist(checkPoints.get(skipNum), checkPoints.get(skipNum + 1));
        int newDist = getDist(checkPoints.get(skipNum - 1), checkPoints.get(skipNum + 1));

        return maxDist - skipDist + newDist;
    }


    private static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
