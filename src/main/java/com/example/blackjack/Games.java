package com.example.blackjack;

import java.util.Random;
import java.util.Scanner;


public class Games {

    Card[] deck = new Card[52];

    Player[] players = new Player[4];
    int[] highScore = new int[4];

    public void generateCards( ) {

        int counter = 0;
        for (int i = 0; i < 4; i++) {
             for (int j = 0; j  <  13;   j++ )   {

                int value = (j >= 10) ?  10 : j + 1;
                Card card = new Card(i, j, value);
                deck[counter] = card;
                counter++;

            }
        }
    }

    public Card drawCard() {
        Random rand = new Random();
        Card card = null;

        do {

            int randNum = rand.nextInt(51);
            card = deck[randNum];
            deck[randNum] = null;

        } while (card == null);

        return card;

    }

    public void setInfo( ) {
        Scanner scanner = new Scanner(System .in);
        for (int i = 0; i < players.length - 1; i++) {
               System.out . print("Enter name of player " + (i+1) + " : ");
            players[i] = new Player();
            players[i].name = scanner.next();
            players[i].addCard(this.drawCard());
            players[i].addCard(this.drawCard());
        }

        players[3 ]  = new Player();
        players[3].name = "Dealer";
        players[3].addCard(this.drawCard());
        players[3].addCard(this.drawCard());
    }

    public void updateGameScore( ) {

        for (int i = 0; i < highScore.length; i++) {
        highScore[i] = players[i].score <= 21  ? players[i].score  : 0;
        }
    }

}
