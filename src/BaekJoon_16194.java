import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16194 {
    private static int n;
    public static int[] minCost;

    public static void main(String[] args) throws IOException {
        getInput();
        calculateDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        minCost = new int[n + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= n; i++) {
            minCost[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void calculateDP() {
        for (int i = 1; i <= n; i++) {
            for (int cardSize = 1; cardSize < n; cardSize++) {
                if (i - cardSize <= 0) {
                    break;
                }

                minCost[i] = Math.min(minCost[i], minCost[i - cardSize] + minCost[cardSize]);
            }
        }
    }

    private static void printAnswer() {
        System.out.println(minCost[n]);
    }
}
