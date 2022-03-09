/*
아쉬운 점
1. 이 문제를 풀 때 abc 세 개의 변수가 모두 1000이상이 된다면 dp를 하는데 모자랄거라
생각했다. 하지만 a + b + c = k(k는 상수) 가 성립하기에 두 개의 변수로만도 dp를 만들
수 있었다.
ㄴ 앞으로의 사고방향은 bfs 사용해야될 거 같은데 메모리가 모자라네? 어떻게 메모리를 줄이는
방법이 없나? 를 고민하는 방향으로 해야할듯. 이번엔 메모리가 커서 안 되겠다 하고 넘어가버림
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_12886 {
    private static int a, b, c;
    private static final boolean[][] checked = new boolean[2000][2000];

    public static void main(String[] args) throws IOException {
        getInput();
        if (canMakeSame()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        a = Integer.parseInt(tokenizer.nextToken());
        b = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
    }

    private static boolean canMakeSame() {
        if ((a + b + c) % 3 != 0) {
            return false;
        }

        int[] arr = new int[3];
        Queue<Stones> queue = new LinkedList<>();
        queue.add(new Stones(a, b, c));

        while (!queue.isEmpty()) {
            Stones poll = queue.poll();

            if (poll.a == poll.b && poll.b == poll.c) {
                return true;
            }

            arr[0] = poll.a;
            arr[1] = poll.b;
            arr[2] = poll.c;
            Arrays.sort(arr);

            tryAllWays(arr, queue);

        }

        return false;
    }

    private static void tryAllWays(int[] arr, Queue<Stones> queue) {
        int x = arr[0] * 2;
        int y = arr[1] - arr[0];
        if (!checked[x][y]) {
            checked[x][y] = true;
            queue.add(new Stones(x, y, arr[2]));
        }

        x = arr[1] * 2;
        y = arr[2] - arr[1];
        if (!checked[x][y]) {
            checked[x][y] = true;
            queue.add(new Stones(x, y, arr[0]));
        }

        x = arr[0] * 2;
        y = arr[2] - arr[0];
        if (!checked[x][y]) {
            checked[x][y] = true;
            queue.add(new Stones(x, y, arr[1]));
        }
    }

    private static class Stones {
        int a, b, c;

        public Stones(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
}
