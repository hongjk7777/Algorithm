package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11052 {
    private static int n;
    private static int[] maxCost;

    public static void main(String[] args) throws IOException {
        getInput();
        initializeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        maxCost = new int[n + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= n; i++) {
            maxCost[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void initializeDP() {
        for (int i = 1; i <= n; i++) {
            for (int cardSize = 1; cardSize < n; cardSize++) {
                if (i - cardSize <= 0) {
                    break;
                }

                maxCost[i] = Math.max(maxCost[i], maxCost[i - cardSize] + maxCost[cardSize]);
            }
        }
    }

    private static void printAnswer() {
        System.out.println(maxCost[n]);
    }
}
