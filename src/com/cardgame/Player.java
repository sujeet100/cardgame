package com.cardgame;

/**
 * Created by maverick on 5/12/15.
 */
public class Player {
    public static final Player NONE = new Player("NONE");
    private final String name;
    private Hand hand;

    public Player(String name) {
        this.name = name;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!name.equals(player.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public Hand getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
