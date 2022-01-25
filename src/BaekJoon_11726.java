/*
아쉬운 점
1. 문제는 10007로 나눈 나머지를 구하는 것인데 그걸 안 읽고 long값으로 출력해버림
ㄴ 할 줄 아는 문제라고 신나서 문제 읽기를 소홀히 하지 말자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11726 {
    private static int n;
    //사실상 row는 2로 고정되어 있지만 편의상 [2][i]로 표현한다
    private static int[][] dp;

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
        dp = new int[3][n + 1];
    }

    private static void initializeDP() {
        dp[2][0] = 1;
        dp[2][1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[2][i] = (dp[2][i - 2] + dp[2][i - 1]) % 10007;
        }
    }

    private static void printAnswer() {
        System.out.println(dp[2][n]);
    }
}
