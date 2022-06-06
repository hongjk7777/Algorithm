package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_16197 {
    public static final int INT_MAX = 987654321;
    private static int mapRow, mapCol, answer = INT_MAX;
    private static char[][] map;
    private static final CoinLocations startLocations = new CoinLocations();
    private static final int[] dRow = {0, 0, 1, -1};
    private static final int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        findMinTime();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        map = new char[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();
            for (int col = 0; col < mapCol; col++) {
                char input = line.charAt(col);
                if (input == 'o') {
                    input = '.';
                    startLocations.add(new Coin(row, col));
                }
                map[row][col] = input;
            }
        }
    }

    private static void findMinTime() {
        Queue<CoinLocations> queue = new LinkedList<>();
        queue.add(startLocations);

        int depth = 0;
        while (depth <= 10) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                CoinLocations poll = queue.poll();
                Coin firstCoin = poll.firstCoin;
                Coin secondCoin = poll.secondCoin;

                if (isOneCoinFallen(firstCoin, secondCoin)) {
                    answer = depth;
                    return;
                }

                if (!isInArea(firstCoin) && !isInArea(secondCoin)) {
                    continue;
                }

                if (depth == 10) {
                    continue;
                }

                for (int dir = 0; dir < 4; dir++) {
                    Coin nextFirstCoin = new Coin(firstCoin);
                    Coin nextSecondCoin = new Coin(secondCoin);
                    nextFirstCoin.move(dir);
                    nextSecondCoin.move(dir);

                    CoinLocations next = new CoinLocations();
                    next.add(nextFirstCoin);
                    next.add(nextSecondCoin);

                    queue.add(next);
                }
            }
            depth++;
        }

        if (answer == INT_MAX) {
            answer = -1;
        }
    }

    private static boolean isOneCoinFallen(Coin firstCoin, Coin secondCoin) {
        return isInArea(firstCoin) && !isInArea(secondCoin) || !isInArea(firstCoin) && isInArea(secondCoin);
    }

    private static boolean isInArea(Coin coin) {
        return 0 <= coin.row && coin.row < mapRow && 0 <= coin.col && coin.col < mapCol;
    }

    private static boolean isWall(int firstCoinRow, int firstCoinCol) {
        return map[firstCoinRow][firstCoinCol] == '#';
    }

    private static class CoinLocations {
        Coin firstCoin, secondCoin;

        public void add(Coin coin) {
            if (firstCoin == null) {
                firstCoin = coin;
            } else {
                secondCoin = coin;
            }
        }
    }

    private static class Coin {
        int row, col;

        public Coin(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Coin(Coin coin) {
            this.row = coin.row;
            this.col = coin.col;
        }

        public void move(int dir) {
            int nextRow = row + dRow[dir];
            int nextCol = col + dCol[dir];

            if (isInArea(new Coin(nextRow, nextCol)) && isWall(nextRow, nextCol)) {
                nextRow = row;
                nextCol = col;
            }
            row = nextRow;
            col = nextCol;
        }
    }

}
