package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2879 {
    private static int size, answer = 0;
    private static int[] now, goal, diff;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDiffArr();
        findMinTime();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        now = new int[size];
        goal = new int[size];
        diff = new int[size];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            now[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            goal[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDiffArr() {
        for (int i = 0; i < size; i++) {
            diff[i] = goal[i] - now[i];
        }
    }

    private static void findMinTime() {
        for (int cur = 0; cur < size; cur++) {
            while (diff[cur] != 0) {
                int temp = cur;
                if (diff[temp] > 0) {
                    while (temp < size && diff[temp] > 0) {
                        diff[temp]--;
                        temp++;
                    }
                    answer++;
                } else if (diff[temp] < 0) {
                    while (temp < size && diff[temp] < 0) {
                        diff[temp]++;
                        temp++;
                    }
                    answer++;
                }
            }
        }
    }
}
