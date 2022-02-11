import java.util.ArrayList;
import java.util.Scanner;

public class BaekJoon_15685 {

    private static int dragonCurveAmount;
    private static final boolean[][] checked = new boolean[101][101];
    private static DragonCurve[] dCurveArr;

    public static void main(String[] args) {
        getInput();
        makeAllDcurves();
        System.out.println(countSurroundedSquare());
    }

    private static void getInput(){
        Scanner scanner = new Scanner(System.in);
        dragonCurveAmount = scanner.nextInt();

        dCurveArr = new DragonCurve[dragonCurveAmount];
        for (int i = 0; i < dragonCurveAmount; i++) {
            int startX = scanner.nextInt();
            int startY = scanner.nextInt();
            int dir = scanner.nextInt();
            int generation = scanner.nextInt();

            DragonCurve dCurve = 
            new DragonCurve(startX, startY, dir, generation);
            dCurveArr[i] = dCurve;
        }
    }

    private static void makeAllDcurves() {
        for (int i = 0; i < dragonCurveAmount; i++) {
            dCurveArr[i].makeAllDCurve();
        }
    }

    private static int countSurroundedSquare() {
        int num = 0;
        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
                if (isSurrounded(row, col)) {
                    num++;
                }
            }
        }

        return num;
    }

    private static boolean isSurrounded(int row, int col) {
        return checked[row][col] && checked[row + 1][col] && checked[row][col + 1] && checked[row + 1][col + 1];
    }

    private static class DragonCurve{
        int row, col, dir, gen;
        private final int[] dRow = {1, 0, -1, 0};
        private final int[] dCol = {0, -1, 0, 1};

        ArrayList<Location> edges = new ArrayList<>();

        public DragonCurve(int startRow, int startCol, int dir, int gen){
            this.row = startRow;
            this.col = startCol;
            this.dir = dir;
            this.gen = gen;
        }

        public void makeAllDCurve() {
            makeFirstDCurve(row, col);
            makeDCurves(0);
        }

        private void makeFirstDCurve(int startRow, int startCol) {
            Location startLocation = new Location(startRow, startCol);
            edges.add(startLocation);
            checkLocation(startLocation);

            int nextRow = startRow + dRow[dir];
            int nextCol = startCol + dCol[dir];

            Location nextLocation = new Location(nextRow, nextCol);
            edges.add(nextLocation);
            checkLocation(nextLocation);

        }

        private void makeDCurves(int curGen){

            if (curGen == gen) return;
            makeDCurve();
            makeDCurves(curGen + 1);
        }

        private void makeDCurve() {
            Location axis = edges.get(edges.size() - 1);

            for (int i = edges.size() - 2; i >= 0; i--) {
                Location temp = edges.get(i);
                Location spinLocation = getSpin90Location(axis, temp);
                checkLocation(spinLocation);
                edges.add(spinLocation);
            }
        }

        private Location getSpin90Location(Location axis, Location temp) {
            int diffRow = temp.row - axis.row;
            int diffCol = temp.col - axis.col;

            int nextRow = axis.row - diffCol;
            int nextCol = axis.col + diffRow;

            return new Location(nextRow, nextCol);
        }

        private void checkLocation(Location location) {
            int row = location.row;
            int col = location.col;

            if (!checked[row][col]) {
                checked[row][col] = true;
            }
        }


        private class Location {
            int row, col;

            public Location(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }
    }

}
