import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaekJoon_16236 {
    private static int mapSize;
    private static Shark shark;
    private static int[][] map = new int[20][20];
    private static int[][] movable = new int[20][20];
    private static int[][] fish = new int[6][400];
    private static FishArr fishArr = new FishArr();

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
            time++;
        }
        return time;
    }

    private static boolean eatNextFish() {
        boolean result;
        ArrayList<Fish> fishList;
        for (int size = 0; size < shark.size; size++) {
            fishList = findFish(size);
            if (canReach(fishList)){
                result = true;
            }
        }
        result = false;
        return result;
    }


    private static ArrayList<Fish> findFish(int size) {
        int fishCount = fishArr.getFishCount(size);
        int min = 987654321;
        ArrayList<Fish> fishList = new ArrayList<>();

        for (int fishNum = 0; fishNum < fishCount; fishNum++) {
            Fish fish = fishArr.getFishArr(size).get(fishNum);
            fish.setDist(getDistance(fishNum, size));
            fishList.add(fish);
        }
        
        return fishList;

//        if (canReach()) {
//        }
    }

    
    private static boolean canReach(ArrayList<Fish> fishList) {
        Fish fish;
        for (int i = 0; i < fishList.size(); i++) {
            fishList.get(0);
            fish = fishList.remove(0);
            findRouteBFS(fish);
        }
        return false;
    }

    private static void findRouteBFS(Fish fish) {
    }

    private static int getDistance(int fishNum, int size) {
        int fishX = fishArr.getFishArr(size).get(fishNum).getX();
        int fishY = fishArr.getFishArr(size).get(fishNum).getY();
        return Math.abs(shark.x - fishX) + Math.abs(shark.y - fishY);
    }

    private static class Shark {
        int x, y, size;

        public Shark(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    private static class FishArr {
        private int size1FishCount = 0;
        private int size2FishCount = 0;
        private int size3FishCount = 0;
        private int size4FishCount = 0;
        private int size5FishCount = 0;
        private int size6FishCount = 0;

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
                return size1FishCount;
            } else if (size == 2) {
                return size2FishCount;
            } else if (size == 3) {
                return size3FishCount;
            } else if (size == 4) {
                return size4FishCount;
            } else if (size == 5) {
                return size5FishCount;
            } else {
                return size6FishCount;
            }
        }

        public void addFishCount(int size) {
            if (size == 1) {
                size1FishCount++;
            } else if (size == 2) {
                size2FishCount++;
            } else if (size == 3) {
                size3FishCount++;
            } else if (size == 4) {
                size4FishCount++;
            } else if (size == 5) {
                size5FishCount++;
            } else {
                size6FishCount++;
            }
        }
    }

    private static class Fish {
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

        public void setDist(int distance) {
            this.dist = distance;
        }
    }
}
