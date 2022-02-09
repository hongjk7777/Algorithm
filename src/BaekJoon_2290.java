/*
아쉬운 점
1. 숫자들을 보면 반복되게 구현되는 부분이 충분히 보이는데도 이를 찾는데 조금 시간이 걸림
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2290 {
    private static int s;
    private static String n;

    public static void main(String[] args) throws IOException {
        getInput();
        printN();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        s = Integer.parseInt(tokenizer.nextToken());
        n = tokenizer.nextToken();
    }

    private static void printN() {
        int maxHeight = 2 * s + 3;
        for (int height = 0; height < maxHeight; height++) {
            printNPerHeights(height);
        }
    }

    private static void printNPerHeights(int height) {
        for (int i = 0; i < n.length(); i++) {
            int digit = Integer.parseInt(String.valueOf(n.charAt(i)));
            printNPerDigit(digit, height);
        }
        System.out.println();
    }

    private static void printNPerDigit(int digit, int height) {
        switch (digit) {
            case 1: print1PerHeight(height);
                break;
            case 2: print2PerHeight(height);
                break;
            case 3: print3PerHeight(height);
                break;
            case 4: print4PerHeight(height);
                break;
            case 5: print5PerHeight(height);
                break;
            case 6: print6PerHeight(height);
                break;
            case 7: print7PerHeight(height);
                break;
            case 8: print8PerHeight(height);
                break;
            case 9: print9PerHeight(height);
                break;
            case 0: print0PerHeight(height);
                break;
        }
    }

    private static void print1PerHeight(int height) {
        int maxWidth = s + 2;

        if (isWidth(height)) {
            notPrintWidth(maxWidth);
        } else {
            printRightHeight(maxWidth);
        }
        System.out.print(" ");
    }

    private static void print2PerHeight(int height) {
        int maxWidth = s + 2;

        if (isWidth(height)) {
            printWidth(maxWidth);
        } else {
            if (height < s + 1) {
                printRightHeight(maxWidth);
            } else {
                printLeftHeight(maxWidth);
            }
        }
        System.out.print(" ");
    }

    private static void print3PerHeight(int height) {
        int maxWidth = s + 2;

        if (isWidth(height)) {
            printWidth(maxWidth);
        } else {
            printRightHeight(maxWidth);
        }
        System.out.print(" ");
    }

    private static void print4PerHeight(int height) {
        int maxWidth = s + 2;

        if (isWidth(height)) {
            if (height == s + 1) {
                printWidth(maxWidth);
            } else {
                notPrintWidth(maxWidth);
            }
        } else {
            if (height < s + 1) {
                printBothHeight(maxWidth);
            } else {
                printRightHeight(maxWidth);
            }
        }
        System.out.print(" ");
    }

    private static void print5PerHeight(int height) {
        int maxWidth = s + 2;

        if (isWidth(height)) {
            printWidth(maxWidth);
        } else {
            if (height < s + 1) {
                printLeftHeight(maxWidth);
            } else {
                printRightHeight(maxWidth);
            }
        }
        System.out.print(" ");
    }

    private static void print6PerHeight(int height) {
        int maxWidth = s + 2;

        if (isWidth(height)) {
            printWidth(maxWidth);
        } else {
            if (height < s + 1) {
                printLeftHeight(maxWidth);
            } else {
                printBothHeight(maxWidth);
            }
        }
        System.out.print(" ");
    }

    private static void print7PerHeight(int height) {
        int maxWidth = s + 2;

        if (isWidth(height)) {
            if (height == 0) {
                printWidth(maxWidth);
            } else {
                notPrintWidth(maxWidth);
            }
        } else {
            printRightHeight(maxWidth);
        }
        System.out.print(" ");
    }

    private static void print8PerHeight(int height) {
        int maxWidth = s + 2;

        if (isWidth(height)) {
            printWidth(maxWidth);
        } else {
            printBothHeight(maxWidth);
        }
        System.out.print(" ");
    }

    private static void print9PerHeight(int height) {
        int maxWidth = s + 2;

        if (isWidth(height)) {
            printWidth(maxWidth);
        } else {
            if (height < s + 1) {
                printBothHeight(maxWidth);
            } else {
                printRightHeight(maxWidth);
            }
        }
        System.out.print(" ");
    }

    private static void print0PerHeight(int height) {
        int maxWidth = s + 2;

        if (isWidth(height)) {
            if (height == 0 || height == 2 * s + 2) {
                printWidth(maxWidth);
            } else {
                notPrintWidth(maxWidth);
            }
        } else {
            printBothHeight(maxWidth);
        }
        System.out.print(" ");
    }

    private static boolean isWidth(int height) {
        return height == 0 || height == s + 1 || height == 2 * s + 2;
    }


    private static void printWidth(int maxWidth) {
        for (int i = 0; i < maxWidth; i++) {
            if (i == 0 || i == maxWidth - 1) {
                System.out.print(" ");
            } else {
                System.out.print("-");
            }
        }
    }

    private static void notPrintWidth(int maxWidth) {
        for (int i = 0; i < maxWidth; i++) {
            System.out.print(" ");
        }
    }

    private static void printLeftHeight(int maxWidth) {
        for (int i = 0; i < maxWidth; i++) {
            if (i == 0) {
                System.out.print("|");
            } else {
                System.out.print(" ");
            }
        }
    }

    private static void printRightHeight(int maxWidth) {
        for (int i = 0; i < maxWidth; i++) {
            if (i == maxWidth - 1) {
                System.out.print("|");
            } else {
                System.out.print(" ");
            }
        }
    }

    private static void printBothHeight(int maxWidth) {
        for (int i = 0; i < maxWidth; i++) {
            if (i == 0 || i == maxWidth - 1) {
                System.out.print("|");
            } else {
                System.out.print(" ");
            }
        }
    }
}
