package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_9248 {
    private static String inputString;
    private static int[] group, pi;
    private static Integer[] perm;
    private static final ArrayList<String> suffixes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        addSuffixes();
        sortSuffixes();
        makeLCPArray();
        System.out.println(1);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        inputString = tokenizer.nextToken();
    }

    private static void addSuffixes() {
        for (int i = 0; i < inputString.length(); i++) {
            suffixes.add(inputString.substring(i));
        }
    }

    private static void sortSuffixes() {
        int length = inputString.length();
        group = new int[length + 1];
        perm = new Integer[length];

        for (int i = 0; i < length; i++) {
            group[i] = inputString.charAt(i);
        }
        group[length] = -1;

        for (int i = 0; i < length; i++) {
            perm[i] = i;
        }

        int t = 1;
        while (t < length) {
            int tempT = t;
            Arrays.sort(perm, (i, j) -> permComparator(i, j, tempT));

            int[] newGroup = new int[length + 1];
            newGroup[length] = -1;
            newGroup[perm[0]] = 1;

            for (int i = 1; i < length; i++) {
                if (permComparator(perm[i - 1], perm[i], t) >= 0) {
                    newGroup[perm[i]] = newGroup[perm[i - 1]];
                } else {
                    newGroup[perm[i]] = newGroup[perm[i - 1]] + 1;
                }
            }

            t *= 2;

            group = newGroup;
        }
    }

    private static int permComparator(Integer i, Integer j, int t) {
        if (group[i] < group[j] || (group[i] == group[j] && group[i + t] < group[j + t])) {
            return -1;
        } else if (group[i] == group[j] && group[i + t] == group[j + t]) {
            return 0;
        } else {
            return 1;
        }
    }

    private static void makeLCPArray() {
        //kmp 방식으로 만들자
        StringBuilder builder = new StringBuilder(inputString);
        String reverse = builder.reverse().toString();
        int length = inputString.length();
        pi = new int[length];

        int begin = 1, matched = 0;

        while (begin + matched < length) {
            if (reverse.charAt(begin + matched) == reverse.charAt(matched)) {
                matched++;
                if (begin + matched < length) {
                    pi[begin + matched] = matched;
                }
            } else {
                if (matched == 0) {
                    begin++;
                    continue;
                }

                begin++;
                begin += matched - pi[matched - 1];
                matched = pi[matched - 1];
            }
        }
        System.out.println(1);
    }
}
