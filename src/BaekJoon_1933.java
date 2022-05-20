import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_1933 {
    private static int size;
    private static Building[] buildings;
    private static final HashMap<Integer, Integer> heightMap = new HashMap<>();
    private static final PriorityQueue<Integer> maxHeightQueue =
            new PriorityQueue<>(Collections.reverseOrder());
    private static final StringBuilder answerStr = new StringBuilder();

    public static void main(String[] args) throws IOException {
        getInput();
        Arrays.sort(buildings);
        drawSkyline();
        System.out.println(answerStr);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        buildings = new Building[2 * size];

        for (int i = 0; i < size; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int height = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());

            //두 번으로 나눠 생길 때 + 없어져야 할 때 -로 표시했다.
            buildings[2 * i] = new Building(height, start);
            buildings[2 * i + 1] = new Building(-1 * height, end);
        }
    }

    private static void drawSkyline() {
        int curHeight = 0, lastHeight;

        maxHeightQueue.add(0);
        heightMap.put(0, 1);

        for (int i = 0; i < 2 * size; i++) {
            Building building = buildings[i];
            int height = Math.abs(building.height);
            int index = building.x;
            boolean add = (building.height > 0);

            addBuildings(height, add);

            if (isNextBuildingGetSameX(i)) {
                continue;
            }

            updateMaxHeight();
            lastHeight = curHeight;
            curHeight = maxHeightQueue.peek();

            if (lastHeight != curHeight) {
                appendChangeStr(index, curHeight);
            }
        }
    }

    private static void addBuildings(int height, boolean add) {
        if (add) {
            if (heightMap.containsKey(height)) {
                int count = heightMap.get(height);
                if (count == 0) {
                    heightMap.replace(height, heightMap.get(height) + 1);
                    maxHeightQueue.add(height);
                } else {
                    heightMap.put(height, heightMap.get(height) + 1);
                }
            } else {
                heightMap.put(height, 1);
                maxHeightQueue.add(height);
            }
        } else {
            heightMap.replace(height, heightMap.get(height) - 1);
        }
    }

    private static boolean isNextBuildingGetSameX(int i) {
        return i < 2 * size - 1 && buildings[i].x == buildings[i + 1].x;
    }

    private static void updateMaxHeight() {
        while (true) {
            int curMaxHeight = maxHeightQueue.peek();
            int count = heightMap.get(curMaxHeight);
            if (count == 0) {
                maxHeightQueue.poll();
            } else {
                break;
            }
        }
    }

    private static void appendChangeStr(int index, int height) {
        answerStr.append(index).append(" ").append(height).append(" ");
    }

    private static class Building implements Comparable<Building> {
        int height;
        int x;

        public Building(int height, int x) {
            this.height = height;
            this.x = x;
        }

        @Override
        public int compareTo(Building o) {
            return Integer.compare(this.x, o.x);
        }
    }
}
