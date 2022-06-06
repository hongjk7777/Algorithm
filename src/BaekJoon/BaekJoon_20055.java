package BaekJoon;/*
아쉬운 점
1. 시간 초과 뜬 것 때문에 O(n)이 잘못된 줄 암. 그러나 종료조건 설정 잘못함
2. 위와 같은 상황 때문에 항상 예외를 제발 좀 생각하자
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_20055 {

    private static int containerSize, end, endContainer = 0;
    private static int[] container;
    private static int[] robots;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = startContainer();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());

        containerSize = Integer.parseInt(tokenizer.nextToken());
        end = Integer.parseInt(tokenizer.nextToken());
        container = new int[2 * containerSize];
        robots = new int[2 * containerSize];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < 2 * containerSize; i++) {
            container[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int startContainer() {
        int count = 0;
        while (endContainer < end) {
            spinContainer();
            moveRobots();
            raiseRobot();
            count++;
        }

        return count;
    }

    private static void spinContainer() {
        int[] temp = new int[2 * containerSize];
        if (2 * containerSize >= 0) {
            System.arraycopy(container, 0, temp, 0, 2 * containerSize);
        }


        for (int i = 0; i < 2 * containerSize; i++) {
            int next = (i + 1) % (2 * containerSize);
            container[next] = temp[i];
        }

        if (2 * containerSize >= 0) {
            System.arraycopy(robots, 0, temp, 0, 2 * containerSize);
        }
        for (int i = 0; i < 2 * containerSize; i++) {
            int next = (i + 1) % (2 * containerSize);
            robots[next] = temp[i];
            if (next == containerSize - 1 && robots[next] == 1) {
                robots[next] = 0;
            }
        }
    }

    private static void moveRobots() {
        for (int i = containerSize - 2; i >= 0; i--) {
            if (robots[i] == 1 && robots[i + 1] == 0 && container[i + 1] > 0) {
                robots[i] = 0;
                robots[i + 1] = 1;
                container[i + 1]--;
                if (container[i + 1] == 0) {
                    endContainer++;
                }
                if (i + 1 == containerSize - 1 && robots[i + 1] == 1) {
                    robots[i + 1] = 0;
                }
            }
        }
    }

    private static void raiseRobot() {
        if (robots[0] == 0 && container[0] > 0) {
            robots[0] = 1;
            container[0]--;
            if (container[0] == 0) {
                endContainer++;
            }
        }
    }

}
