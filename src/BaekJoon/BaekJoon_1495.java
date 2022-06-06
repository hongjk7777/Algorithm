package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1495 {
    private static int songAmount, maxVolume, startVolume;
    private static int[] volumeDiff;
    private static boolean[][] validVolume;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        songAmount = Integer.parseInt(tokenizer.nextToken());
        startVolume = Integer.parseInt(tokenizer.nextToken());
        maxVolume = Integer.parseInt(tokenizer.nextToken());
        volumeDiff = new int[songAmount + 1];
        validVolume = new boolean[songAmount + 1][maxVolume + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= songAmount; i++) {
            volumeDiff[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        validVolume[0][startVolume] = true;

        for (int curSong = 1; curSong <= songAmount; curSong++) {
            for (int tempVol = 0; tempVol <= maxVolume; tempVol++) {
                if (validVolume[curSong - 1][tempVol]) {
                    if (tempVol + volumeDiff[curSong] <= maxVolume) {
                        validVolume[curSong][tempVol + volumeDiff[curSong]] = true;
                    }

                    if (tempVol - volumeDiff[curSong] >= 0) {
                        validVolume[curSong][tempVol - volumeDiff[curSong]] = true;
                    }
                }
            }
        }
    }

    private static void printAnswer() {
        boolean disable = true;
        for (int curVol = maxVolume; curVol >= 0; curVol--) {
            if (validVolume[songAmount][curVol]) {
                System.out.println(curVol);
                disable = false;
                break;
            }
        }

        if (disable) {
            System.out.println(-1);
        }
    }
}
