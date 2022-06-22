package Programmers;

import java.util.Arrays;
import java.util.Stack;

public class Programmers_42584 {
    public static void main(String[] args) {
        new Solution().solution(new int[]{1,2,3,4,3,2,1,3,24,8,2});
    }

    static class Solution {
        Stack<Price> priceStack = new Stack<>();
        int[] answer;
        public int[] solution(int[] prices) {
            answer = new int[prices.length];
            countSeconds(prices);
            System.out.println(Arrays.toString(answer));
            return answer;
        }

        private void countSeconds(int[] prices) {
            for (int i = 0; i < prices.length; i++) {
                while (!priceStack.isEmpty() && priceStack.peek().price > prices[i]) {
                    Price pop = priceStack.pop();
                    answer[pop.start] = i - pop.start;
                }
                priceStack.add(new Price(prices[i], i));
            }

            while (!priceStack.isEmpty()) {
                Price pop = priceStack.pop();
                answer[pop.start] = prices.length - 1 - pop.start;
            }
        }

        private class Price {
            int price, start;

            public Price(int price, int start) {
                this.price = price;
                this.start = start;
            }
        }
    }
}
