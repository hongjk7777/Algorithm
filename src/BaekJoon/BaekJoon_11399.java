package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_11399 {
    private static int size;
    private static int[] time;

    public static void main(String[] args) throws IOException {
        getInput();
        int min = getMinTime();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        time = new int[size];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            time[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int getMinTime() {
        int sum = 0;

        Arrays.sort(time);
        int neededTime = 0;
        for (int i = 0; i < size; i++) {
            neededTime += time[i];
            sum += neededTime;
        }

        return sum;
    }
}
