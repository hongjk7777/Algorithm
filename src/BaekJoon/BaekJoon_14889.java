package BaekJoon;

import java.util.ArrayList;
import java.util.Scanner;

public class BaekJoon_14889 {
    private static int size;
    private static int minDiffAbility = 987654321;
    private static int[][] abilityMap = new int[20][20];

    public static void main(String[] args) {
        getInput();
        getMinDiffTeamAbility();
        System.out.println(minDiffAbility);
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        size = scanner.nextInt();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                abilityMap[i][j] = scanner.nextInt();
            }
        }
    }

    private static void getMinDiffTeamAbility() {
        ArrayList<Integer> teamMember = new ArrayList<>();
        makeTeam(0, 0, teamMember);
    }

    private static void makeTeam(int index, int abilitySum, ArrayList<Integer> teamMember) {
        if (index >= size) {
            if (teamMember.size() == size / 2) {
                int result = Math.abs(abilitySum - getOtherTeamAbility(teamMember));
                if (minDiffAbility > result) {
                    minDiffAbility = result;
                }
            }

            return;
        }

        //not add current index member
        if (size / 2 - teamMember.size() < size - index) {
            makeTeam(index + 1, abilitySum, (ArrayList<Integer>) teamMember.clone());
        }

        //add current index member
        if (teamMember.size() < 10) {
            ArrayList<Integer> clone = (ArrayList<Integer>) teamMember.clone();
            if (!clone.isEmpty()) {
                for (int i = 0; i < clone.size(); i++) {
                    abilitySum += abilityMap[index][clone.get(i)];
                    abilitySum += abilityMap[clone.get(i)][index];
                }
            }
            clone.add(index);

            makeTeam(index + 1, abilitySum, clone);
        }

    }

    private static int getOtherTeamAbility(ArrayList<Integer> teamMember) {
        ArrayList<Integer> otherTeam = new ArrayList<>();
        int abilitySum = 0;

        for (int i = 0; i < size; i++) {
            otherTeam.add(i);
        }
        for (int i = 0; i < teamMember.size(); i++) {
            otherTeam.remove(teamMember.get(i));
        }
        for (int i = 0; i < otherTeam.size(); i++) {
            if (i > 0) {
                for (int j = 0; j < i; j++) {
                    abilitySum += abilityMap[otherTeam.get(i)][otherTeam.get(j)];
                    abilitySum += abilityMap[otherTeam.get(j)][otherTeam.get(i)];
                }
            }
        }

        return abilitySum;

    }
}
