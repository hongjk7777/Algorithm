/*
아쉬운 점
1. 굉장히 간단한 문제인데 생각보다 시간이 오래 걸림
ㄴ 순서가 상관없을 땐 정렬하는 걸 생각해보자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_15989 {
    private static final int MAX = 10000;
    private static final int[][] dp = new int[4][MAX + 1];
    private static int testcaseAmount;
    private static int[] input;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        testcaseAmount = Integer.parseInt(tokenizer.nextToken());
        input = new int[testcaseAmount];

        for (int i = 0; i < testcaseAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            input[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        initializeDP();
        for (int i = 4; i <= MAX; i++) {
            dp[1][i] = 1;
            dp[2][i] = dp[2][i - 2] + dp[1][i - 2];
            dp[3][i] = dp[3][i - 3] + dp[2][i - 3] + dp[1][i - 3];
        }
    }

    private static void initializeDP() {
        dp[1][1] = 1;
        dp[1][2] = 1;
        dp[2][2] = 1;
        dp[1][3] = 1;
        dp[2][3] = 1;
        dp[3][3] = 1;
    }

    private static void printAnswer() {
        for (int i = 0; i < testcaseAmount; i++) {
            int answer = dp[1][input[i]] + dp[2][input[i]] + dp[3][input[i]];
            System.out.println(answer);
        }
    }
}
