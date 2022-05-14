import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class BaekJoon_11652 {
    private static int size;
    private static long[] cards;
    private static final HashMap<Long, Integer> cardNumHash = new HashMap<>();

    public static void main(String[] args) throws IOException {
        getInput();
        addCardsToHashMap();
        long num = getFrequentNum();
        System.out.println(num);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        cards = new long[size];

        for (int i = 0; i < size; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            long num = Long.parseLong(tokenizer.nextToken());
            cards[i] = num;
        }
    }

    private static void addCardsToHashMap() {
        for (int i = 0; i < size; i++) {
            long num = cards[i];
            if (cardNumHash.containsKey(num)) {
                int oldValue = cardNumHash.get(num);
                cardNumHash.replace(num, oldValue + 1);
            } else {
                cardNumHash.put(num, 1);
            }
        }
    }

    private static long getFrequentNum() {
        int count = 0;
        long frequentNum = Long.MAX_VALUE;
        for (Map.Entry<Long, Integer> entry : cardNumHash.entrySet()) {
            Long key = entry.getKey();
            int value = entry.getValue();

            if (count < value) {
                count = value;
                frequentNum = key;
            } else if (count == value) {
                frequentNum = Math.min(frequentNum, key);
            }
        }
        return frequentNum;
    }
}
