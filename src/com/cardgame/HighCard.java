package com.cardgame;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Created by maverick on 6/12/15.
 */
public class HighCard extends AbstractGameRule {

    private final String resultMessageFormat = "%s has won by high card: %s";

    @Override
    public GameResult apply(List<Player> players) {
        HighCardComparator highCardComparator = new HighCardComparator();
        Collections.sort(players, highCardComparator);
        Collections.reverse(players);
        Player first = players.get(0);
        Player second = players.get(1);
        if(highCardComparator.compare(first, second) != 0){
            return getResult(first, highCardComparator.highCard);
        } else if(this.getNext() != null) {
            return this.getNext().apply(players);
        } else {
            return GameResult.DRAW;
        }

    }

    private class HighCardComparator implements Comparator<Player> {

        Card highCard;

        @Override
        public int compare(Player player1, Player player2) {
            Stack<Card> player1Cards = player1.getHand().getOrderedCards();
            Stack<Card> player2Cards = player2.getHand().getOrderedCards();

            Card player1HighCard;
            Card player2HighCard;
            do{
                if(player1Cards.empty() || player2Cards.empty()){
                    return 0;
                }
                player1HighCard = player1Cards.pop();
                player2HighCard = player2Cards.pop();
            }while(player1HighCard.equals(player2HighCard));

            if(player1HighCard.compareTo(player2HighCard) > 0){
                highCard = player1HighCard;
            } else if(player1HighCard.compareTo(player2HighCard) < 0){
                highCard = player2HighCard;
            }
            return player1HighCard.compareTo(player2HighCard);
        }
    }

    private GameResult getResult(Player player, Card card) {
        return new GameResult(player, String.format(resultMessageFormat, player.getName(), card));
    }
}
