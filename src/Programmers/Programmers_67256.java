package Programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Programmers_67256 {
    private  static Hand leftHand = new Hand(true), rightHand = new Hand(false);

    public static String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();

        for(int i = 0; i < numbers.length; i++){
            answer.append(getUsedHand(numbers[i], hand));
        }

        return answer.toString();
    }

    public static String getUsedHand(int number, String hand){
        if(isLeft(number)){
            leftHand.moveTo(number);
            return "L";
        } else if(isRight(number)){
            rightHand.moveTo(number);
            return "R";
        } else{
            if(isBetterToUseLeft(number, hand)){
                leftHand.moveTo(number);
                return "L";
            } else{
                rightHand.moveTo(number);
                return "R";
            }
        }
    }

    public static boolean isLeft(int number){
        return number == 1 || number == 4 || number == 7;
    }

    public static boolean isRight(int number){
        return number == 3 || number == 6 || number == 9;
    }

    public static boolean isBetterToUseLeft(int number, String hand){
        int leftDistant = leftHand.getDistant(number);
        int rightDistant = rightHand.getDistant(number);

        if (leftDistant < rightDistant){
            return true;
        } else if (leftDistant > rightDistant){
            return false;
        } else{
            if(hand.equals("left")){
                return true;
            } else {
                return false;
            }
        }
    }


    static class Hand{
        int row, col;

        public Hand(boolean isLeftHand){
            row = 3;

            if(isLeftHand){
                col = 0;
            } else{
                col = 2;
            }
        }

        public void moveTo(int number){
            this.row = getRow(number);
            this.col = getCol(number);
        }


        public int getRow(int number){
            if(number != 0){
                return (number - 1) / 3;
            } else{
                return 3;
            }
        }

        public int getCol(int number){
            if(number != 0){
                return (number - 1) % 3;
            } else{
                return 1;
            }
        }

        public int getDistant(int number){
            return Math.abs(getRow(number) - this.row) + Math.abs(getCol(number) - this.col);
        }
    }


}
