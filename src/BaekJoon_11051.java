import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11051 {
    private static final int MAX = 1001;
    private static final int MOD = 10007;
    private static int n, k;
    private static final int[][] combDP = new int[MAX][MAX];

    public static void main(String[] args) throws IOException {
        getInput();
        initializeDP();
        System.out.println(comb(n, k));
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
    }

    private static void initializeDP() {
        for (int i = 0; i <= n; i++) {
            combDP[i][0] = 1;
            combDP[i][i] = 1;
        }
    }

    private static int comb(int n, int k) {
        int n_1Ck, n_1Ck_1;

        if (combDP[n][k] == 0) {
            n_1Ck = comb(n - 1, k);
            n_1Ck_1 = comb(n - 1, k - 1);

            return combDP[n][k] = (n_1Ck_1 + n_1Ck) % MOD;
        } else {
            return combDP[n][k];
        }
    }
}
