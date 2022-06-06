package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2891 {
    private static int teamSize;
    private static int[] team;

    public static void main(String[] args) throws IOException {
        getInput();
        int min = getMinBrokenTeam();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        teamSize = Integer.parseInt(tokenizer.nextToken());
        int brokenTeamSize = Integer.parseInt(tokenizer.nextToken());
        int extraTeamSize = Integer.parseInt(tokenizer.nextToken());
        team = new int[teamSize];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < brokenTeamSize; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            team[num - 1]--;
        }

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < extraTeamSize; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            team[num - 1]++;
        }
    }

    private static int getMinBrokenTeam() {
        makeBrokenTeamMin();
        return countBrokenTeam();
    }

    private static void makeBrokenTeamMin() {
        for (int num = 0; num < teamSize; num++) {
            if (team[num] < 0) {
                if (num - 1 >= 0 && team[num - 1] > 0) {
                    team[num - 1]--;
                    team[num]++;
                } else if (num + 1 < teamSize && team[num + 1] > 0) {
                    team[num + 1]--;
                    team[num]++;
                }
            }
        }
    }

    private static int countBrokenTeam() {
        int brokenTeam = 0;
        for (int num = 0; num < teamSize; num++) {
            if (team[num] < 0) {
                brokenTeam++;
            }
        }
        return brokenTeam;
    }
}
