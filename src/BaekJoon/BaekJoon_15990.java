package BaekJoon;/*
아쉬웠던 점
1. 일차원 배열만 만드는 것에 익숙해져 있다가 이차원배열을 통해 dp를 해야되는
아이디어를 너무 늦게 떠올려 시간을 너무 많이 잡아먹음
2. 이 문제에서는 MOD가 int범위를 아슬아슬하게 지키는 범위라 마지막에 3개를 더했을 경우
오버플로우가 발생할 수 있는데 이를 생각하지 못함
ㄴ 앞으로는 int범위를 아슬아슬하게 지키는 경우 long으로 편하게 해도 될 듯
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_15990 {
    private static final int MAX = 1000000;
    private static final int MOD = 1000000009;
    private static int t;
    private static int[] input, dpStart1, dpStart2, dpStart3;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDPs();
        printAnswers();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        t = Integer.parseInt(tokenizer.nextToken());
        input = new int[t];
        dpStart1 = new int[MAX];
        dpStart2 = new int[MAX];
        dpStart3 = new int[MAX];

        for (int i = 0; i < t; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            input[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDPs() {
        initializeDPs();
        for (int i = 4; i < MAX; i++) {
            dpStart1[i] = (dpStart2[i - 1] + dpStart3[i - 1]) % MOD;
            dpStart2[i] = (dpStart1[i - 2] + dpStart3[i - 2]) % MOD;
            dpStart3[i] = (dpStart1[i - 3] + dpStart2[i - 3]) % MOD;
        }
    }

    private static void initializeDPs() {
        initializeDP1();
        initializeDP2();
        initializeDP3();
    }

    private static void initializeDP1() {
        dpStart1[1] = 1;
        dpStart1[2] = 0;
        dpStart1[3] = 1;
    }

    private static void initializeDP2() {
        dpStart2[1] = 0;
        dpStart2[2] = 1;
        dpStart2[3] = 1;
    }
    
    private static void initializeDP3() {
        dpStart3[1] = 0;
        dpStart3[2] = 0;
        dpStart3[3] = 1;
    }

    private static void printAnswers() {
        for (int testcaseNum = 0; testcaseNum < t; testcaseNum++) {
            printAnswer(testcaseNum);
        }
    }

    private static void printAnswer(int testcaseNum) {
        int temp = input[testcaseNum];
        int answer = ((dpStart1[temp] + dpStart2[temp]) % MOD + dpStart3[temp]) % MOD;
        System.out.println(answer);
    }
}
