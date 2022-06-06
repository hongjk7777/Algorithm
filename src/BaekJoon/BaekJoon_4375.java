package BaekJoon;/*
아쉬웠던 점
1. 나머지 연산을 활용하는 것까지는 알았지만 아이디어를 생각해내지 못함
관련 문제를 좀 더 풀어보는 게 좋을 것 같다.

알게 된 점
1. 알고리즘 문제 사이트들은 프로그램이 끝나지 않아도 출력만 맞으면 된다.
이 문제와 같은 경우 hasNext로 무한 대기가 걸리는데 상관없이 정답처리됨
 */
import java.io.IOException;
import java.util.Scanner;

public class BaekJoon_4375 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int num = scanner.nextInt();
            int answer = 1;
            int digit = 1;
            while (answer % num != 0) {
                answer = answer * 10 + 1;
                answer %= num;
                digit++;
            }

            System.out.println(digit);
        }
    }
}
