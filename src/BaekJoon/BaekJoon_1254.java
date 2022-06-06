package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1254 {
    private static final int INT_MAX = 987654321;
    private static String string;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = findMinLen();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        string = tokenizer.nextToken();
    }

    private static int findMinLen() {
        int length = string.length();
        int min  = INT_MAX;

        for (int palinLen = length; palinLen <= 2 * length; palinLen++) {
            int temp = INT_MAX;
            if (isPalindrome(palinLen)) {
                temp = palinLen;
            }
            if (temp < min) {
                min = temp;
            }
        }

        return min;
    }


    private static boolean isPalindrome(int palinLen) {
        int halfLen = (palinLen - 1) / 2;
        boolean palindrome = true;
        for (int i = 0; i <= halfLen; i++) {
            if (palinLen - 1 - i >= string.length()) {
                continue;
            }
            if (string.charAt(i) != string.charAt(palinLen - 1 - i)) {
                palindrome = false;
                break;
            }
        }
        return palindrome;
    }
}
