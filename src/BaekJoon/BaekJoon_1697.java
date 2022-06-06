package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_1697 {
    private static int n, k;
    private static final int MAX = 100000;
    private static final int[] dp = new int[MAX + 1];
    private static final boolean[] visited = new boolean[MAX + 1];

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
    }

    private static void makeDP() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        visited[n] = true;
        int depth = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int now = queue.poll();
                int next;
                next = now + 1;
                if (updateDP(next, depth)) {
                    queue.add(next);
                }

                next = now - 1;
                if (updateDP(next, depth)) {
                    queue.add(next);
                }

                next = 2 * now;
                if (updateDP(next, depth)) {
                    queue.add(next);
                }
            }
            depth++;
        }
    }

    private static boolean updateDP(int next, int depth) {
        if (overLimit(next)) {
            return false;
        }

        if (!visited[next]) {
            visited[next] = true;
            dp[next] = depth;
            return true;
        } else if (depth < dp[next]) {
            dp[next] = depth;
            return true;
        }
        return false;
    }

    private static boolean overLimit(int location) {
        return 0 > location || location > MAX;
    }

    private static void printAnswer() {
        System.out.println(dp[k]);
    }
}
