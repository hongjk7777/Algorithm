package BaekJoon;/*
배운 점
1. 그동안 잊고 있던 외적에 대한 개념을 다시 생각하게 해줬다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11758 {
    private static final Vector[] vectors = new Vector[3];
    public static void main(String[] args) throws IOException {
        getInput();
        int answer = getAnswer();
        System.out.println(answer);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        for (int i = 0; i < 3; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            vectors[i] = new Vector(x, y);
        }
    }

    private static int getAnswer() {
        int crossProduct;

        Vector vector10 = new Vector(vectors[0].x - vectors[1].x, vectors[0].y - vectors[1].y);
        Vector vector21 = new Vector(vectors[1].x - vectors[2].x, vectors[1].y - vectors[2].y);

        crossProduct = vector10.x * vector21.y - vector10.y * vector21.x;

        return Integer.compare(crossProduct, 0);
    }

    private static class Vector {
        int x, y;

        public Vector(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
