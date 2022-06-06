package BaekJoon;/*
잘한 점
1. int 범위의 한계를 깨닫고 long을 씀

아쉬운 점
1. tsp문제의 동적 계획법이 생각해봐도 생각이 안남
ㄴ 찾아봐야 될듯
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_10971 {
    private static final int INIT_VALUE = -987654321;
    private static int mapSize;
    private static long min = INIT_VALUE;
    private static int[][] map;
    private static ArrayList<Integer> ways = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        checkAllWays();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapSize][mapSize];

        for (int row = 0; row < mapSize; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void checkAllWays() {
        if (ways.size() == mapSize) {
            if (isAbleWay()) {
                long temp = getCost();
                updateMin(temp);
            }
        }

        for (int i = 0; i < mapSize; i++) {
            if (!ways.contains(i)) {
                ways.add(i);
                checkAllWays();
                ways.remove(ways.size() - 1);
            }
        }
    }

    private static boolean isAbleWay() {
        for (int i = 0; i < mapSize - 1; i++) {
            int now = ways.get(i);
            int next = ways.get(i + 1);
            if (map[now][next] == 0) {
                return false;
            }
        }

        int last = ways.get(mapSize - 1);
        int first = ways.get(0);
        return map[last][first] != 0;
    }

    private static long getCost() {
        long sum = 0;
        for (int i = 0; i < mapSize - 1; i++) {
            int now = ways.get(i);
            int next = ways.get(i + 1);
            sum += map[now][next];
        }

        int last = ways.get(mapSize - 1);
        int first = ways.get(0);
        sum += map[last][first];

        return sum;
    }

    private static void updateMin(long temp) {
        if (min == INIT_VALUE) {
            min = temp;
        }

        if (temp < min) {
            min = temp;
        }
    }
}
