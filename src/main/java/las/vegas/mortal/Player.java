package las.vegas.mortal;


import las.vegas.equipment.Card;
import las.vegas.equipment.PlayerState;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private final String name;
    protected PlayerState playerState = PlayerState.DRAW;
    private final List<Card> cards = new ArrayList<>();
    private int score;

    public Player(String name) {
        this.name = name;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public PlayerState playCard(Card card) {
        cards.add(card);
        score += card.getRank();

        playerState = applyRules(score);
        return playerState;
    }

    abstract protected PlayerState applyRules(int score);


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", playerState=" + playerState +
                ", cards=" + cards +
                ", score=" + score +
                '}';
    }
}
