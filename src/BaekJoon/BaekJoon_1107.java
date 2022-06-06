package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1107 {
    private static final int MAX = 1000000, INT_MAX = 987654321;
    private static int n, m;
    private static int[] crashedButtons;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getMinButtonToFinish();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        m = Integer.parseInt(tokenizer.nextToken());
        crashedButtons = new int[m];

        if (m > 0) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < m; i++) {
                crashedButtons[i] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static int getMinButtonToFinish() {
        int buttonPush = Math.abs(n - 100);
        int temp;

        temp = getAbleMaxChanel();
        buttonPush = Math.min(buttonPush, temp);
        temp = getAbleMinChanel();
        buttonPush = Math.min(buttonPush, temp);
        return buttonPush;
    }

    private static int getAbleMaxChanel() {
        int buttonPush = 0;
        int curChanel = n;
        while (!isAbleToPushButton(curChanel)) {
            curChanel++;
            buttonPush++;

            if (curChanel > MAX) {
                return INT_MAX;
            }
        }
        buttonPush += getDigit(curChanel);

        return buttonPush;
    }
    
    private static boolean isAbleToPushButton(int curChanel) {
        if (curChanel == 0) {
            return !isCrashed(0);
        }
        while (curChanel > 0) {
            int lastNum = curChanel % 10;
            if (isCrashed(lastNum)) {
                return false;
            }
            curChanel /= 10;
        }
        return true;
    }

    private static boolean isCrashed(int numPad) {
        for (int i = 0; i < m; i++) {
            if (crashedButtons[i] == numPad) {
                return true;
            }
        }
        return false;
    }

    private static int getDigit(int curChanel) {
        int digit = 0;

        if (curChanel == 0) {
            return 1;
        }
        while (curChanel > 0) {
            digit++;
            curChanel /= 10;
        }
        return digit;
    }

    private static int getAbleMinChanel() {
        int buttonPush = 0;
        int curChanel = n;
        while (!isAbleToPushButton(curChanel)) {
            curChanel--;
            buttonPush++;
            if (curChanel < 0) {
                return INT_MAX;
            }
        }
        buttonPush += getDigit(curChanel);

        return buttonPush;
    }
}
