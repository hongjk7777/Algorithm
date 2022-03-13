import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_12869 {
    private static final int INT_MAX = 987654321;
    private static final int MAX = 60;
    private static final int[] scv = new int[3];
    private static final int[][][] scvDP = new int[MAX + 1][MAX + 1][MAX + 1];

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        int scvAmount = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < scvAmount; i++) {
            scv[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        makeScvDP();
    }

    private static void makeScvDP() {
        initializeDP();

        for (int scv1 = 0; scv1 <= 60; scv1++) {
            for (int scv2 = 0; scv2 <= 60; scv2++) {
                for (int scv3 = 0; scv3 <= 60; scv3++) {
                    int min = INT_MAX;
                    scvDP[scv1][scv2][scv3] = INT_MAX;

                    min = getMin(scv1, scv2, scv3, min);

                    fillDP(scv1, scv2, scv3, min);
                }
            }
        }
    }

    private static void initializeDP() {
        for (int scv1 = 0; scv1 <= 60; scv1++) {
            for (int scv2 = 0; scv2 <= 60; scv2++) {
                for (int scv3 = 0; scv3 <= 60; scv3++) {
                    scvDP[scv1][scv2][scv3] = INT_MAX;
                }
            }
        }
    }

    private static int getMin(int scv1, int scv2, int scv3, int min) {
        min = Math.min(min, attack(scv1, scv2, scv3));
        min = Math.min(min, attack(scv1, scv3, scv2));
        min = Math.min(min, attack(scv2, scv1, scv3));
        min = Math.min(min, attack(scv2, scv3, scv1));
        min = Math.min(min, attack(scv3, scv1, scv2));
        min = Math.min(min, attack(scv3, scv2, scv1));
        return min;
    }

    private static int attack(int first, int second, int third) {
        if (first == 0 && second == 0 && third == 0) {
            return 0;
        }

        first = first >= 9 ? first - 9 : 0;
        second = second >= 3 ? second - 3 : 0;
        third = third >= 1 ? third - 1 : 0;

        return scvDP[first][second][third] + 1;
    }

    private static void fillDP(int scv1, int scv2, int scv3, int min) {
        scvDP[scv1][scv2][scv3] = min;
        scvDP[scv1][scv3][scv2] = min;
        scvDP[scv2][scv1][scv3] = min;
        scvDP[scv2][scv3][scv1] = min;
        scvDP[scv3][scv1][scv2] = min;
        scvDP[scv3][scv2][scv1] = min;
    }

    private static void printAnswer() {
        System.out.println(scvDP[scv[0]][scv[1]][scv[2]]);
    }
}
