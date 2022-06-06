package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_1715 {
    private static int size;
    private static final PriorityQueue<Long> cards = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        getInput();
        long min = getMinComp();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < size; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            cards.add(Long.parseLong(tokenizer.nextToken()));
        }
    }

    private static long getMinComp() {
        long count = 0;

        for (int i = 0; i < size - 1; i++) {
            Long minCard1 = cards.poll();
            Long minCard2 = cards.poll();

            long sum = minCard1 + minCard2;
            count += sum;

            cards.add(sum);
        }

        return count;
    }
}
