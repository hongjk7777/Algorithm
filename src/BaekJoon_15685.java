import java.text.NumberFormat.Style;
import java.util.Scanner;

public class BaekJoon_15685 {

    private static int dragonCurveAmount;
    private static DragonCurve[] dCurveArr;
    private static Square[][] squareArr;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        initialize();
        getInput();
        System.out.println(countSurroundedSquare());
    }

    private static void getInput(){
        Scanner scanner = new Scanner(System.in);
        dragonCurveAmount = scanner.nextInt();
        for (int i = 0; i < dragonCurveAmount; i++) {
            int startX = scanner.nextInt();
            int startY = scanner.nextInt();
            int dir = scanner.nextInt();
            int genertaion = scanner.nextInt();

            DragonCurve dCurve = 
            new DragonCurve(startX, startY, dir, genertaion);
            dCurveArr[i] = dCurve;
        }
    }

    private static void initialize(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                squareArr[i][j] = new Square();
            }
        }
    }

    private static class DragonCurve{
        int[] x = new int[10000];
        int[] y = new int[10000];
        int[] dir = new int[10000];
        int gen;
        int curNum = 0;

        public DragonCurve(int startX, int startY, int dir, int gen){
            this.x[0] = startX;
            this.y[0] = startY;
            this.dir[0] = dir;
            this.gen = gen;

            makeFirstDCurve(startX, startY);
            makeDCurve(startX, startY, gen);
        }

        private void makeDCurve(int startX, int startY, int gen){
            
            for (int i = 1; i < gen; i++) {
                makeDCurveGen(startX, startY, gen);
            }
        }

        private void makeFirstDCurve(int startX, int startY) {
            x[curNum + 1] = x[curNum] + dx[dir[curNum]];
            y[curNum + 1] = y[curNum] + dy[dir[curNum]];
            
            curNum++;
        }

        private void makeDCurveGen(int startX, int startY, int gen) {

        }

    }

    public static class Square{
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;

        public void surround(int dir){
            if(dir == 0){
                up = 1;
            } else if(dir == 1){
                down = 1;
            } else if(dir == 2){
                left = 1;
            } else if(dir == 3){
                right = 1;
            }
        }

        public boolean isSurrounded(){
            boolean surrounded = false;
            
            if(up + down + left + right == 4){
                surrounded = true;
            }

            return surrounded;
        }
    }

    private static int countSurroundedSquare() {
        int count = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(squareArr[i][j].isSurrounded()){
                    count++;
                }
            }
        }

        return count;
    }
}
