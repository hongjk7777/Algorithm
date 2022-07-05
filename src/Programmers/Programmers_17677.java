package Programmers;

import java.util.HashMap;
import java.util.Map;

public class Programmers_17677 {
    public static void main(String[] args) {
        new Solution().solution("", "");
    }

    static class Solution {
        private static final int MULT = 65536;
        private Map<String, Integer> str1Map = new HashMap<>();
        private Map<String, Integer> str2Map = new HashMap<>();

        public int solution(String str1, String str2) {
            str1 = str1.toLowerCase();
            str2 = str2.toLowerCase();
            makeStr1Map(str1);
            makeStr2Map(str2);
            int interSectionSize = getInterSectionSize();
            int unionSize = getUnionSize();
            return jakadCal(interSectionSize, unionSize);
        }

        public void makeStr1Map(String str1) {
            for(int i = 0; i < str1.length() - 1; i++) {
                String element = str1.substring(i, i + 2);

                if(!isLetterStr(element)){
                    continue;
                }

                if(str1Map.containsKey(element)){
                    str1Map.replace(element, str1Map.get(element) + 1);
                } else {
                    str1Map.put(element, 1);
                }
            }
        }

        public boolean isLetterStr(String str) {
            for(int i = 0; i< str.length(); i++) {
                if(!isLetter(str.charAt(i))){
                    return false;
                }
            }
            return true;
        }

        private boolean isLetter(char c) {
            return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
        }

        public void makeStr2Map(String str2) {
            for(int i = 0; i < str2.length() - 1; i++) {
                String element = str2.substring(i, i + 2);

                if(!isLetterStr(element)){
                    continue;
                }

                if(str2Map.containsKey(element)){
                    str2Map.replace(element, str2Map.get(element) + 1);
                } else {
                    str2Map.put(element, 1);
                }
            }
        }

        private int getInterSectionSize() {
            int size = 0;

            for (String strKey : str1Map.keySet()){
                if(str2Map.containsKey(strKey)){
                    size += Math.min(str1Map.get(strKey), str2Map.get(strKey));
                }
            }

            return size;
        }

        private int getUnionSize() {
            for (String strKey : str1Map.keySet()) {
                if(str2Map.containsKey(strKey)){
                    int max = Math.max(str1Map.get(strKey), str2Map.get(strKey));
                    str2Map.replace(strKey, max);
                } else{
                    str2Map.put(strKey, str1Map.get(strKey));
                }
            }

            int size = 0;

            for(String strKey : str2Map.keySet()) {
                size += str2Map.get(strKey);
            }
            return size;
        }

        private int jakadCal(int interSection, int union) {
            if(union == 0){
                return MULT;
            }
            return (int) Math.floor((double)interSection / (double)union * MULT);
        }
    }
}
