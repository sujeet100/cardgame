package com.cardgame;

import java.util.Stack;

/**
 * Created by maverick on 6/12/15.
 */
public class HighCard extends AbstractGameRule {

    private final String resultMessageFormat = "%s has won by high card: %s";

    @Override
    public GameResult apply(Player player1, Player player2) {
        Stack<Card> player1Cards = player1.getHand().getOrderedCards();
        Stack<Card> player2Cards = player2.getHand().getOrderedCards();

        Card player1HighCard;
        Card player2HighCard;
        do{
            if(player1Cards.empty() || player2Cards.empty()){
                return GameResult.DRAW;
            }
            player1HighCard = player1Cards.pop();
            player2HighCard = player2Cards.pop();
        }while(player1HighCard.equals(player2HighCard));

        if(player1HighCard.compareTo(player2HighCard) == 1){
            return getResult(player1, player1HighCard);
        } else {
            return getResult(player2, player2HighCard);
        }
    }

    private GameResult getResult(Player player, Card card) {
        return new GameResult(player, String.format(resultMessageFormat, player.getName(), card));
    }
}
