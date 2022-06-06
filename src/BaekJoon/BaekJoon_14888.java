package BaekJoon;

import java.util.Scanner;

public class BaekJoon_14888 {
    private static int operandNum;
    private static int[] operand = new int[11];
    private static int[] operator = new int[4];
    private static int max = -987654321;
    private static int min = 987654321;

    public static void main(String[] args) {
        getInput();
        calculate(0, operand[0], operator);
        System.out.println(max);
        System.out.println(min);
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        operandNum = scanner.nextInt();
        for (int i = 0; i < operandNum; i++) {
            operand[i] = scanner.nextInt();
        }
        for (int i = 0; i < 4; i++) {
            operator[i] = scanner.nextInt();
        }
    }

    private static void calculate(int index, int total, int[] operator) {
        int[] result = new int[4];
        int tempMax = 0;
        if (index == operandNum - 1) {
            if (total > max) {
                max = total;
            }
            if (total < min) {
                min = total;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int[] opClone = operator.clone();
            if (opClone[i] != 0) {
                opClone[i]--;
                if (i == 0) {
                    calculate(index + 1, total + operand[index + 1], opClone);
                } else if (i == 1) {
                    calculate(index + 1,  total - operand[index + 1], opClone);
                } else if (i == 2) {
                    calculate(index + 1, total * operand[index + 1], opClone);
                } else {
                    if (operand[index + 1] != 0) {
                        calculate(index + 1, divide(total, operand[index + 1]), opClone);
                    }
                }
            }
        }
    }

    private static int divide(int numerator, int denominator) {
        if (numerator < 0) {
            return ((numerator * -1) / denominator) * -1;
        } else {
            return numerator / denominator;
        }
    }
}
