package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_15654 {
    private static int n, m;
    private static int[] input;
    private static StringBuilder builder = new StringBuilder();
    private static ArrayList<Integer> permutations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        sortInput();
        appendAllPermutations(n, m);
        System.out.println(builder);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        input = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void sortInput() {
        Arrays.sort(input);
    }

    private static void appendAllPermutations(int n, int m) {
        if (permutations.size() == m) {
            appendPermutation();
        }

        for (int i = 0; i < n; i++) {
            if (permutations.contains(input[i])) {
                continue;
            }
            permutations.add(input[i]);
            appendAllPermutations(n, m);
            permutations.remove(permutations.size() - 1);
        }

    }

    private static void appendPermutation() {
        for (int i = 0; i < m; i++) {
            builder.append(permutations.get(i)).append(" ");
        }
        builder.append("\n");
    }
}
