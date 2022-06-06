package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_1707 {
    private static final int BLUE = 1, RED = 2;
    private static int testcaseNum, edgeNum, nodeNum, graphNode = 0;
    private static int[] colored;
    private static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        StringBuilder builder = new StringBuilder();

        tokenizer = new StringTokenizer(reader.readLine());
        testcaseNum = Integer.parseInt(tokenizer.nextToken());
        while (testcaseNum-- > 0) {
            tokenizer = new StringTokenizer(reader.readLine());
            nodeNum = Integer.parseInt(tokenizer.nextToken());
            edgeNum = Integer.parseInt(tokenizer.nextToken());
            colored = new int[nodeNum];
            nodes = new Node[nodeNum];

            for (int i = 0; i < nodeNum; i++) {
                nodes[i] = new Node();
            }

            boolean biGraph = false;


            for (int i = 0; i < edgeNum; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                int node1 = Integer.parseInt(tokenizer.nextToken()) - 1;
                int node2 = Integer.parseInt(tokenizer.nextToken()) - 1;
                nodes[node1].addLink(node2);
                nodes[node2].addLink(node1);
            }

            if (isBiGraph()) {
                builder.append("YES").append("\n");
            } else {
                builder.append("NO").append("\n");
            }

            graphNode = 0;
        }
        System.out.println(builder);
    }

    private static boolean isBiGraph() {

        while (graphNode != nodeNum) {
            int start = -1;
            for (int i = 0; i < nodeNum; i++) {
                if (colored[i] == 0) {
                    start = i;
                    break;
                }
            }
            Queue<Integer> nodeQueue = new LinkedList<>();
            nodeQueue.add(start);
            colored[start] = BLUE;
            graphNode++;

            while (!nodeQueue.isEmpty()) {
                int poll = nodeQueue.poll();
                Node node = nodes[poll];
                int color = colored[poll];
                for (int i = 0; i < node.links.size(); i++) {
                    int linkNode = node.links.get(i);
                    if (colored[linkNode] == 0) {
                        nodeQueue.add(linkNode);
                        colored[linkNode] = getAnotherColor(color);
                        graphNode++;
                    } else if (colored[linkNode] == color) {
                        return false;
                    }
                }
            }
        }


        return true;
    }

    private static int getAnotherColor(int color) {
        if (color == BLUE) {
            return RED;
        } else {
            return BLUE;
        }
    }

    private static class Node {
        ArrayList<Integer> links = new ArrayList<>();

        public void addLink(int link) {
            links.add(link);
        }
    }

    private static class Location {
        int node, color;

        public Location(int node, int color) {
            this.node = node;
            this.color = color;
        }
    }
}
