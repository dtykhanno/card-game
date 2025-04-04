package las.vegas.casino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomDeck implements DeckReader {
    @Override
    public Deck readDeck() {
        List<Card> cards = Arrays.stream(Card.values()).collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(cards);

        return Deck.of(cards);
    }
}
