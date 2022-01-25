/*
배운 점
1. switch문을 좀 더 적재적소에 쓰자
2. 메모리 제한도 생각하면서 문제를 풀자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11723 {
    private static int s;
    private static final StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        getInput();
        printAll();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        int m = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String sign = tokenizer.nextToken();
            if (sign.equals("all") || sign.equals("empty")) {
                calSign(sign, 0);
                continue;
            }
            int input = Integer.parseInt(tokenizer.nextToken());
            calSign(sign, input);
        }
    }

    private static void calSign(String sign, int num) {
        if (sign.equals("add")) add(num);
        else if (sign.equals("remove")) remove(num);
        else if (sign.equals("check")) check(num);
        else if (sign.equals("toggle")) toggle(num);
        else if (sign.equals("all")) all();
        else if (sign.equals("empty")) empty();

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

    private static void all() {
        s = (1 << 20) - 1;
    }

    private static void empty() {
        s = s >> 21;
    }

    private static void printAll() {
        System.out.println(builder);
    }

}
