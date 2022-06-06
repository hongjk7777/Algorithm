package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1759 {
    private static int l, c;
    private static char[] input;
    private static ArrayList<Character> sequence = new ArrayList<>();
    private static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        getInput();
        sortInput();
        appendAllSequences(0);
        System.out.println(builder);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        l = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
        input = new char[c];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < c; i++) {
            input[i] = tokenizer.nextToken().charAt(0);
        }
    }

    private static void sortInput() {
        Arrays.sort(input);
    }

    private static void appendAllSequences(int start) {
        if (sequence.size() == l) {
            if (isCorrectPw()) {
                appendSequence();
            }
        }

        if (start > c) {
            return;
        }

        for (int i = start; i < c; i++) {
            sequence.add(input[i]);
            appendAllSequences(i + 1);
            sequence.remove(sequence.size() - 1);
        }
    }

    private static boolean isCorrectPw() {
        int vowel = 0;
        int consonant = 0;
        for (Character temp : sequence) {
            if (isVowel(temp)) {
                vowel++;
            }
            if (isConsonant(temp)) {
                consonant++;
            }
        }
        return vowel > 0 && consonant > 1;

    }

    private static boolean isVowel(Character c) {
        return c == 'a' || c == 'e' || c == 'i'
                || c == 'o' || c == 'u';
    }

    private static boolean isConsonant(Character c) {
        return !isVowel(c);
    }

    private static void appendSequence() {
        for (int i = 0; i < l; i++) {
            builder.append(sequence.get(i));
        }
        builder.append("\n");
    }

}
