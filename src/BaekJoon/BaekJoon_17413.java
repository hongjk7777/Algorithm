package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BaekJoon_17413 {
    private static String str;
    private static final StringBuilder builder = new StringBuilder();

    public static void main(String[] args) {
        getInput();
        reverseWords();
        System.out.println(builder);
    }

    private static void getInput(){
        Scanner scanner = new Scanner(System.in);

        str = scanner.nextLine();
    }

    private static void reverseWords() {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '<') {
                word = appendReverseWord(word);

                while (str.charAt(i) != '>') {
                    builder.append(str.charAt(i));
                    i++;
                }
                builder.append(">");
            } else if (str.charAt(i) == ' ') {
                word = appendReverseWord(word);
                builder.append(" ");
            } else {
                word.append(str.charAt(i));
            }
        }

        builder.append(word.reverse());

    }

    private static StringBuilder appendReverseWord(StringBuilder word) {
        builder.append(word.reverse());
        word = new StringBuilder();
        return word;
    }
}
