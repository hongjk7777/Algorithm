package BaekJoon;

import java.io.*;
import java.util.StringTokenizer;

public class BaekJoon_1629 {
    private static long a, b, c;

    public static void main(String[] args) throws IOException {
        getInput();
        long answer = getAnswer();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        a = Long.parseLong(tokenizer.nextToken());
        b = Long.parseLong(tokenizer.nextToken());
        c = Long.parseLong(tokenizer.nextToken());
    }

    private static long getAnswer() {
        long ret = 1;

        while (b > 0) {
            if (b % 2 == 1) {
                ret = (ret * a) % c;
                b -= 1;
            } else {
                a = (a * a) % c;
                b >>= 1;
            }
        }

        return ret;
    }
}
