package com.example.blackjack;

public class Player {

    String name;
    int score = 0;
    private Card[] hand = new Card[11];

    private Integer cardCounter = 0;

    public void addCard(Card card) {

        if (cardCounter < 11) {
            hand[cardCounter] = card;
            cardCounter++;
            score += card.getValue();
        }
        else{
            System.out.println("you can't add more than 11 cards");
        }
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Card[] getHand() {
        return hand;
    }

    public Integer getCardCounter() {
        return cardCounter;
    }
}
