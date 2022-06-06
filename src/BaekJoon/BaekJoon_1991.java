package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1991 {
    private static Node[] nodes;

    public static void main(String[] args) throws IOException {
        getInput();
        printPreOrder(0);
        System.out.println();
        printInOrder(0);
        System.out.println();
        printPostOrder(0);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        int nodeAmount = Integer.parseInt(tokenizer.nextToken());
        nodes = new Node[nodeAmount];

        for (int i = 0; i < nodeAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int index = tokenizer.nextToken().charAt(0) - 'A';
            int left = tokenizer.nextToken().charAt(0) - 'A';
            int right = tokenizer.nextToken().charAt(0) - 'A';
            nodes[index] = new Node(index, left, right);
        }
    }

    private static void printPreOrder(int nodeNum) {
        Node curNode = nodes[nodeNum];
        System.out.print((char) ('A' + nodeNum));
        if (curNode.left != '.' - 'A') {
            printPreOrder(curNode.left);
        }
        if (curNode.right != '.' - 'A') {
            printPreOrder(curNode.right);
        }
    }

    private static void printInOrder(int nodeNum) {
        Node curNode = nodes[nodeNum];
        if (curNode.left != '.' - 'A') {
            printInOrder(curNode.left);
        }
        System.out.print((char) ('A' + nodeNum));
        if (curNode.right != '.' - 'A') {
            printInOrder(curNode.right);
        }
    }

    private static void printPostOrder(int nodeNum) {
        Node curNode = nodes[nodeNum];
        if (curNode.left != '.' - 'A') {
            printPostOrder(curNode.left);
        }
        if (curNode.right != '.' - 'A') {
            printPostOrder(curNode.right);
        }
        System.out.print((char) ('A' + nodeNum));
    }

    private static class Node {
        int index, left , right;

        public Node(int index, int left, int right) {
            this.index = index;
            this.left = left;
            this.right = right;
        }
    }
}
