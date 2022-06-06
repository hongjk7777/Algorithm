package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_15658 {
    private static final int MAX_INT = 1000000001, MIN_INT = -1000000001;
    private static final int PLUS = 0, MINUS = 1, MULTIPLY = 2, DIVIDE = 3;
    private static int numAmount, min = MAX_INT, max = MIN_INT;
    private static int[] sequence, symbol = new int[4];

    public static void main(String[] args) throws IOException {
        getInput();
        findMaxMin(1, sequence[0]);
        printMaxMin();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        numAmount = Integer.parseInt(tokenizer.nextToken());
        sequence = new int[numAmount];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numAmount; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < 4; i++) {
            symbol[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findMaxMin(int depth, int sum) {
        if (depth == numAmount) {
            updateMax(sum);
            updateMin(sum);
            return;
        }

        for (int symbolNum = 0; symbolNum < 4; symbolNum++) {
            if (symbol[symbolNum] > 0) {
                symbol[symbolNum]--;
                int nextSum = calculate(sum, symbolNum, sequence[depth]);
                findMaxMin(depth + 1, nextSum);
                symbol[symbolNum]++;
            }
        }
    }

    private static void updateMax(int temp) {
        max = Math.max(max, temp);
    }

    private static void updateMin(int temp) {
        min = Math.min(min, temp);
    }

    private static int calculate(int sum, int symbolNum, int value) {
        int ret = 0;
        if (symbolNum == PLUS){
            ret = sum + value;
        } else if (symbolNum == MINUS) {
            ret = sum - value;
        } else if (symbolNum == MULTIPLY) {
            ret = sum * value;
        } else if (symbolNum == DIVIDE) {
            ret = sum / value;
        }

        return ret;
    }

    private static void printMaxMin() {
        System.out.println(max);
        System.out.println(min);
    }
}
