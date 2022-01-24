/*
배운 점
1. switch문을 좀 더 적재적소에 쓰자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11723 {
    private static int m, s;
    private static int[] input;
    private static final StringBuilder builder = new StringBuilder();
    private static String[] sign;

    public static void main(String[] args) throws IOException {
        getInput();
        calSigns();
        printAll();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        m = Integer.parseInt(tokenizer.nextToken());
        sign = new String[m];
        input = new int[m];

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            sign[i] = tokenizer.nextToken();
            if (sign[i].equals("all") || sign[i].equals("empty")) {
                continue;
            }
            input[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void calSigns() {
        for (int i = 0; i < m; i++) {
            String temp = sign[i];
            int num = input[i];
            calSign(temp, num);
        }
    }

    private static void calSign(String temp, int num) {
        switch (temp) {
            case "add" -> add(num);
            case "remove" -> remove(num);
            case "check" -> check(num);
            case "toggle" -> toggle(num);
            case "all" -> all(num);
            case "empty" -> empty();
        }
    }

    private static void add(int num) {
        s = s | (1 << (num - 1));

    }

    private static void remove(int num) {
        s = s & ((1 << 20) - 1 - (1 << (num - 1)));
    }

    private static void check(int num) {
        int temp = s & (1 << (num - 1));
        if (temp == 0) {
            builder.append(0);
        } else {
            builder.append(1);
        }
        builder.append("\n");
    }

    private static void toggle(int num) {
        s = s ^ (1 << (num - 1));
    }

    private static void all(int num) {
        s = (1 << 20) - 1;
    }

    private static void empty() {
        s = 0;
    }

    private static void printAll() {
        System.out.println(builder);
    }

}
