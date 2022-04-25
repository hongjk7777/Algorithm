import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_17089 {
    private static final int INT_MAX = 987654321;
    private static int min = INT_MAX;

    private static int peopleAmount;
    private static ArrayList<Integer>[] friends;
    private static final ArrayList<Integer> curFriend = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        findMin();
        printMin();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        peopleAmount = Integer.parseInt(tokenizer.nextToken());
        int relationshipAmount = Integer.parseInt(tokenizer.nextToken());
        friends = new ArrayList[peopleAmount + 1];
        initializeArrayList();

        for (int i = 0; i < relationshipAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int person1 = Integer.parseInt(tokenizer.nextToken());
            int person2 = Integer.parseInt(tokenizer.nextToken());

            friends[person1].add(person2);
            friends[person2].add(person1);
        }
    }

    private static void initializeArrayList() {
        for (int i = 0; i <= peopleAmount; i++) {
            friends[i] = new ArrayList<>();
        }
    }

    private static void findMin() {
        for (int num = 1; num <= peopleAmount; num++) {
            for (int friend1Num = 0; friend1Num < friends[num].size(); friend1Num++) {
                int friend1 = friends[num].get(friend1Num);
                for (int friend2Num = friend1Num + 1; friend2Num < friends[num].size(); friend2Num++) {
                    int friend2 = friends[num].get(friend2Num);
                    if (friends[friend1].contains(friend2)) {
                        curFriend.add(num);
                        curFriend.add(friend1);
                        curFriend.add(friend2);
                        int sum = getSumOfFriends();
                        updateMin(sum);
                        curFriend.clear();
                    }
                }
            }
        }
    }

    private static int getSumOfFriends() {
        int sum = 0;

        for (int num : curFriend) {
            for (int friendNum : friends[num]) {
                if (!curFriend.contains(friendNum)) {
                    sum++;
                }
            }
        }

        return sum;
    }

    private static void updateMin(int tempMin) {
        min = Math.min(min, tempMin);
    }

    private static void printMin() {
        if (isNo3Friends()) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    private static boolean isNo3Friends() {
        return min == INT_MAX;
    }
}
