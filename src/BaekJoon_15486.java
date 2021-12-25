import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_15486 {
    private static int term;
    private static Consulting[] consultings;
    private static int[] maxPayInDay; // maxPayInDay[1]은 0~1일까지의 최대 pay값이다.

    public static void main(String[] args) throws IOException {
        getInput();
        findMaxPay();
        System.out.println(maxPayInDay[term]);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        term = Integer.parseInt(tokenizer.nextToken());
        consultings = new Consulting[term];
        maxPayInDay = new int[term + 1];

        for (int i = 0; i < term; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int time = Integer.parseInt(tokenizer.nextToken());
            int pay = Integer.parseInt(tokenizer.nextToken());

            consultings[i] = new Consulting(time, pay);
        }
    }

    private static void findMaxPay() {
        for (int day = 0; day < term; day++) {
            Consulting consulting = consultings[day];
            int time = consulting.time;
            int pay = consulting.pay;
            int finishDay = day + time;

            maxPayInDay[day + 1] = Math.max(maxPayInDay[day], maxPayInDay[day + 1]);

            if (finishDay <= term) {
                maxPayInDay[finishDay] = Math.max(maxPayInDay[finishDay], maxPayInDay[day] + pay);

            }
        }
    }

    private static class Consulting {
        int time, pay;

        public Consulting(int time, int pay) {
            this.time = time;
            this.pay = pay;
        }
    }
}
