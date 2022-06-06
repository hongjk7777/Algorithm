package BaekJoon;/*
아쉬웠던 점
1. 처음에 greedy로 풀 때는 생각 못한 예외가 너무 많았고,
2. greedy의 문제점을 알고 bfs로 바꾸려 했을 때 미숙한 부분이 많아 제대로 바꾸지 못했다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_1039 {
    private static int n, k, length, answer = 0;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        getInput();
        getMaxNumWithSwap();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        String temp = tokenizer.nextToken();
        n = Integer.parseInt(temp);
        length = temp.length();
        k = Integer.parseInt(tokenizer.nextToken());
    }


    private static void getMaxNumWithSwap() {
        if (isUnableToSwap()) {
            answer = -1;
            return;
        }

        bfs();
    }


    private static boolean isUnableToSwap() {
        int notZeroNum = getNotZeroNum();
        return notZeroNum == 1 && length < 3;
    }

    private static int getNotZeroNum() {
        int temp = n;
        int notZeroNum = 0;
        while (temp > 0) {
            if (temp % 10 != 0) {
                notZeroNum++;
            }
            temp /= 10;
        }

        return notZeroNum;
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);

        int state = 0;

        while (!queue.isEmpty() && state < k) {
            state++;
            visited = new boolean[(int) Math.pow(10, length)];
            int size = queue.size();
            while (size-- > 0) {
                int poll = queue.poll();
                for (int i = 0; i < length - 1; i++) {
                    for (int j = i + 1; j < length; j++) {
                        int temp = swap(poll, i, j);

                        if (temp < Math.pow(10, length - 1)) {
                            continue;
                        }

                        if (!visited[temp]) {
                            if (state == k) {
                                answer = Math.max(answer, temp);
                            }
                            queue.add(temp);
                            visited[temp] = true;
                        }
                    }
                }
            }
        }

    }

    private static int swap(int num, int i, int j) {
        int temp = num;

        int ithNum = (int) ((num / Math.pow(10, i)) % 10);
        int jthNum = (int) ((num / Math.pow(10, j)) % 10);

        temp = temp - (int)Math.pow(10, i) * ithNum + (int)Math.pow(10, i) * jthNum
                - (int)Math.pow(10, j) * jthNum + (int)Math.pow(10, j) * ithNum;

        return temp;
    }
}
