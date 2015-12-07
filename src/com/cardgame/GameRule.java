package com.cardgame;

/**
 * Created by maverick on 6/12/15.
 */
public interface GameRule {
    public GameResult apply(Player player1, Player player2);
    public void setNext(GameRule gameRule);
    public GameRule getNext();
}
