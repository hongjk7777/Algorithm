package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_16927 {
    private static int mapRow, mapCol, rotationAMount;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        getInput();
        spinOutlines();
        printMap();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
        rotationAMount = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapRow][mapCol];

        for (int row = 0; row < mapRow; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapCol; col++) {
                map[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void spinOutlines() {
        int min = Math.min(mapRow, mapCol);

        for (int i = 0; i < min / 2; i++) {
            spinOutline(i);
        }
    }

    private static void spinOutline(int i) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        addElementsClockwise(i, arrayList);
        changeElementsClockwise(i, arrayList);
    }

    private static void addElementsClockwise(int i, ArrayList<Integer> arrayList) {
        int curRow = i;
        int curCol = i;

        while (curCol < mapCol - 1 - i) {
            arrayList.add(map[curRow][curCol]);
            curCol++;
        }
        while (curRow < mapRow - 1 - i) {
            arrayList.add(map[curRow][curCol]);
            curRow++;
        }
        while (curCol > i) {
            arrayList.add(map[curRow][curCol]);
            curCol--;
        }
        while (curRow > i) {
            arrayList.add(map[curRow][curCol]);
            curRow--;
        }
    }

    private static void changeElementsClockwise(int i, ArrayList<Integer> arrayList) {
        int curRow = i;
        int curCol = i;
        int curIndex = 0;
        int size = arrayList.size();
        curIndex = (curIndex + rotationAMount) % size;

        while (curCol < mapCol - 1 - i) {
            map[curRow][curCol] = arrayList.get(curIndex);
            curIndex = (curIndex + 1) % size;
            curCol++;
        }
        while (curRow < mapRow - 1 - i) {
            map[curRow][curCol] = arrayList.get(curIndex);
            curIndex = (curIndex + 1) % size;
            curRow++;
        }
        while (curCol > i) {
            map[curRow][curCol] = arrayList.get(curIndex);
            curIndex = (curIndex + 1) % size;
            curCol--;
        }
        while (curRow > i) {
            map[curRow][curCol] = arrayList.get(curIndex);
            curIndex = (curIndex + 1) % size;
            curRow--;
        }
    }

    private static void printMap() {
        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < mapRow; row++) {
            for (int col = 0; col < mapCol; col++) {
                builder.append(map[row][col]).append(" ");
            }
            builder.append("\n");
        }

        System.out.println(builder);
    }
}
