import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2003 {
    private static int n, m, answ = 0;
    private static int[] sequence;

    public static void main(String[] args) throws IOException {
        getInput();
        getNumberOfPSumEqualM();
        System.out.println(answ);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        sequence = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void getNumberOfPSumEqualM() {
        for (int i = 0; i < n; i++) {
            long pSum = 0;
            for (int j = i; j < n; j++) {
                pSum += sequence[j];
                if (pSum == m) {
                    answ++;
                }
            }
        }
    }
}
