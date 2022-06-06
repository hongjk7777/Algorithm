package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_16198 {
    private static int beadAmount, max = 0;
    private static ArrayList<Integer> beads = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        findMaxEnergy(beadAmount, 0);
        System.out.println(max);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        beadAmount = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < beadAmount; i++) {
            beads.add(Integer.parseInt(tokenizer.nextToken()));
        }
    }

    private static void findMaxEnergy(int leftBeads, int sum) {
        if (leftBeads == 2) {
            updateMax(sum);
            return;
        }

        int size = beads.size();
        for (int i = 1; i < size - 1; i++) {
            int selectedBead = beads.get(i);
            int temp = beads.get(i - 1) * beads.get(i + 1);
            beads.remove(i);
            findMaxEnergy(leftBeads - 1, sum + temp);
            beads.add(i, selectedBead);
        }
    }

    private static void updateMax(int temp) {
        max = Math.max(max, temp);
    }
}
