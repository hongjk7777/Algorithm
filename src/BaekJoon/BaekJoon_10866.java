package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BaekJoon_10866 {
    private static final Deque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String command = tokenizer.nextToken();
            int x = 0;
            if (command.equals("push_front") || command.equals("push_back")) {
                x = Integer.parseInt(tokenizer.nextToken());
            }
            execute(command, x);
        }
    }

    private static void execute(String command, int x) {
        if (command.equals("push_front")) {
            pushFront(x);
        } else if (command.equals("push_back")) {
            pushBack(x);
        } else if (command.equals("pop_front")) {
            printPopFront();
        } else if (command.equals("pop_back")) {
            printPopBack();
        } else if (command.equals("size")) {
            printSize();
        } else if (command.equals("empty")) {
            printIsEmpty();
        } else if (command.equals("front")) {
            printFirst();
        } else if (command.equals("back")) {
            printBack();
        }
    }

    private static void pushFront(int x) {
        deque.addFirst(x);
    }

    private static void pushBack(int x) {
        deque.addLast(x);
    }

    private static void printPopFront() {
        if (!deque.isEmpty()) {
            System.out.println(deque.pollFirst());
        } else {
            System.out.println(-1);
        }
    }

    private static void printPopBack() {
        if (!deque.isEmpty()) {
            System.out.println(deque.pollLast());
        } else {
            System.out.println(-1);
        }
    }

    private static void printSize() {
        System.out.println(deque.size());
    }

    private static void printIsEmpty() {
        if (deque.isEmpty()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void printFirst() {
        if (!deque.isEmpty()) {
            System.out.println(deque.peekFirst());
        } else {
            System.out.println(-1);
        }
    }

    private static void printBack() {
        if (!deque.isEmpty()) {
            System.out.println(deque.peekLast());
        } else {
            System.out.println(-1);
        }
    }
}
