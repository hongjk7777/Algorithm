/*
아쉬웠던 점
1. 문제를 제대로 안 읽었음
2. 이런 형식의 수열을 다루는 게 부족
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_15661 {
    private static final int MAX_INT = 987654321;
    private static int n, minDiff = MAX_INT;
    private static int[][] synergy;
    private static boolean[] checked;
    private static final ArrayList<Integer> team = new ArrayList<>();
    private static final ArrayList<Integer> otherTeam = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        getInput();
        makeTeams(0);
        System.out.println(minDiff);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        synergy = new int[n][n];
        checked = new boolean[n];

        for (int row = 0; row < n; row++) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < n; col++) {
                synergy[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }
    }

    private static void makeTeams(int start) {
        if (start >= n) {
            int temp = getSynergyDiff();
            updateMinDiff(temp);
            return;
        }
        checked[start] = true;
        makeTeams(start + 1);
        checked[start] = false;
        makeTeams(start + 1);
    }

    private static int getSynergyDiff() {
        for (int i = 0; i < n; i++) {
            if (checked[i]) {
                team.add(i);
            } else {
                otherTeam.add(i);
            }
        }

        if (team.size() == n || otherTeam.size() == n) {
            clearTeams();
            return MAX_INT;
        }

        int teamSynergy = getTeamSynergy();

        int otherTeamSynergy = getOtherTeamSynergy();

        clearTeams();
        return Math.abs(teamSynergy - otherTeamSynergy);
    }

    private static int getTeamSynergy() {
        int teamSynergy = 0;
        for (int memberNum = 0; memberNum < team.size(); memberNum++) {
            int member = team.get(memberNum);
            for (int otherMember : team) {
                teamSynergy += synergy[member][otherMember];
            }
        }
        return teamSynergy;
    }

    private static int getOtherTeamSynergy() {
        int otherTeamSynergy = 0;
        for (int memberNum = 0; memberNum < otherTeam.size(); memberNum++) {
            int member = otherTeam.get(memberNum);
            for (int otherMember : otherTeam) {
                otherTeamSynergy += synergy[member][otherMember];
            }
        }
        return otherTeamSynergy;
    }

    private static void clearTeams() {
        team.clear();
        otherTeam.clear();
    }

    private static void updateMinDiff(int temp) {
        if (minDiff > temp) {
            minDiff = temp;
        }
    }
}
