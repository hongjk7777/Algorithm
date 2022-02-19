import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_1339 {
    public static final int ALPHABETNUM = 26;
    private static int wordAmount;
    private static final long[] alphabetSum = new long[ALPHABETNUM];
    private static String[] words;

    public static void main(String[] args) throws IOException {
        getInput();
        makeAlphabetSums();
        long answer = getMaxSum();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        wordAmount = Integer.parseInt(tokenizer.nextToken());
        words = new String[wordAmount];

        for (int i = 0; i < wordAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            words[i] = tokenizer.nextToken();
        }
    }

    private static void makeAlphabetSums() {
        for (int i = 0; i < wordAmount; i++) {
            makeAlphabetSum(words[i]);
        }
    }

    private static void makeAlphabetSum(String word) {
        for (int i = 0; i < word.length(); i++) {
            char digit = word.charAt(i);
            int digitValue = digit - 'A';
            alphabetSum[digitValue] += Math.pow(10, word.length() - 1 - i);
        }
    }

    private static long getMaxSum() {
        long sum = 0;
        Arrays.sort(alphabetSum);

        int index = ALPHABETNUM - 1;
        int num = 9;
        while (index >= 0 && alphabetSum[index] > 0) {
            sum += alphabetSum[index] * num;
            index--;
            num--;
        }

        return sum;
    }
}
