package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1748 {
    private static int n;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getDigit();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
    }

    private static int getDigit() {
        int size = 1;
        int sum = 0;

        while (n >= Math.pow(10, size)) {
            int amount = (int) (Math.pow(10, size) - Math.pow(10, size - 1));
            sum += amount * size;
            size++;
        }

        int amount = (int) (n - Math.pow(10, size - 1) + 1);
        sum += amount * size;

        return sum;
    }
}
