/*
배운 점
1. two pointer라는 새로운 방식을 배웠다.
ㄴ 수열 중 음수가 있으면 불가능하다(방식을 생각하면 당연할지도)
ㄴ 까먹지 않고 잘 써먹을 수 있도록 하자!
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1806 {
    private static int sequenceSize, goalSum, min = 0;
    private static int[] sequence;

    public static void main(String[] args) throws IOException {
        getInput();
        findMinLengthToGoalSum();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        sequenceSize = Integer.parseInt(tokenizer.nextToken());
        goalSum = Integer.parseInt(tokenizer.nextToken());
        sequence = new int[sequenceSize];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < sequenceSize; i++) {
            sequence[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void findMinLengthToGoalSum() {
        //two pointer 방식을 이용
        int startPointer = 0, endPointer = 0, minLength = 0;
        int curSum = sequence[0];

        while (startPointer < sequenceSize) {
            if (curSum < goalSum) {
                if (endPointer == sequenceSize - 1) {
                    curSum -= sequence[startPointer++];
                } else {
                    curSum += sequence[++endPointer];
                }
            } else {
                int length = endPointer - startPointer + 1;
                updateMin(length);
                curSum -= sequence[startPointer++];
            }
        }
    }

    private static void updateMin(int temp) {
        if (min == 0) {
            min = temp;
        } else {
            min = Math.min(min, temp);
        }
    }

}
