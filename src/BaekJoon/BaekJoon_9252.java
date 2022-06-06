package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_9252 {
    private static String str1, str2;
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
        str1 = tokenizer.nextToken();

        tokenizer = new StringTokenizer(reader.readLine());
        str2 = tokenizer.nextToken();

        dp = new int[str1.length() + 1][str2.length() + 1];
    }

    private static void makeDP() {
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                dp[i][j] = dp[i - 1][j - 1];

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j]++;
                }

                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
            }
        }
    }

    private static void printAnswer() {
        int len = getLCSLength();
        System.out.println(len);

        if (len > 0) {
            printLCS();
        }
    }

    private static int getLCSLength() {
        return dp[str1.length()][str2.length()];
    }

    private static void printLCS() {
        System.out.println(getLCS());
    }

    private static String getLCS() {
        StringBuilder builder = new StringBuilder();

        int curLen = 0;
        int row = str1.length();
        int col = str2.length();

        while (curLen < getLCSLength()) {
            if (dp[row][col] == dp[row - 1][col]) {
                row--;
            } else if (dp[row][col] == dp[row][col - 1]) {
                col--;
            } else {
                row--; col--; curLen++;
                builder.append(str1.charAt(row));
            }
        }
        return builder.reverse().toString();
    }
}
