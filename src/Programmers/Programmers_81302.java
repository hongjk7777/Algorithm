package Programmers;

public class Programmers_81302 {
    public static void main(String[] args) {
        new Solution().solution(new String[][]{});
    }

    static class Solution {
        public int[] solution(String[][] places) {
            int[] answer;
            int[] tempAnswer = new int[5];
            for(int i = 0; i < 5; i++){
                if(isManhattenDistance(places, i)){
                    tempAnswer[i] = 1;
                } else{
                    tempAnswer[i] = 0;
                }

            }
            return tempAnswer;
        }

        public boolean isManhattenDistance(String[][] places, int num)
        {
            for(int i = 0; i<5; i++){
                for(int j = 0; j<5; j++){
                    if(places[num][i].charAt(j) == 'P'){
                        if(!checkDistance(places, num, i, j)){
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        public boolean checkDistance(String[][] places, int num, int row, int column)
        {
            if(row < 4 && places[num][row+1].charAt(column) == 'P'){
                return false;
            }

            if(column < 4 && places[num][row].charAt(column+1) == 'P'){
                return false;
            }

            if(row > 0 && places[num][row - 1].charAt(column) == 'P'){
                return false;
            }

            if(column > 0 && places[num][row].charAt(column - 1) == 'P'){
                return false;
            }

            if(row < 4 && column < 4 && places[num][row+1].charAt(column+1) == 'P'){
                if(!(places[num][row].charAt(column+1) == 'X' && places[num][row+1].charAt(column) == 'X'))
                    return false;
            }

            if(row < 4 && column > 0 && places[num][row+1].charAt(column - 1) == 'P'){
                if(!(places[num][row].charAt(column-1) == 'X' && places[num][row+1].charAt(column) == 'X'))
                    return false;
            }

            if(row > 0 && column < 4 && places[num][row-1].charAt(column+1) == 'P'){
                if(!(places[num][row].charAt(column+1) == 'X' && places[num][row-1].charAt(column) == 'X'))
                    return false;
            }

            if(row >0 && column >0 && places[num][row-1].charAt(column-1) == 'P'){
                if(!(places[num][row].charAt(column-1) == 'X' && places[num][row-1].charAt(column) == 'X'))
                    return false;
            }

            if(row < 3 && places[num][row+2].charAt(column) == 'P'){
                if(!(places[num][row + 1].charAt(column) == 'X'))
                    return false;
            }

            if(column < 3 && places[num][row].charAt(column+2) == 'P'){
                if(!(places[num][row].charAt(column+1) == 'X'))
                    return false;
            }

            if(row > 1 && places[num][row-2].charAt(column) == 'P'){
                if(!(places[num][row - 1].charAt(column) == 'X'))
                    return false;
            }

            if(column > 1 && places[num][row].charAt(column-2) == 'P'){
                if(!(places[num][row].charAt(column-1) == 'X'))
                    return false;
            }

            return true;
        }
    }
}
