package com.cardgame;

import java.util.List;

/**
 * Created by maverick on 5/12/15.
 */
public class Game {
    private List<Player> players;

    public Game(List<Player> players) {
        this.players = players;
    }

    public GameResult play(){
        GameRule threeOfAKind = new ThreeOfAKind();
        GameRule highCard = new HighCard();
        threeOfAKind.setNext(highCard);
        return threeOfAKind.apply(players);
    }
}
