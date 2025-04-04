package las.vegas.casino;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Stack;

public class Deck {
    private final Stack<Card> cards;

    public Deck(@NotNull List<Card> cards) {
        this.cards = new Stack<>();
        cards.reversed().forEach(this.cards::push);
    }

    public static Deck of(@NotNull List<Card> cards) {
        if (cards.isEmpty() || cards.size() != Card.values().length) {
            throw new IllegalArgumentException("The deck must contain exactly " + Card.values().length + " cards");
        }
        return new Deck(cards);
    }

    public Card popCard() {
        return cards.pop();
    }

    public boolean hasNextCard() {
        return !cards.isEmpty();
    }

}
