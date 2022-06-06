package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_11286 {
    private static PriorityQueue<Integer> absMinQueue = new PriorityQueue<>();
    private static int[] input;
    private static int totalAmount;

    public static void main(String[] args) throws IOException {
        getInput();
        calculateAbsMinQueues();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());

        totalAmount = Integer.parseInt(tokenizer.nextToken());
        input = new int[totalAmount];

        for (int num = 0; num < totalAmount; num++) {
            tokenizer = new StringTokenizer(reader.readLine());
            input[num] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void calculateAbsMinQueues() {
        for (int i = 0; i < totalAmount; i++) {
            calculateAbsMinQueue(input[i]);
        }
    }

    private static void calculateAbsMinQueue(int input) {
        if (input == 0) {
            if (absMinQueue.size() == 0) {
                System.out.println(0);
                return;
            }
            int value = absMinQueue.poll();

            if (value % 2 == 0) {
                value = (value / 2) * -1;
            } else {
                value = (value / 2);
            }
            System.out.println(value);
        } else {
            int checkPositive = 0;
            if (input > 0) {
                checkPositive = 1;
            }
            int absValue = Math.abs(input);

            absMinQueue.add(absValue * 2 + checkPositive);
        }
    }
}
