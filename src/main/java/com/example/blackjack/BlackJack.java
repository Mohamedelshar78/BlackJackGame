package com.example.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackJack {

    static Games games = new Games();
    public static void main(String[] args) {

        GUI gui = new GUI();

        games.generateCards();

        games.setInfo();

        gui.runGUI(games.deck,
                games.players[0].getHand(),
                games.players[1].getHand(),
                games.players[2].getHand(),
                games.players[3].getHand());

        turnPlayer(gui);
        games.updateGameScore();

        turnDealer(gui);
        games.updateGameScore();

        determineWinner();

    }


    public static void turnPlayer(GUI gui){

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < games.players.length - 1; i++) {
            String state = "";
            System.out.print("player no. " + (i + 1) + " { hit  / stand }  : ");
            while (!state.equalsIgnoreCase("stand")){
                state = scanner.next();
                if (state.equals("hit")){
                    addCardToPlayer(i,gui);
                }
            }
        }
    }

    public static void addCardToPlayer(int index , GUI gui){
        Card card = games.drawCard();
        games.players[index].addCard( card);
        gui.updatePlayerHand(card,index);

    }

    public static void turnDealer(GUI gui)
    {
        boolean dealersWin = true;
        int highestScore = 0;

        for (int i = 0; i < games.players.length - 1; i++) {
            if (games.highScore[i] >= games.players[3].score) {
                dealersWin = false;
            }
            if (games.highScore[i]>highestScore) {
                highestScore = games.highScore[i];
            }
        }

        if (!dealersWin) {
            addCardToDealer(gui,highestScore);
        }
    }
    public static void addCardToDealer(GUI gui , int highestScore){
        while (games.players[3].score <highestScore ) {
            Card card = games.drawCard();
            games.players[3].addCard(card);
            gui.updateDealerHand(card, games.deck);
        }

    }
    public  static  void determineWinner() {

        int maxPlayerScore = 0;
        List<Player> winningPlayers = new ArrayList<>();

        // Find the maximum score among players
        for (Player player : games.players) {
            int playerScore = player.score;
            if (playerScore > maxPlayerScore && playerScore < 21) {
                maxPlayerScore = playerScore;
            }
        }

        // Compare player scores with the dealer's score
        if (maxPlayerScore > 21) {
            System.out.println("All players busted. Dealer wins!");
        } else {
            if (games.players[3].score > 21 || maxPlayerScore > games.players[3].score) {
                for (Player player : games.players) {
                    if (player.score == maxPlayerScore) {
                        winningPlayers.add(player);
                    }
                }

                if (winningPlayers.size() == 1) {
                    System.out.println("Player " + winningPlayers.get(0).getName() + " wins with a score of " + maxPlayerScore);
                } else {
                    System.out.println("It's a tie (PUSH) among the winning players.");
                }
            } else {
                System.out.println("Dealer wins!");
            }
        }
    }

}