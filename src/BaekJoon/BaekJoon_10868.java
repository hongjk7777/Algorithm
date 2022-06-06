package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_10868 {
    private static int numberSize, rangeSize;
    private static int[] numbers;
    private static SegmentTree segmentTree;
    private static Range[] ranges;

    public static void main(String[] args) throws IOException {
        getInput();
        makeSegmentTree();
        printMinValueInRange();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        numberSize = Integer.parseInt(tokenizer.nextToken());
        rangeSize = Integer.parseInt(tokenizer.nextToken());
        numbers = new int[numberSize + 1];
        ranges = new Range[rangeSize];

        for (int i = 1; i <= numberSize; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            numbers[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < rangeSize; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());

            ranges[i] = new Range(start, end);
        }
    }

    private static void makeSegmentTree() {
        segmentTree = new SegmentTree(numberSize);
        segmentTree.init(numbers, 1, 1, numberSize);
    }

    private static void printMinValueInRange() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < rangeSize; i++) {
            Range range = ranges[i];
            builder.append(segmentTree.getMinValue(1, range.start, range.end, 1, numberSize))
                    .append("\n");
        }

        System.out.println(builder);
    }

    private static class SegmentTree {
        int[] tree;

        public SegmentTree(int n) {
            int treeHeight = getTreeHeight();
            int nodeCount = (int) Math.pow(2, treeHeight + 1);
            tree = new int[nodeCount];

            for (int i = 0; i < nodeCount; i++) {
                tree[i] = Integer.MAX_VALUE;
            }
        }

        private int getTreeHeight() {
            int height = 0;
            int tempSize = numberSize;

            while (tempSize > 0) {
                tempSize >>= 1;
                height++;
            }

            return height;
        }

        public int init(int[] arr, int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            } else {
                return tree[node] = Math.min(init(arr, node * 2, start, (start + end) / 2),
                        init(arr, node * 2 + 1, (start + end) / 2 + 1, end));
            }
        }

        public int getMinValue(int node, int start, int end, int left, int right) {
            if (end < left || right < start) {
                return Integer.MAX_VALUE;
            } else if (start <= left && right <= end) {
                return tree[node];
            } else {
                return Math.min(getMinValue(node * 2, start, end, left, (left + right) / 2),
                        getMinValue(node * 2 + 1, start, end, (left + right) / 2 + 1, right));
            }
        }
    }

    private static class Range {
        int start, end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
