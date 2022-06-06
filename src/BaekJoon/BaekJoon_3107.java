package BaekJoon;

import java.util.Scanner;

public class BaekJoon_3107 {
    private static String shortenedIPv6;

    public static void main(String[] args) {
        getInput();
        String originalIPv6 = decodeIPv6();
        System.out.println(originalIPv6);
    }

    private static void getInput() {
        Scanner scanner;
        scanner = new Scanner(System.in);
        shortenedIPv6 = scanner.nextLine();
    }

    private static String decodeIPv6() {
        String decodeColons = getOriginalColonStr();
        return getOriginalIPv6(decodeColons);
    }

    private static String getOriginalColonStr() {
        StringBuilder decodeColons = new StringBuilder();

        int colonNum = getColonNum();

        //1개의 :를 ::로 바꿨을 경우 다시 원상복귀
        if (colonNum > 7) {
            changeToOriginalColon();
        }

        if (colonNum < 7) {
            appendShortenedColons(decodeColons, colonNum);
        } else {
            decodeColons.append(shortenedIPv6);
        }

        return decodeColons.toString();
    }

    private static int getColonNum() {
        int colonNum = 0;

        for (int i = 0; i < shortenedIPv6.length(); i++) {
            if (isColon(shortenedIPv6.charAt(i))) {
                colonNum++;
            }
        }
        return colonNum;
    }

    private static boolean isColon(char c) {
        return c == ':';
    }

    private static void changeToOriginalColon() {
        shortenedIPv6 = shortenedIPv6.replace("::", ":");
    }

    private static void appendShortenedColons(StringBuilder decodeColons, int colonNum) {
        int shortenedSize = 7 - colonNum;
        for (int i = 0; i < shortenedIPv6.length(); i++) {
            if (isShortenedColon(i)) {
                appendColons(decodeColons, shortenedSize);
            }

            decodeColons.append(shortenedIPv6.charAt(i));
        }
    }

    private static boolean isShortenedColon(int index) {
        return index < shortenedIPv6.length() - 1 && isColon(shortenedIPv6.charAt(index)) && isColon(shortenedIPv6.charAt(index + 1));
    }

    private static void appendColons(StringBuilder original, int shortenSize) {
        original.append(":".repeat(shortenSize));
    }

    private static String getOriginalIPv6(String shortenedIP) {
        StringBuilder originalIPv6 = new StringBuilder();

        //앞뒤가 :로 끝날 경우 생략해버리는 경우를 방지하기 위해
        shortenedIP = makeReadyToSplit(shortenedIP);

        String[] letter4 = shortenedIP.split(":");

        for (String dividedStr : letter4) {
            if (isShortenedLetter(dividedStr)) {
                appendShortenedLetter(originalIPv6, dividedStr);
            } else {
                originalIPv6.append(dividedStr).append(':');
            }
        }

        originalIPv6.deleteCharAt(originalIPv6.length() - 1);

        return originalIPv6.toString();
    }

    private static String makeReadyToSplit(String shortenedIP) {
        if (shortenedIP.charAt(0) == ':') {
            shortenedIP = '0' + shortenedIP;
        }

        if (shortenedIP.charAt(shortenedIP.length() - 1) == ':') {
            shortenedIP = shortenedIP + '0';
        }
        return shortenedIP;
    }

    private static boolean isShortenedLetter(String dividedStr) {
        return dividedStr.length() < 4;
    }

    private static void appendShortenedLetter(StringBuilder originalIPv6, String dividedStr) {
        int shortenedSize = 4 - dividedStr.length();
        originalIPv6.append("0".repeat(shortenedSize));
        originalIPv6.append(dividedStr).append(':');
    }
}
