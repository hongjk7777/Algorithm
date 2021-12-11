/*
생각노트
1. 파이어볼 한 턴에 움직이고 맵에 저장
ㄴ 그리고 다시 queue에 넣기
ㄴ 굳이 queue가 아니여도 상관없엇음
2. 파이어볼 중복체크는 어케?
ㄴ 파이어볼 움직였을 때 2개 이상이면 따로 기록
4. 나올 수 있는 예외?
ㄴ 맵을 나갈 경우, 파이어볼이 3개이상 합쳐질 경우 정도?

아쉬웠던 점
1. 예외를 미리 다 생각했음에도 직접 실행해보지 않아 간단한 맵을 나가는 경우의 예외에서
처리를 미숙하게 해서 틀렸다. 다음 부턴 예외 테스트 케이스를 직접 만들어보자!!!!
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_20056 {
    private static int mapSize, fireBallAmount, moveAmount;
    private static FireBall[][] fireballMap;
    private static Queue<FireBall> fireBalls = new LinkedList<>();
    private static Queue<FireBall> overlapFireBalls = new LinkedList<>();
    private static int[] dRow = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dCol = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        getInput();
        for (int i = 0; i < moveAmount; i++) {
            moveFireballs();
            checkFireballOverlap();
        }
        int answer = getTotalMass();
        System.out.println(answer);
    }


    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapSize = Integer.parseInt(tokenizer.nextToken());
        fireBallAmount = Integer.parseInt(tokenizer.nextToken());
        moveAmount = Integer.parseInt(tokenizer.nextToken());
        fireballMap = new FireBall[mapSize][mapSize];

        for (int i = 0; i < fireBallAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int row = Integer.parseInt(tokenizer.nextToken()) - 1;
            int col = Integer.parseInt(tokenizer.nextToken()) - 1;
            int mass = Integer.parseInt(tokenizer.nextToken());
            int speed = Integer.parseInt(tokenizer.nextToken());
            int dir = Integer.parseInt(tokenizer.nextToken());
            fireBalls.add(new FireBall(row, col, mass, speed, dir));
        }
    }

    private static void moveFireballs() {
        while (!fireBalls.isEmpty()) {
            moveFireball();
        }

//나중에 초기화
        //1. 움지이고 맵에 없다면 맵에 저장 후 다시 넣기

    }


    private static void moveFireball() {
        FireBall fireball = fireBalls.poll();
        assert fireball != null;
        int row = fireball.row;
        int col = fireball.col;
        int dir = fireball.dir;
        int mass = fireball.mass;
        int speed = fireball.speed;

        row += speed * dRow[dir];
        row = changeRowIfOverLimit(row);
        col += speed * dCol[dir];
        col = changeColIfOverLimit(col);

        FireBall fireBall = new FireBall(row, col, mass, speed, dir);

        if (fireballMap[row][col] != null) {
            FireBall otherFireball = fireballMap[row][col];
            otherFireball.combine(dir, mass, speed);
        } else {
            fireballMap[row][col] = fireBall;
        }

    }

    private static int changeRowIfOverLimit(int row) {
        if (row < 0) {
            row %= mapSize;
            if (row != 0) {
                row += mapSize;
            }
        } else if (row >= mapSize) {
            row %= mapSize;
//            row -= mapSize;
        }
        return row;
    }

    private static int changeColIfOverLimit(int col) {
        if (col < 0) {
            col %= mapSize;
            if (col != 0) {
                col += mapSize;
            }
        } else if (col >= mapSize) {
            col %= mapSize;
        }
        return col;
    }

    private static void checkFireballOverlap() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                FireBall fireBall = fireballMap[i][j];
                if (fireBall != null) {
                    if (fireBall.getOverlapAmount() > 1) {
                        divideFireball(fireBall);
                    } else {
                        fireBalls.add(fireBall);
                    }
                    fireballMap[i][j] = null;

                }
            }
        }
    }

    private static void divideFireball(FireBall fireBall) {
        int row = fireBall.row;
        int col = fireBall.col;
        int mass = fireBall.mass;
        int speed = fireBall.speed;
        int dir = fireBall.dir;
        boolean sameDir = fireBall.isSameDir;
        int fireballAmount = fireBall.overlapAmount;

        int newMass = mass / 5;
        int newSpeed = speed / fireballAmount;
        ArrayList<FireBall> tempFireballs = new ArrayList<>();

        if (newMass == 0) {
            return;
        }

        if (sameDir) {
            tempFireballs.add(new FireBall(row, col, newMass, newSpeed, 0));
            tempFireballs.add(new FireBall(row, col, newMass, newSpeed, 2));
            tempFireballs.add(new FireBall(row, col, newMass, newSpeed, 4));
            tempFireballs.add(new FireBall(row, col, newMass, newSpeed, 6));
        } else {
            tempFireballs.add(new FireBall(row, col, newMass, newSpeed, 1));
            tempFireballs.add(new FireBall(row, col, newMass, newSpeed, 3));
            tempFireballs.add(new FireBall(row, col, newMass, newSpeed, 5));
            tempFireballs.add(new FireBall(row, col, newMass, newSpeed, 7));
        }
        for (int i = 0; i < 4; i++) {
            fireBalls.add(tempFireballs.get(i));
        }
    }


    private static int getTotalMass() {
        int sum = 0;
        while (!fireBalls.isEmpty()) {
            FireBall fireBall = fireBalls.poll();
            sum += fireBall.mass;
        }
        return sum;
    }

    private static class FireBall {
        int row, col, mass, speed, dir;
        int overlapAmount;
        boolean isSameDir;

        public FireBall(int row, int col, int mass, int speed, int dir) {
            this.row = row;
            this.col = col;
            this.mass = mass;
            this.speed = speed;
            this.dir = dir;
            this.overlapAmount = 1;
            this.isSameDir = true;
        }

        public void combine(int dir, int mass, int speed) {
//            this.dir += dir;
            if ((this.dir % 2) != (dir % 2)) {
                isSameDir = false;
            }
            this.mass += mass;
            this.speed += speed;
            overlapAmount++;
        }

        public int getOverlapAmount() {
            return overlapAmount;
        }
    }
}
