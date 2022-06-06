package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2609 {
    private static int firstN, secondN;

    public static void main(String[] args) throws IOException {
        getInput();
        findAndPrintLCMs();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());

        firstN = Integer.parseInt(tokenizer.nextToken());
        secondN = Integer.parseInt(tokenizer.nextToken());
    }


    private static void findAndPrintLCMs() {
        findAndPrintLeastCM();
        findAndPrintLargestCM();
    }
    
    private static void findAndPrintLeastCM() {
        int min = Math.min(firstN, secondN);
        for (int i = min; i > 0; i--) {
            if (firstN % i == 0 && secondN % i == 0) {
                System.out.println(i);
                break;
            }
        }
    }
    
    private static void findAndPrintLargestCM() {
        int min = Math.min(firstN, secondN);
        int max = Math.max(firstN, secondN);
        int lcm = max;
        for (int i = 1; i <= min; i++) {
            if (lcm % min == 0 && lcm % max == 0) {
                System.out.println(lcm);
                break;
            }
            lcm += max;
        }
    }
}
