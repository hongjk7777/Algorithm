package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_9376 {
    private static int mapRow, mapCol;
    private static int[][] map;
    private static boolean[][] visited1, visited2;
    private static int[] playerRow;
    private static int[] playerCol;
//    private static OpenedDoor[] openedDoors;
    private static int[] dRow = {0, 0, 1, -1};
    private static int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        int testCaseAmount = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < testCaseAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            mapRow = Integer.parseInt(tokenizer.nextToken());
            mapCol = Integer.parseInt(tokenizer.nextToken());
            map = new int[mapRow][mapCol];
            visited1 = new boolean[mapRow][mapCol];
            visited2 = new boolean[mapRow][mapCol];


            for (int row = 0; row < mapRow; row++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String line = tokenizer.nextToken();

                for (int col = 0; col < mapCol; col++) {
                    map[row][col] = line.charAt(col);
                }
            }

            int min = getMinDoorToJailBreak();
            System.out.println(min);
        }
    }

    private static int getMinDoorToJailBreak() {
        for (int playerNum = 0; playerNum < 2; playerNum++) {
            doJailBreak(playerNum);
        }
        return 0;
    }

    private static void doJailBreak(int player) {
        int startRow = playerRow[player];
        int startCol = playerCol[player];

        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(startRow, startCol, 0));
        visited1[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            Location poll = queue.poll();
            int curRow = poll.row;
            int curCol = poll.col;

            for (int dir = 0; dir < 4; dir++) {
                int nextRow = curRow + dRow[dir];
                int nextCol = curCol + dCol[dir];

                if (isInArea(nextRow, nextCol)) {
                    
                }
            }
        }
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapRow && 0 <= col && col < mapCol;
    }

    private static class Location {
        int row, col, door;

        public Location(int row, int col, int door) {
            this.row = row;
            this.col = col;
            this.door = door;
        }
    }
}
