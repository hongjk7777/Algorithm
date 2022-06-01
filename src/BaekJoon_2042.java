import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2042 {
    private static int numSize, changeSize, sumSize;
    private static long[] arr;
    private static Command[] commands;
    private static FenwickTree fenwickTree;
    private static final StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        getInput();
        makeFenwickTree();
        runCommands();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        numSize = Integer.parseInt(tokenizer.nextToken());
        changeSize = Integer.parseInt(tokenizer.nextToken());
        sumSize = Integer.parseInt(tokenizer.nextToken());
        arr = new long[numSize + 1];
        commands = new Command[changeSize + sumSize];

        for (int i = 1; i <= numSize; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            arr[i] = Long.parseLong(tokenizer.nextToken());
        }

        for (int i = 0; i < changeSize + sumSize; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int type = Integer.parseInt(tokenizer.nextToken());
            long value1 = Long.parseLong(tokenizer.nextToken());
            long value2 = Long.parseLong(tokenizer.nextToken());
            commands[i] = new Command(type, value1, value2);
        }
    }

    private static void makeFenwickTree() {
        fenwickTree = new FenwickTree(numSize);

        for (int i = 1; i <= numSize; i++) {
            fenwickTree.add(i, arr[i]);
        }
    }

    private static void runCommands() {
        for (int i = 0; i < changeSize + sumSize; i++) {
            runCommand(i);
        }
        System.out.println(builder);
    }

    private static void runCommand(int i) {
        Command command = commands[i];
        if (command.type == 1) {
            changeNum((int) command.value1, command.value2);
        } else {
            long sum = getSum((int) command.value1, (int) command.value2);
            builder.append(sum).append("\n");
        }
    }

    private static void changeNum(int index, long value) {
        fenwickTree.add(index, -arr[index]);
        fenwickTree.add(index, value);
        arr[index] = value;
    }

    private static long getSum(int start, int end) {
        long last = fenwickTree.sum(end);
        long first = fenwickTree.sum(start - 1);
        return last - first;
    }

    private static class Command {
        int type;
        long value1, value2;

        public Command(int type, long value1, long value2) {
            this.type = type;
            this.value1 = value1;
            this.value2 = value2;
        }
    }

    private static class FenwickTree {
        long[] tree;

        public FenwickTree(int size) {
            tree = new long[size + 1];
        }

        public long sum(int index) {
            long sum = 0;

            while (index > 0) {
                sum += tree[index];
                index &= (index - 1);
            }

            return sum;
        }

        public void add(int index, long value) {
            while (index < tree.length) {
                tree[index] += value;
                index += (index & -index);
            }
        }

    }

}
