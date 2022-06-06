package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_17837 {

    private static int mapSize, chessPieceAmount;
    private static int[][] map;
    private static ArrayList<ChessPiece> chessPieceList = new ArrayList<>();
    private static boolean gameEnd = false;
    private static int[] dRow = {-1, 0, 1, 0};
    private static int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        int count = 0;

        getInput();
        while (!gameEnd && count <= 1000) {
            playTurn();
            count++;
        }

        count = checkOverLimit(count);

        System.out.println(count);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        chessPieceAmount = Integer.parseInt(tokenizer.nextToken());
        map = new int[mapSize + 2][mapSize + 2];
        initializeMap();

        for (int i = 0; i < mapSize; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < mapSize; j++) {
                map[i + 1][j + 1] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 0; i < chessPieceAmount; i++) {
            int row, col, dir;
            tokenizer = new StringTokenizer(reader.readLine());
            row = Integer.parseInt(tokenizer.nextToken());
            col = Integer.parseInt(tokenizer.nextToken());
            dir = Integer.parseInt(tokenizer.nextToken());
            if (dir == 3) dir = 0;
            else if (dir == 4) dir = 2;
            else if (dir == 2) dir = 3;
            chessPieceList.add(new ChessPiece(row, col, dir));
        }
    }

    private static void initializeMap() {
        // 벽은 파란색으로 취급하니 외부를 파란색으로 만든다.
        for (int i = 0; i < mapSize + 2; i++) {
            for (int j = 0; j < mapSize + 2; j++) {
                map[i][j] = 2;
            }
        }
    }


    private static void playTurn() {
        for (ChessPiece chessPiece : chessPieceList) {
            chessPiece.moveArea();
            if (checkEnd()) {
                gameEnd = true;
            }
        }
    }

    private static int checkOverLimit(int count) {
        if (count > 1000) {
            count = -1;
        }
        return count;
    }

    private static class ChessPiece {
        int row, col, dir;
        ArrayList<ChessPiece> upSide = new ArrayList<>();
        ArrayList<ChessPiece> downSide = new ArrayList<>();

        public ChessPiece(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        private void moveTo(int nextRow, int nextCol) {
            this.row = nextRow;
            this.col = nextCol;
            for (ChessPiece chessPiece : upSide) {
                chessPiece.row = nextRow;
                chessPiece.col = nextCol;
            }
        }

        public void moveArea() {
            int tempRow = row + dRow[dir];
            int tempCol = col + dCol[dir];
            int area = map[tempRow][tempCol];
            if (area == 0) {
                separatePiece();
                moveWhiteArea(tempRow, tempCol);
            } else if (area == 1) {
                separatePiece();
                moveRedArea(tempRow, tempCol);
            } else {
                moveBlueArea();
            }

        }

        private void separatePiece() {
            int index= downSide.size() - 1;
            for (int i = 0; i < downSide.size(); i++) {
                for (int j = 0; j <= upSide.size(); j++) {
                    downSide.get(index - i).upSide.remove(i);
                }
            }

            for (ChessPiece chessPiece : upSide) {
                if (downSide.size() > 0) {
                    chessPiece.downSide.subList(0, downSide.size()).clear();
                }
            }

            this.downSide.clear();
        }

        public boolean isGameEnd() {
            return upSide.size() >= 3;
        }

        private void moveWhiteArea(int tempRow, int tempCol) {
            ChessPiece bottomPiece;

            bottomPiece = getBottomPiece(tempRow, tempCol);

            this.moveTo(tempRow, tempCol);
            if (bottomPiece != null) {
                for (int i = 0; i < upSide.size(); i++) {
                    upSide.get(i).downSide.addAll(0, bottomPiece.upSide);
                    upSide.get(i).downSide.add(0, bottomPiece);
                }
                downSide.add(bottomPiece);
                downSide.addAll(bottomPiece.upSide);
                bottomPiece.pileUp(this);
            }
        }

        private ChessPiece getBottomPiece(int tempRow, int tempCol) {
            for (int i = 0; i < chessPieceAmount; i++) {
                ChessPiece chessPiece = chessPieceList.get(i);
                if (chessPiece.isInArea(tempRow, tempCol)
                        && chessPiece.isBottom()) {
                    return chessPiece;
                }
            }
            return null;
        }

        private boolean isInArea(int tempRow, int tempCol) {
            return this.row == tempRow && this.col == tempCol;
        }

        private boolean isBottom() {
            return downSide.isEmpty();
        }

        private void pileUp(ChessPiece chessPiece) {
            if (!upSide.isEmpty()) {
                upSide.get(0).pileUp(chessPiece);
            }
            upSide.add(chessPiece);
            upSide.addAll(chessPiece.upSide);
        }

        private void moveRedArea(int tempRow, int tempCol) {
            ChessPiece topPiece = null;

            if (!upSide.isEmpty()) {
                topPiece = upSide.get(upSide.size() - 1);
            }

            reverse(downSide.size());
//            this.moveTo(tempRow, tempCol);

            if (topPiece != null) {
                topPiece.moveWhiteArea(tempRow, tempCol);
            } else {
                moveWhiteArea(tempRow, tempCol);
            }
        }

        public void reverse(int pieceIndex) {

            if (!upSide.isEmpty()) {
                upSide.get(0).reverse(pieceIndex);
            }

            ArrayList<ChessPiece> tempUpList = new ArrayList<>();
            ArrayList<ChessPiece> tempDownList = new ArrayList<>();

            for (int i = 0; i < upSide.size(); i++) {
                tempUpList.add(upSide.get(upSide.size() - 1 - i));
            }
            for (int i = 0; i < downSide.size(); i++) {
                tempDownList.add(downSide.get(downSide.size() - 1 - i));
            }
            upSide = tempDownList;
            downSide = tempUpList;

        }

        private void moveBlueArea() {
            int tempRow;
            int tempCol;
            changeDir();
            tempRow = row + dRow[dir];
            tempCol = col + dCol[dir];

            if (map[tempRow][tempCol] == 0) {
                separatePiece();
                moveWhiteArea(tempRow, tempCol);
            } else if (map[tempRow][tempCol] == 1) {
                separatePiece();
                moveRedArea(tempRow, tempCol);
            }
        }

        private void changeDir() {
            dir = (dir + 2) % 4;
        }
    }

    private static boolean checkEnd() {
        for (ChessPiece chessPiece : chessPieceList) {
            if (chessPiece.isGameEnd()) {
                return true;
            }
        }
        return false;
    }
}
