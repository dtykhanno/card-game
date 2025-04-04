package las.vegas.service;

import java.nio.file.Path;

public class FileDeck implements DeckReader {

    private final String file;

    public FileDeck(String file) {
        this.file = file;
    }

    @Override
    public Deck readDeck() {
        return readDeck(file);
    }

    public Deck readDeck(String file) {
        Path path = Path.of(file);
        return null;
    }
}
