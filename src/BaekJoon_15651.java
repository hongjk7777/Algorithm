/*
아쉬웠던 점
1. 출력이 많은 거 같으면 제발 한 번에 출력하자
ㄴ 아니면 모든 문제에서 출력을 한 번에 할까?
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_15651 {
    private static int n, m;
    private static ArrayList<Integer> sequence = new ArrayList<>();
    private static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        getInput();
        appendAllSequences(n, m);
        System.out.println(builder);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
    }

    private static void appendAllSequences(int n, int m) {
        if (sequence.size() == m) {
            appendSequence();
            return;
        }

        for (int i = 1; i <= n; i++) {
            sequence.add(i);
            appendAllSequences(n, m);
            sequence.remove(sequence.size() - 1);
        }
    }

    private static void appendSequence() {

        for (int i = 0; i < m; i++) {
            builder.append(sequence.get(i)).append(" ");
        }
        builder.append("\n");
    }
}
