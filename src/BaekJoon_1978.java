import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1978 {
    private static final int MAX_NUM = 1000;
    private static int numAmount;
    private static int[] num;
    private static boolean[] isNotPrime = new boolean[1001];

    public static void main(String[] args) throws IOException {
        getInput();
        findPrime();
        printPrimeAmount();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        numAmount = Integer.parseInt(tokenizer.nextToken());
        num = new int[numAmount];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numAmount; i++) {
            num[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findPrime() {
        int sqrtN = (int) Math.sqrt(MAX_NUM);
        isNotPrime[1] = true;
        for (int i = 2; i <= sqrtN; i++) {
            if (isNotPrime[i]) {
                continue;
            }

            int temp = i * 2;
            while (temp <= MAX_NUM) {
                if (!isNotPrime[temp]) {
                    isNotPrime[temp] = true;
                }
                temp += i;
            }
        }
    }

    private static void printPrimeAmount() {
        int sum = 0;
        sum = getPrimeAmount(sum);
        System.out.println(sum);
    }

    private static int getPrimeAmount(int sum) {
        for (int i = 0; i < numAmount; i++) {
            if (!isNotPrime[num[i]]) {
                sum += 1;
            }
        }
        return sum;
    }
}
