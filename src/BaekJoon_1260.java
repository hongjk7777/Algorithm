/*
아쉬운 점
1. 기본적인 문제임에도 불구하고 너무 오래 걸림
ㄴ 앞으로 dfs, bfs 관련 문제 많이 풀어야 할듯
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_1260 {
    private static int n;
    private static int startNode;
    private static boolean[] visited;
    private static ArrayList<Integer>[] LinkedNodeLists;

    public static void main(String[] args) throws IOException {
        getInput();
        printDFS(startNode);
        System.out.println();
        initializeVisited();
        printBFS(startNode);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());
        startNode = Integer.parseInt(tokenizer.nextToken()) - 1;
        visited = new boolean[n];
        initializeEdges();

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int node = Integer.parseInt(tokenizer.nextToken()) - 1;
            int otherNode = Integer.parseInt(tokenizer.nextToken()) - 1;
            LinkedNodeLists[node].add(otherNode);
            LinkedNodeLists[otherNode].add(node);
        }
        sortEdges();
    }

    private static void initializeEdges() {
        LinkedNodeLists = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            LinkedNodeLists[i] = new ArrayList<>();
        }
    }

    private static void sortEdges() {
        for (int i = 0; i < n; i++) {
            Collections.sort(LinkedNodeLists[i]);
        }
    }

    private static void printDFS(int curNode) {
        System.out.print((curNode + 1) + " ");
        if (curNode == startNode) {
            visited[startNode] = true;
        }

        ArrayList<Integer> LinkedNodes = LinkedNodeLists[curNode];
        for (int tempNode : LinkedNodes) {
            if (!visited[tempNode]) {
                visited[tempNode] = true;
                printDFS(tempNode);
            }
        }

    }

    private static void initializeVisited() {
        visited = new boolean[n];
    }

    private static void printBFS(int startNode) {
        Queue<Integer> nodes = new LinkedList<>();
        nodes.add(startNode);
        visited[startNode] = true;
        System.out.print((startNode + 1) + " ");

        while (!nodes.isEmpty()) {
            int tempNode = nodes.poll();
            ArrayList<Integer> LinkedNode = LinkedNodeLists[tempNode];

            for (int next : LinkedNode) {
                if (!visited[next]) {
                    visited[next] = true;
                    nodes.add(next);
                    System.out.print((next + 1) + " ");
                }
            }
        }
    }
}
