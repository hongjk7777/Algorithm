import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_15652 {
    private static int n, m;
    private static ArrayList<Integer> sequences = new ArrayList<>();
    private static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        getInput();
        appendAllSequences(n, m, 1);
        System.out.println(builder);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
    }

    private static void appendAllSequences(int n, int m, int start) {
        if (sequences.size() == m) {
            appendSequence();
            return;
        }

        if (start > n) {
            return;
        }

        for (int i = start; i <= n; i++) {
            sequences.add(i);
            appendAllSequences(n, m, i);
            sequences.remove(sequences.size() - 1);
        }
    }

    private static void appendSequence() {
        for (int i = 0; i < m; i++) {
            builder.append(sequences.get(i)).append(" ");
        }
        builder.append("\n");
    }
}
