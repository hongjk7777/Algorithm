package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1476 {
    private static int e, s, m, startE, startS, startM, answer = 1;

    public static void main(String[] args) throws IOException {
        getInput();
        findMinYear();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        e = Integer.parseInt(tokenizer.nextToken());
        s = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
    }

    private static void findMinYear() {
        startE = 1;
        startS = 1;
        startM = 1;


        while (!isSameYear()) {
            addESMYear();
            answer++;
        }
    }

    private static boolean isSameYear() {
        return e == startE && s == startS && m == startM;
    }

    private static void addESMYear() {
        startE++;
        startS++;
        startM++;
        if (startE == 16) {
            startE = 1;
        }
        if (startS == 29) {
            startS = 1;
        }
        if (startM == 20) {
            startM = 1;
        }
    }
}
