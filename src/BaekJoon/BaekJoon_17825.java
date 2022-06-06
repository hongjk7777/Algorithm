package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_17825 {
    private static int max = 0;
    private static int[] move = new int[10];
    private static int[] map = new int[41];
    private static int[] mapTo10 = new int[41];
    private static int[] mapTo20 = new int[41];
    private static int[] mapTo30 = new int[41];
    private static Horse[] horses = new Horse[4];

    public static void main(String[] args) throws IOException {
        initialize();
        getInput();
        playGame(0, 0);
        System.out.println(max);
    }

    private static void initialize() {
        initializeHorses();
        initializeMap();
    }

    private static void initializeHorses() {
        for (int i = 0; i < 4; i++) {
            horses[i] = new Horse();
        }
    }

    private static void initializeMap() {
        for (int i = 0; i < 20; i++) {
            map[2 * i] = 2 * i + 2;
        }
        map[40] = 0;

        mapTo10[13] = 16;
        mapTo10[16] = 19;
        mapTo10[19] = 25;
        mapTo10[25] = 30;
        mapTo10[30] = 35;
        mapTo10[35] = 40;
        mapTo10[40] = 0;

        mapTo30[28] = 27;
        mapTo30[27] = 26;
        mapTo30[26] = 25;
        mapTo30[25] = 30;
        mapTo30[30] = 35;
        mapTo30[35] = 40;
        mapTo30[40] = 0;

        mapTo20[22] = 24;
        mapTo20[24] = 25;
        mapTo20[25] = 30;
        mapTo20[30] = 35;
        mapTo20[35] = 40;
        mapTo20[40] = 0;

    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        for (int i = 0; i < 10; i++) {
            move[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void playGame(int count, int sum) {
        if (count == 10) {
            if (max < sum) {
                max = sum;
            }
            return;
        }

        int dist = move[count];

        for (int i = 0; i < 4; i++) {
            Horse horse = horses[i];
            if (horse.isFinished()) {
                continue;
            }
            int curPlace = horse.place;
            int curMap = horse.mapNum;
            horse.move(dist);
//            moveHorse(horse, dist);
            if (!overLap(horse)) {
                playGame(count + 1 , sum + horse.place);
            }
            horse.reset(curPlace, curMap);
        }
    }



    private static boolean overLap(Horse horse) {
        int count = 0;

        if (horse.isFinished() || horse.place == 0) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            Horse anotherHorse = horses[i];

            if ((horse.mapNum > 0 && anotherHorse.mapNum > 0) ||
                    horse.place == 40) {
                if (horse.place == anotherHorse.place) {
                    count++;
                }
            } else if (horse.place == anotherHorse.place &&
                    horse.mapNum == anotherHorse.mapNum) {
                count++;
            }
        }

        return count > 1;
    }

    private static class Horse {
        int place = 0;
        int mapNum = 0;
        boolean finish = false;

        public void finish() {
            finish = true;
        }

        public boolean isFinished() {
            return finish;
        }

        public void reset(int curPlace, int curMap) {
            this.place = curPlace;
            this.mapNum = curMap;
            this.finish = false;
        }

        public void move(int dist) {
            int curPlace = this.place;
            int curMap = this.mapNum;
            int nextPlace;

            for (int i = 0; i < dist; i++) {
                if (curMap == 0) {
                    if (i == 0 && checkMapChange(curPlace)) {
                        nextPlace = changeMapAndPlace(curPlace);
                        curMap = this.mapNum;
                    } else {
                        nextPlace = map[curPlace];
                    }
                } else if (curMap == 1) {
                    nextPlace = mapTo10[curPlace];
                } else if (curMap == 2) {
                    nextPlace = mapTo20[curPlace];
                } else {
                    nextPlace = mapTo30[curPlace];
                }

                this.place = nextPlace;
                curPlace = nextPlace;

                if (nextPlace == 0) {
                    finish();
                    break;
                }
            }


        }

        private boolean checkMapChange(int curPlace) {
            return curPlace == 10 || curPlace == 20 || (curPlace == 30 && mapNum == 0);
        }

        private int changeMapAndPlace(int curPlace) {
            if (curPlace == 10) {
                this.mapNum = 1;
                this.place = 13;
            } else if (curPlace == 20) {
                this.place = 22;
                this.mapNum = 2;
            } else {
                this.place = 28;
                this.mapNum = 3;
            }

            return this.place;
        }
    }
}
