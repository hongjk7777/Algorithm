package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_2164 {
    private static int cardAmount;
    private static Queue<Integer> cardList = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        getInput();

        makeCardList();

        shuffleCards();

        System.out.println(cardList.poll());
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(reader.readLine());
        cardAmount = Integer.parseInt(tokenizer.nextToken());
    }

    private static void makeCardList() {
        for (int cardNum = 1; cardNum <= cardAmount; cardNum++) {
            cardList.add(cardNum);
        }
    }

    private static void shuffleCards() {
        while (cardList.size() != 1) {
            cardList.poll();
            cardList.add(cardList.poll());
        }
    }
}
