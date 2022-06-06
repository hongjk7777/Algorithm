package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_12904 {
    private static String s, t;

    public static void main(String[] args) throws IOException {
        getInput();
        if (canChangeToS(t)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        s = tokenizer.nextToken();

        tokenizer = new StringTokenizer(reader.readLine());
        t = tokenizer.nextToken();
    }

    private static boolean canChangeToS(String str) {
        while (str.length() > s.length()) {
            if (str.charAt(str.length() - 1) == 'A') {
                str = str.substring(0, str.length() - 1);
            } else {
                StringBuilder temp = new StringBuilder(str.substring(0, str.length() - 1));
                str = temp.reverse().toString();
            }
        }

        return str.equals(s);
    }
}
