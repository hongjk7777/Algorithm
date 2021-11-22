import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon_5373 {

    private final static int CUBE_SIZE = 3;
    private final static int UP_SIDE = 0, LEFT_SIDE = 1, FORWARD_SIDE = 2;
    private final static int BACK_SIDE = 3, RIGHT_SIDE = 4, DOWN_SIDE = 5;
    private static int testCaseAmount;
    private static int[] spinAmount = new int[100];
    private static String[][] cube = new String[6][CUBE_SIZE * CUBE_SIZE];
    private static int[][][] spin = new int[4][1000][2];
    // 1st testcaseNum 2nd spinNum 3rd 0:spinDir 1:spinSide

    public static void main(String[] args) {
        paintCube();
        getInput();
        for (int i = 0; i < testCaseAmount; i++) {
            spinAllCube(spin[i], i);
            printCubeSide(cube[0]);
            resetCube();
        }
    }

    private static void paintCube() {
        Arrays.fill(cube[UP_SIDE], "w");
        Arrays.fill(cube[DOWN_SIDE], "y");
        Arrays.fill(cube[FORWARD_SIDE], "r");
        Arrays.fill(cube[BACK_SIDE], "o");
        Arrays.fill(cube[LEFT_SIDE], "g");
        Arrays.fill(cube[RIGHT_SIDE], "b");
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        testCaseAmount = scanner.nextInt();

        for (int i = 0; i < testCaseAmount; i++) {
            spinAmount[i] = scanner.nextInt();
            scanner.nextLine();
            for (int j = 0; j < spinAmount[i]; j++) {
                String input = scanner.next();
                spin[i][j][0] = getSpinDir(input.charAt(0));
                spin[i][j][1] = getCubeSide(input.charAt(1));
            }
        }
    }

    private static int getSpinDir(char spinDir) {
        if (spinDir == '-') {
            return 0;
        } else {
            return 1;
        }
    }

    private static int getCubeSide(char side) {
        if (side == 'U') {
            return UP_SIDE;
        } else if (side == 'D') {
            return DOWN_SIDE;
        } else if (side == 'F') {
            return FORWARD_SIDE;
        } else if (side == 'B') {
            return BACK_SIDE;
        } else if (side == 'L') {
            return LEFT_SIDE;
        } else {
            return RIGHT_SIDE;
        }
    }

    private static void spinAllCube(int[][] spin, int testCaseNum) {
        for (int spinNum = 0; spinNum < spinAmount[testCaseNum]; spinNum++) {
            spinCube(spin[spinNum]);
        }
    }

    private static void spinCube(int[] spin) {
        if (spin[0] == UP_SIDE) {
            spinCubeSide(UP_SIDE, spin[1]);
        } else if (spin[0] == DOWN_SIDE) {
            spinCubeSide(DOWN_SIDE, spin[1]);
        } else if (spin[0] == FORWARD_SIDE) {
            spinCubeSide(FORWARD_SIDE, spin[1]);
        } else if (spin[0] == BACK_SIDE) {
            spinCubeSide(BACK_SIDE, spin[1]);
        } else if (spin[0] == LEFT_SIDE) {
            spinCubeSide(LEFT_SIDE, spin[1]);
        } else {
            spinCubeSide(RIGHT_SIDE, spin[1]);
        }
    }

    private static void spinCubeSide(int cubeSide, int dir) {
        String[][] spinElement = new String[4][3];

        int[] temp = new int[3];
        for (int i = 1; i < 5; i++) {
            int curSide = (i + cubeSide) % 6;
            if (curSide != cubeSide) {
                continue;
            }

            getSpinElement(spinElement, cubeSide, curSide);
        }

        if (dir == 0) {
            spinCounterClockwise(spinElement);
        } else {
            spinClockwise(spinElement);
        }
    }



    private static void getSpinElement(String[][] spinElement, int axisSide, int curSide) {

        if (axisSide == UP_SIDE) {
            if (curSide == LEFT_SIDE) {
                spinElement[0][0] = cube[curSide][2];
                spinElement[0][1] = cube[curSide][1];
                spinElement[0][2] = cube[curSide][0];
            } else if (curSide == RIGHT_SIDE) {
                spinElement[2][0] = cube[curSide][2];
                spinElement[2][1] = cube[curSide][1];
                spinElement[2][2] = cube[curSide][0];
            } else if (curSide == FORWARD_SIDE) {
                spinElement[1][0] = cube[curSide][2];
                spinElement[1][1] = cube[curSide][1];
                spinElement[1][2] = cube[curSide][0];
            } else {
                spinElement[3][0] = cube[curSide][2];
                spinElement[3][1] = cube[curSide][1];
                spinElement[3][2] = cube[curSide][0];
            }
        } else if (axisSide == DOWN_SIDE) {
            if (curSide == LEFT_SIDE) {
                spinElement[0][0] = cube[curSide][8];
                spinElement[0][1] = cube[curSide][7];
                spinElement[0][2] = cube[curSide][6];
            } else if (curSide == RIGHT_SIDE) {
                spinElement[2][0] = cube[curSide][8];
                spinElement[2][1] = cube[curSide][7];
                spinElement[2][2] = cube[curSide][6];
            } else if (curSide == FORWARD_SIDE) {
                spinElement[3][0] = cube[curSide][8];
                spinElement[3][1] = cube[curSide][7];
                spinElement[3][2] = cube[curSide][6];
            } else {
                spinElement[1][0] = cube[curSide][8];
                spinElement[1][1] = cube[curSide][7];
                spinElement[1][2] = cube[curSide][6];
            }
        } else if (axisSide == LEFT_SIDE) {
            if (curSide == UP_SIDE) {
                spinElement[0][0] = cube[curSide][0];
                spinElement[0][1] = cube[curSide][3];
                spinElement[0][2] = cube[curSide][6];
            } else if (curSide == DOWN_SIDE) {
                spinElement[2][0] = cube[curSide][0];
                spinElement[2][1] = cube[curSide][3];
                spinElement[2][2] = cube[curSide][6];
            } else if (curSide == FORWARD_SIDE) {
                spinElement[1][0] = cube[curSide][0];
                spinElement[1][1] = cube[curSide][3];
                spinElement[1][2] = cube[curSide][6];
            } else {
                spinElement[3][0] = cube[curSide][2];
                spinElement[3][1] = cube[curSide][5];
                spinElement[3][2] = cube[curSide][8];
            }
        } else if (axisSide == RIGHT_SIDE) {
            if (curSide == UP_SIDE) {
                spinElement[0][0] = cube[curSide][8];
                spinElement[0][1] = cube[curSide][5];
                spinElement[0][2] = cube[curSide][2];
            } else if (curSide == DOWN_SIDE) {
                spinElement[2][0] = cube[curSide][8];
                spinElement[2][1] = cube[curSide][5];
                spinElement[2][2] = cube[curSide][2];
            } else if (curSide == FORWARD_SIDE) {
                spinElement[1][0] = cube[curSide][8];
                spinElement[1][1] = cube[curSide][5];
                spinElement[1][2] = cube[curSide][2];
            } else {
                spinElement[3][0] = cube[curSide][0];
                spinElement[3][1] = cube[curSide][3];
                spinElement[3][2] = cube[curSide][6];
            }
        } else if (axisSide == FORWARD_SIDE) {
            if (curSide == UP_SIDE) {
                spinElement[0][0] = cube[curSide][6];
                spinElement[0][1] = cube[curSide][7];
                spinElement[0][2] = cube[curSide][8];
            } else if (curSide == RIGHT_SIDE) {
                spinElement[1][0] = cube[curSide][0];
                spinElement[1][1] = cube[curSide][3];
                spinElement[1][2] = cube[curSide][6];
            } else if (curSide == DOWN_SIDE) {
                spinElement[2][0] = cube[curSide][2];
                spinElement[2][1] = cube[curSide][1];
                spinElement[2][2] = cube[curSide][0];
            } else {
                spinElement[3][0] = cube[curSide][8];
                spinElement[3][1] = cube[curSide][5];
                spinElement[3][2] = cube[curSide][2];
            }
        } else {
            if (curSide == UP_SIDE) {
                spinElement[0][0] = cube[curSide][2];
                spinElement[0][1] = cube[curSide][1];
                spinElement[0][2] = cube[curSide][0];
            } else if (curSide == LEFT_SIDE) {
                spinElement[1][0] = cube[curSide][0];
                spinElement[1][1] = cube[curSide][3];
                spinElement[1][2] = cube[curSide][6];
            } else if (curSide == DOWN_SIDE) {
                spinElement[2][0] = cube[curSide][6];
                spinElement[2][1] = cube[curSide][7];
                spinElement[2][2] = cube[curSide][8];
            } else {
                spinElement[3][0] = cube[curSide][8];
                spinElement[3][1] = cube[curSide][5];
                spinElement[3][2] = cube[curSide][2];
            }
        }
    }

    private static void spinClockwise(String[][] spinElement) {
        String[] temp = new String[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = spinElement[3][i];
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                spinElement[(i + 1) % 4][j] = spinElement[i][j];
            }
        }
        for (int i = 0; i < 3; i++) {
            spinElement[0][i] = temp[i];
        }
    }

    private static void spinCounterClockwise(String[][] spinElement) {
        String[] temp = new String[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = spinElement[0][i];
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                spinElement[(i) % 4][j] = spinElement[i + 1][j];
            }
        }
        for (int i = 0; i < 3; i++) {
            spinElement[3][i] = temp[i];
        }
    }

    private static void printCubeSide(String[] topSide) {
        for (int i = 0; i < CUBE_SIZE * CUBE_SIZE; i++) {
            System.out.print(topSide[i]);
            if (i % 3 == 2) {
                System.out.println();
            }
        }
    }

    private static void resetCube() {
        for (int j = 0; j < CUBE_SIZE * CUBE_SIZE; j++) {
            cube[0][j] = "w";
        }
        for (int j = 0; j < CUBE_SIZE * CUBE_SIZE; j++) {
            cube[1][j] = "g";
        }
        for (int j = 0; j < CUBE_SIZE * CUBE_SIZE; j++) {
            cube[2][j] = "r";
        }
        for (int j = 0; j < CUBE_SIZE * CUBE_SIZE; j++) {
            cube[3][j] = "o";
        }
        for (int j = 0; j < CUBE_SIZE * CUBE_SIZE; j++) {
            cube[4][j] = "b";
        }
        for (int j = 0; j < CUBE_SIZE * CUBE_SIZE; j++) {
            cube[5][j] = "y";
        }
    }
}
