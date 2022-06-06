package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2138 {
    private static int size, min = -1;
    private static char[] goalState, curState, saveState;

    public static void main(String[] args) throws IOException {
        getInput();
        getMinSwitch();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        goalState = new char[size];
        curState = new char[size];
        saveState = new char[size];

        tokenizer = new StringTokenizer(reader.readLine());
        String goalStr = tokenizer.nextToken();
        for (int i = 0; i < size; i++) {
            goalState[i] = goalStr.charAt(i);
        }

        tokenizer = new StringTokenizer(reader.readLine());
        String curStr = tokenizer.nextToken();
        for (int i = 0; i < size; i++) {
            curState[i] = curStr.charAt(i);
            saveState[i] = curStr.charAt(i);
        }
    }

    private static void getMinSwitch() {
        int count;

        count = getCount();
        if (count != -1) {
            updateMin(count);
        }

        resetCurState();

        turnSwitch(0);
        count = getCount();
        if (count != -1) {
            updateMin(count + 1);
        }

    }

    private static int getCount() {
        int count = 0;
        for (int i = 1; i < size; i++) {
            if (goalState[i - 1] != curState[i - 1]) {
                turnSwitch(i);
                count++;
            }
        }

        if (isImpossible()) {
            count = -1;
        }

        return count;
    }

    private static void turnSwitch(int index) {
        if (index - 1 >= 0) {
            reverse(index - 1);
        }
        reverse(index);
        if (index + 1 < size) {
            reverse(index + 1);
        }
    }

    private static void reverse(int index) {
        if (curState[index] == '0') {
            curState[index] = '1';
        } else {
            curState[index] = '0';
        }
    }

    private static boolean isImpossible() {
        return goalState[size - 1] != curState[size - 1];
    }

    private static void updateMin(int tempMin) {
        if (min == -1) {
            min = tempMin;
        } else {
            min = Math.min(min, tempMin);
        }
    }

    private static void resetCurState() {
        if (size >= 0) System.arraycopy(saveState, 0, curState, 0, size);
    }
}
