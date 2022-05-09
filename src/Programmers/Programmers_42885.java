package Programmers;

import java.util.Arrays;

public class Programmers_42885 {
    public static void main(String[] args) {
        new Solution().solution(new int[]{30,51,55,56,57,58,70,90}, 100);
    }

    static class Solution {
        public int solution(int[] people, int limit) {
            Arrays.sort(people);
            return getMinBoat(people, limit);
        }

        private int getMinBoat(int[] people, int limit) {
            int lightNum = 0;
            int heavyNum = people.length - 1;
            int boatCount = people.length;

            while (lightNum < heavyNum){
                while (people[lightNum] + people[heavyNum] > limit) {
                    heavyNum--;
                    if (lightNum == heavyNum) {
                        boatCount++;
                        break;
                    }
                }
                lightNum++;
                heavyNum--;
                boatCount--;
            }

            return boatCount;
        }
    }
}
