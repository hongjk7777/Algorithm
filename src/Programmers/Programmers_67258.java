package Programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Programmers_67258 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[]{"AA", "AB"});
    }

    static class Solution {
        Map<String, Integer> gemMap = new HashMap<>();
        int fulfilledGem = 0;
        ArrayList<String> gemTypes = new ArrayList<>();

        public int[] solution(String[] gems) {
            initializeMap(gems);

            return getMinLen(gems);
        }

        private void initializeMap(String[] gems) {
            for (String curGem : gems) {
                if (!gemMap.containsKey(curGem)) {
                    gemMap.put(curGem, 0);
                    gemTypes.add(curGem);
                }
            }
        }

        private int[] getMinLen(String[] gems) {
            int[] ret = {1, gems.length};
            int start = 1, last = 1;

            while (last <= gems.length + 1) {
                if (fulfilledGem == gemTypes.size()) {
                    ret = getMin(ret, new int[]{start, last - 1});
                    addGem(gems[start - 1]);
                    start++;
                } else {
                    if (last == gems.length + 1) {
                        return ret;
                    }
                    removeGem(gems[last - 1]);
                    last++;
                }
            }

            return ret;
        }

        private int[] getMin(int[] ret, int[] tempMin) {
            if (ret[1] - ret[0] < tempMin[1] - tempMin[0]) {
                return ret;
            } else if (ret[1] - ret[0] > tempMin[1] - tempMin[0]) {
                return tempMin;
            } else {
                if (ret[0] < tempMin[0]) {
                    return ret;
                } else {
                    return tempMin;
                }
            }
        }

        private void addGem(String gem) {
            int curGemNum = gemMap.get(gem) - 1;
            gemMap.replace(gem, curGemNum);
            if (curGemNum == 0) {
                fulfilledGem--;
            }
        }

        private void removeGem(String gem) {
            int curGemNum = gemMap.get(gem) + 1;
            gemMap.replace(gem, curGemNum);
            if (curGemNum == 1) {
                fulfilledGem++;
            }
        }
    }
}