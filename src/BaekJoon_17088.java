import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_17088 {
    private static int size;
    public static int answer = -1;
    private static int[] sequence;

    public static void main(String[] args) throws IOException {
        getInput();
        findMinCalculate();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        sequence = new int[size];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findMinCalculate() {
        if (size == 1) {
            answer = 0;
            return;
        }

        int calculNum = 0;

        for (int i = -1; i <= 1; i++) {
            sequence[0] += i;
            if (i != 0) {
                calculNum++;
            }

            for (int j = -1; j <= 1; j++) {
                sequence[1] += j;
                if (j != 0) {
                    calculNum++;
                }

                int commonDiff = sequence[1] - sequence[0];
                int tempMin = getCalculateNum(commonDiff, calculNum);

                updateMin(tempMin);

                if (j != 0) {
                    calculNum--;
                }
                sequence[1] -= j;
            }

            if (i != 0) {
                calculNum--;
            }
            sequence[0] -= i;
        }
    }

    private static int getCalculateNum(int commonDiff, int calculNum) {
        int curValue = sequence[1] + commonDiff;

        for (int i = 2; i < size; i++) {
            if (Math.abs(sequence[i] - curValue) == 0) {
                curValue += commonDiff;
            } else if (Math.abs(sequence[i] - curValue) == 1) {
                calculNum++;
                curValue += commonDiff;
            } else {
                return -1;
            }
        }
        return calculNum;
    }

    private static void updateMin(int tempMin) {
        if (tempMin >= 0) {
            if (answer == -1) {
                answer = tempMin;
            } else {
                answer = Math.min(answer, tempMin);
            }
        }
    }
}
