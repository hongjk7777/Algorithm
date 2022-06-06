package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon_5373 {

    private final static int CUBE_SIZE = 3;
    private final static int UP_SIDE = 0, LEFT_SIDE = 1, FORWARD_SIDE = 2;
    private final static int BACK_SIDE = 3, RIGHT_SIDE = 4, DOWN_SIDE = 5;
    private static int testCaseAmount;
    private static int[] spinAmount = new int[100];
    private static String[][] cube = new String[6][CUBE_SIZE * CUBE_SIZE];
    private static int[][][] spin = new int[100][1000][2];
    // 1st testcaseNum 2nd spinNum 3rd 0:spinDir 1:spinSide

    public static void main(String[] args) {
        resetCube();
        getInput();
        for (int i = 0; i < testCaseAmount; i++) {
            spinAllCube(spin[i], i);
            printCubeSide(cube[0]);
            resetCube();
        }
    }

    private static void resetCube() {
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
                spin[i][j][0] = getCubeSide(input.charAt(0));
                spin[i][j][1] = getSpinDir(input.charAt(1));
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
        spinAxisCube(spin);
        if (spin[0] == UP_SIDE) {
            spinSideCube(UP_SIDE, spin[1]);
        } else if (spin[0] == DOWN_SIDE) {
            spinSideCube(DOWN_SIDE, spin[1]);
        } else if (spin[0] == FORWARD_SIDE) {
            spinSideCube(FORWARD_SIDE, spin[1]);
        } else if (spin[0] == BACK_SIDE) {
            spinSideCube(BACK_SIDE, spin[1]);
        } else if (spin[0] == LEFT_SIDE) {
            spinSideCube(LEFT_SIDE, spin[1]);
        } else {
            spinSideCube(RIGHT_SIDE, spin[1]);
        }
    }

    private static void spinAxisCube(int[] spin) {
        int axisSide = spin[0];
        int dir = spin[1];

        String[] temp = new String[9];
        System.arraycopy(cube[axisSide], 0, temp, 0, 9);

        if (dir == 0) {
            cube[axisSide][0] = temp[2];
            cube[axisSide][1] = temp[5];
            cube[axisSide][2] = temp[8];
            cube[axisSide][3] = temp[1];
            cube[axisSide][4] = temp[4];
            cube[axisSide][5] = temp[7];
            cube[axisSide][6] = temp[0];
            cube[axisSide][7] = temp[3];
            cube[axisSide][8] = temp[6];
        } else {
            cube[axisSide][0] = temp[6];
            cube[axisSide][1] = temp[3];
            cube[axisSide][2] = temp[0];
            cube[axisSide][3] = temp[7];
            cube[axisSide][4] = temp[4];
            cube[axisSide][5] = temp[1];
            cube[axisSide][6] = temp[8];
            cube[axisSide][7] = temp[5];
            cube[axisSide][8] = temp[2];
        }
    }

    private static void spinSideCube(int cubeSide, int dir) {
        int[][] spinElement = new int[4][3];
        int[] spinSide = new int[4];

        int[] temp = new int[3];
        for (int i = 1; i < 6; i++) {
            int curSide = (i + cubeSide) % 6;
            if (curSide + cubeSide == 5) {
                continue;
            }
            getSpinElement(spinElement, cubeSide, curSide, spinSide);
        }

        if (dir == 0) {
            spinCounterClockwise(spinElement, spinSide);
        } else {
            spinClockwise(spinElement, spinSide);
        }

    }



    private static int getSpinElement(int[][] spinElement, int axisSide, int curSide, int[] spinSide) {
        if (axisSide == UP_SIDE) {
            if (curSide == LEFT_SIDE) {
                spinElement[0][0] = 0;
                spinElement[0][1] = 1;
                spinElement[0][2] = 2;
                spinSide[0] = curSide;
            } else if (curSide == RIGHT_SIDE) {
                spinElement[2][0] = 0;
                spinElement[2][1] = 1;
                spinElement[2][2] = 2;
                spinSide[2] = curSide;
            } else if (curSide == FORWARD_SIDE) {
                spinElement[3][0] = 0;
                spinElement[3][1] = 1;
                spinElement[3][2] = 2;
                spinSide[3] = curSide;
            } else {
                spinElement[1][0] = 0;
                spinElement[1][1] = 1;
                spinElement[1][2] = 2;
                spinSide[1] = curSide;
            }
        } else if (axisSide == DOWN_SIDE) {
            if (curSide == LEFT_SIDE) {
                spinElement[0][0] = 6;
                spinElement[0][1] = 7;
                spinElement[0][2] = 8;
                spinSide[0] = curSide;
            } else if (curSide == RIGHT_SIDE) {
                spinElement[2][0] = 6;
                spinElement[2][1] = 7;
                spinElement[2][2] = 8;
                spinSide[2] = curSide;
            } else if (curSide == FORWARD_SIDE) {
                spinElement[1][0] = 6;
                spinElement[1][1] = 7;
                spinElement[1][2] = 8;
                spinSide[1] = curSide;
            } else {
                spinElement[3][0] = 6;
                spinElement[3][1] = 7;
                spinElement[3][2] = 8;
                spinSide[3] = curSide;
            }
        } else if (axisSide == LEFT_SIDE) {
            if (curSide == UP_SIDE) {
                spinElement[0][0] = 0;
                spinElement[0][1] = 3;
                spinElement[0][2] = 6;
                spinSide[0] = curSide;
            } else if (curSide == DOWN_SIDE) {
                spinElement[2][0] = 0;
                spinElement[2][1] = 3;
                spinElement[2][2] = 6;
                spinSide[2] = curSide;
            } else if (curSide == FORWARD_SIDE) {
                spinElement[1][0] = 0;
                spinElement[1][1] = 3;
                spinElement[1][2] = 6;
                spinSide[1] = curSide;
            } else {
                spinElement[3][0] = 8;
                spinElement[3][1] = 5;
                spinElement[3][2] = 2;
                spinSide[3] = curSide;
            }
        } else if (axisSide == RIGHT_SIDE) {
            if (curSide == UP_SIDE) {
                spinElement[0][0] = 8;
                spinElement[0][1] = 5;
                spinElement[0][2] = 2;
                spinSide[0] = curSide;
            } else if (curSide == DOWN_SIDE) {
                spinElement[2][0] = 8;
                spinElement[2][1] = 5;
                spinElement[2][2] = 2;
                spinSide[2] = curSide;
            } else if (curSide == FORWARD_SIDE) {
                spinElement[3][0] = 8;
                spinElement[3][1] = 5;
                spinElement[3][2] = 2;
                spinSide[3] = curSide;
            } else {
                spinElement[1][0] = 0;
                spinElement[1][1] = 3;
                spinElement[1][2] = 6;
                spinSide[1] = curSide;
            }
        } else if (axisSide == FORWARD_SIDE) {
            if (curSide == UP_SIDE) {
                spinElement[0][0] = 6;
                spinElement[0][1] = 7;
                spinElement[0][2] = 8;
                spinSide[0] = curSide;
            } else if (curSide == RIGHT_SIDE) {
                spinElement[1][0] = 0;
                spinElement[1][1] = 3;
                spinElement[1][2] = 6;
                spinSide[1] = curSide;
            } else if (curSide == DOWN_SIDE) {
                spinElement[2][0] = 2;
                spinElement[2][1] = 1;
                spinElement[2][2] = 0;
                spinSide[2] = curSide;
            } else {
                spinElement[3][0] = 8;
                spinElement[3][1] = 5;
                spinElement[3][2] = 2;
                spinSide[3] = curSide;
            }
        } else {
            if (curSide == UP_SIDE) {
                spinElement[0][0] = 2;
                spinElement[0][1] = 1;
                spinElement[0][2] = 0;
                spinSide[0] = curSide;
            } else if (curSide == LEFT_SIDE) {
                spinElement[1][0] = 0;
                spinElement[1][1] = 3;
                spinElement[1][2] = 6;
                spinSide[1] = curSide;
            } else if (curSide == DOWN_SIDE) {
                spinElement[2][0] = 6;
                spinElement[2][1] = 7;
                spinElement[2][2] = 8;
                spinSide[2] = curSide;
            } else {
                spinElement[3][0] = 8;
                spinElement[3][1] = 5;
                spinElement[3][2] = 2;
                spinSide[3] = curSide;
            }
        }

        return curSide;
    }

    private static void spinClockwise(int[][] spinElement, int[] spinSide) {
        String[] temp = new String[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = cube[spinSide[3]][spinElement[3][i]];
        }
        for (int i = 3; i > 0; i--) {
            for (int j = 0; j < 3; j++) {
                cube[spinSide[i]][spinElement[i][j]]
                        = cube[spinSide[i - 1]][spinElement[i - 1][j]];
            }
        }
        for (int i = 0; i < 3; i++) {
            cube[spinSide[0]][spinElement[0][i]] = temp[i];
        }
    }

    private static void spinCounterClockwise(int[][] spinElement, int[] spinSide) {
        String[] temp = new String[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = cube[spinSide[0]][spinElement[0][i]];
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[spinSide[i]][spinElement[i][j]]
                        = cube[spinSide[i + 1]][spinElement[i + 1][j]];
            }
        }
        for (int i = 0; i < 3; i++) {
            cube[spinSide[3]][spinElement[3][i]] = temp[i];
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

}
