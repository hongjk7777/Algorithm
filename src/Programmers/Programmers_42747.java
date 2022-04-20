package Programmers;

import java.util.Arrays;
import java.util.Collections;

public class Programmers_42747 {
    public static void main(String[] args) {
        new Solution().solution(new int[]{3, 3, 2});
    }
    static class Solution {
        public int solution(int[] citations) {
            Integer[] tempCitations = Arrays.stream( citations ).boxed().toArray( Integer[]::new );
            Arrays.sort(tempCitations, Collections.reverseOrder());
            return getHIndex(tempCitations);
        }

        private int getHIndex(Integer[] citations) {
            int min = Integer.MAX_VALUE;
            int hIndex = 0;

            while (min >= hIndex) {
                min = Math.min(min, citations[hIndex++]);

                if (hIndex == citations.length) {
                    if (min >= hIndex) {
                        hIndex++;
                    }
                    break;
                }
            }
            return hIndex - 1;
        }
    }
}
