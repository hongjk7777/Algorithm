import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon_15662 {

    private static int moveNum, size;
    private static int[] spinedGear = new int[1000];
    private static int[] gear = new int[1000];
    private static int[][] move = new int[1000][2];

    public static void main(String[] args) {
        getInput();
        spin();
        System.out.println(getTotalScore());
    }


    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        size = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < size; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < 8; j++) {
                gear[i] += Integer.parseInt(String.valueOf(line.charAt(j))) << (7 - j);
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
            Arrays.fill(spinedGear, 0);
            if (dir == 1) {
                spinClockWise(gearNum);
            }
            if (dir == -1) {
                spinCounterClockWise(gearNum);
            }
        }
    }

    private static void spinClockWise(int gearNum) {
        if (!isRightGearSamePole(gearNum)) {
            if (spinedGear[gearNum + 1] != 1) {
                spinedGear[gearNum] = 1;
                spinCounterClockWise(gearNum + 1);
            }
        }
        if (!isLeftGearSamePole(gearNum)) {
            if (spinedGear[gearNum - 1] != 1) {
                spinedGear[gearNum] = 1;
                spinCounterClockWise(gearNum - 1);
            }
        }

        gear[gearNum] = (gear[gearNum] >> 1) + (gear[gearNum] << 7 & (1 << 7));
    }

    private static void spinCounterClockWise(int gearNum) {
        if (!isRightGearSamePole(gearNum)) {
            if (spinedGear[gearNum + 1] != 1) {
                spinedGear[gearNum] = 1;
                spinClockWise(gearNum + 1);
            }
        }
        if (!isLeftGearSamePole(gearNum)) {
            if (spinedGear[gearNum - 1] != 1) {
                spinedGear[gearNum] = 1;
                spinClockWise(gearNum - 1);
            }
        }

        gear[gearNum] = (((gear[gearNum] << 1) & ((1 << 8) - 1)) + (gear[gearNum] >> 7));
    }

    private static boolean isRightGearSamePole(int gearNum) {
        if (gearNum == size - 1) {
            return true;
        }
        int xor = ((gear[gearNum] >> 5) & 1) ^ ((gear[gearNum + 1] >> 1) & 1);

        return (xor != 1);
    }

    private static boolean isLeftGearSamePole(int gearNum) {
        if (gearNum == 0) {
            return true;
        }
        int xor = ((gear[gearNum] >> 1) & 1) ^ ((gear[gearNum - 1] >> 5) & 1);

        return (xor != 1);
    }



    private static int getTotalScore() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += gear[i] >> 7;
        }
        return sum;
    }

}
