import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16943 {
    private static int a, b, size, max = -1, curVal;
    private static final int[] numberCount = new int[10];

    public static void main(String[] args) throws IOException {
        getInput();
        makeNumberCount();
        dfs(0);
        System.out.println(max);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        a = Integer.parseInt(tokenizer.nextToken());
        b = Integer.parseInt(tokenizer.nextToken());
    }

    private static void makeNumberCount() {
        size = 0;

        if (a == 0) {
            size = 1;
        }

        while (a > 0) {
            numberCount[a % 10]++;
            a /= 10;
            size++;
        }
    }

    private static void dfs(int depth) {
        if (depth == size) {
            if (meetConditions()) {
                updateMax();
            }
        }

        for (int num = 0; num < 10; num++) {
            if (numberCount[num] > 0) {
                curVal += num * Math.pow(10, size - depth - 1);
                numberCount[num]--;
                dfs(depth + 1);
                numberCount[num]++;
                curVal -= num * Math.pow(10, size - depth - 1);
            }
        }
    }

    private static boolean meetConditions() {
        return Math.pow(10, size - 1) <= curVal && curVal < b;
    }

    private static void updateMax() {
        max = Math.max(max, curVal);
    }
}
