package BaekJoon;/*
아쉬웠던 점
1. knapsack에 대한 이해가 모자라 knapsack을 다시 공부하고 풀었다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_7579 {
    private static int appAmount, neededMemory;
    private static int[] memoryArr, costArr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printMinCost();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        appAmount = Integer.parseInt(tokenizer.nextToken());
        neededMemory = Integer.parseInt(tokenizer.nextToken());
        memoryArr = new int[appAmount];
        costArr = new int[appAmount];
        dp = new int[appAmount + 1][10001];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < appAmount; i++) {
            memoryArr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < appAmount; i++) {
            costArr[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        for (int appNum = 1; appNum <= appAmount; appNum++) {
            for (int cost = 0; cost <= 10000; cost++) {
                if (cost - costArr[appNum - 1] >= 0) {
                    dp[appNum][cost] = Math.max(dp[appNum - 1][cost], dp[appNum - 1][cost - costArr[appNum - 1]] + memoryArr[appNum - 1]);
                } else {
                    dp[appNum][cost] = dp[appNum - 1][cost];
                }
            }
        }
    }


    private static void printMinCost() {
        for (int cost = 0; cost <= 10000; cost++) {
            if (dp[appAmount][cost] >= neededMemory) {
                System.out.println(cost);
                break;
            }
        }
    }
}
