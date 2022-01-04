import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_11066 {
    private static int testcaseAmount, fileAmount;
    private static Testcase[] testcases;

    public static void main(String[] args) throws IOException {
        getInput();
        findMinAddCosts();
        printMinAddCosts();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        testcaseAmount = Integer.parseInt(tokenizer.nextToken());
        testcases = new Testcase[testcaseAmount];

        for (int testcaseNum = 0; testcaseNum < testcaseAmount; testcaseNum++) {
            tokenizer = new StringTokenizer(reader.readLine());
            fileAmount = Integer.parseInt(tokenizer.nextToken());
            ArrayList<Integer> file = new ArrayList<>();

            tokenizer = new StringTokenizer(reader.readLine());
            for (int fileNum = 0; fileNum < fileAmount; fileNum++) {
                int input = Integer.parseInt(tokenizer.nextToken());
                file.add(input);
            }

            testcases[testcaseNum] = new Testcase(file);
        }
    }

    private static void findMinAddCosts() {
        for (int testcaseNum = 0; testcaseNum < testcaseAmount; testcaseNum++) {
            findMinAddCost(testcaseNum);
        }
    }

    private static void findMinAddCost(int testcaseNum) {
        Testcase testcase = testcases[testcaseNum];
        ArrayList<Integer> file = testcase.file;
        int size = testcase.size;
        int[][] dp = testcase.dp;
        int[] psum = testcase.psum;
        int cost = 0;

        for (int i = 1; i <= size; i++) {
            psum[i] = psum[i - 1] + file.get(i - 1);
        }

        for (int fileAmount = 1; fileAmount <= size; fileAmount++) {
            for (int startFile = 1; startFile <= size - fileAmount; startFile++) {
                int endFile = startFile + fileAmount;
                dp[startFile][endFile] = 987654321;

                for (int fileIndex = startFile; fileIndex < endFile; fileIndex++) {
                    dp[startFile][endFile] = Math.min(dp[startFile][endFile],
                            dp[startFile][fileIndex] + dp[fileIndex + 1][endFile] + psum[endFile] - psum[startFile - 1]);
                }

            }
        }

    }

    private static void printMinAddCosts() {
        for (int testcaseNum = 0; testcaseNum < testcaseAmount; testcaseNum++) {
            printMinAddCost(testcaseNum);
        }
    }

    private static void printMinAddCost(int testcaseNum) {
        Testcase testcase = testcases[testcaseNum];
        System.out.println(testcase.dp[1][testcase.size]);
    }

    private static class Testcase {
        ArrayList<Integer> file;
        int size;
        int[][] dp;
        int[] psum;

        public Testcase(ArrayList<Integer> file) {
            this.file = file;
            this.size = file.size();
            dp = new int[file.size() + 1][file.size() + 1];
            psum = new int[file.size() + 1];
        }
    }
}
