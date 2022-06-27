package Programmers;
import java.util.*;

public class Programmers_64065 {

    public static void main(String[] args) {
        new Solution().solution("");
    }

    static class Solution {

        public int[] solution(String s) {
            Set<String> set = new HashSet<>();
            String[] arr = s.replaceAll("\\{", " ").replaceAll("}", " ").trim().split(" , ");
            int[] answer = new int[arr.length];
            Arrays.sort(arr, Comparator.comparingInt(String::length));

            for(int i = 0; i < arr.length; i++) {
                String[] elements = arr[i].split(",");
                for(String element : elements){
                    if(set.add(element)){
                        answer[i] = Integer.parseInt(element);
                    }
                }
            }

            return answer;
        }
    }
}
