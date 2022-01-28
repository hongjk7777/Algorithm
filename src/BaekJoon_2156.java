/*
아쉬운 점
1. 생각이 잘 정리가 되지 않을 때는 코드만 만지는 게 아니라 한 번 써보자
2. 이 문제에서는 dp의 col을 3으로 하는게 더 나았을 듯
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2156 {
    private static int n;
    public static int[] wine;
    //dp[i][j]는 i번 와인까지 왔고 j개의 와인을 연속해서 먹었을 때 최대 값
    public static int[][] dp;

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
        wine = new int[n + 1];
        dp = new int[n + 1][2];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            wine[i + 1] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        dp[1][0] = 0;
        dp[1][1] = wine[1];

        for (int i = 2; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 2][1] + wine[i - 1], dp[i - 1][1]);
            dp[i][0] = Math.max(dp[i][0], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + wine[i];
        }
    }

    private static void printAnswer() {
        int max = Math.max(dp[n][0], dp[n][1]);
        max = Math.max(max, dp[n - 1][1] + wine[n]);
        System.out.println(max);
    }
}
