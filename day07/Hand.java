package day07;

public class Hand {
    
    private String hand;
    private int bid;
    private int handValue;

    public Hand(String hand, int bid, int handValue) {
        this.hand = hand;
        this.bid = bid;
        this.handValue = handValue;
    }

    public String toString() {
        return String.format("Hand: %s, %d, %d", hand, bid, handValue);
    }

    public String getHand() {
        return hand;
    }

    public int getBid() {
        return bid;
    }

    public int getHandValue() {
        return handValue;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public void setHandValue(int handValue) {
        this.handValue = handValue;
    }

    public boolean isBiggerThan(Hand hand2, char[] cards) {
        for (int i = 0; i < hand.length(); i++) {
            if (this.hand.charAt(i) == hand2.hand.charAt(i)) {
                continue;
            }
            for (int j = 0; j < cards.length; j++) {
                if (this.hand.charAt(i) == cards[j]) {
                    return false;
                }
                if (hand2.hand.charAt(i) == cards[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
