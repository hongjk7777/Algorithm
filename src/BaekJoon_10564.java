import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_10564 {
    private static final int MAX_SCORE = 500, MAX_PUSH_UP = 5000;
    private static int goalScore;
    private static boolean[][] dp;
    private static ArrayList<Integer> score;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        int testcaseAmount = Integer.parseInt(tokenizer.nextToken());

        for (int num = 0; num < testcaseAmount; num++) {
            tokenizer = new StringTokenizer(reader.readLine());
            goalScore = Integer.parseInt(tokenizer.nextToken());
            int scoreAmount = Integer.parseInt(tokenizer.nextToken());
            dp = new boolean[MAX_SCORE + 1][MAX_PUSH_UP];

            tokenizer = new StringTokenizer(reader.readLine());
            score = new ArrayList<>();
            for (int i = 0; i < scoreAmount; i++) {
                score.add(Integer.parseInt(tokenizer.nextToken()));
            }

            makeDP();
            int max = getMax();
            System.out.println(max);
        }
    }

    private static void makeDP() {
        dp[0][0] = true;

        for (int i = 0; i <= MAX_SCORE; i++) {
            for (int j = 0; j <= MAX_PUSH_UP; j++) {
                for (int score : score) {
                    if (i >= score && j >= i) {
                        if (dp[i - score][j - i]) {
                            dp[i][j] = true;
                        }
                    }
                }
            }
        }
    }

    private static int getMax() {
        int max = -1;
        for (int i = MAX_SCORE; i >= 0; i--) {
            if (dp[i][goalScore]) {
                max = i;
                break;
            }
        }

        return max;
    }
}
