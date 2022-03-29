import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2195 {
    private static String startStr, goalStr;

    public static void main(String[] args) throws IOException {
        getInput();
        int min = getMinTimeCopy();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        startStr = tokenizer.nextToken();

        tokenizer = new StringTokenizer(reader.readLine());
        goalStr = tokenizer.nextToken();
    }

    private static int getMinTimeCopy() {
        int copyTime = 0;

        for (int i = 0; i < goalStr.length(); ) {
            int length = 2;
            while (i + length <= goalStr.length() && startStr.contains(goalStr.substring(i, i + length))) {
                length++;
            }

            length--;
            i += length;
            copyTime++;
        }

        return copyTime;
    }
}
