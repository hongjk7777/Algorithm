package BaekJoon;/*
생각노트
1. 조건이 굉장히 많기 때문에 구현할 때 문제를 잘 읽고 헷갈리지 말자
2. 구현 자체는 bfs를 통해 가장 큰 그룹 찾고 내리고 돌리면 됨. 구현 자체는 어렵지 않음

나올 수 있는 예외
1. 경계값?
2. 내가 조건을 잘못 읽을 확률도 높아서 여러 번 확인
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_21609 {
    private static int mapSize, colorAmount, totalScore = 0;
    private static Block curBaseBlock;
    static BlockGroup curBlockGroup = null;
    private static int[][] map, visited, tempMap;
    private static int[] dRow = {1, -1, 0, 0};
    private static int[] dCol = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        getInput();
        playGame();
        System.out.println(totalScore);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());

        mapSize = Integer.parseInt(tokenizer.nextToken());
        colorAmount = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapSize][mapSize];
        tempMap = new int[mapSize][mapSize];
        visited = new int[mapSize][mapSize];

        for (int row = 0; row < mapSize; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void playGame() {
        int maxSize = 0;

        while (true) {
            for (int row = 0; row < mapSize; row++) {
                for (int col = 0; col < mapSize; col++) {
                    if (map[row][col] > 0 && visited[row][col] == 0) {
                        //무지개가 아닌 곳에서만 찾아서 무조건 일반 블록 하나가 들어감
                        findBiggestGroup(row, col, maxSize);
                    }
                }
            }

            if (curBlockGroup == null || curBlockGroup.blockList.size() <= 1) {
                break;
            }

            deleteBlockGroup();
            applyGravity();
            spin();
            applyGravity();
            reset();
        }

    }

    private static void findBiggestGroup(int row, int col, int curSize) {
        Block baseBlock;
        ArrayList<Block> tempBlockList = new ArrayList<>();
        Queue<Block> blockQueue = new LinkedList<>();

        //무지개블럭의 경우 블럭 그룹마다 여러 번 그룹이 방문 가능하기에 따로 visited를 만들어 관리
        int[][] visitedRainbow = new int[mapSize][mapSize];
        int color = map[row][col];
        int rainbowAmount = 0;

        Block curBlock = new Block(row, col);
        blockQueue.add(curBlock);
        tempBlockList.add(curBlock);
        baseBlock = curBlock;
        visited[row][col] = 1;

        while (!blockQueue.isEmpty()) {
            Block block = blockQueue.poll();
            int curRow = block.row;
            int curCol = block.col;

            for (int dir = 0; dir < 4; dir++) {
                int tempRow = curRow + dRow[dir];
                int tempCol = curCol + dCol[dir];
                if (isInArea(tempRow, tempCol) && map[tempRow][tempCol] != -2) {

                    //이 조건은 너무 읽기 어려워 보여 개선 필요해 보임
                    if (visited[tempRow][tempCol] == 0 && (map[tempRow][tempCol] == color ||
                            isUnvisitedRainbow(visitedRainbow[tempRow][tempCol], tempRow, tempCol))) {
                        Block tempBlock = new Block(tempRow, tempCol);
                        if (isBaseBlock(baseBlock, tempBlock)) {
                            baseBlock = tempBlock;
                        }

                        if (map[tempRow][tempCol] != 0) {
                            visited[tempRow][tempCol] = 1;
                        } else {
                            visitedRainbow[tempRow][tempCol] = 1;
                            rainbowAmount++;
                        }

                        blockQueue.add(tempBlock);
                        tempBlockList.add(new Block(tempRow, tempCol));
                    }
                }
            }
        }

        BlockGroup tempBlockGroup = new BlockGroup(tempBlockList, baseBlock, rainbowAmount);
        compareGroup(tempBlockGroup);
        //여기서 현재 그룹하고 비교

        //초기화 잘하기
    }
    private static boolean isInArea(int row, int col) {
        return 0 <= row && row < mapSize && 0 <= col && col < mapSize;
    }

    private static boolean isUnvisitedRainbow(int visited, int tempRow, int tempCol) {
        return map[tempRow][tempCol] == 0 && visited == 0;
    }

    private static boolean isBaseBlock(Block baseBlock, Block tempBlock) {
        int row = baseBlock.row;
        int col = baseBlock.col;
        int tempRow = tempBlock.row;
        int tempCol = tempBlock.col;

        if (map[tempBlock.row][tempBlock.col] == 0) {
            return false;
        } else if (row < tempRow) {
            return false;
        } else if (tempRow < row) {
            return true;
        } else {
            return col >= tempCol;
        }
    }

    private static void compareGroup(BlockGroup tempBlockGroup) {
        if (curBlockGroup == null) {
            curBlockGroup = tempBlockGroup;
            return;
        }

        if (curBlockGroup.blockList.size() > tempBlockGroup.blockList.size()) {
            return;
        } else if (curBlockGroup.blockList.size() < tempBlockGroup.blockList.size()){
            curBlockGroup = tempBlockGroup;
        } else{
            if (curBlockGroup.rainbowAmount > tempBlockGroup.rainbowAmount) {
                return;
            } else if (curBlockGroup.rainbowAmount < tempBlockGroup.rainbowAmount) {
                curBlockGroup = tempBlockGroup;
            } else{
                if (curBlockGroup.baseBlock.row > tempBlockGroup.baseBlock.row) {
                    return;
                } else if (curBlockGroup.baseBlock.row < tempBlockGroup.baseBlock.row) {
                    curBlockGroup = tempBlockGroup;
                } else {
                    if (curBlockGroup.baseBlock.col > tempBlockGroup.baseBlock.col) {
                        return;
                    } else {
                        curBlockGroup = tempBlockGroup;
                    }
                }
            }

        }

    }

    private static void deleteBlockGroup() {
        ArrayList<Block> blockList = curBlockGroup.blockList;
        int size = blockList.size();

        for (Block block : blockList) {
            int row = block.row;
            int col = block.col;

            map[row][col] = -2;
        }

        totalScore += Math.pow(size, 2);
    }

    private static void applyGravity() {
        int blankBlock;
        for (int col = 0; col < mapSize; col++) {
            blankBlock = 0;
            for (int row = mapSize - 1; row >= 0; row--) {
                if (map[row][col] == -2) {
                    blankBlock++;
                } else if (map[row][col] == -1) {
                    blankBlock = 0;
                } else {
                    map[row + blankBlock][col] = map[row][col];
                    if (blankBlock != 0) {  //제자리에 있는 경우는 빈자리가 생기지 않으므로
                        map[row][col] = -2;
                    }
                }
            }
        }
    }

    private static void spin() {
        for (int row = 0; row < mapSize; row++) {
            System.arraycopy(map[row], 0, tempMap[row], 0, mapSize);
        }

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = tempMap[j][mapSize - 1 - i];
            }
        }
    }

    private static void reset() {
        resetVisited();
        curBlockGroup = null;
    }

    private static void resetVisited() {
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                visited[row][col] = 0;
            }
        }
    }

    private static class Block {
        int row, col;

        public Block(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class BlockGroup {
        ArrayList<Block> blockList;
        Block baseBlock;
        int rainbowAmount;

        public BlockGroup(ArrayList<Block> blockList, Block baseBlock, int rainbowAmount) {
            this.blockList = blockList;
            this.baseBlock = baseBlock;
            this.rainbowAmount = rainbowAmount;
        }

    }
}
