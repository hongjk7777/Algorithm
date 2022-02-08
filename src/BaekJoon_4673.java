public class BaekJoon_4673 {
    public static final int MAX = 10000;
    private static boolean[] notSelfNumber = new boolean[MAX + 1];

    public static void main(String[] args) {
        checkNotSelfNums();
        printSelfNums();
    }

    private static void checkNotSelfNums() {
        for (int num = 1; num <= MAX; num++) {
            int dn = getDn(num);

            if (overLimit(dn)) {
                continue;
            }
            notSelfNumber[dn] = true;
        }
    }

    private static boolean overLimit(int num) {
        return MAX < num;
    }

    private static int getDn(int num) {
        int dn = num;

        while (num > 0) {
            dn += (num % 10);
            num /= 10;
        }

        return dn;
    }


    private static void printSelfNums() {
        StringBuilder builder = new StringBuilder();

        for (int num = 1; num <= MAX; num++) {
            if (!notSelfNumber[num]) {
                builder.append(num).append("\n");
            }
        }

        System.out.println(builder);
    }
}
