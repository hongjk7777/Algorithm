package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class BaekJoon_1790 {
    private static long n, k;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getNumber();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Long.parseLong(tokenizer.nextToken());
        k = Long.parseLong(tokenizer.nextToken());
    }

    private static int getNumber() {
        int ret = -1;
        for (int size = 1; size <= 9; size++) {
            int numberAmount = 9 * (int) Math.pow(10, size - 1);
            long totalSize = (long) size * numberAmount;

            if (k - totalSize > 0) {
                k -= totalSize;
                continue;
            }

            long quotient = (k - 1) / size;
            int remainder = (int) ((k - 1) % size);

            long number = (int) Math.pow(10, size - 1) + quotient;
            if (n < number) {
                break;
            }

            char c = Long.toString(number).charAt(remainder);
            ret = Character.getNumericValue(c);
            break;
        }
        return ret;
    }
}
