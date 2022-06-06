package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_17822 {

    public static final int CLOCKWISE = 0;
    private static int roundPlateAmount, numberAmount, spinAmount, totalNumber = 0;
    private static int[][] roundPlate, deletePlate;
    private static int[][] spin;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        getInput();
        for (int i = 0; i < spinAmount; i++) {
            spinRoundPlates(i);
        }
        System.out.println(calculateSum());
    }


    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        roundPlateAmount = Integer.parseInt(tokenizer.nextToken());
        numberAmount = Integer.parseInt(tokenizer.nextToken());
        spinAmount = Integer.parseInt(tokenizer.nextToken());
        roundPlate = new int[roundPlateAmount][numberAmount];
        deletePlate = new int[roundPlateAmount][numberAmount];
        spin = new int[spinAmount][3];

        for (int i = 0; i < roundPlateAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < numberAmount; j++) {
                roundPlate[i][j] = Integer.parseInt(tokenizer.nextToken());
                totalNumber++;
            }
        }

        for (int i = 0; i < spinAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 3; j++) {
                spin[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void spinRoundPlates(int spinNum) {
        int spinPlate = spin[spinNum][0];
        boolean delete = false;

        for (int plateNum = 0; plateNum < roundPlateAmount; plateNum++) {
            if ((plateNum + 1) % spinPlate == 0) {
                spinRoundPlate(plateNum, spinNum);
            }
        }

        for (int plateNum = 0; plateNum < roundPlateAmount; plateNum++) {
            if (checkOverlap(plateNum)) {
                delete = true;
            }
        }

        if (delete) {
            deleteOverlap();
        } else {
            changeNumber();
        }

    }



    private static void spinRoundPlate(int plateNum, int spinNum) {
        int spinDir = spin[spinNum][1];
        int spinDegree = spin[spinNum][2];
        int[] tempRoundPlate = new int[numberAmount];

        System.arraycopy(roundPlate[plateNum], 0, tempRoundPlate, 0, numberAmount);

        if (spinDir == CLOCKWISE) {
            for (int i = 0; i < numberAmount; i++) {
                roundPlate[plateNum][(i + spinDegree) % numberAmount] = tempRoundPlate[i];
            }
        } else {
            for (int i = 0; i < numberAmount; i++) {
                roundPlate[plateNum][(i + (numberAmount - spinDegree)) % numberAmount] = tempRoundPlate[i];
            }
        }


    }

    private static boolean checkOverlap(int plateNum) {
        boolean delete = false;
        for (int numLocation = 0; numLocation < numberAmount; numLocation++) {
            boolean change = false;

            if (roundPlate[plateNum][numLocation] == 0) {
                continue;
            }

            for (int j = 0; j < 4; j++) {
                int tempPlateNum = plateNum + dx[j];
                int tempNumLocation = (numLocation + dy[j]) % numberAmount;

                if (isInArea(tempPlateNum, tempNumLocation)) {
                    if (roundPlate[plateNum][numLocation] ==
                            roundPlate[tempPlateNum][tempNumLocation]) {
                        change = true;
                        deletePlate[tempPlateNum][tempNumLocation] = 1;
                    }
                }
            }
            if (change) {
                delete = true;
                deletePlate[plateNum][numLocation] = 1;
            }
        }

        return delete;
    }

    private static boolean isInArea(int plateNum, int numLocation) {
        return 0 <= plateNum && plateNum < roundPlateAmount &&
                0 <= numLocation && numLocation < numberAmount;
    }

    private static void deleteOverlap() {
        for (int i = 0; i < roundPlateAmount; i++) {
            for (int j = 0; j < numberAmount; j++) {
                if (deletePlate[i][j] == 1) {
                    roundPlate[i][j] = 0;
                    totalNumber--;
                }
            }
        }

        resetDeletePlate();
    }

    private static void resetDeletePlate() {
        for (int i = 0; i < roundPlateAmount; i++) {
            for (int j = 0; j < numberAmount; j++) {
                deletePlate[i][j] = 0;
            }
        }
    }

    private static void changeNumber() {
        double sum = calculateSum();
        int count = totalNumber;
        double average = sum / count;

        for (int plateNum = 0; plateNum < roundPlateAmount; plateNum++) {
            for (int i = 0; i < numberAmount; i++) {
                if (roundPlate[plateNum][i] == 0) {
                    continue;
                } else if (roundPlate[plateNum][i] < average) {
                    roundPlate[plateNum][i]++;
                } else if (roundPlate[plateNum][i] > average) {
                    roundPlate[plateNum][i]--;
                }
            }
        }
    }

    private static int calculateSum() {
        int sum = 0;
        for (int i = 0; i < roundPlateAmount; i++) {
            for (int j = 0; j < numberAmount; j++) {
                sum += roundPlate[i][j];
            }
        }

        return sum;
    }

}
