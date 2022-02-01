/*
아쉬운 점
1. 변수의 개수를 잘 확인해보자
ㄴ이 문제를 처음 풀 때 dp를 일차원 배열로 만들었다.
  그러나 복사된 이모티콘의 개수, 현재 이모티콘의 개수 모두 들어가 있어야 했다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_14226 {
    private static final int INT_MAX = 987654321, MAX = 1000;
    private static int s;
    private static int[][] dp = new int[MAX + 1][MAX + 1];
    private static final boolean[][] visited = new boolean[MAX + 1][MAX + 1];

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        s = Integer.parseInt(tokenizer.nextToken());
    }

    private static void makeDP() {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(1, 0));
        visited[1][0] = true;
        int time = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair poll = queue.poll();
                int curNum = poll.emoticonNum;
                int copiedSize = poll.copiedSize;

                if (updateDP(curNum - 1, copiedSize, time)) {
                    queue.add(new Pair(curNum - 1, copiedSize));
                }
                if (updateDP(curNum + copiedSize, copiedSize, time)) {
                    queue.add(new Pair(curNum + copiedSize, copiedSize));
                }
                if (copiedSize != curNum) {
                    queue.add(new Pair(curNum, curNum));
                }
            }
            time++;
        }
    }

    private static boolean updateDP(int emoticonNum, int copiedSize, int time) {
        if (!isInArea(emoticonNum)) {
            return false;
        }
        if (!visited[emoticonNum][copiedSize]) {
            visited[emoticonNum][copiedSize] = true;
            dp[emoticonNum][copiedSize] = time;
            return true;
        }
        if (time < dp[emoticonNum][copiedSize]) {
            dp[emoticonNum][copiedSize] = time;
            return true;
        }
        return false;
    }

    private static boolean isInArea(int emoticonNum) {
        return 0 <= emoticonNum && emoticonNum <= MAX;
    }

    private static void printAnswer() {
        int minTime = getMinTime(s);
        System.out.println(minTime);
    }

    private static int getMinTime(int emoticonNum) {
        int min = INT_MAX;

        for (int i = 1; i <= emoticonNum; i++) {
            int tempMin = dp[emoticonNum][i];
            if (tempMin < min) {
                min = tempMin;
            }
        }

        return min;
    }

    private static class Pair {
        int emoticonNum, copiedSize;

        public Pair(int emoticonNum, int copiedSize) {
            this.emoticonNum = emoticonNum;
            this.copiedSize = copiedSize;
        }
    }
}
