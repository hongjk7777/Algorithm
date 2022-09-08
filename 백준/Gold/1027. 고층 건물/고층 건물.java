import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int size;
    private static int[] height;


    public static void main(String[] args) throws IOException {
        getInput();

        int max = 0;
        for (int i = 0; i < size; i++) {
            max = Math.max(max, getBuilding(i));
        }

        System.out.println(max);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        height = new int[size];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            height[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int getBuilding(int i) {
        int leftCount = getLeftBuilding(i);
        int rightCount = getRightBuilding(i);

        return leftCount + rightCount;
    }

    private static int getLeftBuilding(int buildingNum) {
        int count = 0;

        for (int i = 0; i < buildingNum; i++) {
            if(canSee(i, buildingNum)){
                count++;
            }
        }
        return count;
    }

    private static boolean canSee(int start, int goal) {
        for (int i = start + 1; i < goal; i++) {
            long maxHeight = (long) (i - start) * height[goal] + (long) (goal - i) * height[start];
            long curHeight = (long) (goal - start) * height[i];
            if(maxHeight <= curHeight){
                return false;
            }
        }

        return true;
    }

    private static int getRightBuilding(int buildingNum) {
        int count = 0;

        for (int i = buildingNum + 1; i < size; i++) {
            if(canSee(buildingNum, i)){
                count++;
            }
        }
        return count;
    }
}
