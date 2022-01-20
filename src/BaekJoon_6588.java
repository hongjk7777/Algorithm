import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_6588 {
    private static final int MAX_SIZE = 1000000;
    private static boolean[] isNotPrime = new boolean[MAX_SIZE + 1];
    private static ArrayList<Integer> evens = new ArrayList<>();
    private static ArrayList<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        findPrime();
        findAndPrintGoldbaches();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        int input = Integer.parseInt(tokenizer.nextToken());

        while (input != 0) {
            evens.add(input);

            tokenizer = new StringTokenizer(reader.readLine());
            input = Integer.parseInt(tokenizer.nextToken());
        }
    }


    private static void findPrime() {
        int sqrtN = (int) Math.sqrt(MAX_SIZE);
        isNotPrime[1] = true;

        for (int i = 2; i <= sqrtN; i++) {
            if (isNotPrime[i]) {
                continue;
            }

            int temp = 2 * i;
            while (temp <= MAX_SIZE) {
                isNotPrime[temp] = true;
                temp += i;
            }
        }

        for (int i = 1; i <= MAX_SIZE; i++) {
            if (!isNotPrime[i]) {
                primes.add(i);
            }
        }
    }


    private static void findAndPrintGoldbaches() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < evens.size(); i++) {
            findGoldbach(builder, i);
        }
        printGoldbaches(builder);
    }

    private static void findGoldbach(StringBuilder builder, int i) {
        int even = evens.get(i);
        for (int j = 1; j < primes.size(); j++) {
            int prime = primes.get(j);

            if (!isNotPrime[even - prime]) {
                builder.append(even).append(" = ")
                        .append(prime).append(" + ")
                        .append(even - prime).append("\n");
                break;
            }
        }
    }

    private static void printGoldbaches(StringBuilder builder) {
        System.out.println(builder);
    }
}
