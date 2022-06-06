package BaekJoon;/*
아쉬운 점
1. 문제가 진짜 전형적인 dfs문제였는데 이를 생각하는데 걸리는 시간이 많았음
ㄴ 내가 생각하는 거에 확신을 갖고 해보자
2. stack으로 만들고 관리하는 것보다 boolean 배열으로 하면 시간을 더 줄일 수 있을 듯
3. forEach문을 쓰는 것에 익숙해지기
4. generic-raw를 쓰면 안 되는 이유를 공부하자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BaekJoon_13023 {
    private static int n;
    private static final Stack<Integer> curFriend = new Stack<>();
    private static ArrayList<Integer>[] friendLists;

    public static void main(String[] args) throws IOException {
        getInput();
        if (isExist()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());
        initializeFriends();

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int person = Integer.parseInt(tokenizer.nextToken());
            int otherPerson = Integer.parseInt(tokenizer.nextToken());
            friendLists[person].add(otherPerson);
            friendLists[otherPerson].add(person);
        }
    }

    private static void initializeFriends() {
        friendLists = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            friendLists[i] = new ArrayList<>();
        }
    }

    private static boolean isExist() {
        for (int start = 0; start < n; start++) {
            curFriend.add(start);
            if (dfs(start, 0)) {
                return true;
            }
            curFriend.pop();
        }
        return false;
    }

    private static boolean dfs(int curPerson, int linkNum) {
        if (linkNum == 4) {
            return true;
        }

        ArrayList<Integer> friends = friendLists[curPerson];
        for (int next : friends) {
            if (!curFriend.contains(next)) {
                curFriend.add(next);
                if (dfs(next, linkNum + 1)) {
                    return true;
                }
                curFriend.pop();
            }
        }

        return false;
    }
}
