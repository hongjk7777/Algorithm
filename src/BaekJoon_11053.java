/*
아쉬운 점
1. 굉장히 간단한 dp 문제였는데 숙련도가 모자라 너무 헤맴
ㄴ dp 문제 더 풀어보자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11053 {
    private static int n;
    private static int[] arr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        arr = new int[n];
        dp = new int[n + 1][1001];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        for (int i = 1; i <= n; i++) {
            int temp = arr[i - 1];
            for (int j = 0; j <= 1000; j++) {
                if (j - temp >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][temp - 1] + 1);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
    }

    private static void printAnswer() {
        System.out.println(dp[n][1000]);
    }
}
