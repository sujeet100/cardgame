package com.cardgame;

/**
 * Created by maverick on 6/12/15.
 */
public class GameResult {
    public static final GameResult DRAW = new GameResult(Player.NONE, "Match was drawn");
    private Player winner;
    private String resultMessage;

    public GameResult(){}

    public GameResult(Player winner, String resultMessage) {
        this.winner = winner;
        this.resultMessage = resultMessage;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
