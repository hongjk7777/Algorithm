import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1254 {
    private static final int INT_MAX = 987654321;
    private static String string;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = findMinLen();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        string = tokenizer.nextToken();
    }

    private static int findMinLen() {
        int length = string.length();
        int halfPalinLen = length / 2;
        int min  = INT_MAX;

        for (int i = length; i <= 2 * length; i++) {
            int temp = getMinPalin(i);
            if (temp < min) {
                min = temp;
            }
        }

        return min;
    }


    private static int getMinPalin(int palinLen) {
        int halfLen = (palinLen - 1) / 2;
        int ret = palinLen;
        for (int i = 0; i <= halfLen; i++) {
            if (palinLen - 1 - i >= string.length()) {
                continue;
            }
            if (string.charAt(i) != string.charAt(palinLen - 1 - i)) {
                ret = INT_MAX;
                break;
            }
        }
        return ret;
    }
}
