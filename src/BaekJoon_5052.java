/*
아쉬운 점
1. 처음에 속도 상승을 위해 Integer로 바꿔 연산했는데 0을 없애버려 멍청한 생각이었다
ㄴ 앞으로 string -> int는 0을 항상 생각해보자

2. testcase 하나를 완료한 후 초기화를 시키지 않았다.
ㄴ 이런 testcase가 여러 개인 문제는 초기화를 항상 먼저 만들고 하자

시도해볼 점
1. 나는 이진검색을 통해 풀었지만 트라이를 통해 푸는 풀이도 많았다
ㄴ 트라이에 대해 공부해 한 번 풀어보자

시간 복잡도: O(NlgN)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_5052 {
    private static final ArrayList<String> phoneNum = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        int testcaseAmount = Integer.parseInt(tokenizer.nextToken());

        for (int testcaseNum = 0; testcaseNum < testcaseAmount; testcaseNum++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int size = Integer.parseInt(tokenizer.nextToken());

            for (int i = 0; i < size; i++) {
                tokenizer = new StringTokenizer(reader.readLine());
                phoneNum.add(tokenizer.nextToken());
            }

            if (isConsistent(size)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

            phoneNum.clear();
        }
    }

    private static boolean isConsistent(int size) {
        //이진 검색을 위한 정렬
        Collections.sort(phoneNum);

        for (int wordIndex = 0; wordIndex < size; wordIndex++) {
            String word = phoneNum.get(wordIndex);
            //자기자신을 못찾도록 한다
            phoneNum.remove(wordIndex);


            for (int i = 0; i < word.length(); i++) {
                String keyword = word.substring(0, i + 1);
                if (Collections.binarySearch(phoneNum, keyword) >= 0) {
                    return false;
                }
            }


            //배열 원상복귀
            phoneNum.add(wordIndex, word);
        }

        return true;
    }
}
