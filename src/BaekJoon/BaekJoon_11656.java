package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BaekJoon_11656 {
    private static String inputString;
    private static String[] suffixes;
    private static int[] group;
    private static Integer[] permutation;

    public static void main(String[] args) throws IOException {
        getInput();
        addSuffixes();
        sortSuffixes();
        printSuffixes();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        inputString = tokenizer.nextToken();
        suffixes = new String[inputString.length()];
        group = new int[inputString.length() + 1];
        permutation = new Integer[inputString.length()];
    }

    private static void addSuffixes() {
        for (int i = 0; i < inputString.length(); i++) {
            suffixes[i] = inputString.substring(i);
        }
    }

    private static void sortSuffixes() {
        int length = inputString.length();

        for (int i = 0; i < length; i++) {
            group[i] = inputString.charAt(i);
            permutation[i] = i;
        }
        group[length] = -1;


        int t = 1;
        while (t <= length) {
            int term = t;
            Arrays.sort(permutation, (i, j) -> compareGroup(i, j, term));


            int[] newGroup = new int[length + 1];
            newGroup[permutation[0]] = 1;
            newGroup[length] = -1;
            for (int i = 1; i < length; i++) {
                if (compareGroup(permutation[i], permutation[i - 1], t) > 0) {
                    newGroup[permutation[i]] = newGroup[permutation[i - 1]] + 1;
                } else {
                    newGroup[permutation[i]] = newGroup[permutation[i - 1]];
                }

            }

            group = newGroup;
            t *= 2;

        }
    }

    private static int compareGroup(int i, int j, int t) {
        if (group[i] > group[j] || (group[i] == group[j] && group[i + t] > group[j + t])) {
            return 1;
        } else if (group[i] == group[j] && group[i + t] == group[j + t]) {
            return 0;
        } else {
            return -1;
        }
    }

    private static void printSuffixes() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            builder.append(suffixes[permutation[i]]).append("\n");
        }

        System.out.println(builder);
    }
}
