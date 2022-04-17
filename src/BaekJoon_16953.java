import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_16953 {
    private static int startNum, goalNum;

    public static void main(String[] args) throws IOException {
        getInput();
        int min = getMinCalculate();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        startNum = Integer.parseInt(tokenizer.nextToken());
        goalNum = Integer.parseInt(tokenizer.nextToken());
    }

    private static int getMinCalculate() {
        Queue<Long> queue = new LinkedList<>();
        queue.add((long) startNum);
        int min = 987654321;
        int calNum = 0;
        boolean success = false;

        if (startNum == goalNum) {
            return calNum + 1;
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            calNum++;

            for (int i = 0; i < size; i++) {

                long curNum = queue.poll();

                long multiplyNum = curNum * 2;
                if (multiplyNum * 2 == goalNum) {
                    queue.clear();
                    success = true;
                    min = Math.min(min, calNum);
                    break;
                }
                if (multiplyNum <= goalNum) {
                    queue.add(curNum * 2);
                }

                long addRight1 = curNum * 10 + 1;
                if (addRight1 == goalNum) {
                    queue.clear();
                    success = true;
                    min = Math.min(min, calNum);
                    break;
                }
                if (addRight1 <= goalNum) {
                    queue.add(curNum * 10 + 1);
                }
            }
        }

        if (success) {
            return calNum + 1;
        } else {
            return -1;
        }
    }
}
