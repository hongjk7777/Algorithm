import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_9881 {
    
    private static int hillAmount;
    private static int[] hill;

    public static void main(String[] args) throws IOException {
        getInput();
        int answer = findMinCost();
        System.out.println(answer);
    }

    public static void getInput() throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());

        hillAmount = Integer.parseInt(tokenizer.nextToken());
        hill = new int[hillAmount];

        for (int i = 0; i < hillAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            hill[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int findMinCost() {
        int minVar = 987654321;
        for (int number = 0; number <= 93; number++) {
            int variance = getVariance(number);
            if(variance < minVar){
                minVar = variance;
            }
        }
        
        return minVar;
    }

    private static int getVariance(int number) {
        int sum = 0;
        for (int hillNum = 0; hillNum < hillAmount; hillNum++) {
            int height = hill[hillNum];
            if(height < number){
                sum += Math.pow(number - height, 2);
            } else if(number + 17 < height){
                sum += Math.pow(height - (number + 17), 2);
            }
        }
        return sum;
    }
}
