import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_17425 {
    private static int testcaseAmount;
    private static int[] n;
    private static long[] dp = new long[1000001];
    private static long[] divisor = new long[1000001];

    public static void main(String[] args) throws IOException {
        getInput();
        initialize();
        printGNs();
    }


    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        testcaseAmount = Integer.parseInt(tokenizer.nextToken());
        n = new int[testcaseAmount];

        for (int i = 0; i < testcaseAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int input = Integer.parseInt(tokenizer.nextToken());
            n[i] = input;
        }

    }

    private static void initialize() {
        initializeDivisor();
        initializeDP();
    }

    private static void initializeDivisor() {
        for (int i = 2; i <= 1000000; i++) {
            int temp = i;
            while (temp <= 1000000) {
                divisor[temp] += i;
                temp += i;
            }
        }
    }

    private static void initializeDP() {
        for (int i = 1; i <= 1000000; i++) {
            dp[i] = dp[i - 1] + divisor[i] + 1;
        }
    }

    private static void printGNs() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < testcaseAmount; i++) {
            builder.append(dp[n[i]]).append("\n");
        }
        System.out.println(builder);
    }
}
