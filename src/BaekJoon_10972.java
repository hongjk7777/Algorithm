/*
아쉬운 점
1. 구현 능력이 아쉬웠다
2. 아이디어 발상이 늦음
ㄴ 직접 써보자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_10972 {
    private static int n;
    private static int[] permutation;

    public static void main(String[] args) throws IOException {
        getInput();
        findAndPrintNextPermu();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        permutation = new int[n];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            permutation[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findAndPrintNextPermu() {
        int first = getIndexToChangeFirst();
        if (first == -1) return;

        int second = getIndexToChangeSecond(first);

        swap(permutation, first, second);

        printNextPermu(first);
    }

    private static int getIndexToChangeFirst() {
        int first;
        for (first = n - 1; first > 0; first--) {
            if (permutation[first - 1] < permutation[first]) {
                break;
            }
        }
        first--;
        if (first == -1) {
            System.out.println(-1);
            return -1;
        }
        return first;
    }

    private static int getIndexToChangeSecond(int first) {
        int second;
        for (second = n - 1; second > first; second--) {
            if (permutation[first] < permutation[second]) {
                break;
            }
        }
        return second;
    }

    private static void swap(int[] permutation, int i, int j) {
        int temp = permutation[i];
        permutation[i] = permutation[j];
        permutation[j] = temp;
    }

    private static void printNextPermu(int first) {
        StringBuilder builder = new StringBuilder();
        Arrays.sort(permutation, first + 1, n);

        for (int i = 0; i < n; i++) {
            builder.append(permutation[i]).append(" ");
        }

        System.out.println(builder);
    }
}
