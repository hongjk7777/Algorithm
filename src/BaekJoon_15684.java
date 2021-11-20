import java.util.Scanner;

public class BaekJoon_15684 {

    private static int min = 987654321;
    private static int columnAmount, rowLineAmount, height;
    private static int[][] rowLine = new int[30][9];

    public static void main(String[] args) {
        getInput();
        rigLadderGame(3, rowLine);
        if (min > 3) {
            min = -1;
        }
        System.out.println(min);
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        columnAmount = scanner.nextInt();
        rowLineAmount = scanner.nextInt();
        height = scanner.nextInt();
        for (int i = 0; i < rowLineAmount; i++) {
            int lineHeight = scanner.nextInt() - 1;
            int lineNum = scanner.nextInt() - 1;
            rowLine[lineHeight][lineNum] = 1;
        }
    }


    private static void rigLadderGame(int leftLadder, int[][] rowLine) {
        //1을 찾앗으면 2는 x 알골
        if (min <= 3 - leftLadder) {
            return;
        }

        if (rigSuccess(rowLine)) {
            int usedLadder = 3 - leftLadder;
            if (usedLadder < min) {
                min = usedLadder;
            }
            return;
        }

        if (leftLadder == 0) {  //개선 필요
            return;
        }



        //for statement
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < columnAmount - 1; j++) {

                if (isPlaceable(i, j, rowLine) && rowLine[i][j] != 1) {
                    rowLine[i][j] = 1;
                    rigLadderGame(leftLadder - 1, rowLine);
                    rowLine[i][j] = 0;
                }
            }
        }


    }


    private static boolean rigSuccess(int[][] rowLine) {
        boolean rig = true;

        for (int i = 0; i < columnAmount; i++) {
            if (getResultOfColumn(i, rowLine) != i) {
                rig = false;
                break;
            }
        }
        return rig;
    }

    private static int getResultOfColumn(int columnNum, int[][] rowLine) {
        int curColumn = columnNum; //column can change every height

        for (int i = 0; i < height; i++) {
            int tempColumn = curColumn;
            if (tempColumn < columnAmount -1 && rowLine[i][tempColumn] == 1 ) {
                curColumn++;
            }

            if (tempColumn > 0 && rowLine[i][tempColumn - 1] == 1) {
                curColumn--;
            }
        }

        return curColumn;
    }

    private static boolean isPlaceable(int height, int column, int[][] ladder) {
        boolean placeable = column + 1 >= columnAmount - 1 || ladder[height][column + 1] != 1;

        if (column > 0 && ladder[height][column - 1] == 1) {
            placeable = false;
        }
        return placeable;
    }

}