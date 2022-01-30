import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_11724 {
    private static int n, m;
    private static boolean[] visited;
    private static ArrayList<Integer>[] connectedNodeLists;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getConnectedNodeNum();
        System.out.println(answer);
    }

    private static int getConnectedNodeNum() {
        int count = 0;
        for (int startNode = 0; startNode < n; startNode++) {
            if (!visited[startNode]) {
                dfs(startNode);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int curNode) {
        ArrayList<Integer> connectedNodes = connectedNodeLists[curNode];

        for (int next : connectedNodes) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next);
            }
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        m = Integer.parseInt(tokenizer.nextToken());

        initialize();

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int node = Integer.parseInt(tokenizer.nextToken()) - 1;
            int otherNode = Integer.parseInt(tokenizer.nextToken()) - 1;
            connectedNodeLists[node].add(otherNode);
            connectedNodeLists[otherNode].add(node);
        }
    }

    private static void initialize() {
        visited = new boolean[n];
        connectedNodeLists = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            connectedNodeLists[i] = new ArrayList<>();
        }
    }
}
