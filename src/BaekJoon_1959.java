/*
아쉬웠던 점
1. 입력 범위가 int의 아슬아슬하게 걸치는 범위여서 선이 꺽이는 횟수는 int범위를 넘었다.
    때문에 long으로 했다. 그러나 늦게 알아채서 굉장히 오래 걸림
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1959 {
    private static int mapRow, mapCol;

    public static void main(String[] args) throws IOException {
        getInput();
        solveAndPrintAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        mapRow = Integer.parseInt(tokenizer.nextToken());
        mapCol = Integer.parseInt(tokenizer.nextToken());
    }

    private static void solveAndPrintAnswer() {
        int deleteSize = Math.min(mapRow, mapCol) / 2;
        long curved = (long) deleteSize * 4;
        int tempRow = mapRow - deleteSize * 2;
        int tempCol = mapCol - deleteSize * 2;
        int endRow, endCol;

        // 1. row >= col 인 경우
        // 2. 위와 반대의 경우
        // 3. row, col 중 작은 값이 짝수일 경우
        // 4. 위와 반대의 경우
        // 위의 4가지 경우를 고려해서 코드를 짰다.
        if (tempRow == 0 || tempCol == 0) {
            endRow = Math.min(mapRow, mapCol) / 2 + 1;
            endCol = Math.min(mapRow, mapCol) / 2;
            if (mapRow <= mapCol) {
                curved -= 2;
            } else {
                curved -= 1;
            }
        } else {
            endRow = Math.min(mapRow, mapCol) / 2 + tempRow;
            endCol = Math.min(mapRow, mapCol) / 2 + tempCol;
            if (mapRow > mapCol) {
                curved++;
            }
        }

        System.out.println(curved);
        System.out.println(endRow + " " + endCol);
    }
}
