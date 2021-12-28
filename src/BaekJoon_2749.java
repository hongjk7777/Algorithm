/*
배운 점
1. 피사노 주기에 대해 배웠다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2749 {
    private static final long MOD = 1000000;
    private static long n;
    private static long pisanoPeriod = MOD + (MOD / 2);
    private static int[] fibonacci = new int[(int) pisanoPeriod + 1];


    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getFibonacciRemainder();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        n = Long.parseLong(tokenizer.nextToken());
    }

    private static int getFibonacciRemainder() {
        //피사노 주기를 통해 구하자
        n %= pisanoPeriod;

        return getFibonacci((int) n);
    }

    private static int getFibonacci(int n) {
        //dp를 통해 구하자
        fibonacci[1] = 1;
        fibonacci[2] = 1;

        for (int i = 3; i <= n; i++) {
            fibonacci[i] = (int) ((fibonacci[i - 1] + fibonacci[i - 2]) % MOD);
        }

        return fibonacci[n];
    }
}
