package com.cardgame;

import java.util.*;
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
        return (Stack<Card>) cards.clone();
    }

    public boolean hasThreeOfAKind(){
        Rank threeOfAKindRank = getThreeOfAKindRank();
        return threeOfAKindRank !=null;
    }

    public Rank getThreeOfAKindRank(){
        Map<Rank, AtomicInteger> cardRankCount= new HashMap<Rank, AtomicInteger>();
        for(Card card : getOrderedCards()){
            if(cardRankCount.containsKey(card.rank)){
                cardRankCount.get(card.rank).incrementAndGet();
            } else {
                cardRankCount.put(card.rank, new AtomicInteger(1));
            }
        }
        for(Rank rank : cardRankCount.keySet()){
            if(cardRankCount.get(rank).get() == 3 ){
                return rank;
            }
        }
        return null;
    }

    public Card highCard(){
        return Collections.max(cards);
    }

    public void addCards(ArrayList<Card> cards) {
        if(this.cards.size() + cards.size() > size){
            throw new RuntimeException("Cannot exceed hand size");
        }
        this.cards.addAll(cards);
    }
}
