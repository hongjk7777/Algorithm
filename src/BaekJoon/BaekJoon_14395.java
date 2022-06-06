package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_14395 {
    private static final long MAX = 1000000000;
    private static int start, goal;

    public static void main(String[] args) throws IOException {
        getInput();
        bfs();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());
        start = Integer.parseInt(tokenizer.nextToken());
        goal = Integer.parseInt(tokenizer.nextToken());
    }

    private static void bfs() {
        Queue<Expressions> queue = new LinkedList<>();
        queue.add(new Expressions(start));
        boolean able = false, divide = false, minus = false;
        Expressions answer = null;

        while (!queue.isEmpty()) {
            Expressions poll = queue.poll();

            if (poll.value == goal) {
                answer = poll;
                able = true;
                break;
            }

            if (poll.value == 0) {
                continue;
            }

            addMultiply(queue, poll);
            addPlus(queue, poll);

            if (!minus) {
                addMinus(queue);
                minus = true;
            }

            if (!divide) {
                addDivide(queue);
                divide = true;
            }
        }

        printAnswer(able, answer);
    }

    private static void addMultiply(Queue<Expressions> queue, Expressions poll) {
        if (poll.value != 1 && poll.value * poll.value <= MAX) {
            Expressions temp = new Expressions(poll);
            temp.setValue(temp.value * temp.value);
            temp.addExpression('*');
            queue.add(temp);
        }
    }

    private static void addPlus(Queue<Expressions> queue, Expressions poll) {
        if (poll.value * 2 <= MAX) {
            Expressions temp = new Expressions(poll);
            temp.setValue(temp.value * 2);
            temp.addExpression('+');
            queue.add(temp);
        }
    }

    private static void addMinus(Queue<Expressions> queue) {
        Expressions temp = new Expressions(0);
        temp.addExpression('-');
        queue.add(temp);
    }

    private static void addDivide(Queue<Expressions> queue) {
        Expressions temp = new Expressions(1);
        temp.addExpression('/');
        queue.add(temp);
    }

    private static void printAnswer(boolean able, Expressions answer) {
        if (able) {
            if (answer.expression.size() == 0) {
                System.out.println(0);
            } else {
                for (Character expression : answer.expression) {
                    System.out.print(expression);
                }
            }
        } else {
            System.out.println(-1);
        }
    }

    private static class Expressions {
        ArrayList<Character> expression = new ArrayList<>();
        long value;

        public Expressions(long value) {
            this.value = value;
        }

        public Expressions(Expressions poll) {
            expression = new ArrayList<>(poll.expression);
            value = poll.value;
        }

        public void addExpression(char c) {
            expression.add(c);
        }

        public void setValue(long value) {
            this.value = value;
        }
    }
}
