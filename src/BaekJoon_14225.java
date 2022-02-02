/*
아쉬운 점
1. 조금 더 쉬운 방법이 있었는데 살짝 돌아감
ㄴ 들어갈지 말지 해서 재귀로 했으면 시간 단축 가능했을 듯
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_14225 {
    private static int n;
    private static int[] sequence;
    private static final boolean[] checked = new boolean[2000010];
    private static ArrayList<Integer> subsequence = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        checkAbleNums();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        sequence = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void checkAbleNums() {
        for (int size = 1; size <= n; size++) {
            makeSubsequences(size, 0);
        }
    }

    private static void makeSubsequences(int size, int start) {
        if (subsequence.size() == size) {
            int sum = getSum();
            checked[sum] = true;
            return;
        }

        if (n <= start) {
            return;
        }

        for (int i = start; i < n; i++) {
            subsequence.add(sequence[i]);
            makeSubsequences(size, i + 1);
            subsequence.remove(subsequence.size() - 1);
        }
    }

    private static int getSum() {
        int sum = 0;
        for (int temp : subsequence) {
            sum += temp;
        }
        return sum;
    }

    private static void printAnswer() {
        int min = getMin();
        System.out.println(min);
    }

    private static int getMin() {
        int number = 1;
        while (checked[number]) {
            number++;
        }
        return number;
    }
}
