package com.cardgame;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by maverick on 5/12/15.
 */
public class GameTest {
    @Test
    public void thePlayerWithGreaterRankHighCardWins(){

        Player john = new Player("John");
        Player bob = new Player("Bob");


        Hand player1Hand = new Hand(3);
        player1Hand.addCard(new Card(Rank.TEN, Suit.CLUB));
        player1Hand.addCard(new Card(Rank.JACK, Suit.HEART));
        player1Hand.addCard(new Card(Rank.JACK, Suit.DIAMOND));
        john.setHand(player1Hand);

        Hand player2Hand = new Hand(3);
        player2Hand.addCard(new Card(Rank.NINE, Suit.CLUB));
        player2Hand.addCard(new Card(Rank.THREE, Suit.HEART));
        player2Hand.addCard(new Card(Rank.EIGHT, Suit.DIAMOND));
        bob.setHand(player2Hand);

        Game game = new Game(john, bob);
        
        GameResult result = game.play();
        Assert.assertThat(result.getWinner(), CoreMatchers.is(john));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("John has won by high card: JACK - DIAMOND"));
    }

    @Test
    public void thePlayerWithGreaterSuitWinsIfBothOfThemHaveSameRankHighCardAsHighestRankCard(){

        Player john = new Player("John");
        Player bob = new Player("Bob");


        Hand player1Hand = new Hand(3);
        player1Hand.addCard(new Card(Rank.TEN, Suit.CLUB));
        player1Hand.addCard(new Card(Rank.THREE, Suit.HEART));
        player1Hand.addCard(new Card(Rank.JACK, Suit.DIAMOND));
        john.setHand(player1Hand);

        Hand player2Hand = new Hand(3);
        player2Hand.addCard(new Card(Rank.NINE, Suit.CLUB));
        player2Hand.addCard(new Card(Rank.THREE, Suit.HEART));
        player2Hand.addCard(new Card(Rank.JACK, Suit.SPADE));
        bob.setHand(player2Hand);

        Game game = new Game(john, bob);

        GameResult result = game.play();
        Assert.assertThat(result.getWinner(), CoreMatchers.is(bob));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("Bob has won by high card: JACK - SPADE"));
    }

    @Test
    public void ifBothPlayersHaveTheSameHighCardNextCardShouldBeCompared(){

        Player john = new Player("John");
        Player bob = new Player("Bob");


        Hand player1Hand = new Hand(3);
        player1Hand.addCard(new Card(Rank.TEN, Suit.CLUB));
        player1Hand.addCard(new Card(Rank.THREE, Suit.HEART));
        player1Hand.addCard(new Card(Rank.JACK, Suit.SPADE));
        john.setHand(player1Hand);

        Hand player2Hand = new Hand(3);
        player2Hand.addCard(new Card(Rank.NINE, Suit.CLUB));
        player2Hand.addCard(new Card(Rank.THREE, Suit.HEART));
        player2Hand.addCard(new Card(Rank.JACK, Suit.SPADE));
        bob.setHand(player2Hand);

        Game game = new Game(john, bob);

        GameResult result = game.play();
        Assert.assertThat(result.getWinner(), CoreMatchers.is(john));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("John has won by high card: TEN - CLUB"));
    }

    @Test
    public void playerWithThreeOfAKindWinsEvenThoughHeHasNotGotAHighCard(){

        Player john = new Player("John");
        Player bob = new Player("Bob");


        Hand player1Hand = new Hand(3);
        player1Hand.addCard(new Card(Rank.TEN, Suit.CLUB));
        player1Hand.addCard(new Card(Rank.TEN, Suit.HEART));
        player1Hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        john.setHand(player1Hand);

        Hand player2Hand = new Hand(3);
        player2Hand.addCard(new Card(Rank.NINE, Suit.CLUB));
        player2Hand.addCard(new Card(Rank.THREE, Suit.HEART));
        player2Hand.addCard(new Card(Rank.ACE, Suit.SPADE));
        bob.setHand(player2Hand);

        Game game = new Game(john, bob);

        GameResult result = game.play();
        Assert.assertThat(result.getWinner(), CoreMatchers.is(john));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("John has won by three of a kind: TEN"));
    }

    @Test
    public void playerWithHigherThreeOfAKindWins(){

        Player john = new Player("John");
        Player bob = new Player("Bob");


        Hand player1Hand = new Hand(3);
        player1Hand.addCard(new Card(Rank.TEN, Suit.CLUB));
        player1Hand.addCard(new Card(Rank.TEN, Suit.HEART));
        player1Hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        john.setHand(player1Hand);

        Hand player2Hand = new Hand(3);
        player2Hand.addCard(new Card(Rank.QUEEN, Suit.CLUB));
        player2Hand.addCard(new Card(Rank.QUEEN, Suit.HEART));
        player2Hand.addCard(new Card(Rank.QUEEN, Suit.SPADE));
        bob.setHand(player2Hand);

        Game game = new Game(john, bob);

        GameResult result = game.play();
        Assert.assertThat(result.getWinner(), CoreMatchers.is(bob));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("Bob has won by three of a kind: QUEEN"));
    }

    @Test
    public void playerWithHighCardWinsIfBothPlayersHaveThreeOfAKindOfSameRank(){

        Player john = new Player("John");
        Player jack = new Player("Bob");


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
        player2Hand.addCard(new Card(Rank.QUEEN, Suit.SPADE));
        jack.setHand(player2Hand);

        Game game = new Game(john, jack);

        GameResult result = game.play();
        Assert.assertThat(result.getWinner(), CoreMatchers.is(john));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("John has won by high card: ACE - SPADE"));    }

    @Test
    public void gameIsDrawnIfNoneOfTheRuleCanDecideTheWinner(){

        Player john = new Player("John");
        Player bob = new Player("Bob");


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

        Game game = new Game(john, bob);

        GameResult result = game.play();
        Assert.assertThat(result.getWinner(), CoreMatchers.is(Player.NONE));
        Assert.assertThat(result.getResultMessage(), CoreMatchers.is("Match was drawn"));
    }
}
