package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_15235 {
    private static int contestantsAmount;
    private static int[] contestant, finishTime;

    public static void main(String[] args) throws IOException {
        getInput();
        findTimeToFinish();
        printFinishTime();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        contestantsAmount = Integer.parseInt(tokenizer.nextToken());
        contestant = new int[contestantsAmount];
        finishTime = new int[contestantsAmount];

        if (contestantsAmount == 0) {
            return;
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < contestantsAmount; i++) {
            contestant[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findTimeToFinish() {
        int time = 1;
        int endContestant = 0;

        while (endContestant != contestantsAmount) {
            for (int i = 0; i < contestantsAmount; i++) {
                if (contestant[i] > 0) {
                    contestant[i]--;
                    if (contestant[i] == 0) {
                        finishTime[i] = time;
                        endContestant++;
                    }
                    time++;
                }
            }
        }
    }

    private static void printFinishTime() {
        for (int i = 0; i < contestantsAmount; i++) {
            System.out.print(finishTime[i] + " ");
        }
    }
}
