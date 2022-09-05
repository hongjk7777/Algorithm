

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static Node[] nodes;
    private static int nodeSize;

    public static void main(String[] args) throws IOException {
        getInput();

        for (int nodeNum = 0; nodeNum < nodeSize; nodeNum++) {
            if(checkNode(nodeNum)){
                System.out.println(nodeNum + 1);
                return;
            }
        }

        //없을 경우
        System.out.println(-1);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        nodeSize = Integer.parseInt(tokenizer.nextToken());
        nodes = new Node[nodeSize];

        initializeNodes(nodeSize);
        addNodeLinks(reader, nodeSize);
    }

    private static void initializeNodes(int nodeSize) {
        for (int i = 0; i < nodeSize; i++) {
            nodes[i] = new Node();
        }
    }

    private static void addNodeLinks(BufferedReader reader, int nodeSize) throws IOException {
        StringTokenizer tokenizer;
        for (int i = 0; i < nodeSize - 1; i++) {
            tokenizer = new StringTokenizer(reader.readLine());

            int end = Integer.parseInt(tokenizer.nextToken());
            int start = Integer.parseInt(tokenizer.nextToken());

            nodes[start - 1].addNode(end - 1);
        }
    }

    private static boolean checkNode(int nodeNum) {
        boolean[] checked = new boolean[nodeSize];
        checked[nodeNum] = true;

        ArrayList<Integer> linkedNode = nodes[nodeNum].getLinkedNode();

        findNodes(checked, nodeNum);

        return isCorrect(checked);
    }



    private static void findNodes(boolean[] checked, int nodeNum) {
        ArrayList<Integer> linkedNode = nodes[nodeNum].getLinkedNode();

        for (int i = 0; i < linkedNode.size(); i++) {
            Integer nextNode = linkedNode.get(i);
            if(!checked[nextNode]){
                checked[nextNode] = true;
                findNodes(checked, nextNode);
            }
        }
    }

    private static boolean isCorrect(boolean[] checked) {
        for (int i = 0; i < checked.length; i++) {
            if (!checked[i]){
                return false;
            }
        }
        return true;
    }
    private static class Node {
        ArrayList<Integer> linkedNode = new ArrayList<>();

        public void addNode(int n){
            linkedNode.add(n);
        }

        public ArrayList<Integer> getLinkedNode() {
            return linkedNode;
        }
    }
}
