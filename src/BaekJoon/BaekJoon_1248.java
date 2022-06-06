package BaekJoon;/*
아쉬운 점
1. 문제를 읽고 이에 따른 풀이를 생각하는데 너무 오래 걸림
ㄴ 원소를 하나씩 정하면서 푼다는 생각은 충분히 빨리 생각할 수 있었는데 아쉬움
ㄴ 비슷한 문제를 더 풀어보자

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_1248 {
    private static int size;
    private static boolean end = false;
    private static char[][] pSum;
    private static Limit[] limits;
    private static ArrayList<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        initializeLimits();
        findAndPrintAnswer(0);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        limits = new Limit[size];
        pSum = new char[size][size];

        tokenizer = new StringTokenizer(reader.readLine());
        String line = tokenizer.nextToken();

        int temp = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                pSum[i][j] = line.charAt(temp++);
            }
        }
    }

    private static void initializeLimits() {
        for (int i = 0; i < size; i++) {
            limits[i] = new Limit(i);
        }
    }

    private static void findAndPrintAnswer(int depth) {
        //수열의 원소를 하나씩 정하여 그에 따른 limit을 재귀함수로 모두 탐색한다
        if (depth == size){
            printAnswer();
            end = true;
            return;
        }
        
        limits[depth].initialize(depth);

        makeLimitsMeetCriteria(depth);

        for (int i = limits[depth].minLimit; i <= limits[depth].maxLimit; i++) {
            answer.add(i);
            if (!end){
                findAndPrintAnswer(depth + 1);
            }
            answer.remove(answer.size() - 1);
        }
    }

    private static void printAnswer() {
        for (int num : answer) {
            System.out.print(num + " ");
        }
    }

    private static void makeLimitsMeetCriteria(int depth) {
        int lastSum = 0;

        for (int row = depth - 1; row >= 0; row--) {
            lastSum += answer.get(row);
            if (pSum[row][depth] == '+'){
                limits[depth].updateMinLimit(1 - lastSum);
            } else if (pSum[row][depth] == '-'){
                limits[depth].updateMaxLimit(-1 - lastSum);
            } else {
                limits[depth].setMinMaxLimit(-1 * lastSum);
            }
        }
    }

    private static class Limit {
        int maxLimit, minLimit;

        public Limit(int index) {

        }

        public void initialize(int depth){
            if (pSum[depth][depth] == '+'){
                this.maxLimit = 10;
                this.minLimit = 1;
            } else if (pSum[depth][depth] == '-'){
                this.maxLimit = -1;
                this.minLimit = -10;
            } else {
                this.maxLimit = 0;
                this.minLimit = 0;
            }
        }

        public void updateMinLimit(int tempMin) {
            minLimit = Math.max(minLimit, tempMin);
        }

        public void updateMaxLimit(int tempMax) {
            maxLimit = Math.min(maxLimit, tempMax);
        }

        public void setMinMaxLimit(int value) {
            if (minLimit <= value && value <= maxLimit) {
                minLimit = value;
                maxLimit = value;
            } else {
                minLimit = 1;
                maxLimit = 0;
            }
        }
    }
}
