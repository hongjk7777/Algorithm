import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1085 {
    private static int x, y, w, h;

    public static void main(String[] args) throws IOException {
        getInput();
        int min = getMin();
        System.out.println(min);
    }

    private static int getMin() {
        int min = Math.min(w - x, x);
        min = Math.min(min, h - y);
        min = Math.min(min, y);
        return min;
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());
        x = Integer.parseInt(tokenizer.nextToken());
        y = Integer.parseInt(tokenizer.nextToken());
        w = Integer.parseInt(tokenizer.nextToken());
        h = Integer.parseInt(tokenizer.nextToken());
    }
}
