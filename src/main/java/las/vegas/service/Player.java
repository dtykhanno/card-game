package las.vegas.service;


import las.vegas.model.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final int order;
    private PlayerState playerState;
    private final List<Card> cards = new ArrayList<>();
    private int score;

    public Player(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public static Player of(String name, int order) {
        return new Player(name, order);
    }

    public PlayerState playCard(Card card) {
        cards.add(card);
        score += card.getRank();

        if (score == 21) {
            playerState = PlayerState.WON;
        }
        if (score > 21 ) {
            playerState = PlayerState.LOST;
        }
        if (score < 17) {
            playerState = PlayerState.DRAW;
        }
        if (score >= 17 && score < 21) {
            playerState = PlayerState.STAND;
        }
        return playerState;
    }
}
