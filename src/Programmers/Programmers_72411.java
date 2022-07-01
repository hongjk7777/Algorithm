package Programmers;

import java.util.*;

public class Programmers_72411 {
    public static void main(String[] args) {
        new Solution().solution(new String[]{}, new int[]{});
    }

    static class Solution {
        private static final int MAX_ORDER = 10;
        private Map<String, Integer> orderMap = new HashMap<>();
        private boolean[] neededCourseSize = new boolean[MAX_ORDER + 1];
        //각 메뉴 개수별로 가장 많이 주문된 수를 저장하는 배열
        private int[] orderMax = new int[MAX_ORDER + 1];

        public String[] solution(String[] orders, int[] course) {
            addOrdersToMap(orders);
            checkNeededCourseSize(course);
            System.out.println(orderMap.get("WX"));
            return getCourseMenu();
        }

        private void addOrdersToMap(String[] orders){
            for(String order : orders) {
                char[] charArr = order.toCharArray();
                Arrays.sort(charArr);
                String sortedOrder =new String(charArr);
                addOrderToMap(sortedOrder);
            }
        }

        private void addOrderToMap(String order) {
            for(int size = 2; size <= MAX_ORDER; size++) {
                dfs(order, "", 0, size);
            }
        }

        private void dfs(String order, String curOrder, int start, int size) {
            if(curOrder.length() == size) {
                // System.out.println(curOrder);
                if(orderMap.containsKey(curOrder)) {
                    int value = orderMap.get(curOrder);
                    orderMap.replace(curOrder, value + 1);
                    orderMax[size] = Math.max(orderMax[size], value + 1);
                } else {
                    orderMap.put(curOrder, 1);
                    orderMax[size] = Math.max(orderMax[size], 1);
                }
                return;
            }

            StringBuilder nextOrder = new StringBuilder(curOrder);
            for(int i = start; i < order.length(); i++) {
                nextOrder.append(order.charAt(i));
                dfs(order, nextOrder.toString(), i + 1, size);
                nextOrder.delete(nextOrder.length() - 1, nextOrder.length());
            }
        }

        private void checkNeededCourseSize(int[] course) {
            for(int size : course) {
                neededCourseSize[size] = true;
            }
        }

        private String[] getCourseMenu() {
            ArrayList<String> courseMenu = new ArrayList<>();

            for(String strkey : orderMap.keySet()) {
                int orderedNum = orderMap.get(strkey);

                if (orderedNum < 2){
                    continue;
                }

                if(orderMax[strkey.length()] == orderedNum && neededCourseSize[strkey.length()]) {
                    courseMenu.add(strkey);
                }
            }

            Collections.sort(courseMenu);
            return courseMenu.toArray(new String[0]);
        }
    }
}
