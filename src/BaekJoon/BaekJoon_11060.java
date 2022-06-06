package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11060 {
    private static final int INT_MAX = 987654321;
    private static int size;
    private static int[] maze, dp;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        System.out.println(dp[size - 1]);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        maze = new int[size];
        dp = new int[size];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            maze[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {

        //초기값 설정
        for (int i = 1; i < size; i++) {
            dp[i] = INT_MAX;
        }

        for (int i = 0; i < size - 1; i++) {
            int maxJump = maze[i];
            for (int jump = 1; jump <= maxJump; jump++) {
                if (i + jump < size) {
                    dp[i + jump] = Math.min(dp[i + jump], dp[i] + 1);
                }
            }
        }

        //도달 못할 경우
        if (dp[size - 1] == INT_MAX) {
            dp[size - 1] = -1;
        }
    }
}
