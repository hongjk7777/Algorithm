package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_13549 {
    private static final int MAX = 100000;
    private static int n, k;

    private static final boolean[] visited = new boolean[MAX + 1];
    private static int[] dp = new int[MAX + 1];

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
        addInitVal(queue);
        int time = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();

                int temp = cur - 1;
                updateDPs(temp, time, queue);

                temp = cur + 1;
                updateDPs(temp, time, queue);
            }
            time++;
        }
    }

    private static void addInitVal(Queue<Integer> queue) {
        while (n <= MAX) {
            queue.add(n);
            visited[n] = true;
            n = n << 1;

            if (n == 0) {
                break;
            }
        }
    }

    private static void updateDPs(int temp, int time, Queue<Integer> queue) {
        while (temp <= MAX) {
            if (updateDP(temp, time)) {
                queue.add(temp);
            }
            if (temp == 0) {
                break;
            }
            temp = temp << 1;
        }
    }

    private static boolean updateDP(int location, int time) {
        if (overLimit(location)) {
            return false;
        }
        if (!visited[location]) {
            dp[location] = time;
            visited[location] = true;
            return true;
        }

        if (time < dp[location]) {
            dp[location] = time;
            return true;
        }
        return false;
    }

    private static boolean overLimit(int location) {
        return location < 0 || MAX < location;
    }

    private static void printAnswer() {
        System.out.println(dp[k]);
    }
}
