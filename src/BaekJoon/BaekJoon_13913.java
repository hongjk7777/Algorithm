package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_13913 {
    private static int n, k;
    private static final int MAX = 100000;
    private static int[] dp = new int[MAX + 1];
    private static boolean[] visited = new boolean[MAX + 1];

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

                next = now - 1;
                if (updateDP(next, depth)) {
                    queue.add(next);
                }

                next = now + 1;
                if (updateDP(next, depth)) {
                    queue.add(next);
                }

                next = now * 2;
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
            dp[next] = depth;
            visited[next] = true;
            return true;
        }

        else if (dp[next] > depth) {
            dp[next] = depth;
            return true;
        }
        return false;
    }

    private static boolean overLimit(int location) {
        return location < 0 || MAX < location;
    }

    private static void printAnswer() {
        StringBuilder builder = new StringBuilder();

        builder.append(dp[k]).append("\n");
        appendRoute(builder);

        System.out.println(builder);
    }

    private static void appendRoute(StringBuilder builder) {
        ArrayList<Integer> reverseRoute = new ArrayList<>();

        int now = k;
        int depth = dp[k];
        reverseRoute.add(k);
        while (depth >= 0) {
            if (now % 2 == 0 && dp[now / 2] == depth - 1) {
                now /= 2;
                reverseRoute.add(now);
            } else if (now - 1 >= 0 && dp[now - 1] == depth - 1) {
                now -= 1;
                reverseRoute.add(now);
            } else if (now + 1 <= MAX && dp[now + 1] == depth - 1) {
                now += 1;
                reverseRoute.add(now);
            }
            depth--;
        }

        for (int i = reverseRoute.size() - 1; i >= 0; i--) {
            builder.append(reverseRoute.get(i)).append(" ");
        }
    }
}
