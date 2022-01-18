/*
아쉬웠던 점
1. 가장 큰 수와 가장 작은 수만 곱하면 원래 수가 나오는데 못 찾고 쩔쩔 매고
다른 방법으로 풀고 나중에 알게 됨

배운 점
1. 수학 관련 문제를 조금 더 풀어보자

 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1037 {
    private static int divisorAmount;
    private static int[] divisors;


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        divisorAmount = Integer.parseInt(tokenizer.nextToken());
        divisors = new int[divisorAmount];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < divisorAmount; i++) {
            divisors[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.sort(divisors);

        System.out.println(divisors[0] * divisors[divisorAmount - 1]);
    }


}
