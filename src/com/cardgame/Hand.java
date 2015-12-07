package com.cardgame;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by maverick on 5/12/15.
 */
public class Hand {
    private final Stack<Card> cards = new Stack<Card>();
    private final int size;

    public Hand(int size) {
        this.size = size;
    }

    public int size() {
        return size;
    }

    public void addCard(Card card) {
        if(cards.size() == size){
            throw new RuntimeException("Hand is full");
        }
        cards.add(card);
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public Stack<Card> getOrderedCards() {
        Collections.sort(cards);
        return cards;
    }

    public boolean hasThreeOfAKind(){
        Rank threeOfAKindRank = getThreeOfAKindRank();
        return threeOfAKindRank !=null;
    }

    public Rank getThreeOfAKindRank(){
        Map<Rank, AtomicInteger> player1CardRankCounter= new HashMap<Rank, AtomicInteger>();
        for(Card card : getOrderedCards()){
            if(player1CardRankCounter.containsKey(card.rank)){
                player1CardRankCounter.get(card.rank).incrementAndGet();
            } else {
                player1CardRankCounter.put(card.rank, new AtomicInteger(1));
            }
        }
        for(Rank rank : player1CardRankCounter.keySet()){
            if(player1CardRankCounter.get(rank).get() == 3 ){
                return rank;
            }
        }
        return null;
    }

    public Card highCard(){
        return Collections.max(cards);
    }
}
