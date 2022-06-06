package BaekJoon;/*
아쉬운 점
1. 경계값 정도는 제발 넣어보자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_10819 {
    private static int n, max = 0;
    private static int[] input;
    private static boolean[] checked;
    private static ArrayList<Integer> permutation = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        findMax();
        System.out.println(max);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        input = new int[n];
        checked = new boolean[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findMax() {
        if (permutation.size() == n) {
            int temp = getResult();
            updateMax(temp);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!checked[i]) {
                permutation.add(input[i]);
                checked[i] = true;
                findMax();
                checked[i] = false;
                permutation.remove(permutation.size() - 1);
            }
        }
    }

    private static int getResult() {
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += Math.abs(permutation.get(i) - permutation.get(i + 1));
        }
        return sum;
    }

    private static void updateMax(int temp) {
        if (temp > max) {
            max = temp;
        }
    }
}
