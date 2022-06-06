package BaekJoon;/*
아쉬운 점
1. int long 차이 때문에 틀린 적이 한 두 번이 아닌데 오늘도 범위 확인을 안 하고 틀림
2. Greedy의 기본 정신을 좀만 더 생각했으면 혼자서 풀 수 있었을 듯
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_1202 {
    private static int jewelAmount, bagAmount;
    private static int[] bags;
    private static Jewel[] jewels;
    private static final PriorityQueue<Integer> jewelValuePriorityQueue =
            new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        getInput();
        Arrays.sort(bags);
        Arrays.sort(jewels);
        long maxVal = getMaxValue();
        System.out.println(maxVal);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        jewelAmount = Integer.parseInt(tokenizer.nextToken());
        bagAmount = Integer.parseInt(tokenizer.nextToken());
        jewels = new Jewel[jewelAmount];
        bags = new int[bagAmount];

        for (int i = 0; i < jewelAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int mass = Integer.parseInt(tokenizer.nextToken());
            int value = Integer.parseInt(tokenizer.nextToken());
            jewels[i] = new Jewel(mass, value);
        }

        for (int i = 0; i < bagAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            bags[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static long getMaxValue() {
        long totalValue = 0;
        int jewelIndex = 0;

        for (int num = 0; num < bagAmount; num++) {
            int capacity = bags[num];
            while (jewelIndex < jewelAmount &&
                    jewels[jewelIndex].mass <= capacity) {
                jewelValuePriorityQueue.add(jewels[jewelIndex].value);
                jewelIndex++;
            }

            if (!jewelValuePriorityQueue.isEmpty()) {
                int highestValue = jewelValuePriorityQueue.poll();
                totalValue += highestValue;
            }
        }

        return totalValue;
    }

    private static class Jewel implements Comparable<Jewel>{
        int mass, value;

        public Jewel(int mass, int value) {
            this.mass = mass;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel o) {
            return Integer.compare(this.mass, o.mass);
        }
    }
}
