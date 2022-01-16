import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_1026 {
    private static int size;
    private static Integer[] arrA, arrB;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getMinVal();
        System.out.println(answer);
    }


    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        arrA = new Integer[size];
        arrB = new Integer[size];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            arrA[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            arrB[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int getMinVal() {
        Arrays.sort(arrA);
        Arrays.sort(arrB, Collections.reverseOrder());

        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += arrA[i] * arrB[i];
        }

        return sum;
    }
}
