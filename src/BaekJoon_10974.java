import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_10974 {
    private static int n;
    private static ArrayList<Integer> permutation = new ArrayList<>();
    private static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        getInput();
        appendAllPermutations();
        System.out.println(builder);
    }

    private static void appendAllPermutations() {
        if (permutation.size() == n) {
            appendPermutation();
        }

        for (int i = 0; i < n; i++) {
            if (!permutation.contains(i + 1)) {
                permutation.add(i + 1);
                appendAllPermutations();
                permutation.remove(permutation.size() - 1);
            }
        }
    }

    private static void appendPermutation() {
        for (int i = 0; i < n; i++) {
            builder.append(permutation.get(i)).append(" ");
        }
        builder.append("\n");
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
    }
}
