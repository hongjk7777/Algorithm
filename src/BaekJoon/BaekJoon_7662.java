package BaekJoon;/*
아쉬운 점
1. 내가 혼자 못 품

2. 일단 이진 트리에서 remove()는 O(lgn)이라는 글만 보고 remove(Object o)를 사용했는데
이는 바보같은 짓이었다. remove()는 root를 지우는 거고 내가 쓴 건 해당 지점을 찾아야하기 때문에
O(n)시간이 걸림

3. 자료구조 문제 풀이도 미흡하다고 생각
ㄴ 더 풀자!
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_7662 {
    private static final int MAX = 1000000;
    private static final boolean[] valid = new boolean[MAX + 1];

    private static final PriorityQueue<Data> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
    private static final PriorityQueue<Data> minQueue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        int testcaseAmount = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < testcaseAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int dataAmount = Integer.parseInt(tokenizer.nextToken());

            for (int expNum = 0; expNum < dataAmount; expNum++) {
                tokenizer = new StringTokenizer(reader.readLine());
                String expr = tokenizer.nextToken();
                int value = Integer.parseInt(tokenizer.nextToken());
                calculate(expr, value, expNum);
            }

            printMaxMin();

            maxQueue.clear();
            minQueue.clear();
        }
    }

    private static void calculate(String expr, int value, int expNum) {
        if (expr.equals("I")) {
            add(value, expNum);
        } else {
            if (value == 1) {
                remove(maxQueue);
            } else {
                remove(minQueue);
            }
        }
    }

    private static void add(int value, int expNum) {
        maxQueue.add(new Data(value, expNum));
        minQueue.add(new Data(value, expNum));
        valid[expNum] = true;
    }

    private static void remove(PriorityQueue<Data> queue) {
        while (!queue.isEmpty() && !valid[queue.peek().expNum]) {
            queue.remove();
        }

        if (!queue.isEmpty()) {
            valid[queue.peek().expNum] = false;
        }
    }

    private static void printMaxMin() {
        removeInvalidValue(minQueue);
        removeInvalidValue(maxQueue);

        if (maxQueue.isEmpty() || minQueue.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            Data max = maxQueue.poll();
            Data min = minQueue.poll();

            System.out.print(max.value + " ");
            System.out.print(min.value);
            System.out.println();
        }
    }

    private static void removeInvalidValue(PriorityQueue<Data> minQueue) {
        while (!minQueue.isEmpty() && !valid[minQueue.peek().expNum]) {
            minQueue.remove();
        }
    }

    private static class Data implements Comparable<Data> {
        int value, expNum;

        public Data(int value, int expNum) {
            this.value = value;
            this.expNum = expNum;
        }

        @Override
        public int compareTo(Data data) {
            return Integer.compare(value, data.value);
        }
    }
}
