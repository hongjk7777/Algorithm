package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16938 {
    private static int problemAmount, minDifficultySum, maxDifficultySum, difficultyGap;
    private static int answer = 0;
    private static int[] difficulty;
    private static ProblemSet currentProblems;

    public static void main(String[] args) throws IOException {
        getInput();
        checkAllWays(0);
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        problemAmount = Integer.parseInt(tokenizer.nextToken());
        minDifficultySum = Integer.parseInt(tokenizer.nextToken());
        maxDifficultySum = Integer.parseInt(tokenizer.nextToken());
        difficultyGap = Integer.parseInt(tokenizer.nextToken());
        difficulty = new int[problemAmount];
        currentProblems = new ProblemSet();

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < problemAmount; i++) {
            difficulty[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    //dfs방식으로 재귀호출을 통해 모든 경우 생각
    private static void checkAllWays(int depth) {
        if (depth == problemAmount) {
            return;
        }
        checkAllWays(depth + 1);

        currentProblems.addProblem(depth);
        if (currentProblems.isCorrectProblemSet()) {
            answer++;
        }
        checkAllWays(depth + 1);
        currentProblems.removeProblem(depth);
    }

    private static class ProblemSet {
        private static final int INT_MAX = 987654321;
        boolean[] added;
        int min, max, size, sum;

        public ProblemSet() {
            min = INT_MAX;
            max = 0;
            size = 0;
            sum = 0;
            added = new boolean[problemAmount];
        }

        private void addProblem(int num) {
            size++;
            added[num] = true;
            sum += difficulty[num];
            min = Math.min(min, difficulty[num]);
            max = Math.max(max, difficulty[num]);
        }

        private boolean isCorrectProblemSet() {
            return size > 1 && minDifficultySum <= sum &&
                    sum <= maxDifficultySum && difficultyGap <= (max - min);
        }

        private void removeProblem(int num) {
            size--;
            added[num] = false;
            sum -= difficulty[num];
            if (min == difficulty[num]) {
                min = findMin();
            }
            if (max == difficulty[num]) {
                max = findMax();
            }
        }

        private int findMin() {
            int min = INT_MAX;
            for (int i = 0; i < problemAmount; i++) {
                if (added[i]) {
                    min = Math.min(min, difficulty[i]);
                }
            }
            return min;
        }

        private int findMax() {
            int max = 0;
            for (int i = 0; i < problemAmount; i++) {
                if (added[i]) {
                    max = Math.max(max, difficulty[i]);
                }
            }
            return max;
        }
    }
}
