import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon_15686 {
    private static int mapSize, maxChick, min = 987654321;
    private static int houseCount = 0, chickHouseCount = 0;
    private static int[][] house = new int[100][2];
    private static int[][] chickHouse = new int[100][2];

    public static void main(String[] args) {
        getInput();
        getMinChick();
        System.out.println(min);
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        mapSize = scanner.nextInt();
        maxChick = scanner.nextInt();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                int input = scanner.nextInt();
                if (input == 1) {
                    house[houseCount][0] = i;
                    house[houseCount][1] = j;
                    houseCount++;
                }
                if (input == 2) {
                    chickHouse[chickHouseCount][0] = i;
                    chickHouse[chickHouseCount][1] = j;
                    chickHouseCount++;
                }
            }
        }
    }


    private static void getMinChick() {
        for (int i = 0; i < (int)Math.pow(2, chickHouseCount); i++) {
            if (countBit(i) == maxChick) {
                int dist = getMinDistSum(i);
                if (min > dist) {
                    min = dist;
                }
            }
        }
        //bitmask
    }



    private static int countBit(int chickenBit) {
        int count = 0;
        for (int i = 0; i < chickHouseCount ; i++) {
            count += chickenBit & 1;
            chickenBit = chickenBit >> 1;
        }

        return count;
    }

    private static int getMinDistSum(int chickenBit) {
        int sum = 0;

        for (int houseNum = 0; houseNum < houseCount; houseNum++) {
            sum += getMinDist(houseNum, chickenBit);
        }

        return sum;
    }

    private static int getMinDist(int houseNum, int chickenBit) {
        int minDist = 987654321;

        for (int i = 0; i < chickHouseCount; i++) {
            if ((chickenBit & 1) == 1) {
                int distX = Math.abs(house[houseNum][0] - chickHouse[i][0]);
                int distY = Math.abs(house[houseNum][1] - chickHouse[i][1]);
                if (minDist > distX + distY) {
                    minDist = distX + distY;
                }
            }
            chickenBit = chickenBit >> 1;
        }

        return minDist;
    }
}
