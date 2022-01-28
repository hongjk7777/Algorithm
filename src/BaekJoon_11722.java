/*
아쉬운 점
1. 쉬운 문제로 테스트를 제대로 안 함
ㄴ 쉬워도 최소한 금방 생각나는 예외는 해보자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11722 {
    private static final int INT_MAX = 987654321;
    private static int n;
    private static int[] input, dp;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        getAndPrintAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        input = new int[n + 1];
        dp = new int[n + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= n; i++) {
            input[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        initialize();

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (input[i] < input[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
    }

    private static void initialize() {
        input[0] = INT_MAX;
    }

    private static void getAndPrintAnswer() {
        int max = getMax();
        System.out.println(max);
    }

    private static int getMax() {
        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
