import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
생각 노트
1. 격자를 나누기
2. 격자 돌리기 - 어떻게?
ㄴ 원래 것을 다른 map에 저장 후 넣어야 할듯
(걸리는 시간: 배열 복사: 2 * 2^6 * 2^6 '=. 10000으로 시간 ㄱㅊ)
3. 얼음양 줄이기
4. 가장 큰 얼음 덩어리 계산하기- 어떻게?
ㄴ bfs로 visit 만들어서 계산하자!!(이것도 최대 크기 64라 시간 걱정 x)

나올 수 있는 예외?
1. 경계값은 실제로 입력해보기
2. bfs할 때 visit 잘 조정해서 무한 루프 안 돌게 하기
 */
public class BaekJoon_20058 {
    private static int mapSize, castAmount;
    private static int[][] map;
    private static int[][] tempMap;
    private static int[][] visited;
    private static int[] cast;
    private static int[] dRow = {0, 0, 1, -1};
    private static int[] dCol = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        getInput();

        for (int i = 0; i < castAmount; i++) {
            castFirestorms(i);
            deleteIce();
        }
        System.out.println(getTotalIce());
        System.out.println(getBiggestIceCube());
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = (int) Math.pow(2, Integer.parseInt(tokenizer.nextToken()));
        castAmount = Integer.parseInt(tokenizer.nextToken());

        map = new int[mapSize][mapSize];
        tempMap = new int[mapSize][mapSize];
        visited = new int[mapSize][mapSize];
        cast = new int[castAmount];

        for (int i = 0; i < mapSize; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < castAmount; i++) {
            cast[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }


    private static void castFirestorms(int castNum) {
        if (cast[castNum] == 0) {
            return;
        }
        int castSize = (int) Math.pow(2, cast[castNum]);
        int castMapSize = mapSize / castSize;

        for (int row = 0; row < castMapSize; row++) {
            for (int col = 0; col < castMapSize; col++) {
                castFirestorm(row * castSize, col * castSize, castSize);
            }
        }
    }

    private static void castFirestorm(int row, int col, int castSize) {
        int[][] tempMap = new int[castSize][castSize];
        for (int i = 0; i < castSize; i++) {
            System.arraycopy(map[row + i], col, tempMap[i], 0, castSize);
        }

        for (int i = 0; i < castSize; i++) {
            for (int j = 0; j < castSize; j++) {
                map[row + i][col + j] =
                        tempMap[(castSize - j - 1)][i];
            }
        }
    }

    private static void deleteIce() {
        for (int i = 0; i < mapSize; i++) {
            System.arraycopy(map[i], 0, tempMap[i], 0, mapSize);
        }
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                int surroundedIce = 0;

                //얼음이 없는 경우
                if (map[row][col] == 0) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int tempRow = row + dRow[i];
                    int tempCol = col + dCol[i];
                    if (isInArea(tempRow, tempCol)) {
                        if (tempMap[tempRow][tempCol] > 0) {
                            surroundedIce++;
                        }
                    }
                }

                if (surroundedIce < 3) {
                    map[row][col]--;
                }
            }
        }
    }

    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }

    private static int getTotalIce() {
        int sum = 0;

        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                sum += map[row][col];
            }
        }

        return sum;
    }

    private static int getBiggestIceCube() {
        //bfs 하기
        int max = 0;
        int cubeSize = 0;

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {

                if (visited[i][j] == 1 || map[i][j] == 0) {
                    continue;
                }
                Queue<Location> iceQueue = new LinkedList<>();
                iceQueue.add(new Location(i, j));
                visited[i][j] = 1;
                int size = 1;

                while (!iceQueue.isEmpty()) {
                    Location location = iceQueue.poll();
                    int row = location.row;
                    int col = location.col;
//                    int size = location.size;

                    for (int dir = 0; dir < 4; dir++) {
                        int tempRow = row + dRow[dir];
                        int tempCol = col + dCol[dir];

                        if (isInArea(tempRow, tempCol)) {
                            if (visited[tempRow][tempCol] == 0 && map[tempRow][tempCol] > 0) {
                                iceQueue.add(new Location(tempRow, tempCol));
                                visited[tempRow][tempCol] = 1;
                                size++;
                            }
                        }
                    }

                    if (max < size) {
                        max = size;
                    }
                }


            }
        }
        //한 번만 찾으면 되기에 reset할 필요 없음

        return max;
    }

    private static class Location {
        int row, col;

        public Location(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
