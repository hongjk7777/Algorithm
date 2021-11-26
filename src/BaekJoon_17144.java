import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BaekJoon_17144 {
    
    private static int mapRow, mapCol, time;
    private static int[][] map = new int[52][52];
    private static int filterRow, filterCol;
    private static boolean filterCheck = false;
    private static List<Dust> dustList = new ArrayList<>();
    private static int[] dx = {0,0,1,-1};
    private static int[] dy = {-1,1,0,0};

    public static void main(String[] args) {
        initialize();
        getInput();
        for (int i = 0; i < time; i++) {
            spreadDust();
            operateFilter();
        }
        System.out.println(countDust());
    }

    private static void initialize() {
        for (int i = 0; i < 52; i++) {
            Arrays.fill(map[i], -1);
        }
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        mapRow = scanner.nextInt();
        mapCol = scanner.nextInt();
        time = scanner.nextInt();
        for (int i = 0; i < mapRow; i++) {
            for (int j = 0; j < mapCol; j++) {
                int input = scanner.nextInt();
                map[i+1][j+1] = input;

                if(input == -1 && !filterCheck){ //filter
                    filterRow = i + 1;
                    filterCol = j + 1;
                } 
                // else if(input > 0){           //dust
                //     // dustList.add(new Dust(i+1,j+1));
                // }
            }
        }
    }

    private static void spreadDust() {
        int[][] tempMap = new int[50][50];
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                // Dust dust = dustList.get(i);
            int curRow = row;
            int curCol = col;
            int size = map[curRow][curCol];
            int spreadSize = size/5;
            for (int k = 0; k < 4; k++) {
                int tempX = curRow + dx[k];
                int tempY = curRow + dy[k];
                if(map[tempX][tempY] != -1){
                    tempMap[tempX][tempY] = spreadSize;
                    size -= spreadSize;
                }
            }
            tempMap[curRow][curCol] += size;
            }
            
        }
        map = tempMap;
    }

    private static void operateFilter() {
        makeUpCycle(filterRow, filterCol + 1, 0);
        makeDownCycle(filterRow + 1, filterCol + 1, 0);
    }

    private static void makeUpCycle(int curRow, int curCol, int preVal) {
        int nextRow, nextCol, curVal;
        
        if(map[curRow][curCol-1] == -1){
            nextRow = curRow + 1;
            nextCol = curCol;
        } else if(curRow == 1){
            nextRow = curRow;
            nextCol = curCol - 1;
        } else if(map[curRow][curCol+1] == -1){
            nextRow = curRow - 1;
            nextCol = curCol;
        } else{
            nextRow = curRow;
            nextCol = curCol + 1;
        }
        
        if(curRow == filterRow && curCol == filterCol){
            return;
        }
        curVal = map[nextRow][nextCol];
        map[nextRow][nextCol] = preVal;
        makeUpCycle(nextRow, nextCol, curVal);
    }

    private static void makeDownCycle(int curRow, int curCol, int preVal) {
        int nextRow, nextCol, curVal;
        if(curRow == filterRow + 1 && curCol == filterCol){
            return;
        }
        if(map[curRow][curCol-1] == -1){
            nextRow = curRow - 1;
            nextCol = curCol;
        } else if(curRow == mapRow){
            nextRow = curRow;
            nextCol = curCol - 1;
        } else if(map[curRow][curCol+1] == -1){
            nextRow = curRow + 1;
            nextCol = curCol;
        } else{
            nextRow = curRow;
            nextCol = curCol + 1;
        }

        if(curRow == filterRow + 1 && curCol == filterCol){
            return;
        }
        curVal = map[nextRow][nextCol];
        map[nextRow][nextCol] = preVal;
        makeUpCycle(nextRow, nextCol, curVal);
    }

    private static int countDust() {
        int count = 0;
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                // Dust dust = dustList.get(row);
                int temp = map[row][col];
                if(temp >0){
                    count += temp;
                }
                // count += map[dust.getX()][dust.getY()];
            }
            
        }
        return count;
    }
    

    public static class Dust {
        int x, y;

        Dust(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    
}
