package com.cardgame;

/**
 * Created by maverick on 5/12/15.
 */
public class Game {
    private Player player1;
    private Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public GameResult play(){
        GameRule threeOfAKind = new ThreeOfAKind();
        GameRule highCard = new HighCard();
        threeOfAKind.setNext(highCard);
        return threeOfAKind.apply(player1, player2);
    }
}
