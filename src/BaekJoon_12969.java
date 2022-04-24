import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_12969 {
    private static int strSize, goalNum;
    //문자열 길이 a개수 b개수
    private static ArrayList<Integer>[][][] conditionAmounts;

    public static void main(String[] args) throws IOException {
        getInput();
        initializeArrayLists();
        makeDP();
        String answerStr = getAnswerStr();
        System.out.println(answerStr);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        strSize = Integer.parseInt(tokenizer.nextToken());
        goalNum = Integer.parseInt(tokenizer.nextToken());

        conditionAmounts = new ArrayList[strSize + 1][strSize + 1][strSize + 1];
    }

    private static void initializeArrayLists() {
        for (int len = 0; len <= strSize; len++) {
            for (int aNum = 0; aNum <= strSize; aNum++) {
                for (int bNum = 0; bNum <= strSize; bNum++) {
                    conditionAmounts[len][aNum][bNum] = new ArrayList<>();
                }
            }
        }
    }

    private static void makeDP() {
        initializeDP();

        for (int strLen = 0; strLen < strSize; strLen++) {
            for (int aNum = 0; aNum <= strLen; aNum++) {
                for (int bNum = 0; bNum <= strLen; bNum++) {
                    for (int curConditionNum : conditionAmounts[strLen][aNum][bNum]) {
                        addA(strLen, aNum, bNum, curConditionNum);
                        addB(strLen, aNum, bNum, curConditionNum);
                        addC(strLen, aNum, bNum, curConditionNum);
                    }
                }
            }
        }
    }

    private static void initializeDP() {
        conditionAmounts[0][0][0].add(0);
    }

    private static void addA(int strLen, int aNum, int bNum, int curConditionNum) {
        if (!conditionAmounts[strLen + 1][aNum + 1][bNum].contains(curConditionNum)) {
            conditionAmounts[strLen + 1][aNum + 1][bNum].add(curConditionNum);
        }
    }

    private static void addB(int strLen, int aNum, int bNum, int curConditionNum) {
        if (!conditionAmounts[strLen + 1][aNum][bNum + 1].contains(curConditionNum + aNum)) {
            conditionAmounts[strLen + 1][aNum][bNum + 1].add(curConditionNum + aNum);
        }
    }

    private static void addC(int strLen, int aNum, int bNum, int curConditionNum) {
        if (!conditionAmounts[strLen + 1][aNum][bNum].contains(curConditionNum + aNum + bNum)) {
            conditionAmounts[strLen + 1][aNum][bNum].add(curConditionNum + aNum + bNum);
        }
    }

    private static String getAnswerStr() {
        int curLen = strSize, aNum = 0, bNum = 0;
        StringBuilder answerStr = new StringBuilder();

        //정답이 되는 a개수와 b개수를 찾음
        for (; aNum <= strSize; aNum++) {
            bNum = getBNum(aNum);
            if (bNum >= 0) {
                break;
            }
        }

        //정답이 없을 경우 -1 리턴
        if (bNum < 0) {
            return "-1";
        }

        while (curLen > 0) {
            for (int conditionAmount : conditionAmounts[curLen][aNum][bNum]) {
                if (aNum > 0 && conditionAmount == goalNum &&
                        conditionAmounts[curLen - 1][aNum - 1][bNum].contains(goalNum)) {
                    answerStr.append("A");
                    aNum--;
                    break;
                }

                if (bNum > 0 && conditionAmount == goalNum - aNum &&
                        conditionAmounts[curLen - 1][aNum][bNum - 1].contains(goalNum - aNum)) {
                    answerStr.append("B");
                    bNum--;
                    goalNum -= aNum;
                    break;
                }

                if (aNum + bNum < strSize && conditionAmount == goalNum - aNum - bNum &&
                        conditionAmounts[curLen - 1][aNum][bNum].contains(goalNum - aNum - bNum)) {
                    answerStr.append("C");
                    goalNum -= (aNum + bNum);
                    break;
                }
            }
            curLen--;
        }

        return answerStr.reverse().toString();
    }

    //정답이 없을 경우 -1 리턴
    private static int getBNum(int aNum) {
        for (int bNum = 0; bNum <= strSize - aNum; bNum++) {
            for (int conditionAmount : conditionAmounts[strSize][aNum][bNum]) {
                if (conditionAmount == goalNum) {
                    return bNum;
                }
            }
        }
        return -1;
    }
}
