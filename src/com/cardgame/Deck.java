package com.cardgame;

import java.util.Collections;
import java.util.Stack;

/**
 * Created by maverick on 5/12/15.
 */
public class Deck {
    Stack<Card> cards = new Stack<Card>();
    Deck(){
        for (Rank rank : Rank.values()){
            for(Suit suit : Suit.values()){
                //One card can appear twice in a deck of (51*2=104) cards
                cards.add(new Card(rank, suit));
                cards.add(new Card(rank, suit));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public Card getCard() {
        return this.cards.pop();
    }
}
