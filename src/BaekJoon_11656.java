import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_11656 {
    private static String inputString;
    private static String[] suffixes;

    public static void main(String[] args) throws IOException {
        getInput();
        addSuffixes();
        sortSuffixes();
        printSuffixes();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        inputString = tokenizer.nextToken();
        suffixes = new String[inputString.length()];
    }

    private static void addSuffixes() {
        for (int i = 0; i < inputString.length(); i++) {
            suffixes[i] = inputString.substring(i);
        }
    }

    private static void sortSuffixes() {
        Arrays.sort(suffixes);
    }

    private static void printSuffixes() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            builder.append(suffixes[i]).append("\n");
        }

        System.out.println(builder);
    }
}
