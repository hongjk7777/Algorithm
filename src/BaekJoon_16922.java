import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16922 {
    private static int size;
    private static boolean[][] checked;
    private static final int[] romaNumber = {1, 5, 10, 50};

    public static void main(String[] args) throws IOException {
        getInput();
        dfs(0, 0);
        int answer = getAnswer();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        checked = new boolean[size][1001];
    }

    private static void dfs(int depth, int value) {
        if (depth == size) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextVal = value + romaNumber[i];
            if (!checked[depth][nextVal]) {
                checked[depth][nextVal] = true;
                dfs(depth + 1, nextVal);
            }
        }
    }

    private static int getAnswer() {
        int count = 0;
        for (int i = 1; i <= 1000; i++) {
            if (checked[size - 1][i]) {
                count++;
            }
        }
        return count;
    }
}
