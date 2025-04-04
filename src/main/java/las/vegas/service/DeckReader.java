package las.vegas.service;

import java.io.IOException;

public interface DeckReader {
    Deck readDeck() throws IOException;
}
