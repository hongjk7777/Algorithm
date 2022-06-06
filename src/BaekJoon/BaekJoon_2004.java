package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_2004 {
    private static final int INT_MAX = 987654321;
    private static int teamSize;
    private static int[] codePerform;

    public static void main(String[] args) throws IOException {
        getInput();
        Arrays.sort(codePerform);
        int min = getMinPerform();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        teamSize = Integer.parseInt(tokenizer.nextToken());
        codePerform = new int[2 * teamSize];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < 2 * teamSize; i++) {
            codePerform[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int getMinPerform() {
        int min = INT_MAX;

        for (int i = 0; i < teamSize; i++) {
            min = Math.min(min, codePerform[i] + codePerform[2 * teamSize - 1 - i]);
        }

        return min;
    }
}
