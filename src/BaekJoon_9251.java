import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_9251 {
    private static String str1, str2;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        System.out.println(dp[str1.length()][str2.length()]);
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
        for (int str1Len = 1; str1Len <= str1.length(); str1Len++) {
            for (int str2Len = 1; str2Len <= str2.length(); str2Len++) {
                dp[str1Len][str2Len] = dp[str1Len - 1][str2Len - 1];

                if (str1.charAt(str1Len - 1) == str2.charAt(str2Len - 1)) {
                    dp[str1Len][str2Len]++;
                }

                dp[str1Len][str2Len] = Math.max(dp[str1Len][str2Len], dp[str1Len][str2Len - 1]);
                dp[str1Len][str2Len] = Math.max(dp[str1Len][str2Len], dp[str1Len - 1][str2Len]);
            }
        }
    }
}
