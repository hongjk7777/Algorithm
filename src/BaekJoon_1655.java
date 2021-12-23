/*
생각노트
1. 출력은 생각보다 시간이 오래 걸리니 StringBuilder로 한 번 만 출력하자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_1655 {
    private static int totalAmount, middleValue;
    private static int[] input;
    private static PriorityQueue<Integer> biggerQueue = new PriorityQueue<>();
    private static PriorityQueue<Integer> smallerQueue = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        getInput();
        playGame();
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

    private static void playGame() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= totalAmount; i++) {
            printMiddleValue(i);
            builder.append(middleValue).append("\n");
        }
        System.out.println(builder);
    }

    private static void printMiddleValue(int gameTurn) {
        int value = input[gameTurn - 1];

        if (gameTurn == 1) {
            middleValue = value;
            return;
        }

        if (value < middleValue) {
            smallerQueue.add(value);
            if (gameTurn % 2 == 0) {
                if (smallerQueue.size() > biggerQueue.size()) {
                    // 중간 값 두 개 중 작은 하나가 smallerQueue에 들어가 있는 상황
                    biggerQueue.add(middleValue);
                    middleValue = smallerQueue.poll();
                }
            } else if (smallerQueue.size() > biggerQueue.size() + 1) {
                biggerQueue.add(middleValue);
                middleValue = smallerQueue.poll();

            }
        } else {
            biggerQueue.add(value);
            if (gameTurn % 2 == 0) {
                if (smallerQueue.size() > biggerQueue.size()) {
                    biggerQueue.add(middleValue);
                    middleValue = smallerQueue.poll();
                }
            } else if (biggerQueue.size() > smallerQueue.size() + 1) {
                smallerQueue.add(middleValue);
                middleValue = biggerQueue.poll();
            }
        }
    }
}
