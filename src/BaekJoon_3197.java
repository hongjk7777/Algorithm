/*
아쉬운 점
1. 중간에 하던 걸 저장해서 다시 시작할 생각을 하지 못 함.
2. 조금만 생각해보면 평소대로 bfs를 구현하면
시간초과가 나서 다른 방법이 필요하단 걸 알 수 있었지만 그러지 않음

고쳐야 할 점
1. 변수들 중 직관적이지 않은 이름이 있어서 읽기 힘들어 보임. => 이름 직관적으로
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_3197 {
    private static int mapRow, mapCol;
    private static int[][] map, visited;
    private static int[] dRow = {0, 0, 1, -1};
    private static int[] dCol = {1, -1, 0, 0};
    private static ArrayList<Location> swans = new ArrayList<>();

    //얼음에 인접한 물만 저장하는 큐
    private static Queue<Location> waterQueue = new LinkedList<>();
    // 다음 단계에 bfs 시작할 위치를 저장하는 큐
    private static Queue<Location> nextQueue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getDayToReach();

        System.out.println(answer);
    }


    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapRow][mapCol];
        visited = new int[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String input = tokenizer.nextToken();
            for (int col = 0; col < mapCol; col++) {
                char ice = input.charAt(col);

                if (ice == 'X') {
                    map[row][col] = 1;
                } else {
                    if (ice == 'L') {
                        swans.add(new Location(row, col));
                    }
                    map[row][col] = 0;
                    waterQueue.add(new Location(row, col));
                }
            }
        }
    }

    private static int getDayToReach() {
        int day = 0;

        //얼음 녹이기, bfs 모두 하던 걸 저장하고 다음에 다시 거기서부터 시작한다.
        while (!isReachable()) {
            meltIce();
            day++;
        }
        return day;
    }

    private static boolean isReachable() {
        Location startSwan = swans.get(0);
        Location goalSwan = swans.get(1);
        boolean reachable = false;

        Queue<Location> swanLocation = new LinkedList<>();
        if (nextQueue.isEmpty()) {
            swanLocation.add(startSwan);
        } else {
            swanLocation = nextQueue;
        }

        nextQueue = new LinkedList<>();

        while (!swanLocation.isEmpty()) {
            Location location = swanLocation.poll();
            int row = location.row;
            int col = location.col;
            boolean lastable = false;

            for (int dir = 0; dir < 4; dir++) {
                int tempRow = row + dRow[dir];
                int tempCol = col + dCol[dir];

                if (!isInArea(tempRow, tempCol)) {
                    continue;
                }

                if (map[tempRow][tempCol] == 0 && visited[tempRow][tempCol] == 0) {
                    swanLocation.add(new Location(tempRow, tempCol));
                    visited[tempRow][tempCol] = 1;
                }

                if (map[tempRow][tempCol] == 1) {
                    lastable = true;
                }

                if (tempRow == goalSwan.row && tempCol == goalSwan.col) {
                    reachable = true;
                }
            }

            if (lastable) {
                nextQueue.add(new Location(row, col));
            }
        }

        return reachable;
    }

    private static void meltIce() {
        int size = waterQueue.size();

        for (int i = 0; i < size; i++) {
            Location water = waterQueue.poll();
            int row = water.row;
            int col = water.col;

            for (int dir = 0; dir < 4; dir++) {
                int tempRow = row + dRow[dir];
                int tempCol = col + dCol[dir];

                if (!isInArea(tempRow, tempCol)) {
                    continue;
                }

                if (map[tempRow][tempCol] == 1) {
                    map[tempRow][tempCol] = 0;
                    waterQueue.add(new Location(tempRow, tempCol));
                }
            }
        }
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }

    private static class Location {
        int row, col;
        int dist;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
            this.dist = 0;
        }

    }
}
