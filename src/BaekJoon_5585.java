import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_5585 {
    private static int pay;
    private static final int[] changesTypes = {500, 100, 50, 10, 5, 1};

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getChangesAmount();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        pay = Integer.parseInt(tokenizer.nextToken());
    }

    private static int getChangesAmount() {
        int changes = 1000 - pay;
        int amount = 0;

        for (int changesType : changesTypes) {
            while (changes - changesType >= 0) {
                changes -= changesType;
                amount++;
            }
        }

        return amount;
    }
}
