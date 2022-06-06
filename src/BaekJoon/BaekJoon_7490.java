package BaekJoon;/*
아쉬운 점
1. 문제를 잘못 읽어서 정렬을 하지 않고 출력해 틀렸다
2. 문자열 문제에 미숙한 거 같다. 앞으로 더 풀어봐야할 듯
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_7490 {
    private static char[] operators;
    private static final ArrayList<String> strings = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        int testcaseAmount = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < testcaseAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            find0Expressions(n);
            System.out.println();
        }
    }

    private static void find0Expressions(int n) {
        operators = new char[n - 1];
        strings.clear();
        findExpressionsToMake0(0, n);
        print0Expressions();
    }

    private static void findExpressionsToMake0(int depth, int goalDepth) {
        if (depth == goalDepth - 1) {
            if (is0Expression(goalDepth)) {
                add0Expressions(goalDepth);
            }
            return;
        }

        for (int operatorNum = 0; operatorNum < 3; operatorNum++) {
            operators[depth] = getOperator(operatorNum);
            findExpressionsToMake0(depth + 1, goalDepth);
        }
    }

    private static boolean is0Expression(int goalDepth) {
        int sum = 0;
        int temp = 1;
        char preOperator = '+';
        for (int i = 0; i < goalDepth - 1; i++) {
            if (operators[i] != ' ') {
                sum = calculate(sum, preOperator, temp);
                preOperator = operators[i];
                temp = i + 2;
            }else {
                temp *= 10;
                temp += i + 2;
            }

            if (i == goalDepth - 2) {
                sum = calculate(sum, preOperator, temp);
            }

        }
        return sum == 0;
    }

    private static int calculate(int sum, char preOperator, int temp) {
        if (preOperator == '+') {
            return sum + temp;
        } else{
            return sum - temp;
        }
    }

    private static void add0Expressions(int goalDepth) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < goalDepth - 1; i++) {
            builder.append(i + 1).append(operators[i]);
        }
        builder.append(goalDepth);

        strings.add(builder.toString());
    }

    private static char getOperator(int operatorNum) {
        if (operatorNum == 0) {
            return '+';
        } else if (operatorNum == 1) {
            return '-';
        } else {
            return ' ';
        }
    }

    private static void print0Expressions() {
        Collections.sort(strings);

        for (String str : strings) {
            System.out.println(str);
        }
    }
}
