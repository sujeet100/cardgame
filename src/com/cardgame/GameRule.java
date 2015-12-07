package com.cardgame;

import java.util.List;

/**
 * Created by maverick on 6/12/15.
 */
public interface GameRule {
    public GameResult apply(List<Player> players);
    public void setNext(GameRule gameRule);
    public GameRule getNext();
}
