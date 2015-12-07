package com.cardgame;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by maverick on 6/12/15.
 */
public class ThreeOfAKind extends AbstractGameRule {

    private final String resultMessageFormat = "%s has won by three of a kind: %s";

    @Override
    public GameResult apply(List<Player> players) {

        ThreeOfAKindComparator threeOfAKindComparator = new ThreeOfAKindComparator();
        Collections.sort(players, threeOfAKindComparator);
        Collections.reverse(players);
        Player first = players.get(0);
        Player second = players.get(1);
        if(threeOfAKindComparator.compare(first, second) != 0){
            return getResult(first, first.getHand().getThreeOfAKindRank());
        } else if(this.getNext() != null) {
            return this.getNext().apply(players);
        } else {
            return GameResult.DRAW;
        }
    }

    private class ThreeOfAKindComparator implements Comparator<Player> {

        @Override
        public int compare(Player player1, Player player2) {
            Rank threeOfAKindRankPlayer1 = player1.getHand().getThreeOfAKindRank();
            Rank threeOfAKindRankPlayer2 = player2.getHand().getThreeOfAKindRank();
            if (threeOfAKindRankPlayer1 != null && threeOfAKindRankPlayer2 != null) {
                if (threeOfAKindRankPlayer1.getValue() > threeOfAKindRankPlayer2.getValue()) {
                    return 1;
                } else if (threeOfAKindRankPlayer1.getValue() < threeOfAKindRankPlayer2.getValue()) {
                    return -1;
                } else {
                    return 0;
                }

            } else {
                if (threeOfAKindRankPlayer1 != null) {
                    return 1;
                } else if (threeOfAKindRankPlayer2 != null) {
                    return -1;
                } else {
                    return 0;
                }
            }

        }
    }

    private GameResult getResult(Player player, Rank threeOfAKindRank) {
        return new GameResult(player, String.format(resultMessageFormat, player.getName(), threeOfAKindRank));
    }
}
