import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_1644 {
    private static int goalSum, answer = 0;
    private static final int MAX = 4000000;
    private static boolean[] isNotPrime = new boolean[MAX + 1];
    private static ArrayList<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        checkPrimeNum();
        addPrimesToArrayList();
        findAnswer();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        goalSum = Integer.parseInt(tokenizer.nextToken());
    }

    private static void checkPrimeNum() {
        int limit = (int) Math.sqrt(MAX);

        for (int i = 2; i <= limit; i++) {
            int temp = i * 2;
            while (temp <= MAX) {
                isNotPrime[temp] = true;
                temp += i;
            }
        }
    }

    private static void addPrimesToArrayList() {
        for (int i = 2; i <= MAX; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
            }
        }
    }

    private static void findAnswer() {
        //two pointer 방식으로 진행
        int startPointer = 0;
        int endPointer = 0;
        int partialSum = 0;
        int size = primes.size();

        while (startPointer < size) {
            if (partialSum < goalSum && endPointer < size) {
                partialSum += primes.get(endPointer++);
            } else {
                if (partialSum == goalSum) {
                    answer++;
                }
                partialSum -= primes.get(startPointer++);
            }
        }
    }
}
