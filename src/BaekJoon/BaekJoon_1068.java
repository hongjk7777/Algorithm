package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_1068 {
    private static int eraseNodeNum, root;
    private static Node[] nodes;

    public static void main(String[] args) throws IOException {
        getInput();
        eraseNode();
        int answer = getLeafNodes();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        int nodeAmount = Integer.parseInt(tokenizer.nextToken());
        nodes = new Node[nodeAmount];

        for (int i = 0; i < nodeAmount; i++) {
            nodes[i] = new Node();
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < nodeAmount; i++) {
            int parent = Integer.parseInt(tokenizer.nextToken());
            if (parent == -1) {
                root = i;
            }
            else {
                nodes[parent].addNode(nodes[i]);
            }
        }

        tokenizer = new StringTokenizer(reader.readLine());
        eraseNodeNum = Integer.parseInt(tokenizer.nextToken());
    }

    private static void eraseNode() {
        for (Node node : nodes) {
            node.childNodes.remove(nodes[eraseNodeNum]);
        }
    }

    private static int getLeafNodes() {
        int sum = 0;

        Queue<Node> nodeQueue = new LinkedList<>();
        if (eraseNodeNum != root) {
            nodeQueue.add(nodes[root]);
        }

        while (!nodeQueue.isEmpty()) {
            Node poll = nodeQueue.poll();
            if (poll.isLeafNode()) {
                sum++;
            }

            nodeQueue.addAll(poll.childNodes);
        }

        return sum;
    }

    private static class Node {
        ArrayList<Node> childNodes = new ArrayList<>();

        public void addNode(Node childNode) {
            childNodes.add(childNode);
        }

        public boolean isLeafNode() {
            return childNodes.isEmpty();
        }
    }
}
