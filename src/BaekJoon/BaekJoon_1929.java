package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1929 {
    private static final int MAX_NUM = 1000000;
    private static int min, max;
    private static boolean[] isNotPrime = new boolean[MAX_NUM + 1];

    public static void main(String[] args) throws IOException {
        getInput();
        findPrime();
        printPrimes();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        min = Integer.parseInt(tokenizer.nextToken());
        max = Integer.parseInt(tokenizer.nextToken());
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

    private static void printPrimes() {
        StringBuilder builder = new StringBuilder();

        for (int i = min; i <= max; i++) {
            if (!isNotPrime[i]) {
                builder.append(i).append("\n");
            }
        }

        System.out.println(builder);
    }

}
