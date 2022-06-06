package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_12919 {
    private static String startStr, goalStr;

    public static void main(String[] args) throws IOException {
        getInput();
        if (canMakeGoalStr(startStr)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        startStr = tokenizer.nextToken();

        tokenizer = new StringTokenizer(reader.readLine());
        goalStr = tokenizer.nextToken();
    }

    private static boolean canMakeGoalStr(String curStr) {
        if (curStr.length() == goalStr.length()) {
            return goalStr.equals(curStr);
        }

        String addAStr = addA(curStr);
        String addBStr = addBAndReverse(curStr);

        if (isSubStr(addAStr) && canMakeGoalStr(addAStr)) {
            return true;
        }

        if (isSubStr(addBStr) && canMakeGoalStr(addBStr)) {
            return true;
        }

        return false;
    }

    private static String addA(String curStr) {
        return curStr + "A";
    }

    private static String addBAndReverse(String curStr) {
        StringBuilder builder = new StringBuilder(curStr);
        builder.append("B");
        return builder.reverse().toString();
    }

    private static boolean isSubStr(String curStr) {
        String reverseStr = new StringBuilder(curStr).reverse().toString();
        return (goalStr.contains(curStr) || goalStr.contains(reverseStr));
    }
}
