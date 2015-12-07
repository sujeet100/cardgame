package com.cardgame;

/**
 * Created by maverick on 6/12/15.
 */
public class ThreeOfAKind extends AbstractGameRule {

    private final String resultMessageFormat = "%s has won by three of a kind: %s";

    @Override
    public GameResult apply(Player player1, Player player2) {
        Rank threeOfAKindRankPlayer1 = player1.getHand().getThreeOfAKindRank();
        Rank threeOfAKindRankPlayer2 = player2.getHand().getThreeOfAKindRank();
        if (threeOfAKindRankPlayer1 != null && threeOfAKindRankPlayer2 != null) {
            if (threeOfAKindRankPlayer1.getValue() > threeOfAKindRankPlayer2.getValue()) {
                return getResult(player1, threeOfAKindRankPlayer1);
            } else if (threeOfAKindRankPlayer1.getValue() < threeOfAKindRankPlayer2.getValue()) {
                return getResult(player2, threeOfAKindRankPlayer2);
            }

        } else {
            if (threeOfAKindRankPlayer1 != null) {
                return getResult(player1, threeOfAKindRankPlayer1);
            }

            if (threeOfAKindRankPlayer2 != null) {
                return getResult(player2, threeOfAKindRankPlayer2);
            }
        }


        return this.getNext().apply(player1, player2);
    }

    private GameResult getResult(Player player, Rank threeOfAKindRank) {
        return new GameResult(player, String.format(resultMessageFormat, player.getName(), threeOfAKindRank));
    }
}
