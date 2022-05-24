import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2875 {
    private static int male, female, internshipNum;

    public static void main(String[] args) throws IOException {
        getInput();
        int max = getMaxTeam();
        System.out.println(max);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        female = Integer.parseInt(tokenizer.nextToken());
        male = Integer.parseInt(tokenizer.nextToken());
        internshipNum = Integer.parseInt(tokenizer.nextToken());
    }

    private static int getMaxTeam() {
        selectInternship();

        return Math.min(male, female / 2);
    }

    private static void selectInternship() {
        for (int i = 0; i < internshipNum; i++) {
            if (male * 2 < female) {
                female--;
            } else {
                male--;
            }
        }
    }
}
