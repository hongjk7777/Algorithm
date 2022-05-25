/*
아쉬운 점
1. 실수는 수 비교를 할 때 적합하지 않음
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11444 {
    private static final int MOD = 1000000007;
    private static long n;
    private static final Matrix[] squaredMatrix = new Matrix[63];

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getAnswer();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Long.parseLong(tokenizer.nextToken());
    }

    private static int getAnswer() {
        long[][] value = getMatrixPow(n);
        return (int) value[0][1];
    }

    private static long[][] getMatrixPow(long n) {
        Matrix ret = new Matrix(new long[][]{{1, 0}, {0, 1}});
        Matrix temp = new Matrix(new long[][]{{1, 1}, {1, 0}});

        while (n > 0) {
            if (n % 2 == 1) {
                ret.value = ret.multiply(temp);
            }
            temp.value = temp.multiply(temp);
            n >>= 1;
        }

        return ret.value;
    }

    private static class Matrix {
        long[][] value;

        public Matrix(long[][] value) {
            this.value = value;
        }

        public long[][] multiply(Matrix matrix) {
            long[][] ret = new long[2][2];

            ret[0][0] = (this.value[0][0] * matrix.value[0][0] + this.value[0][1] * matrix.value[1][0]) % MOD;
            ret[0][1] = (this.value[0][0] * matrix.value[0][1] + this.value[0][1] * matrix.value[1][1]) % MOD;
            ret[1][0] = (this.value[1][0] * matrix.value[0][0] + this.value[1][1] * matrix.value[1][0]) % MOD;
            ret[1][1] = (this.value[1][0] * matrix.value[0][1] + this.value[1][1] * matrix.value[1][1]) % MOD;

            return ret;
        }
    }
}
