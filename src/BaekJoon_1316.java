import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1316 {
    private static int wordAmount;
    private static String[] strArr;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getGroupWordAmount();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        wordAmount = Integer.parseInt(tokenizer.nextToken());
        strArr = new String[wordAmount];

        for (int i = 0; i < wordAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            strArr[i] = tokenizer.nextToken();
        }
    }

    private static int getGroupWordAmount() {
        int amount = 0;

        for (int i = 0; i < wordAmount; i++) {
            if (isGroupWord(strArr[i])) {
                amount++;
            }
        }

        return amount;
    }

    private static boolean isGroupWord(String s) {
        boolean groupWord = true;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            int start = i + 1;
            while (start < s.length() && s.charAt(start) == c) {
                start++;
            }

            for (int j = start; j < s.length(); j++) {
                if (s.charAt(j) == c) {
                    groupWord = false;
                    break;
                }
            }
        }

        return groupWord;
    }
}
