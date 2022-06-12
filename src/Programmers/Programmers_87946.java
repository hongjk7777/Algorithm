package Programmers;

public class Programmers_87946 {
    public static void main(String[] args) {
        new Solution().solution(80, new int[][]{{80, 20}, {50, 40}, {30, 10}});
    }

    static class Solution {
        int max = 0;
        boolean[] visited;

        public int solution(int maxFatigue, int[][] dungeons) {
            visited = new boolean[dungeons.length];
            dfs(maxFatigue, dungeons, 0);
            return max;
        }

        private void dfs(int curFatigue, int[][] dungeons, int count) {
            for (int num = 0; num < dungeons.length; num++) {
                int[] dungeon = dungeons[num];
                if (!visited[num]) {
                    if (curFatigue >= dungeon[0]) {
                        visited[num] = true;
                        dfs(curFatigue - dungeon[1], dungeons, count + 1);
                        max = Math.max(max, count + 1);
                        visited[num] = false;
                    }
                }
            }
        }
    }
}
