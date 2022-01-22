import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_15657 {
    private static int n, m;
    private static int[] input;
    private static ArrayList<Integer> sequence = new ArrayList<>();
    private static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        getInput();
        sortInput();
        appendAllSequences(0);
        System.out.println(builder);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        input = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void sortInput() {
        Arrays.sort(input);
    }

    private static void appendAllSequences(int start) {
        if (sequence.size() == m) {
            appendSequence();
            return;
        }

        for (int i = start; i < n; i++) {
            sequence.add(input[i]);
            appendAllSequences(i);
            sequence.remove(sequence.size() - 1);
        }
    }

    private static void appendSequence() {
        for (int i = 0; i < m; i++) {
            builder.append(sequence.get(i)).append(" ");
        }
        builder.append("\n");
    }
}
