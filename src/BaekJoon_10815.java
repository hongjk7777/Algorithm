import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BaekJoon_10815 {
    private static int n, m;
    private static int[] numberCard, input;
    private static final HashMap<Integer, Boolean> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        getInput();
        addNumberCardToHashMap();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        numberCard = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            numberCard[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        m = Integer.parseInt(tokenizer.nextToken());
        input = new int[m];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; i++) {
            input[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void addNumberCardToHashMap() {
        for (int i = 0; i < n; i++) {
            hashMap.put(numberCard[i], true);
        }
    }

    private static void printAnswer() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (hashMap.containsKey(input[i])) {
                builder.append("1 ");
            } else {
                builder.append("0 ");
            }
        }

        System.out.println(builder);
    }
}
