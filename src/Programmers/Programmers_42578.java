package Programmers;

import java.util.HashMap;
import java.util.Map;

public class Programmers_42578 {
    public static void main(String[] args) {
        new Solution().solution(new String[][]{});
    }

    static class Solution {
        private Map<String, Integer> clothMap = new HashMap<>();

        public int solution(String[][] clothes) {
            addClothesToMap(clothes);

            return getNumberOfClothSets();
        }

        private void addClothesToMap(String[][] clothes) {
            for(String[] cloth : clothes) {
                String name = cloth[0];
                String type = cloth[1];

                if(clothMap.containsKey(type)){
                    clothMap.replace(type, clothMap.get(type) + 1);
                } else {
                    clothMap.put(type, 1);
                }
            }
        }

        private int getNumberOfClothSets() {
            int numberOfClothSets = 1;
            for(String strkey : clothMap.keySet()) {
                numberOfClothSets *= (clothMap.get(strkey) + 1);
            }

            //아무것도 안 입는 경우를 빼기 위해 -1이다.
            return numberOfClothSets - 1;
        }
    }
}
