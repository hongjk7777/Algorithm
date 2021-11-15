import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon_14891 {


    private static int[][] gear = new int[4][8];
    private static int moveNum;
    private static int[][] move = new int[100][2];
    private static int[] moveAlready = new int[4];

    public static void main(String[] args) {
        getInput();
        spin();
        System.out.println(getTotalScore());
    }


    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }
        moveNum = scanner.nextInt();
        for (int i = 0; i < moveNum; i++) {
            move[i][0] = scanner.nextInt() - 1;
            move[i][1] = scanner.nextInt();
        }
    }

    private static void spin() {
        for (int i = 0; i < moveNum; i++) {
            int gearNum = move[i][0];
            int dir = move[i][1];
            Arrays.fill(moveAlready, 0);
            if (dir == 1) {
                spinRightGear(gearNum);
            }
            if (dir == -1) {
                spinLeftGear(gearNum);
            }
        }
    }

    private static void spinRightGear(int gearNum) {
        if (!isRightGearSamePole(gearNum)) {
            if (moveAlready[gearNum + 1] != 1) {
                moveAlready[gearNum] = 1;
                spinLeftGear(gearNum + 1);
            }
        }
        if (!isLeftGearSamePole(gearNum)) {
            if (moveAlready[gearNum - 1] != 1) {
                moveAlready[gearNum] = 1;
                spinLeftGear(gearNum - 1);
            }
        }
        int[] temp = Arrays.stream(gear[gearNum]).toArray();
        for (int i = 0; i < 8; i++) {
            gear[gearNum][(i + 1) % 8] = temp[i];
        }
    }

    private static void spinLeftGear(int gearNum) {
        if (!isRightGearSamePole(gearNum)) {
            if (moveAlready[gearNum + 1] != 1) {
                moveAlready[gearNum] = 1;
                spinRightGear(gearNum + 1);
            }
        }
        if (!isLeftGearSamePole(gearNum)) {
            if (moveAlready[gearNum - 1] != 1) {
                moveAlready[gearNum] = 1;
                spinRightGear(gearNum - 1);
            }
        }

        int[] temp = Arrays.stream(gear[gearNum]).toArray();
        for (int i = 0; i < 8; i++) {
            gear[gearNum][(i - 1 + 8) % 8] = temp[i];
        }
    }

    private static boolean isRightGearSamePole(int gearNum) {
        if (gearNum == 3) {
            return true;
        }

        if (gear[gearNum][2] == gear[gearNum + 1][6]) {
            return true;
        }
        return false;
    }

    private static boolean isLeftGearSamePole(int gearNum) {
        if (gearNum == 0) {
            return true;
        }

        if (gear[gearNum][6] == gear[gearNum - 1][2]) {
            return true;
        }

        return false;
    }



    private static int getTotalScore() {
        return gear[0][0] + gear[1][0] * 2 + gear[2][0] * 4 + gear[3][0] * 8;
    }

}
