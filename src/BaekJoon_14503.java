import java.util.Scanner;

public class Main {

    private static int row, column, robotX, robotY, robotDir;
    private static int[][] map = new int[52][52];
    private static int[][] cleanMap = new int[52][52];
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        RobotVacuum robotVacuum = new RobotVacuum();
        getInput();
        robotVacuum.clean();
        System.out.println(robotVacuum.getCleanArea());
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        row = scanner.nextInt();
        column = scanner.nextInt();
        robotX = scanner.nextInt() + 1;
        robotY = scanner.nextInt() + 1;
        robotDir = scanner.nextInt();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                map[i + 1][j + 1] = scanner.nextInt();
            }
        }
        initializeMap();
    }

    private static void initializeMap() {
        for (int i = 0; i < row + 2; i++) {
            map[i][0] = 1;
            map[i][column + 1] = 1;
        }
        for (int i = 0; i < column + 2; i++) {
            map[0][i] = 1;
            map[row + 1][i] = 1;
        }
    }

    private static class RobotVacuum {

        private static int totalCleanArea = 1;

        public int getCleanArea() {
            return totalCleanArea;
        }

        private void clean() {
            int flag = 0;
            cleanMap[robotX][robotY] = 1;
            for (int dir = 0; dir < 4; dir++) {
                changeDir();
                if (map[robotX + dx[robotDir]][robotY + dy[robotDir]] == 0
                        && cleanMap[robotX + dx[robotDir]][robotY + dy[robotDir]] != 1) {
                    robotX += dx[robotDir];
                    robotY += dy[robotDir];
                    cleanMap[robotX][robotY] = 1;
                    totalCleanArea++;
                    flag = 1;
                    clean();
                    break;
                }
            }
            if (flag == 0) {
                if (map[robotX - dx[robotDir]][robotY - dy[robotDir]] == 0) {
                    robotX -= dx[robotDir];
                    robotY -= dy[robotDir];
                    clean();
                }
            }

            return;
        }

        private void changeDir() {
            robotDir--;
            if (robotDir < 0) {
                robotDir = 3;
            }
        }
    }
}
