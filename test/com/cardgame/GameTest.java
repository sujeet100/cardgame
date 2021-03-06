package com.cardgame;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by maverick on 5/12/15.
 */
public class GameTest {
    @Test
    public void thePlayerWithGreaterRankHighCardWins(){

        final Player john = new Player("John");
        final Player bob = new Player("Bob");
        Game game = new Game(3, 3);
        game.addPlayer(john);
        game.addPlayer(bob);
        game.deal(john, new ArrayList<Card>(){
            {
                add(new Card(Rank.TEN, Suit.CLUB));
                add(new Card(Rank.JACK, Suit.HEART));
                add(new Card(Rank.JACK, Suit.DIAMOND));
            }
        });

        game.deal(bob, new ArrayList<Card>(){
            {
                add(new Card(Rank.NINE, Suit.CLUB));
                add(new Card(Rank.THREE, Suit.HEART));
                add(new Card(Rank.EIGHT, Suit.DIAMOND));
            }
        });
        
        GameResult result = game.play();
        Assert.assertThat(result.getWinner(), CoreMatchers.is(john));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("John has won by high card: JACK - HEART"));
    }

    @Test
    public void thePlayerWithGreaterSuitWinsIfBothOfThemHaveSameRankHighCardAsHighestRankCard(){

        final Player john = new Player("John");
        final Player bob = new Player("Bob");
        final Player phill = new Player("Phill");


        Game game = new Game(3, 3);
        game.addPlayer(john);
        game.addPlayer(bob);
        game.addPlayer(phill);

        game.deal(john, new ArrayList<Card>(){
            {
                add(new Card(Rank.TEN, Suit.CLUB));
                add(new Card(Rank.THREE, Suit.HEART));
                add(new Card(Rank.JACK, Suit.DIAMOND));
            }
        });

        game.deal(bob, new ArrayList<Card>(){
            {
                add(new Card(Rank.NINE, Suit.CLUB));
                add(new Card(Rank.THREE, Suit.HEART));
                add(new Card(Rank.JACK, Suit.SPADE));
            }
        });

        game.deal(phill, new ArrayList<Card>(){
            {
                add(new Card(Rank.THREE, Suit.CLUB));
                add(new Card(Rank.SIX, Suit.HEART));
                add(new Card(Rank.JACK, Suit.HEART));
            }
        });

        GameResult result = game.play();

        Assert.assertThat(result.getWinner(), CoreMatchers.is(phill));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("Phill has won by high card: JACK - HEART"));
    }

    @Test
    public void ifBothPlayersHaveTheSameHighCardNextCardShouldBeCompared(){

        final Player john = new Player("John");
        final Player bob = new Player("Bob");

        Hand player1Hand = new Hand(5);
        player1Hand.addCard(new Card(Rank.TEN, Suit.CLUB));
        player1Hand.addCard(new Card(Rank.JACK, Suit.HEART));
        player1Hand.addCard(new Card(Rank.JACK, Suit.DIAMOND));
        player1Hand.addCard(new Card(Rank.KING, Suit.HEART));
        player1Hand.addCard(new Card(Rank.ACE, Suit.SPADE));
        john.setHand(player1Hand);

        Hand player2Hand = new Hand(5);
        player2Hand.addCard(new Card(Rank.NINE, Suit.CLUB));
        player2Hand.addCard(new Card(Rank.THREE, Suit.HEART));
        player2Hand.addCard(new Card(Rank.EIGHT, Suit.DIAMOND));
        player2Hand.addCard(new Card(Rank.KING, Suit.HEART));
        player2Hand.addCard(new Card(Rank.ACE, Suit.SPADE));
        bob.setHand(player2Hand);

        Game game = new Game(3, 5);
        game.addPlayer(john);
        game.addPlayer(bob);

        game.deal(john, new ArrayList<Card>(){
            {
                add(new Card(Rank.TEN, Suit.CLUB));
                add(new Card(Rank.JACK, Suit.HEART));
                add(new Card(Rank.JACK, Suit.DIAMOND));
                add(new Card(Rank.KING, Suit.HEART));
                add(new Card(Rank.ACE, Suit.SPADE));
            }
        });

        game.deal(bob, new ArrayList<Card>(){
            {
                add(new Card(Rank.NINE, Suit.CLUB));
                add(new Card(Rank.THREE, Suit.HEART));
                add(new Card(Rank.EIGHT, Suit.DIAMOND));
                add(new Card(Rank.KING, Suit.HEART));
                add(new Card(Rank.ACE, Suit.SPADE));
            }
        });

        GameResult result = game.play();

        Assert.assertThat(result.getWinner(), CoreMatchers.is(john));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("John has won by high card: JACK - HEART"));
    }

    @Test
    public void playerWithThreeOfAKindWinsEvenThoughHeHasNotGotAHighCard(){

        final Player john = new Player("John");
        final Player bob = new Player("Bob");

        Hand player1Hand = new Hand(3);
        player1Hand.addCard(new Card(Rank.JACK, Suit.CLUB));
        player1Hand.addCard(new Card(Rank.JACK, Suit.SPADE));
        player1Hand.addCard(new Card(Rank.JACK, Suit.DIAMOND));
        john.setHand(player1Hand);

        Hand player2Hand = new Hand(3);
        player2Hand.addCard(new Card(Rank.ACE, Suit.SPADE));
        player2Hand.addCard(new Card(Rank.KING, Suit.SPADE));
        player2Hand.addCard(new Card(Rank.THREE, Suit.DIAMOND));
        bob.setHand(player2Hand);

        Game game = new Game(3, 3);

        game.addPlayer(john);
        game.addPlayer(bob);

        game.deal(john, new ArrayList<Card>(){
            {
                add(new Card(Rank.JACK, Suit.CLUB));
                add(new Card(Rank.JACK, Suit.SPADE));
                add(new Card(Rank.JACK, Suit.DIAMOND));
            }
        });

        game.deal(bob, new ArrayList<Card>(){
            {
                add(new Card(Rank.ACE, Suit.SPADE));
                add(new Card(Rank.KING, Suit.SPADE));
                add(new Card(Rank.THREE, Suit.DIAMOND));
            }
        });

        GameResult result = game.play();
        Assert.assertThat(result.getWinner(), CoreMatchers.is(john));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("John has won by three of a kind: JACK"));
    }

    @Test
    public void playerWithHigherThreeOfAKindWins(){

        final Player john = new Player("John");
        final Player bob = new Player("Bob");

        Hand player1Hand = new Hand(3);
        player1Hand.addCard(new Card(Rank.JACK, Suit.CLUB));
        player1Hand.addCard(new Card(Rank.JACK, Suit.SPADE));
        player1Hand.addCard(new Card(Rank.JACK, Suit.DIAMOND));
        john.setHand(player1Hand);

        Hand player2Hand = new Hand(3);
        player2Hand.addCard(new Card(Rank.ACE, Suit.SPADE));
        player2Hand.addCard(new Card(Rank.ACE, Suit.CLUB));
        player2Hand.addCard(new Card(Rank.ACE, Suit.DIAMOND));
        bob.setHand(player2Hand);

        Game game = new Game(3, 3);

        game.addPlayer(john);
        game.addPlayer(bob);

        game.deal(john, new ArrayList<Card>(){
            {
                add(new Card(Rank.JACK, Suit.CLUB));
                add(new Card(Rank.JACK, Suit.SPADE));
                add(new Card(Rank.JACK, Suit.DIAMOND));
            }
        });

        game.deal(bob, new ArrayList<Card>(){
            {
                add(new Card(Rank.ACE, Suit.SPADE));
                add(new Card(Rank.ACE, Suit.CLUB));
                add(new Card(Rank.ACE, Suit.DIAMOND));
            }
        });


        GameResult result = game.play();
        Assert.assertThat(result.getWinner(), CoreMatchers.is(bob));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("Bob has won by three of a kind: ACE"));
    }

    @Test
    public void playerWithHighCardWinsIfBothPlayersHaveThreeOfAKindOfSameRank(){

        final Player john = new Player("John");
        final Player bob = new Player("Bob");

        Hand player1Hand = new Hand(5);
        player1Hand.addCard(new Card(Rank.ACE, Suit.SPADE));
        player1Hand.addCard(new Card(Rank.ACE, Suit.CLUB));
        player1Hand.addCard(new Card(Rank.ACE, Suit.DIAMOND));
        player1Hand.addCard(new Card(Rank.KING, Suit.SPADE));
        player1Hand.addCard(new Card(Rank.KING, Suit.CLUB));
        john.setHand(player1Hand);

        Hand player2Hand = new Hand(5);
        player2Hand.addCard(new Card(Rank.ACE, Suit.SPADE));
        player2Hand.addCard(new Card(Rank.ACE, Suit.CLUB));
        player2Hand.addCard(new Card(Rank.ACE, Suit.DIAMOND));
        player2Hand.addCard(new Card(Rank.QUEEN, Suit.CLUB));
        player2Hand.addCard(new Card(Rank.FOUR, Suit.SPADE));
        bob.setHand(player2Hand);

        Game game = new Game(3, 5);

        game.addPlayer(john);
        game.addPlayer(bob);

        game.deal(john, new ArrayList<Card>(){
            {
                add(new Card(Rank.ACE, Suit.SPADE));
                add(new Card(Rank.ACE, Suit.CLUB));
                add(new Card(Rank.ACE, Suit.DIAMOND));
                add(new Card(Rank.KING, Suit.SPADE));
                add(new Card(Rank.KING, Suit.CLUB));
            }
        });

        game.deal(bob, new ArrayList<Card>(){
            {
                add(new Card(Rank.ACE, Suit.SPADE));
                add(new Card(Rank.ACE, Suit.CLUB));
                add(new Card(Rank.ACE, Suit.DIAMOND));
                add(new Card(Rank.QUEEN, Suit.CLUB));
                add(new Card(Rank.FOUR, Suit.SPADE));
            }
        });


        GameResult result = game.play();
        Assert.assertThat(result.getWinner(), CoreMatchers.is(john));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("John has won by high card: KING - SPADE"));    }

    @Test
    public void gameIsDrawnIfNoneOfTheRuleCanDecideTheWinner(){

        final Player john = new Player("John");
        final Player bob = new Player("Bob");

        Hand player1Hand = new Hand(5);
        player1Hand.addCard(new Card(Rank.TEN, Suit.CLUB));
        player1Hand.addCard(new Card(Rank.TEN, Suit.HEART));
        player1Hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        player1Hand.addCard(new Card(Rank.JACK, Suit.SPADE));
        player1Hand.addCard(new Card(Rank.ACE, Suit.SPADE));
        john.setHand(player1Hand);

        Hand player2Hand = new Hand(5);
        player2Hand.addCard(new Card(Rank.TEN, Suit.CLUB));
        player2Hand.addCard(new Card(Rank.TEN, Suit.HEART));
        player2Hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        player2Hand.addCard(new Card(Rank.JACK, Suit.SPADE));
        player2Hand.addCard(new Card(Rank.ACE, Suit.SPADE));
        bob.setHand(player2Hand);

        Game game = new Game(3, 5);

        game.addPlayer(john);
        game.addPlayer(bob);

        game.deal(john, new ArrayList<Card>(){
            {
                add(new Card(Rank.TEN, Suit.CLUB));
                add(new Card(Rank.TEN, Suit.HEART));
                add(new Card(Rank.TEN, Suit.SPADE));
                add(new Card(Rank.JACK, Suit.SPADE));
                add(new Card(Rank.ACE, Suit.SPADE));
            }
        });

        game.deal(bob, new ArrayList<Card>(){
            {
                add(new Card(Rank.TEN, Suit.CLUB));
                add(new Card(Rank.TEN, Suit.HEART));
                add(new Card(Rank.TEN, Suit.SPADE));
                add(new Card(Rank.JACK, Suit.SPADE));
                add(new Card(Rank.ACE, Suit.SPADE));
            }
        });


        GameResult result = game.play();
        Assert.assertThat(result.getWinner(), CoreMatchers.is(Player.NONE));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("Match was drawn"));
    }
}
