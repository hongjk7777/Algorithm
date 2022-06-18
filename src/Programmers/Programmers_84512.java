package Programmers;

import java.util.HashMap;
import java.util.Map;

public class Programmers_84512 {
    public static void main(String[] args) {
        new Solution().solution("I");
    }

    static class Solution {
        private static final int MAX_LENGTH = 5;
        Map<Character, Integer> map = new HashMap<>();

        public int solution(String word) {
            makeAlphabetMap();
            return countLowerWords(word);
        }

        private void makeAlphabetMap() {
            map.put('A', 1);
            map.put('E', 2);
            map.put('I', 3);
            map.put('O', 4);
            map.put('U', 5);
        }

        private int countLowerWords(String word) {
            if (word.length() == 0) {
                return 0;
            }

            char lastChar = word.charAt(word.length() - 1);
            int size = MAX_LENGTH - word.length();
            int mult = 1;
            while (size > 0) {
                mult += Math.pow(5, size--);
            }
            int num = 1 + (map.get(lastChar) - 1) * mult;

            return num + countLowerWords(word.substring(0, word.length() - 1));
        }
    }
}
