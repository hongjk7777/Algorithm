package Programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Programmers_42890 {
    public static void main(String[] args) {
        new Solution().solution(new String[][]{});
    }

    static class Solution {
        private ArrayList<Integer> curAttrs = new ArrayList<>();
        private Set<String> keySet = new HashSet<>();
        private int attrSize, answer = 0;

        public int solution(String[][] relation) {
            attrSize = relation[0].length;
            dfs(0, relation);

            return answer;
        }

        private void dfs(int depth, String[][] relation) {
            if(depth == attrSize) {
                // System.out.println(getCurAttrs());
                if(isCandidateKey(relation)){
                    System.out.println(getCurAttrs());
                    keySet.add(getCurAttrs());
                    answer++;
                }
                return;
            }

            dfs(depth + 1, relation);
            curAttrs.add(depth);
            dfs(depth + 1, relation);
            curAttrs.remove(curAttrs.size() - 1);
        }

        private boolean isCandidateKey(String[][] relation) {
            if(curAttrs.size() == 0){
                return false;
            }

            if(!isUnique(relation)){
                return false;
            }

            return isMinimal();
        }

        private boolean isUnique(String[][] relation) {
            Set<String> set = new HashSet<>();

            for(String[] data : relation) {
                String str = changeDataToCurAttr(data);
                if(!set.add(str)) {
                    return false;
                }
            }

            return true;
        }

        private String changeDataToCurAttr(String[] data) {
            StringBuilder builder = new StringBuilder();

            for(int col = 0; col < data.length; col++) {
                if(isCurAttr(col)){
                    builder.append(data[col]).append(",");
                }

            }

            return builder.toString();
        }

        private boolean isCurAttr(int col) {
            return curAttrs.contains(col);
        }

        private boolean isMinimal() {
            String curAttrs = getCurAttrs();

            for(String key : keySet) {
                for(int i = 0; i < key.length(); i++) {
                    if(!curAttrs.contains(String.valueOf(key.charAt(i)))){
                        break;
                    }

                    if(i == key.length() - 1) {
                        return false;
                    }
                }
            }

            return true;
        }

        private String getCurAttrs() {
            StringBuilder builder = new StringBuilder();
            for (Integer curAttr : curAttrs) {
                builder.append(curAttr);
            }

            return builder.toString();
        }
    }
}
