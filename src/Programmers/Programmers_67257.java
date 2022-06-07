package Programmers;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    Queue<Long> operands = new LinkedList<>();
    Queue<Character> operators = new LinkedList<>();
    char[][] priority =
            { {'-', '+', '*'},
            {'-', '*', '+'},
            {'+', '-', '*'},
            {'+', '*', '-'},
            {'*', '-', '+'},
            {'*', '+', '-'}};

    public long solution(String expression) {
        long answer;

        parseExpression(expression);
        answer = calculateAndGetMax(expression);

        return answer;
    }

    private void parseExpression(String expression) {
        StringBuilder builder = new StringBuilder();

        int curIndex = 0;

        while (curIndex < expression.length()) {
            char c = expression.charAt(curIndex);

            if ('0' <= c && c <= '9') {
                builder.append(c);
            } else {
                operands.add(Long.parseLong(builder.toString()));
                operators.add(expression.charAt(curIndex));
                builder = new StringBuilder();
            }

            curIndex++;
        }
        operands.add(Long.parseLong(builder.toString()));
    }

    private long calculateAndGetMax(String expression) {
        long max = 0;
        for (int priorityNum = 0; priorityNum < 6; priorityNum++) {
            long result = getResult(expression, priorityNum);
            max = Math.max(max, Math.abs(result));
        }
        return max;
    }

    private long getResult(String expression, int priorityNum) {
        Queue<Long> tempOperands = new LinkedList<>(operands);
        Queue<Character> tempOperators = new LinkedList<>(operators);

        for (int i = 0; i < 3; i++) {
            int curIndex = 0;
            long curNum = tempOperands.poll();
            int size = tempOperands.size();

            while (curIndex < size){
                char curOperator = tempOperators.poll();
                if (curOperator == priority[priorityNum][i]) {
                    curNum = calculate(curNum, curOperator, tempOperands.poll());
                } else {
                    tempOperands.add(curNum);
                    tempOperators.add(curOperator);
                    curNum = tempOperands.poll();
                }

                curIndex++;
            }
            tempOperands.add(curNum);

        }
        return tempOperands.peek();
    }

    private long calculate(long num1, char curOperator, Long num2) {
        if (curOperator == '+'){
            return num1 + num2;
        } else if (curOperator == '-') {
            return num1 - num2;
        } else {
            return num1 * num2;
        }
    }

}