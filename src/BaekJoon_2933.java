/*
아쉬웠던 점
1. 코드를 짠 후 바꿔야 되는 부분이 있을 경우에는 내가 바꾸는 게 미칠 수 있는 영향이나
그로 인해 달라져야 할 것들을 면밀히 확인하고 바꾸자.
2. 매번 그렇지만 너무 직접 입력을 안 넣어보고 예제 입력만 넣고 예외 체크를 너무 안함 
다음엔 꼭 직접 해보자
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_2933 {
    private static int mapRow, mapCol, stickAmount, mineralAmount = 0;
    private static int[][] map, visited;
    private static int[] stickHeights;
    private static Cluster floatingCluster;
    private static int[] dRow = {0, 0, 1, -1};
    private static int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        throwSticks();
        printMap();
    }


    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();
            for (int col = 0; col < mapCol; col++) {
                char area = line.charAt(col);
                if (area == '.') {
                    map[row][col] = 0;
                } else {
                    map[row][col] = 1;
                    mineralAmount++;
                }
            }
        }

        tokenizer = new StringTokenizer(reader.readLine());
        stickAmount = Integer.parseInt(tokenizer.nextToken());
        stickHeights = new int[stickAmount];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int num = 0; num < stickAmount; num++) {
            stickHeights[num] = mapRow - Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void throwSticks() {
        floatingCluster = new Cluster();

        for (int stickNum = 0; stickNum < stickAmount; stickNum++) {
            int height = stickHeights[stickNum];
            if (stickNum % 2 == 0) {
                throwStickFromLeft(height);
            } else {
                throwStickFromRight(height);
            }
        }
    }

    private static void throwStickFromLeft(int height) {
        int row = height;
        for (int col = 0; col < mapCol; col++) {
            if (map[row][col] == 1) {
                map[row][col] = 0;
                mineralAmount--;
                for (int dir = 0; dir < 4; dir++) {
                    int tempRow = row + dRow[dir];
                    int tempCol = col + dCol[dir];

                    if (isInArea(tempRow, tempCol)) {

                        if (map[tempRow][tempCol] == 1) {
                            findFloatingCluster(new Mineral(tempRow, tempCol));
                        }

                        if (!floatingCluster.isEmpty()) {
                            dropFloatingCluster();
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }

    private static void findFloatingCluster(Mineral mineral) {
        //bfs를 통해 cluster를 찾고 height != 0 즉, 떠있을때만 저장한다. 
        Queue<Mineral> mineralQueue = new LinkedList<>();
        visited = new int[mapRow][mapCol];

        mineralQueue.add(mineral);
        visited[mineral.row][mineral.col] = 1;
        floatingCluster.addMineral(mineral);

        while (!mineralQueue.isEmpty()) {
            Mineral poll = mineralQueue.poll();
            int row = poll.row;
            int col = poll.col;

            for (int dir = 0; dir < 4; dir++) {
                int tempRow = row + dRow[dir];
                int tempCol = col + dCol[dir];

                if (!isInArea(tempRow, tempCol)) {
                    continue;
                }

                if (map[tempRow][tempCol] == 1 && visited[tempRow][tempCol] == 0) {
                    Mineral nextMineral = new Mineral(tempRow, tempCol);
                    mineralQueue.add(nextMineral);
                    visited[tempRow][tempCol] = 1;
                    floatingCluster.addMineral(nextMineral);
                }
            }
        }
//floatingCluster.size() == mineralAmount ||
        if (floatingCluster.height == 0) {
            floatingCluster.clear();
        }
    }

    private static void dropFloatingCluster() {
        // 움직여야 하는 map에서 지우고 높이 계산 후 떨어 졌을 때 위치를 map에 입력
        int size = floatingCluster.size();

        for (int num = 0; num < size; num++) {
            Mineral mineral = floatingCluster.get(num);
            map[mineral.row][mineral.col] = 0;
        }

        int dropHeight = getHeightToDrop();

        for (int num = 0; num < size; num++) {
            Mineral mineral = floatingCluster.get(num);
            map[mineral.row + dropHeight][mineral.col] = 1;
        }

        floatingCluster.clear();
    }

    private static int getHeightToDrop() {
        int dropHeight = mapRow;
        int size = floatingCluster.size();
        for (int num = 0; num < size; num++) {
            Mineral mineral = floatingCluster.get(num);

            for (int height = 1; height <= dropHeight; height++) {

                if (mineral.row + height == mapRow ||map[mineral.row + height][mineral.col] == 1) {
                    dropHeight = height - 1;
                }
            }
        }

        return dropHeight;
    }

    private static void throwStickFromRight(int height) {
        int row = height;
        for (int col = mapCol - 1; col >= 0; col--) {
            if (map[row][col] == 1) {
                map[row][col] = 0;
                mineralAmount--;
                for (int dir = 0; dir < 4; dir++) {
                    int tempRow = row + dRow[dir];
                    int tempCol = col + dCol[dir];

                    if (isInArea(tempRow, tempCol)) {

                        if (map[tempRow][tempCol] == 1) {
                            findFloatingCluster(new Mineral(tempRow, tempCol));
                        }

                        if (!floatingCluster.isEmpty()) {
                            dropFloatingCluster();
                        }
                    }
                }
                break;
            }
        }
    }

    private static void printMap() {
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                int mineral = map[row][col];
                if (mineral == 1) {
                    System.out.print('x');
                } else {
                    System.out.print('.');
                }
            }
            if (row != mapRow - 1) {
                System.out.println();
            }
        }
    }

    private static class Cluster {
        int height;
        ArrayList<Mineral> minerals;

        public Cluster() {
            minerals = new ArrayList<>();
            height = mapRow;
        }

        public void addMineral(Mineral mineral) {
            minerals.add(mineral);
            updateHeight(mineral);
        }

        private void updateHeight(Mineral mineral) {
            int tempHeight = mapRow - mineral.row - 1;
            if (tempHeight < height) {
                height = tempHeight;
            }
        }

        public boolean isEmpty() {
            return minerals.isEmpty();
        }

        public int size() {
            return minerals.size();
        }

        public void clear() {
            height = mapRow;
            minerals.clear();
        }

        public Mineral get(int index) {
           return minerals.get(index);
        }

    }

    private static class Mineral {
        int row, col;

        public Mineral(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
