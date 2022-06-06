package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16916 {
    private static String string, subStr;
    private static int[] pi;

    public static void main(String[] args) throws IOException {
        getInput();
        makeArr();
        if (isSubStr()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        string = tokenizer.nextToken();
        tokenizer = new StringTokenizer(reader.readLine());
        subStr = tokenizer.nextToken();

        pi = new int[subStr.length()];
    }

    private static void makeArr() {
        int start = 1, matched = 0;

        while (start + matched < subStr.length()) {
            if (subStr.charAt(start + matched) == subStr.charAt(matched)) {
                matched++;
                pi[start + matched - 1] = matched;
            } else {
                if (matched == 0) {
                    start++;
                } else {
                    start += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
    }

    private static boolean isSubStr() {
        //kmp로 푼다.
        int start = 0, matched = 0;

        while (start + matched < string.length()) {
            if (string.charAt(start + matched) == subStr.charAt(matched)) {
                matched++;
                if (matched == subStr.length()) {
                    return true;
                }
            } else {
                if (matched == 0) {
                    start++;
                } else {
                    start += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }

        return false;
    }
}
