package BaekJoon;

import java.util.Scanner;

public class BaekJoon_11047 {
    private static int coinAmount;
    private static int desiredValue;
    private static int[] coin = new int[10];

    public static void main(String[] args) {
        getInput();
        System.out.println(getMinCoinTo(desiredValue));
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        coinAmount = scanner.nextInt();
        desiredValue = scanner.nextInt();
        for (int i = 0; i < coinAmount; i++) {
            coin[i] = scanner.nextInt();
        }
    }


    private static int getMinCoinTo(int desiredValue) {
        int nowValue = 0;
        int count = 0;

        for (int i = coinAmount - 1; i >= 0; i--) {
            if (nowValue == desiredValue) {
                break;
            }
            while (nowValue + coin[i] <= desiredValue) {
                nowValue += coin[i];
                count++;
            }
            if (nowValue > desiredValue) {
                nowValue -= coin[i];

            }
        }

        return count;
    }
}
