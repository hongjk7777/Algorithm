package BaekJoon;/*
느낀 점
1. 별로 어렵지 않은 문제라고 너무 바로 제출하려는 습관이 있음.
아무리 쉬운 문제도 간단한 예외, 경계값은 체크하자
2. 배열을 쓸 때는 outofbound가 나올만 하게 코딩하지 말자.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_13335 {
    private static int truckAmount, bridgeLength, maxWeight;
    private static ArrayList<Integer> truck = new ArrayList<>();
    private static int[] bridge;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getTimeToCrossBridge();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        truckAmount = Integer.parseInt(tokenizer.nextToken());
        bridgeLength = Integer.parseInt(tokenizer.nextToken());
        maxWeight = Integer.parseInt(tokenizer.nextToken());
        bridge = new int[bridgeLength];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int truckNum = 0; truckNum < truckAmount; truckNum++) {
            truck.add(Integer.parseInt(tokenizer.nextToken()));
        }

    }

    private static int getTimeToCrossBridge() {
        int time = 0;
        while (!truck.isEmpty()) {
            int truckWeight = truck.get(0);
            if (isAbleToEnter(truckWeight)) {
                bridge[bridgeLength - 1] = truckWeight;
                truck.remove(0);
            }
            moveTrucks();
            time++;
        }
        time += bridgeLength;
        return time;
    }

    private static void moveTrucks() {
        if (bridgeLength == 1) {
            bridge[0] = 0;
            return;
        }
        int temp = bridge[1];

        for (int i = 0; i < bridgeLength - 1; i++) {
            bridge[i] = temp;
            if (i != bridgeLength - 2) {
                temp = bridge[i + 2];
            }
        }
        bridge[bridgeLength - 1] = 0;
    }

    private static boolean isAbleToEnter(int weight) {
        int sum = 0;
        for (int i = 0; i < bridgeLength; i++) {
            sum += bridge[i];
        }

        return sum + weight <= maxWeight;
    }
}
