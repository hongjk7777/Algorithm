import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_6064 {
    private static int testcaseAmount;
    private static ArrayList<Testcase> testcases = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        getAndPrintKs();
    }

    private static void getAndPrintKs() {
        for (int testcaseNum = 0; testcaseNum < testcaseAmount; testcaseNum++) {
            int k = getK(testcaseNum);
            System.out.println(k);
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        testcaseAmount = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < testcaseAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int m = Integer.parseInt(tokenizer.nextToken());
            int n = Integer.parseInt(tokenizer.nextToken());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            testcases.add(new Testcase(m, n, x, y));
        }
    }

    private static int getK(int testcaseNum) {
        Testcase testcase = testcases.get(testcaseNum);
        int m = testcase.m;
        int n = testcase.n;
        int x = testcase.x;
        int y = testcase.y;

        int max = getLCM(m, n);
        int k;

        while (x != y && !overLimit(x, y, max)) {
            if (x < y) {
                x += m;
            } else {
                y += n;
            }
        }

        if (isNotValidYear(x, y)) {
            k = -1;
        } else {
            k = x;
        }

        return k;
    }

    private static int getLCM(int m, int n) {
        int temp = m;
        while (temp % n != 0) {
            temp += m;
        }
        return temp;
    }

    private static boolean overLimit(int x, int y, int max) {
        return x > max || y > max;
    }

    private static boolean isNotValidYear(int x, int y) {
        return x != y;
    }

    private static class Testcase {
        int m, n, x, y;

        public Testcase(int m, int n, int x, int y) {
            this.m = m;
            this.n = n;
            this.x = x;
            this.y = y;
        }
    }
}
