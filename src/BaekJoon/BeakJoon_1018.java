package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BeakJoon_1018 {
    private static final int INT_MAX = 987654321;
    private static int mapRow, mapCol;
    private static char[][] map;
    private static char[][] chessMap =
            {
                    {'W', 'B','W', 'B','W', 'B','W', 'B'},
                    {'B', 'W','B', 'W','B', 'W','B', 'W'},
                    {'W', 'B','W', 'B','W', 'B','W', 'B'},
                    {'B', 'W','B', 'W','B', 'W','B', 'W'},
                    {'W', 'B','W', 'B','W', 'B','W', 'B'},
                    {'B', 'W','B', 'W','B', 'W','B', 'W'},
                    {'W', 'B','W', 'B','W', 'B','W', 'B'},
                    {'B', 'W','B', 'W','B', 'W','B', 'W'}
            };
    private static char[][] otherChessMap =
            {
                    {'B', 'W','B', 'W','B', 'W','B', 'W'},
                    {'W', 'B','W', 'B','W', 'B','W', 'B'},
                    {'B', 'W','B', 'W','B', 'W','B', 'W'},
                    {'W', 'B','W', 'B','W', 'B','W', 'B'},
                    {'B', 'W','B', 'W','B', 'W','B', 'W'},
                    {'W', 'B','W', 'B','W', 'B','W', 'B'},
                    {'B', 'W','B', 'W','B', 'W','B', 'W'},
                    {'W', 'B','W', 'B','W', 'B','W', 'B'}
            };

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = findMinPaintAmount();
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
                map[row][col] = line.charAt(col);
            }
        }
    }

    private static int findMinPaintAmount() {
        int minDiff = INT_MAX;
        for (int row = 0; row < mapRow - 7; row++) {
            for (int col = 0; col < mapCol - 7; col++) {
                int diff = getDiffAmount(row, col);
                if (diff < minDiff) {
                    minDiff = diff;
                }
            }
        }

        return minDiff;
    }

    private static int getDiffAmount(int row, int col) {
        int diff = 0;
        int otherDiff = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (map[row + i][col + j] != chessMap[i][j]) {
                    diff++;
                }
            }
        }


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (map[row + i][col + j] != otherChessMap[i][j]) {
                    otherDiff++;
                }
            }
        }

        return Math.min(diff, otherDiff);
    }
}
