/*
아쉬운 점
1. 코드를 좀 더 읽기 쉽게 바꾸고 싶은데 입력을 따로 저장하면 시간초과가 나온다.
ㄴ 어떻게 해야 할까?
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BaekJoon_10845 {
    private static final Deque<Integer> deque = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String command = tokenizer.nextToken();
            int x = 0;
            if (command.equals("push")) {
                x = Integer.parseInt(tokenizer.nextToken());
            }
            execute(command, x);
        }
    }

    private static void execute(String command, int x) {
        if (command.equals("push")) {
            push(x);
        } else if (command.equals("pop")) {
            printPop();
        } else if (command.equals("size")) {
            printSize();
        } else if (command.equals("empty")) {
            printIsEmpty();
        } else if (command.equals("front")) {
            printFirst();
        } else if (command.equals("back")) {
            printLast();
        }
    }

    private static void push(int x) {
        deque.add(x);
    }

    private static void printPop() {
        if (deque.size() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(deque.poll());
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
        if (deque.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(deque.peekFirst());
        }
    }

    private static void printLast() {
        if (deque.isEmpty()) {
            System.out.println(-1);
        } else {
            System.out.println(deque.peekLast());
        }
    }
}
