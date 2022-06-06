package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1305 {
    private static int length;
    private static int[] pi;
    private static String string;

    public static void main(String[] args) throws IOException {
        getInput();
        makePi();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        length = Integer.parseInt(tokenizer.nextToken());
        pi = new int[length];

        tokenizer = new StringTokenizer(reader.readLine());
        string = tokenizer.nextToken();
    }

    private static void makePi() {
        int begin = 1, matched = 0;

        while (begin + matched < length) {
            if (string.charAt(begin + matched) == string.charAt(matched)) {
                matched++;
                pi[begin + matched - 1] = matched;
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
    }

    private static void printAnswer() {
        System.out.println(length - pi[length - 1]);
    }
}
