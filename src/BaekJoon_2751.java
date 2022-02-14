import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_2751 {
    private static int n;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        getInput();
        Arrays.sort(arr);
        printArr();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void printArr() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(arr[i]).append("\n");
        }
        System.out.println(builder);
    }
}
