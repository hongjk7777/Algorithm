package BaekJoon;/*
배운 점
1. 자료구조(스택)의 활용이 아쉬웠다. 스택의 활용 시점에 대해 잘 모르는 것 같다.
2. 해당 풀이는 분할 정복을 쓰지 않았지만, 분할 정복에 대해 미숙해서
 분할 정복으로도 풀 수 있도록 공부해야 한다.

아쉬웠던 점
1. 경계값을 안 해봐서 int로는 다 담을 수 없는 것을 알아채지 못함
+ 굳이 경계값을 안 해봐도 수의 범위가 int로는 힘들다는 것을 충분히 알 수 있었음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BaekJoon_6549 {
    private static Testcase[] testcases = new Testcase[1000000];
    private static int testcaseAmount = 0;

    public static void main(String[] args) throws IOException {
        getInput();
        getMaxAreas();
        printMaxAreas();
    }


    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        while (true) {
            tokenizer = new StringTokenizer(reader.readLine());
            int size = Integer.parseInt(tokenizer.nextToken());
            long[] histogram = new long[size];

            if (size == 0) {
                break;
            }

            for (int i = 0; i < size; i++) {
                histogram[i] = Integer.parseInt(tokenizer.nextToken());
            }

            testcases[testcaseAmount] = new Testcase(size, histogram);
            testcaseAmount++;
        }
    }

    private static void getMaxAreas() {
        for (int i = 0; i < testcaseAmount; i++) {
            getMaxArea(i);
        }
    }

    private static void getMaxArea(int num) {
        Testcase testcase = testcases[num];
        int size = testcase.size;
        long[] height = testcase.height;
        int[] right = testcase.right;
        int[] left = testcase.left;
        int lastHeight = 0;
        Stack<Integer> remainingIndex = new Stack<>();
        remainingIndex.push(0);

        for (int curIndex = 1; curIndex <= size; curIndex++) {
            while (!remainingIndex.isEmpty() &&
                    (curIndex == size || height[remainingIndex.lastElement()] > height[curIndex])) {
                int index = remainingIndex.pop();

                int width;
                if (remainingIndex.isEmpty()) {
                    width = curIndex;
                } else {
                    width = (curIndex - remainingIndex.lastElement()) - 1;
                }
                long area = width * height[index];
                testcase.maxArea = Math.max(testcase.maxArea, area);
            }

            remainingIndex.push(curIndex);
        }


    }

    private static void printMaxAreas() {
        for (int i = 0; i < testcaseAmount; i++) {
            printMaxArea(i);
        }
    }

    private static void printMaxArea(int testcaseNum) {
        Testcase testcase = testcases[testcaseNum];
        System.out.println(testcase.maxArea);
    }

    private static class Testcase {
        int size;
        long[] height;
        int[] left, right;
        long maxArea = 0;

        public Testcase(int size, long[] height) {
            this.size = size;
            this.height = height;
            left = new int[size];
            right = new int[size];
        }

    }
}
