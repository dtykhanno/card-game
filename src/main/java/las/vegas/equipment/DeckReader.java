package las.vegas.equipment;

import java.io.IOException;

public interface DeckReader {
    Deck readDeck() throws IOException;
}
