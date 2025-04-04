package las.vegas.mortal;


import las.vegas.casino.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private final String name;
    protected PlayerState playerState = PlayerState.DRAW;
    private final List<Card> hand = new ArrayList<>();
    private int score;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void playCard(Card card) {
        hand.add(card);
        score += card.getRank();

        playerState = applyRules(score);
    }

    abstract protected PlayerState applyRules(int score);


    public String stateDetails() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hand=" + hand +
                ", playerState=" + playerState +
                ", score=" + score +
                '}';
    }

    @Override
    public String toString() {
        return name + ':' + hand + ' ' + score;
    }
}
