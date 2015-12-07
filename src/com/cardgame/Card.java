package com.cardgame;

/**
 * Created by maverick on 5/12/15.
 */
public class Card implements Comparable<Card> {
    Suit suit;
    Rank rank;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (rank != card.rank) return false;
        if (suit != card.suit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }

    @Override
    public int compareTo(Card that) {
         if(this.rank.getValue() < that.rank.getValue()) {
            return -1;
         } else if(this.rank.getValue() == that.rank.getValue()) {
             return 0;
         } else if(this.rank.getValue() > that.rank.getValue()) {
             return 1;
         } else{
             return 0;
         }
    }

    @Override
    public String toString() {
        return rank+" - "+suit;
    }
}
