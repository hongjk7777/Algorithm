import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16967 {
    private static int h, w, x, y;
    private static int[][] arrA, arrB;

    public static void main(String[] args) throws IOException {
        getInput();
        findArrA();
        printArrA();
    }

    private static void printArrA() {
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                System.out.print(arrA[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        h = Integer.parseInt(tokenizer.nextToken());
        w = Integer.parseInt(tokenizer.nextToken());
        x = Integer.parseInt(tokenizer.nextToken());
        y = Integer.parseInt(tokenizer.nextToken());
        arrA = new int[h][w];
        arrB = new int[h + x][w + y];

        for (int row = 0; row < h + x; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < w + y; col++) {
                arrB[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void findArrA() {
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                int temp;
                if (isInArea(row - x, col - y)) {
                    temp = arrB[row][col] - arrA[row - x][col - y];
                } else {
                    temp = arrB[row][col];
                }
                arrA[row][col] = temp;
            }
        }
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < h && 0 <= col && col < w;
    }
}
