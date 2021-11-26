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
        initialize(map);
        getInput();
        for (int i = 0; i < time; i++) {
            spreadDust();
            operateFilter();
        }
        System.out.println(countDust());
    }

    private static void initialize(int[][] tempMap) {
        for (int i = 0; i < 52; i++) {
            Arrays.fill(tempMap[i], -1);
        }
        for (int i = 0; i < mapRow; i++) {
            for (int j = 0; j < mapCol; j++) {
                int input = map[i + 1][j + 1];
                if (input > 0) {
                    tempMap[i + 1][j + 1] = 0;
                } else {
                    tempMap[i + 1][j + 1] = map[i + 1][j + 1];
                }
            }
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
        int[][] tempMap = new int[52][52];
        initialize(tempMap);
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                // Dust dust = dustList.get(i);
                int curRow = row + 1;
                int curCol = col + 1;
                int size = map[curRow][curCol];
                int spreadSize = size/5;
                if (size > 0) {
                    for (int k = 0; k < 4; k++) {
                        int tempX = curRow + dx[k];
                        int tempY = curCol + dy[k];
                        if(map[tempX][tempY] != -1){
                            tempMap[tempX][tempY] += spreadSize;
                            size -= spreadSize;
                        }
                    }
                    tempMap[curRow][curCol] += size;
                }

            }
            
        }
        map = tempMap;
    }

    private static void operateFilter() {
        makeUpCycle(filterRow - 1, filterCol + 1, 0);
        makeDownCycle(filterRow, filterCol + 1, 0);
    }

    private static void makeUpCycle(int curRow, int curCol, int preVal) {
        int nextRow, nextCol, nextVal;
        
        if(curCol == 1){
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
        nextVal = map[curRow][curCol];

        if(curRow == filterRow - 1 && curCol == filterCol){
            return;
        }
        map[curRow][curCol] = preVal;
        makeUpCycle(nextRow, nextCol, nextVal);
    }

    private static void makeDownCycle(int curRow, int curCol, int preVal) {
        int nextRow, nextCol, nextVal;

        if(curCol == 1){
            nextRow = curRow - 1;
            nextCol = curCol;
        } else if(curRow == mapRow){
            nextRow = curRow;
            nextCol = curCol - 1;
        } else if(curCol == mapCol){
            nextRow = curRow + 1;
            nextCol = curCol;
        } else{
            nextRow = curRow;
            nextCol = curCol + 1;
        }
        nextVal = map[curRow][curCol];

        if(curRow == filterRow && curCol == filterCol){
            return;
        }

        map[curRow][curCol] = preVal;
        makeDownCycle(nextRow, nextCol, nextVal);
    }

    private static int countDust() {
        int count = 0;
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                // Dust dust = dustList.get(row);
                int temp = map[row + 1][col + 1];
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
