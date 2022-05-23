import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_11725 {
    private static int size;
    private static Node[] nodes;

    public static void main(String[] args) throws IOException {
        getInput();
        findParentOfNodes();
        printParents();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        initializeNodes(size);

        for (int i = 0; i < size - 1; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int node1Num = Integer.parseInt(tokenizer.nextToken());
            int node2Num = Integer.parseInt(tokenizer.nextToken());

            nodes[node1Num - 1].addAdjacentNode(node2Num - 1);
            nodes[node2Num - 1].addAdjacentNode(node1Num - 1);
        }
    }

    private static void initializeNodes(int size) {
        nodes = new Node[size];

        for (int nodeNum = 0; nodeNum < size; nodeNum++) {
            nodes[nodeNum] = new Node(nodeNum);
        }
    }

    private static void findParentOfNodes() {
        Node root = nodes[0];
        root.markParent(-1);
    }

    private static void printParents() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < size; i++) {
            builder.append(nodes[i].parent + 1).append("\n");
        }
        System.out.println(builder);
    }

    private static class Node {
        int parent, nodeNum;
        ArrayList<Integer> adjacentList = new ArrayList<>();

        public Node(int nodeNum) {
            this.nodeNum = nodeNum;
        }

        public void addAdjacentNode(int nodeNum) {
            adjacentList.add(nodeNum);
        }

        public void markParent(int parentNum) {
            this.parent = parentNum;

            for (int childNum : this.adjacentList) {
                if (childNum != parentNum) {
                    Node childNode = nodes[childNum];
                    childNode.markParent(this.nodeNum);
                }
            }
        }
    }
}
