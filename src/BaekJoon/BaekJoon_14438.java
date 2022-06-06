package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_14438 {
    private static int arrSize, querySize;
    private static int[] arr;
    private static Query[] queries;
    private static SegmentTree segmentTree;
    private static StringBuilder builder = new StringBuilder();


    public static void main(String[] args) throws IOException {
        getInput();
        makeSegmentTree();
        executeQueries();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        arrSize = Integer.parseInt(tokenizer.nextToken());
        arr = new int[arrSize + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= arrSize; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        tokenizer = new StringTokenizer(reader.readLine());
        querySize = Integer.parseInt(tokenizer.nextToken());
        queries = new Query[querySize];

        for (int i = 0; i < querySize; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int type = Integer.parseInt(tokenizer.nextToken());
            int value1 = Integer.parseInt(tokenizer.nextToken());
            int value2 = Integer.parseInt(tokenizer.nextToken());
            queries[i] = new Query(type, value1, value2);
        }
    }

    private static void makeSegmentTree() {
        segmentTree = new SegmentTree(arrSize);
        segmentTree.init(1, arr, 1, arrSize);
    }

    private static void executeQueries() {
        for (int i = 0; i < querySize; i++) {
            executeQuery(i);
        }
        System.out.println(builder);
    }

    private static void executeQuery(int num) {
        Query query = queries[num];

        if (query.type == 1) {
            segmentTree.changeValue(1, query.value1, query.value2, 1, arrSize);
        } else {
            int minValue = segmentTree.getMinValue(1, query.value1, query.value2, 1, arrSize);
            builder.append(minValue).append("\n");
        }
    }

    private static class Query {
        int type, value1, value2;

        public Query(int type, int value1, int value2) {
            this.type = type;
            this.value1 = value1;
            this.value2 = value2;
        }
    }

    private static class SegmentTree {
        int[] tree;

        public SegmentTree(int size) {
            int treeHeight = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
            int treeSize = (int) Math.pow(2, treeHeight + 1);
            tree = new int[treeSize];

            for (int i = 0; i < treeSize; i++) {
                tree[i] = Integer.MAX_VALUE;
            }
        }

        public int init(int node, int[] arr, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            } else {
                return tree[node] = Math.min(init(2 * node, arr, start, (start + end) / 2),
                        init(2 * node + 1, arr, (start + end) / 2 + 1, end));
            }
        }

        public int getMinValue(int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
                return Integer.MAX_VALUE;
            } else if (start <= left && right <= end) {
                return tree[node];
            } else {
                return Math.min(getMinValue(2 * node, start, end, left, (left + right) / 2),
                        getMinValue(2 * node + 1, start, end, (left + right) / 2 + 1, right));
            }
        }

        public int changeValue(int curNode, int changeNode, int value, int left, int right) {
            if (left == right && left == changeNode) {
                return tree[curNode] = value;
            } else if (left <= changeNode && changeNode <= right) {
                return tree[curNode] = Math.min(changeValue(curNode * 2, changeNode, value, left, (left + right) / 2),
                        changeValue(curNode * 2 + 1, changeNode, value, (left + right) / 2 + 1, right));
            } else {
                return tree[curNode];
            }
        }
    }
}
