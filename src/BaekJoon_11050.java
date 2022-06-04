import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11050 {
    private static int n, k;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = comb(n, k);
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
    }

    private static int comb(int n, int k) {
        int ret = 1;

        for (int i = 1; i <= n; i++) {
            ret *= i;
        }

        for (int i = 1; i <= k; i++) {
            ret /= i;
        }

        for (int i = 1; i <= n - k; i++) {
            ret /= i;
        }

        return ret;
    }
}
