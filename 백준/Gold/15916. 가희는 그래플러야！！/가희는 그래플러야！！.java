import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] fx;
    private static int size, k;

    public static void main(String[] args) throws IOException {

        getInput();
        if (isMeet()) {
            System.out.println("T");
        } else {
            System.out.println("F");
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());

        fx = new int[size + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            fx[i + 1] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        k = Integer.parseInt(tokenizer.nextToken());
    }

    private static boolean isMeet() {

        if(k == fx[1]){
            return true;
        }

        for (int i = 1; i < size; i++) {
            long fxStart = fx[i];
            long fxEnd = fx[i + 1];

            long kStart = (long) k * i;
            long kEnd = (long) k * (i + 1);

            int sign1 = getSign(fxStart - kStart);
            int sign2 = getSign(fxEnd - kEnd);

            if(sign1 != sign2) {
                return true;
            }
        }
        return false;
    }

    private static int getSign(long value) {
        if (value > 0) return 1;
        else if(value == 0) return 0;
        else return -1;
    }
}
