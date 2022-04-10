import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16917 {
    private static int firedPrice, seasonedPrice, halfPrice, friedNum, seasonedNum;

    public static void main(String[] args) throws IOException {
        getInput();
        int min = getMinCost();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());
        firedPrice = Integer.parseInt(tokenizer.nextToken());
        seasonedPrice = Integer.parseInt(tokenizer.nextToken());
        halfPrice = Integer.parseInt(tokenizer.nextToken());
        friedNum = Integer.parseInt(tokenizer.nextToken());
        seasonedNum = Integer.parseInt(tokenizer.nextToken());
    }

    private static int getMinCost() {
        int minCost = firedPrice * friedNum + seasonedPrice * seasonedNum;

        int max = Math.max(friedNum, seasonedNum);

        for (int half = 0; half <= max; half++) {
            int tempCost = half * 2 * halfPrice;
            if (friedNum - half > 0) {
                tempCost += firedPrice * (friedNum - half);
            }
            if (seasonedNum - half > 0) {
                tempCost += seasonedPrice * (seasonedNum - half);
            }
            minCost = Math.min(minCost, tempCost);
        }


        return minCost;
    }
}
