package las.vegas.casino;

import java.io.IOException;

public interface DeckReader {
    Deck readDeck() throws IOException;
}
