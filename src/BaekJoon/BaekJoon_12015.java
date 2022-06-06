package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_12015 {
    private static int size;
    private static int[] arr;
    private static final ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        makeList();
        System.out.println(list.size());
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        arr = new int[size];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeList() {
        list.add(arr[0]);

        for (int i = 1; i < size; i++) {
            int value = arr[i];
            if (value > list.get(list.size() - 1)) {
                list.add(value);
            } else {
                int left = 0, right = list.size() - 1;

                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (list.get(mid) >= value) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                list.set(right, value);
            }

        }
    }
}
