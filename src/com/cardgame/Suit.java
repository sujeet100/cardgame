package com.cardgame;

/**
 * Created by maverick on 5/12/15.
 */
public enum Suit {
    CLUB(0),
    DIAMOND(1),
    HEART(2),
    SPADE(3);

    private final int value;

    Suit(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
