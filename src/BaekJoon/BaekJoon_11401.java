package BaekJoon;/*
배운 점
1. 이항 계수에 대해 공부할 수 있었다.(ex. 페르마의 소정리과 역원을 사용하는 법)
2. 역원에 대한 이해가 부족해 더 공부가 필요할 듯
3. 분할 정복에 대해 공부가 더 필요할 듯

추가해야 할 점
1. inverse[] 도 미리 계산해보기
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11401 {
    private static final long MOD = 1000000007;
    private static int n, k; // 이항 계수 nck의 n,k
    private static long[] factorial, inverse;

    public static void main(String[] args) throws IOException {
        getInput();
        findFactorial();
        int nCk = getBinoCoeffi();
        System.out.println(nCk);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        k = Integer.parseInt(tokenizer.nextToken());
        factorial = new long[n + 1];
        inverse = new long[n + 1];
    }

    private static void findFactorial() {
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = (factorial[i - 1] * i) % MOD;
        }
    }

    private static int getBinoCoeffi() {
//        inverse[n] = pow(factorial[n], MOD - 2);
//        for (int i = n - 1; i > 0; i--) {
//            inverse[i] = (inverse[i + 1] * (i + 1)) % MOD;
//        }
        long denominatorInverse = (pow(factorial[n - k], MOD - 2) * pow(factorial[k], MOD - 2)) % MOD;
        long numerator = factorial[n];

        long answer = (denominatorInverse * numerator) % MOD;

        return (int) answer;
    }

    private static long pow(long x, long y) {
        long ret = 1;

        while (y > 0) {
            if (y % 2 != 0) {
                ret *= x;
                ret %= MOD;
            }

            x *= x;
            x %= MOD;
            y /= 2;
        }

        return ret;
    }

}
