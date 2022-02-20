import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1062 {
    private static int wordAmount, alphabetAmount, max = 0;
    private static String[] words;
    private static boolean[] learnedAlphabet = new boolean[26];

    public static void main(String[] args) throws IOException {
        getInput();
        initializeLearnedAlphabet();
        choiceAlphabetToLearn(5, 0);
        System.out.println(max);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        wordAmount = Integer.parseInt(tokenizer.nextToken());
        alphabetAmount = Integer.parseInt(tokenizer.nextToken());
        words = new String[wordAmount];

        for (int i = 0; i < wordAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            words[i] = tokenizer.nextToken();
        }
    }

    private static void initializeLearnedAlphabet() {
        //모든 단어는 anti~tica이므로 antic 5글자는 항상 true로 만들어둔다.

        learnedAlphabet['a' - 'a'] = true;
        learnedAlphabet['n' - 'a'] = true;
        learnedAlphabet['t' - 'a'] = true;
        learnedAlphabet['i' - 'a'] = true;
        learnedAlphabet['c' - 'a'] = true;
    }

    private static void choiceAlphabetToLearn(int depth, int start) {
        //5글자를 미리 정해두므로 시작 depth는 5로 설정

        if (depth == alphabetAmount) {
            int temp = getReadableWordsNum();
            updateMax(temp);
            return;
        }

        for (int i = start; i < 26; i++) {
            if (learnedAlphabet[i]) {
                continue;
            }

            learnedAlphabet[i] = true;
            choiceAlphabetToLearn(depth + 1, i + 1);
            learnedAlphabet[i] = false;
        }
    }

    private static int getReadableWordsNum() {
        int readableWordsNum = 0;

        for (int i = 0; i < wordAmount; i++) {
            if (isReadable(words[i])) {
                readableWordsNum++;
            }
        }
        return readableWordsNum;
    }

    private static boolean isReadable(String word) {
        boolean readable = true;

        for (int i = 4; i < word.length() - 4; i++) {
            int index = word.charAt(i) - 'a';
            if (!learnedAlphabet[index]) {
                readable = false;
                break;
            }
        }

        return readable;
    }

    private static void updateMax(int temp) {
        max = Math.max(max, temp);
    }
}
