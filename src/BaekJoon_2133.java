/*
아쉬운 점
1. 접근 자체는 완벽했지만 경우의 수를 나누는 경우에서 내가 어림짐작해서 풀어
놓친 경우의 수가 생김
ㄴ 앞으로는 진짜 많은 경우가 아니면 하나씩 해보면서 생각해보자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2133 {
    private static int n;
    private static int[] dp;

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
        dp = new int[n + 1];
    }

    private static void makeDP() {
        dp[0] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] += 3 * dp[i - 2];

            int prev = i - 4;
            while (prev >= 0) {
                dp[i] += 2 * dp[prev];
                prev -= 2;
            }
        }
    }

    private static void printAnswer() {
        System.out.println(dp[n]);
    }
}
