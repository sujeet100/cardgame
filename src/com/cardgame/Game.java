package com.cardgame;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maverick on 5/12/15.
 */
public class Game {
    private int noOfPlayers;
    private int handSize;
    private List<Player> players = new ArrayList<Player>();

    public Game(int noOfPlayers, int handSize) {
        this.noOfPlayers = noOfPlayers;
        this.handSize = handSize;
    }

    public GameResult play(){
        GameRule threeOfAKind = new ThreeOfAKind();
        GameRule highCard = new HighCard();
        threeOfAKind.setNext(highCard);
        return threeOfAKind.apply(players);
    }

    public void deal(){
        Deck deck = new Deck();
        deck.shuffle();
        for (Player player : players){
            Hand hand = new Hand(handSize);
            for (int i=0; i<handSize; i++){
                hand.addCard(deck.getCard());
            }
            player.setHand(hand);
        }
    }

    public void deal(Player player, ArrayList<Card> cards) {
        Hand hand = new Hand(handSize);
        hand.addCards(cards);
        this.players.get(this.players.indexOf(player)).setHand(hand);
    }

    public void addPlayer(Player player) {
        if(this.players.size() < noOfPlayers){
            this.players.add(player);
        } else{
            throw new RuntimeException("Game is full. No place for a new player.");
        }
    }
}
