package BaekJoon;/*
출력이 시간이 오래걸리는 작업이란 걸 잊지 말자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11728 {
    private static int n, m;
    private static int[] arr1, arr2;

    public static void main(String[] args) throws IOException {
        getInput();
        printResult();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());
        arr1 = new int[n];
        arr2 = new int[m];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void printResult() {
        int arr1Index = 0, arr2Index = 0;
        StringBuilder builder = new StringBuilder();

        while (!isEnd(arr1Index, arr2Index)) {
            if (arr1Index == n) {
                builder.append(arr2[arr2Index]).append(" ");
                arr2Index++;
            } else if (arr2Index == m) {
                builder.append(arr1[arr1Index]).append(" ");
                arr1Index++;
            } else if (arr1[arr1Index] < arr2[arr2Index]) {
                builder.append(arr1[arr1Index]).append(" ");
                arr1Index++;
            } else {
                builder.append(arr2[arr2Index]).append(" ");
                arr2Index++;
            }
        }
        System.out.println(builder);
    }

    private static boolean isEnd(int arr1Index, int arr2Index) {
        return arr1Index == n && arr2Index == m;
    }
}
