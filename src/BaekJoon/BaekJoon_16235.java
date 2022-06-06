package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;


public class BaekJoon_16235 {
    private static int mapSize, treeAmount, yearAmount;
    private static int[][] map = new int[10][10];
    private static int[][] nourishment = new int[10][10];
    private static int[][][] tree = new int[10][10][100000];
    //[x][y][0]은 xy 좌표에 심어져있는 나무의 수
    private static int[][][] deathTree = new int[10][10][100000];
    private static int[][] treeNum = new int[10][10];
    private static int[][] deathTreeNum = new int[10][10];
    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) {
        getInput();
        initialize();
        for (int i = 0; i < yearAmount; i++) {
            takeYear();
        }
        System.out.println(countTree());
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        mapSize = scanner.nextInt();
        treeAmount = scanner.nextInt();
        yearAmount = scanner.nextInt();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                nourishment[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < treeAmount; i++) {
            int treeX = scanner.nextInt() - 1;
            int treeY = scanner.nextInt() - 1;
            int treeAge = scanner.nextInt();
            tree[treeX][treeY][treeNum[treeX][treeY]] = treeAge;
            treeNum[treeX][treeY]++;
        }
    }


    private static void initialize() {
        for (int i = 0; i < mapSize; i++) {
            Arrays.fill(map[i], 5);
        }
    }

    private static void takeYear() {
        takeSpring();
        takeSummer();
        takeAutumn();
        takeWinter();
    }

    private static void takeSpring() {
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                int treeAmount = treeNum[row][col];
                if (treeAmount != 0) {
                    Arrays.sort(tree[row][col], 0, treeAmount);
                }
                for (int i = 0; i < treeAmount; i++) {
                    int required = tree[row][col][i];
                    if (map[row][col] >= required) {
                        map[row][col] -= required;
                        tree[row][col][i]++;
                    } else {
                        int deathNum = deathTreeNum[row][col];
                        deathTree[row][col][deathNum] = tree[row][col][i];
                        deathTreeNum[row][col]++;
                        tree[row][col][i] = 0;
                        treeNum[row][col]--;
                        //이렇게 하면 i가 마지막이 아니면 중간에 빈 공간이 생기는데 왜 되지?
                    }
                }
            }
        }
    }

    private static void takeSummer() {
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                int deathTreeAmount = deathTreeNum[row][col];
                for (int i = 0; i < deathTreeAmount; i++) {
                    int nutrition = deathTree[row][col][i] / 2;
                    map[row][col] += nutrition;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Arrays.fill(deathTreeNum[i], 0);
        }
    }

    private static void takeAutumn() {
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                int treeAmount = treeNum[row][col];
                for (int i = 0; i < treeAmount; i++) {
                    int age = tree[row][col][i];
                    if (age % 5 == 0) {
                        for (int j = 0; j < 8; j++) {
                            int tempRow = row + dx[j];
                            int tempCol = col + dy[j];
                            if (enterArea(tempRow, tempCol)) {
                                int temp = treeNum[tempRow][tempCol];

                                tree[tempRow][tempCol][temp] = 1;
                                treeNum[tempRow][tempCol]++;
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean enterArea(int tempRow, int tempCol) {
        return 0 <= tempRow && tempRow < 10 && 0 <= tempCol && tempCol < 10;
    }

    private static void takeWinter() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] += nourishment[i][j];
            }
        }
    }

    private static int countTree() {
        int count = 0;
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                count += treeNum[i][j];
            }
        }
        return count;
    }

}
