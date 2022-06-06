package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_10973 {
    private static int n;
    private static Integer[] permutation;

    public static void main(String[] args) throws IOException {
        getInput();
        printPrePermu();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        permutation = new Integer[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            permutation[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void printPrePermu() {
        int first;
        for (first = n - 1; first > 0; first--) {
            if (permutation[first - 1] > permutation[first]) {
                break;
            }
        }
        first--;

        if (first == -1) {
            System.out.println(-1);
            return;
        }

        Arrays.sort(permutation, first + 1, n);

        int second;
        for (second = n - 1; second > first; second--) {
            if (permutation[second] < permutation[first]) {
                break;
            }
        }

        swap(permutation, first, second);

        Arrays.sort(permutation, first + 1, n, Collections.reverseOrder());

        printPermu();
    }

    private static void swap(Integer[] permutation, int first, int second) {
        int temp = permutation[first];
        permutation[first] = permutation[second];
        permutation[second] = temp;
    }

    private static void printPermu() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            builder.append(permutation[i]).append(" ");
        }
        System.out.println(builder);
    }
}
