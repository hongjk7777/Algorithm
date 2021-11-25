import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaekJoon_17144 {
    
    private static int row, column, time;
    private static int[][] map = new int[52][52];
    private static int filterX, filterY;
    private static List<Dust> dustList = new ArrayList<>();

    public static void main(String[] args) {
        getInput();
        for (int i = 0; i < time; i++) {
            operateFilter();
        }
        System.out.println(countDust());
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        row = scanner.nextInt();
        column = scanner.nextInt();
        time = scanner.nextInt();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                int input = scanner.nextInt();
                map[i+1][j+1] = input;

                if(input == 1){
                    dustList.add(new Dust(i+1,j+1,input));
                } else if(input != 0){
                    filterX = i + 1;
                    filterY = j + 1;
                }
            }
        }
    }

    
    private static void operateFilter() {
    }

    private static int countDust() {
        int count = 0;
        for (int i = 0; i < dustList.size(); i++) {
            count += dustList.get(i).size;
        }
        return count;
    }

    public static class Dust {
        int x, y, size;

        Dust(int x, int y, int size){
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }
    
}
