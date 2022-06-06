package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1092 {
    private static int craneNum, boxesNum;
    private static int[] cranes, boxes, craneMemory;
    private static boolean[] checkedBoxes;

    public static void main(String[] args) throws IOException {
        getInput();
        Arrays.sort(cranes);
        Arrays.sort(boxes);
        int min = getMinTime();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        craneNum = Integer.parseInt(tokenizer.nextToken());
        cranes = new int[craneNum];
        craneMemory = new int[craneNum];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < craneNum; i++) {
            cranes[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        boxesNum = Integer.parseInt(tokenizer.nextToken());
        boxes = new int[boxesNum];
        checkedBoxes = new boolean[boxesNum];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < boxesNum; i++) {
            boxes[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int getMinTime() {
        int time = 0;

        if (cranes[craneNum - 1] < boxes[boxesNum - 1]) {
            return -1;
        }

        for (int num = 0; num < craneNum; num++) {
            craneMemory[num] = boxesNum - 1;
        }

        while (true) {
            boolean move = false;
            for (int num = craneNum - 1; num >= 0; num--) {
                int lastBoxNum = craneMemory[num];
                while (lastBoxNum >= 0) {
                    if (!checkedBoxes[lastBoxNum] && (boxes[lastBoxNum] <= cranes[num])) {
                        checkedBoxes[lastBoxNum] = true;
                        move = true;
                        break;
                    }
                    craneMemory[num]--;
                    lastBoxNum--;
                }
            }

            if (!move) {
                break;
            }

            time++;
        }

        return time;
    }
}
