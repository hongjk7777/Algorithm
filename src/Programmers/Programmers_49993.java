package Programmers;

import java.util.HashMap;
import java.util.Map;

public class Programmers_49993 {
    public static void main(String[] args) {
        new Solution().solution("CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"});
    }

    static class Solution {
        Map<Character, Boolean> skillMap = new HashMap<>();
        public int solution(String skill, String[] skill_trees) {
            addSkillsToMap(skill);

            return getAvailableCount(skill, skill_trees);
        }

        private void addSkillsToMap(String skill) {
            for (int i = 0; i < skill.length(); i++) {
                skillMap.put(skill.charAt(i), true);
            }
        }

        private int getAvailableCount(String skill, String[] skill_trees) {
            int count = 0;

            for (String skill_tree : skill_trees) {
                if (isAvailable(skill, skill_tree)) {
                    count++;
                }
            }

            return count;
        }

        private boolean isAvailable(String skill, String skill_tree) {
            int skillIndex = 0;

            for (int skillTreeIndex = 0; skillTreeIndex < skill_tree.length(); skillTreeIndex++) {
                char curSkill = skill_tree.charAt(skillTreeIndex);
                if (skillMap.containsKey(curSkill)) {
                    if (skillIndex == skill.indexOf(curSkill)) {
                        skillIndex++;
                    } else {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}
