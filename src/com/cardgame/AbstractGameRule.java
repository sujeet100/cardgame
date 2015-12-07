package com.cardgame;

/**
 * Created by maverick on 6/12/15.
 */
public abstract class AbstractGameRule implements GameRule {
    private GameRule next;

    @Override
    public void setNext(GameRule next) {
        this.next = next;
    }

    @Override
    public GameRule getNext() {
        return this.next;
    }
}
