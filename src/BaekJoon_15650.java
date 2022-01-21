import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_15650 {
    private static int n, m;
    private static ArrayList<Integer> combinations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        printAllCombinations(n, m, 1);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
    }

    private static void printAllCombinations(int n, int m, int start) {
        if (combinations.size() == m) {
            printCombination();
            return;
        }

        if (start > n) {
            return;
        }

        for (int i = start; i <= n; i++) {
            combinations.add(i);
            printAllCombinations(n, m, i + 1);
            combinations.remove(combinations.size() - 1);
        }
    }

    private static void printCombination() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < m; i++) {
            builder.append(combinations.get(i)).append(" ");
        }

        System.out.println(builder);
    }
}
