/*
아쉬웠던 점
1. 예외 안 넣어보고 바로 제출해서 틀림.
ㄴ 맨처음엔 앞에서부터 한 칸 씩 뒤로 이동해가면 겹치는 문자 확인했는데 시작지점을
   잘못 생각했다. cdedfd, abcde가 있으면 0 ~ n번째 a가 들어갈 경우만 생각
   a부터 시작 안 할 수도 있는 걸 생각 못함
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_5582 {
    private static String baseStr, campareStr;
    private static int longest = 0;

    public static void main(String[] args) throws IOException {
        getInput();
        findLongestSubStr();
        System.out.println(longest);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        String str1 = tokenizer.nextToken();

        tokenizer = new StringTokenizer(reader.readLine());
        String str2 = tokenizer.nextToken();

        decideStrType(str1, str2);
    }

    private static void decideStrType(String str1, String str2) {
        if (str1.length() > str2.length()) {
            baseStr = str1;
            campareStr = str2;
        } else {
            baseStr = str2;
            campareStr = str1;
        }
    }

    private static void findLongestSubStr() {
        for (int i = -baseStr.length() + 1; i < baseStr.length(); i++) {
            int temp = getSubstrLength(i);
            longest = Math.max(longest, temp);
        }
    }

    private static int getSubstrLength(int start) {
        int max = 0;

        int matched = 0;
        for (int curIndex = 0; curIndex < campareStr.length(); curIndex++) {
            if (start + curIndex >= baseStr.length() || start + curIndex < 0) {
                matched = 0;
                continue;
            }

            if (baseStr.charAt(start + curIndex) == campareStr.charAt(curIndex)) {
                matched++;
                max = Math.max(max, matched);
            } else {
                matched = 0;
            }
        }

        return max;
    }
}
