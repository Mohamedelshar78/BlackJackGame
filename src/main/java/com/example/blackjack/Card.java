package com.example.blackjack;

public class Card {

    private final Integer suit;
    private final Integer rank;
    private final Integer value;

    public Card(Integer suit, Integer rank, Integer value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    public Card(Card card) {
        this.suit = card.getSuit();
        this.rank = card.getRank();
        this.value = card.getValue();
    }

    public Integer getSuit() {
        return suit;
    }

    public Integer getRank() {
        return rank;
    }

    public Integer getValue() {
        return value;
    }

}
