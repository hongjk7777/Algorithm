import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] pay;

    public static void main(String[] args) throws IOException {
        getInput();
        long maxPay = getMaxPay();
        System.out.println(maxPay);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        pay = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            pay[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static long getMaxPay() {
        long maxPay = 0;
        long curPay = 0;

        for (int i = 0; i < m; i++) {
            curPay += pay[i];
        }
        maxPay = curPay;

        for (int i = m; i < n; i++) {
            curPay -= pay[i - m];
            curPay += pay[i];

            maxPay = Math.max(maxPay, curPay);
        }

        return maxPay;
    }
}
