import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BaekJoon_2015 {
    private static int numSize, goalSum;
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
        goalSum = Integer.parseInt(tokenizer.nextToken());
        arr = new int[numSize];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numSize; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAnswer() {
        Map<Long, Long> map = new HashMap<>();

        long sum = 0;
        for (int i = 0; i < numSize; i++) {
            sum += arr[i];

            if (sum == goalSum) {
                answer++;
            }

            if (map.containsKey(sum - goalSum)) {
                answer += map.get(sum - goalSum);
            }

            if (map.containsKey(sum)) {
                map.replace(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 1L);
            }
        }
    }
}
