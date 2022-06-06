package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16637 {
    private static final int INT_MAX = 987654321;
    private static int length, max = -INT_MAX;
    private static String expression;
    private static boolean[] bracket;

    public static void main(String[] args) throws IOException {
        getInput();
        dfs(0);
        System.out.println(max);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        length = Integer.parseInt(tokenizer.nextToken());
        bracket = new boolean[length / 2 + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        expression = tokenizer.nextToken();
    }

    private static void dfs(int start) {
        int maxDepth = length / 2 + 1;

        if (start == maxDepth) {
            int value = calculateExpression();
            updateMax(value);
            return;
        }
        dfs(start + 1);

        if (start < maxDepth - 1) {
            bracket[start] = true;
            bracket[start + 1] = true;
            dfs(start + 2);
            bracket[start] = false;
            bracket[start + 1] = false;
        }
    }

    private static int calculateExpression() {
        char curOperator = '+';
        int start = 0;
        long sum = 0;

        while (start < length) {
            if (bracket[start / 2]) {
                char nextOperator = expression.charAt(start + 1);
                int firstNum = Integer.parseInt(String.valueOf(expression.charAt(start)));
                int secondNum = Integer.parseInt(String.valueOf(expression.charAt(start + 2)));
                long nextVal = calculate(firstNum, secondNum, nextOperator);
                sum = calculate(sum, nextVal, curOperator);
                start += 3;
            } else {
                long nextVal = Integer.parseInt(String.valueOf(expression.charAt(start)));
                sum = calculate(sum, nextVal, curOperator);
                start++;
            }
            if (start < length) {
                curOperator = expression.charAt(start);
            }
            start++;
        }

        return (int) sum;
    }

    private static long calculate(long num1, long num2, char operator) {
        if (operator == '*') {
            return num1 * num2;
        } else if (operator == '+') {
            return num1 + num2;
        } else {
            return num1 - num2;
        }
    }

    private static void updateMax(int value) {
        if (max == -INT_MAX) {
            max = value;
        } else {
            max = Math.max(max, value);
        }
    }
}
