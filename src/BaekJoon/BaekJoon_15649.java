package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_15649 {
    private static int n, m;
    private static ArrayList<Integer> permutation = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        printAllPermutations(n, m);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
    }

    private static void printAllPermutations(int n, int m) {
        if (permutation.size() == m) {
            printPermutation();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (permutation.contains(i)) {
                continue;
            }
            permutation.add(i);
            printAllPermutations(n, m);
            permutation.remove(permutation.size() - 1);
        }
    }

    private static void printPermutation() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            builder.append(permutation.get(i)).append(" ");
        }

        System.out.println(builder);
    }
}
