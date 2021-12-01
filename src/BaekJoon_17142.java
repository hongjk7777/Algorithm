import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
시도를 굉장히 많이 함
문제점:
1. 간단한 실수를 못 찾고 헤맸다. 예외를 생각하려는 버릇이 부족함
   + 하나를 바꿀 때 파생되는 문제점도 생각
2. 조합에 대한 구현이 미숙했다.
 */

public class BaekJoon_17142 {

    private static int mapSize, activatedAmount, safeArea = 0, min = 987654321;
    private static int[][] map;
    private static int[][] visited;
    private static ArrayList<Virus> virusList = new ArrayList<>();
    private static ArrayList<Virus> activatedVirusList = new ArrayList<>();
    private static boolean[] activated;
    private static int[] dRow = {0, 0, 1, -1};
    private static int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();
        combinationVirus(0, virusList.size(), activatedAmount);
        if (min == 987654321) {
            min = -1;
        }
        System.out.println(min);
    }


    private static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(br.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        activatedAmount = Integer.parseInt(tokenizer.nextToken());

        map = new int[mapSize][mapSize];
        visited = new int[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < mapSize; j++) {
                int input = Integer.parseInt(tokenizer.nextToken());
                map[i][j] = input;

                if (input == 2) {
                    virusList.add(new Virus(i, j));
                    visited[i][j] = -1;
                }

                if (input == 1) {
                    visited[i][j] = 1;
                }

                if (input == 0) {
                    safeArea++;
                    visited[i][j] = 0;
                }
            }
        }

        activated = new boolean[virusList.size()];

    }

    private static void combinationVirus(int start, int n, int r) {
        if (r == 0) {
            for (int i = 0; i < virusList.size(); i++) {
                Virus virus = virusList.get(i);
                if (activated[i]) {
                    activatedVirusList.add(virus);
                    visited[virus.row][virus.col] = 1;
                }
            }
            spreadVirusBFS();

//            for (int i = 0; i < virusList.size(); i++) {
//                Virus virus = virusList.get(i);
//                map[virus.row][virus.col] = -1;
//            }
            activatedVirusList.clear();
            return;
        }

        if (safeArea == 0) {
            min = 0;
            return;
        }


        for (int i = start; i < virusList.size(); i++) {
            activated[i] = true;
            combinationVirus(i + 1, n, r - 1);
            activated[i] = false;
        }



    }

    private static void spreadVirusBFS() {
        Queue<Virus> virusQueue = new LinkedList<>();
        boolean enable = true;
        int contaminated = 0, time = 1;
        int second = activatedAmount;
        int count = 0;
        for (int i = 0; i < activatedAmount; i++) {
            Virus virus = activatedVirusList.get(i);
            visited[virus.row][virus.col] = 1;
            virusQueue.add(virus);
        }

        while (contaminated != safeArea) {
            if (time >= min) {
                resetVisited();
                return;
            }
            if (virusQueue.isEmpty()) {
                enable = false;
                break;
            }
            if (count == second) {
                time++;
                second = virusQueue.size();
                count = 0;
            }
            Virus virus = virusQueue.poll();
            int row = virus.row;
            int col = virus.col;

            for (int i = 0; i < 4; i++) {
                int tempRow = row + dRow[i];
                int tempCol = col + dCol[i];
                if (isInArea(tempRow, tempCol)) {
                    if (visited[tempRow][tempCol] == 0) {
                        visited[tempRow][tempCol] = 1;
                        virusQueue.add(new Virus(tempRow, tempCol));
                        contaminated++;
                    } else if (visited[tempRow][tempCol] == -1) {
                        visited[tempRow][tempCol] = 1;
                        virusQueue.add(new Virus(tempRow, tempCol));
                    }
                }
            }
            count++;

        }

        if (min > time && enable) {
            min = time;
        }

        resetVisited();

    }

    private static boolean isInArea(int tempRow, int tempCol) {
        return (0 <= tempRow && tempRow < mapSize && 0 <= tempCol && tempCol < mapSize);
    }

    private static void resetVisited() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                int input = map[i][j];
                if (input == 2) {
                    visited[i][j] = -1;
                } else {
                    visited[i][j] = input;
                }
            }
        }
    }

    private static class Virus {
        int row, col;
        boolean activated = false;

        public Virus(int row, int col) {
            this.row = row;
            this.col = col;
        }

    }
}
