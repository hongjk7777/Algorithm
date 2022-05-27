import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1701 {
    private static String str;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        getInput();
        findMaxStr();
        System.out.println(max);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        str = tokenizer.nextToken();
    }

    private static void findMaxStr() {
        for (int start = 0; start < str.length(); start++) {
            executeKmp(start);
        }
    }

    private static void executeKmp(int start) {
        String subStr = str.substring(start);
        int[] pi = new int[subStr.length()];

        int begin = 1;
        int matched = 0;

        while (begin + matched < subStr.length()) {
            if (subStr.charAt(matched) == subStr.charAt(begin + matched)) {
                matched++;
                pi[begin + matched - 1] = matched;
                max = Math.max(max, matched);
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
    }
}
