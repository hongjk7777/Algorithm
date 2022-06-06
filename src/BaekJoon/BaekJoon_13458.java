package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_13458 {
    private static int areaAmount, standard, notStandard;
    private static int[] area;

    public static void main(String[] args) throws IOException {
        getInput();
        int min = getMin();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        areaAmount = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < areaAmount; i++) {
            area[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        standard = Integer.parseInt(tokenizer.nextToken());
        notStandard = Integer.parseInt(tokenizer.nextToken());
    }

    private static int getMin() {
        int num = 0;

        for (int i = 0; i < areaAmount; i++) {
            area[i] -= standard;
            num++;
            while (area[i] > 0) {
                area[i] -= notStandard;
                num++;
            }
        }

        return num;
    }
}
