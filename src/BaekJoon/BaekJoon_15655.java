package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BaekJoon_15655 {
    private static int n, m;
    private static int[] input;
    private static StringBuilder builder = new StringBuilder();
    private static ArrayList<Integer> combinations = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        sortInput();
        appendAllCombinations(0);
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

    private static void appendAllCombinations(int start) {
        if (combinations.size() == m) {
            appendCombination();
        }

        for (int i = start; i < n; i++) {
            combinations.add(input[i]);
            appendAllCombinations(i + 1);
            combinations.remove(combinations.size() - 1);
        }
    }

    private static void appendCombination() {
        for (int i = 0; i < m; i++) {
            builder.append(combinations.get(i)).append(" ");
        }
        builder.append("\n");
    }
}
