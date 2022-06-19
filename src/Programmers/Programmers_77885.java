package Programmers;

import java.util.ArrayList;

public class Programmers_77885 {
    public static void main(String[] args) {
        new Solution().solution(new long[]{1000000000000000L});
    }

    static class Solution {
        ArrayList<Long> answer = new ArrayList<>();
        public long[] solution(long[] numbers) {
            for (long number : numbers) {
                long value = getMinValue(number);
                answer.add(value);
            }

            return answer.stream().mapToLong(i -> i).toArray();
        }

        private long getMinValue(long number) {
            if ((number & 1) == 0) {
                return number + 1;
            }

            int size = 1;
            while (((number >> size) & 1) == 1) {
                size++;
            }

            return (long) (number - Math.pow(2, size - 1) + Math.pow(2, size));
        }
    }
}
