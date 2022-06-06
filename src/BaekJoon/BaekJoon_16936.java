package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_16936 {
    private static int size;
    private static long[] answer;
    private static boolean findAnswer = false;
    private static final ArrayList<Long> sequence = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        Collections.sort(sequence);
        findAndPrintCorrectSeq();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        answer = new long[size];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            sequence.add(Long.parseLong(tokenizer.nextToken()));
        }
    }

    private static void findAndPrintCorrectSeq() {
        for (int i = 0; i < size; i++) {
            answer[0] = sequence.get(i);
            dfs(1);
        }
    }

    private static void dfs(int depth) {
        if (findAnswer) {
            return;
        }

        if (isEnd(depth)) {
            printAnswer();
            findAnswer = true;
        }
        checkMultiply2(depth);
        checkDivide3(depth);
    }

    private static boolean isEnd(int depth) {
        return depth == size;
    }

    private static void checkMultiply2(int depth) {
        long mul2 = answer[depth - 1] * 2;
        int mul2Index = Collections.binarySearch(sequence, mul2);
        if (mul2Index >= 0) {
            sequence.remove(mul2Index);
            answer[depth] = mul2;
            dfs(depth + 1);
            answer[depth] = 0;
            sequence.add(mul2Index, mul2);
        }
    }

    private static void checkDivide3(int depth) {
        if (answer[depth - 1] % 3 == 0) {
            long div3 = answer[depth - 1] / 3;
            int div3Index = Collections.binarySearch(sequence, div3);
            if (div3Index >= 0) {
                sequence.remove(div3Index);
                answer[depth] = div3;
                dfs(depth + 1);
                answer[depth] = 0;
                sequence.add(div3Index, div3);
            }
        }
    }

    private static void printAnswer() {
        for (int i = 0; i < size; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
