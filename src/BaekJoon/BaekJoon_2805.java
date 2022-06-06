package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2805 {
    private static final int MAX_HEIGHT = 2000000000;
    private static int size, goalTree;
    private static int[] tree;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getAnswer();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        goalTree = Integer.parseInt(tokenizer.nextToken());
        tree = new int[size];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            tree[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int getAnswer() {
        int left = 0, mid = MAX_HEIGHT / 2, right = MAX_HEIGHT;
        int answer = 0;

        while (left <= right) {
            long cutAmount = getCutAmount(mid);
            if (cutAmount >= goalTree) {
                answer = mid;
                left = mid + 1;
            } else if (cutAmount < goalTree) {
                right = mid - 1;
            }
            mid = (left + right) / 2;
        }

        return answer;
    }

    private static long getCutAmount(int height) {
        long cutAmount = 0;

        for (int i = 0; i < size; i++) {
            if (tree[i] > height) {
                cutAmount += tree[i] - height;
            }
        }

        return cutAmount;
    }
}
