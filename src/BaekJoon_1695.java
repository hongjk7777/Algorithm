import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_1695 {
    private static int size;
    private static final ArrayList<Integer> sequence = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        changeSequence2Palindrome();
        System.out.println(sequence.size() - size);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            sequence.add(Integer.parseInt(tokenizer.nextToken()));
        }
    }

    private static void changeSequence2Palindrome() {
        for (int i = 0; i <= sequence.size() / 2; i++) {
            if (!sequence.get(i).equals(sequence.get(sequence.size() - 1 - i))) {
                sequence.add(sequence.size(), sequence.get(i));
            }
        }
    }
}
