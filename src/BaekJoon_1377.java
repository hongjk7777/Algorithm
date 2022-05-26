import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
풀이방법
1. 버블 소트 알고리즘은 한 원소가 왼쪽으로 이동하는 것은 한 번의 루프 당 한 번 밖에 못함
2. 위를 이용해서 최소 몇 번 왼쪽으로 가야하는지 확인한다.
 */
public class BaekJoon_1377 {
    private static int size;
    private static int[] arr, sortedArr;
    private static final HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        getInput();
        Arrays.sort(sortedArr);
        makeMap();
        int answer = getAnswer();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        arr = new int[size];
        sortedArr = new int[size];

        for (int i = 0; i < size; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int input = Integer.parseInt(tokenizer.nextToken());
            arr[i] = input;
            sortedArr[i] = input;
        }
    }

    private static void makeMap() {
        for (int curIndex = 0; curIndex < size; curIndex++) {
            int value = sortedArr[curIndex];

            if (map.containsKey(value)) {
                map.replace(value, curIndex);
            } else {
                map.put(value, curIndex);
            }
        }
    }

    private static int getAnswer() {
        int max = 0;

        for (int curIndex = 0; curIndex < size; curIndex++) {
            int value = arr[curIndex];
            int sortedIndex = map.get(value);

            if (curIndex > sortedIndex) {
                max = Math.max(max, curIndex - sortedIndex);
            }
        }

        return max + 1;
    }
}
