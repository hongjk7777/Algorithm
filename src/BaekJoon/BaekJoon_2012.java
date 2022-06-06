package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_2012 {
    private static int size;
    private static int[] guessedScore;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getMinComplain();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        guessedScore = new int[size];

        for (int i = 0; i < size; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            guessedScore[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int getMinComplain() {
        int complain = 0;
        int[] sortedArr = new int[size];

        System.arraycopy(guessedScore, 0, sortedArr, 0, size);
        Arrays.sort(sortedArr);

        for (int i = 0; i < size; i++) {
            complain += Math.abs(i + 1 - sortedArr[i]);
        }

        return complain;
    }
}
