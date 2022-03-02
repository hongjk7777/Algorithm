import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1158 {
    private static int n, k;
    private static boolean[] checked;
    private static final ArrayList<Integer> josephusSequence = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        makeJosephusSequence();
        printJosephusSequence();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());

        checked = new boolean[n];
    }

    private static void makeJosephusSequence() {
        int count = 0;
        int index = 0;

        while (josephusSequence.size() < n) {
            if (!checked[index]) {
                if (count == k - 1) {
                    josephusSequence.add(index + 1);
                    count = 0;
                    checked[index] = true;
                } else {
                    count++;
                }
            }
            index++;
            if (index == n) {
                index = 0;
            }
        }
    }

    private static void printJosephusSequence() {
        StringBuilder builder = new StringBuilder();

        builder.append("<");
        for (Integer num : josephusSequence) {
            builder.append(num);
            if (!num.equals(josephusSequence.get(josephusSequence.size() - 1))) {
                builder.append(", ");
            }
        }
        builder.append(">");

        System.out.println(builder);
    }
}
