package day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem_1 {

    private static ArrayList<Hand> cardsAndBids = new ArrayList<Hand>();
    private static char[] CARDS = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};
    // private static String[] HAND_NAMES = {"High card", "One pair", "Two pair", "Three of a kind", "Full house", "Four of a kind", "Five of a kind"};

    public Problem_1() throws FileNotFoundException {
        File myObj = new File("day07\\input.txt");
        Scanner myReader = new Scanner(myObj);
        getAllHands(myReader);
        sortHands();
        int sum = calculateWinnings();
        myReader.close();
        System.out.println(sum);
    }

    private void getAllHands(Scanner myReader) {
        while (myReader.hasNextLine()) {
            String[] string = myReader.nextLine().split(" ");
            cardsAndBids.add(new Hand(string[0], Integer.parseInt(string[1]), -1));
        }
    }

    private static void sortHands() {
        for (Hand hand : cardsAndBids) {
            evaluateHand(hand);
        }
        ArrayList<Hand> sortedHands = new ArrayList<Hand>();
        while (cardsAndBids.size() > 0) {
            Hand hand = cardsAndBids.removeFirst();
            sortedHands.add(sortByHand(sortedHands, hand), hand);
        }
        cardsAndBids = sortedHands;
        for (Hand hand : sortedHands) {
            System.out.println(hand.toString());
        }
    }

    private static void evaluateHand(Hand hand) {
        int[] cardsOfHand = new int[CARDS.length];
        for (int i = 0; i < hand.getHand().length(); i++) {
            for (int j = 0; j < cardsOfHand.length; j++) {
                if (CARDS[j] == hand.getHand().charAt(i)) {
                    cardsOfHand[j]++;
                }
            }
        }
        int max1 = 0;
        int max2 = 0;
        for (int j = 0; j < cardsOfHand.length; j++) {
            if (cardsOfHand[j] > max1) {
                max2 = max1;
                max1 = cardsOfHand[j];
            } else if (cardsOfHand[j] > max2) {
                max2 = cardsOfHand[j];
            }
        }
        switch (max2) {
            case 2:
                switch (max1) {
                    case 3:
                        hand.setHandValue(4);
                        break;
                    case 2:
                        hand.setHandValue(2);
                        break;
                }
                break;
            case 0:
            case 1:
                switch (max1) {
                    case 5:
                        hand.setHandValue(6);
                        break;
                    case 4:
                        hand.setHandValue(5);
                        break;
                    case 3:
                        hand.setHandValue(3);
                        break;
                    case 2:
                        hand.setHandValue(1);
                        break;
                    case 1:
                        hand.setHandValue(0);
                        break;
                }
                break;
        }
    }

    private static int sortByHand(ArrayList<Hand> sortedArray, Hand hand) {
        for (Hand sortedHand : sortedArray) {
            if (hand.getHandValue() > sortedHand.getHandValue()) {
                return sortedArray.indexOf(sortedHand);
            }
            if (sortedHand.getHandValue() == hand.getHandValue()) {
                if (hand.isBiggerThan(sortedHand, CARDS)) {
                    return sortedArray.indexOf(sortedHand); 
                }
            }
        }
        return sortedArray.size();
    }

    private static int calculateWinnings() {
        int sum = 0;
        int i = cardsAndBids.size();
        for (Hand hand : cardsAndBids) {
            sum += i*hand.getBid();
            i--;
        }
        return sum;
    }
}
