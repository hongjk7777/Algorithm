import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_2109 {
    private static int size;
    private static Offer[] offers;
    private static final PriorityQueue<Integer> pay = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        getInput();
        Arrays.sort(offers);
        int max = getMaxPay();
        System.out.println(max);
    }
    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        offers = new Offer[size];

        for (int i = 0; i < size; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int pay = Integer.parseInt(tokenizer.nextToken());
            int due = Integer.parseInt(tokenizer.nextToken());
            offers[i] = new Offer(pay, due);
        }
    }

    //역순으로 찾는다.
    private static int getMaxPay() {
        int maxPay = 0;
        int curDay = getLastDay();
        int pointer = 0;

        while (curDay > 0) {
            while (isAbleOffer(curDay, pointer)) {
                pay.add(offers[pointer].pay);
                pointer++;
            }

            if (!pay.isEmpty()) {
                int nextPay = pay.poll();
                maxPay += nextPay;
            }

            curDay--;
        }

        return maxPay;
    }

    private static boolean isAbleOffer(int curDay, int pointer) {
        return pointer < size && offers[pointer].due >= curDay;
    }

    private static int getLastDay() {
        if (size == 0) {
            return 0;
        } else {
            return offers[0].due;
        }
    }


    private static class Offer implements Comparable<Offer> {
        int due, pay;

        public Offer(int pay, int due) {
            this.pay = pay;
            this.due = due;
        }

        @Override
        public int compareTo(Offer other) {
            return Integer.compare(other.due, this.due);
        }
    }
}
