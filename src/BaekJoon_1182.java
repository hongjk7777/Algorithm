import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1182 {
    private static int n, s;
    private static int[] input;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getSubsequenceNum();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        s = Integer.parseInt(tokenizer.nextToken());
        input = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int getSubsequenceNum() {
        int ret = 0;

        for (int sequence = 1; sequence < 1 << n; sequence++) {
            if (getSum(sequence) == s) {
                ret++;
            }
        }
        return ret;
    }

    private static int getSum(int sequence) {
        int sum = 0;

        for (int i = 0; i < n; i++) {
            if ((sequence & (1 << i)) != 0) {
                sum += input[i];
            }
        }

        return sum;
    }
}
