/*
아쉬웠던 점
1. System.out.println은 시간이 오래 걸리기 때문에 출력이 잦으면
StringBuilder를 사용해야 하는데 이를 간과해 시간이 오래 걸림
2. 예외 케이스 생각이 부족함
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_10942 {
    private static int sequenceSize, questionSize;
    private static int[] sequence;
    private static int[][] question;
    private static boolean[][] pelindrome;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printAnswerOfQuestions();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        sequenceSize = Integer.parseInt(tokenizer.nextToken());
        sequence = new int[sequenceSize];
        pelindrome = new boolean[sequenceSize][sequenceSize];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < sequenceSize; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        questionSize = Integer.parseInt(tokenizer.nextToken());
        question = new int[questionSize][2];

        for (int i = 0; i < questionSize; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            question[i][0] = Integer.parseInt(tokenizer.nextToken()) - 1;
            question[i][1] = Integer.parseInt(tokenizer.nextToken()) - 1;
        }
    }

    private static void makeDP() {
        for (int start = 0; start < sequenceSize; start++) {
            int limit = Math.min(start, sequenceSize - 1 - start);
            limit = Math.min(limit, (sequenceSize - 1) / 2);
            for (int size = 0; size <= limit; size++) {
                if (sequence[start - size] != sequence[start + size]) {
                    break;
                } else {
                    pelindrome[start - size][start + size] = true;
                }
            }
            for (int size = 0; size <= limit; size++) {
                if (start + 1 + size >= sequenceSize) {
                    break;
                }
                if (sequence[start - size] != sequence[start + 1 + size]) {
                    break;
                } else {
                    pelindrome[start - size][start + 1 + size] = true;
                }
            }
        }
    }

    private static void printAnswerOfQuestions() {
        StringBuilder builder = new StringBuilder();
        for (int num = 0; num < questionSize; num++) {
            builder.append(printAnswerOfQuestion(num));
            if (num != questionSize - 1) {
                builder.append("\n");
            }
        }
        System.out.println(builder);
    }

    private static int printAnswerOfQuestion(int num) {
        int start = question[num][0];
        int end = question[num][1];

        if (pelindrome[start][end]) {
            return 1;
        } else {
            return 0;
        }
    }
}
