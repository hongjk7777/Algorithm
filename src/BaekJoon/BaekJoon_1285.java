package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1285 {
    private static int size;
    private static int min = Integer.MAX_VALUE;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        getInput();
        findMinTail();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        map = new int[size][size];

        for (int row = 0; row < size; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String line = tokenizer.nextToken();
            for (int col = 0; col < size; col++) {
                map[row][col] = line.charAt(col);
            }
        }
    }

    private static void findMinTail() {
        for (int bit = 0; bit < (1 << size); bit++) {
            int tailAmount = 0;

            reverseRowByBit(bit);

            tailAmount = getTailAmount(tailAmount);

            min = Math.min(min, tailAmount);

            // 원래대로 돌려주기 위해
            reverseRowByBit(bit);
        }
    }

    private static void reverseRowByBit(int bit) {
        int tempBit = bit;
        for (int rowNum = 0; rowNum < size; rowNum++) {
            if ((tempBit & 1) == 1) {
                reverseRow(rowNum);
            }
            tempBit >>= 1;
        }
    }

    private static void reverseRow(int rowNum) {
        for (int col = 0; col < size; col++) {
            reverseCoin(rowNum, col);
        }
    }

    private static void reverseCoin(int row, int col) {
        if (map[row][col] == 'H') {
            map[row][col] = 'T';
        } else {
            map[row][col] = 'H';
        }
    }

    private static int getTailAmount(int tailAmount) {
        for (int colNum = 0; colNum < size; colNum++) {
            int count = getTailInCol(colNum);
            if (count > (size / 2)) {
                count = size - count;
            }
            tailAmount += count;
        }
        return tailAmount;
    }

    private static int getTailInCol(int colNum) {
        int count = 0;
        for (int row = 0; row < size; row++) {
            if (isTail(row, colNum)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isTail(int row, int col) {
        return map[row][col] == 'T';
    }
}
