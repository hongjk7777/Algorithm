import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BaekJoon_10986 {
    private static int numSize, divisor;
    private static long answer = 0;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        getInput();
        findAnswer();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        numSize = Integer.parseInt(tokenizer.nextToken());
        divisor = Integer.parseInt(tokenizer.nextToken());
        arr = new int[numSize];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numSize; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        Map<Integer, Long> map = new HashMap<>();
        int sum = 0;

        for (int i = 0; i < numSize; i++) {
            sum = (sum + arr[i]) % divisor;

            if (sum == 0) {
                answer++;
            }

            if (map.containsKey(sum)) {
                answer += map.get(sum);
                map.replace(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1L);
            }
        }
    }
}
