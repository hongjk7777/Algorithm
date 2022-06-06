package BaekJoon;

import java.util.*;

public class BaekJoon_16236 {
    private static int mapSize, answer = 0;
    private static Shark shark;
    private static int[][] map = new int[20][20];
    private static int[][] visited = new int[20][20];
    private static FishArr fishArr = new FishArr();
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        getInput();
        int time = getTimeToHungry();
        System.out.println(time);
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        mapSize = scanner.nextInt();
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                int input = scanner.nextInt();
                if (0 < input && input < 7) {
                    fishArr.getFishArr(input).add(new Fish(i, j, input));
                }
                if (input == 9) {
                    shark = new Shark(i, j, 2);
                }
                map[i][j] = input;
            }
        }
    }


    private static int getTimeToHungry() {
        int time = 0;
        while (eatNextFish()) {
        }
        return answer;
    }

    private static boolean eatNextFish() {
        boolean result = false;
        ArrayList<Fish> fishList = new ArrayList<>();
        for (int size = 1; size < shark.size; size++) {
            findFish(size, fishList);
        }

        Collections.sort(fishList);

        for (Fish fish : fishList) {
            if (fish.dist < 987654321) {
                map[fish.x][fish.y] = 0;
                answer += fish.dist;
                shark.eatFish(fish);
                result = true;
                break;
            }
        }
        return result;
    }

    private static void findFish(int size, ArrayList<Fish> fishList) {
        int fishCount = fishArr.getFishCount(size);
        int min = 987654321;

        for (int fishNum = 0; fishNum < fishCount; fishNum++) {
            Fish fish = fishArr.getFishArr(size).get(fishNum);
            fish.dist = getFishDistBFS(fish);
            fishList.add(fish);
        }
    

    }

    private static int getFishDistBFS(Fish fish) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{shark.x, shark.y, 0});

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            int curX = xy[0];
            int curY = xy[1];
            int dist = xy[2];

            if (curX == fish.x && curY == fish.y) {
                resetVisited();
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int tempX = curX + dx[i];
                int tempY = curY + dy[i];
                if (isInArea(tempX, tempY)) {
                    if (visited[tempX][tempY] != 1) {
                        visited[tempX][tempY] = 1;
                        queue.add(new int[]{tempX, tempY, dist + 1});
                    }
                }
            }
        }

        resetVisited();

        return 987654321;
    }



    private static boolean isInArea(int curX, int curY) {
        if (0 <= curX && curX < mapSize && 0 <= curY && curY < mapSize){
            return map[curX][curY] <= shark.size;
        }
        return false;
    }

    private static void resetVisited() {
        for (int i = 0; i < 20; i++) {
            Arrays.fill(visited[i], 0);
        }
    }

    private static class Shark {
        int x, y, size, eatenFish;

        public Shark(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            eatenFish = 0;
        }

        public void eatFish(Fish fish) {
            map[x][y] = 0;
            this.x = fish.x;
            this.y = fish.y;
            map[x][y] = 9;
            fishArr.getFishArr(fish.size).remove(fish);
            eatenFish++;
            if (eatenFish == size) {
                size++;
                eatenFish = 0;
            }
        }
    }

    private static class FishArr {

        private List<Fish> size1Fish = new ArrayList<>();
        private List<Fish> size2Fish = new ArrayList<>();
        private List<Fish> size3Fish = new ArrayList<>();
        private List<Fish> size4Fish = new ArrayList<>();
        private List<Fish> size5Fish = new ArrayList<>();
        private List<Fish> size6Fish = new ArrayList<>();

        public List<Fish> getFishArr(int size) {
            if (size == 1) {
                return size1Fish;
            } else if (size == 2) {
                return size2Fish;
            } else if (size == 3) {
                return size3Fish;
            } else if (size == 4) {
                return size4Fish;
            } else if (size == 5) {
                return size5Fish;
            } else {
                return size6Fish;
            }
        }

        public int getFishCount(int size) {
            if (size == 1) {
                return size1Fish.size();
            } else if (size == 2) {
                return size2Fish.size();
            } else if (size == 3) {
                return size3Fish.size();
            } else if (size == 4) {
                return size4Fish.size();
            } else if (size == 5) {
                return size5Fish.size();
            } else {
                return size6Fish.size();
            }
        }


    }

    private static class Fish implements Comparable<Fish>{
        private int x, y, size;
        private int dist;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Fish(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }


        @Override
        public int compareTo(Fish fish) {
            int thisDist = Math.abs(this.x - shark.x) + Math.abs(this.y - shark.y);
            int otherDist = Math.abs(fish.x - shark.x) + Math.abs(fish.y - shark.y);

            if (this.dist < fish.dist) {
                return -1;
            } else if (this.dist > fish.dist) {
                return 1;
            } else {
                if (this.x < fish.x) {
                    return -1;
                } else if (this.x > fish.x) {
                    return 1;
                } else {
                    return Integer.compare(this.y, fish.y);
                }
            }
        }
    }
}
