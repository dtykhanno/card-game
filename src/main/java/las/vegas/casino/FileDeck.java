package las.vegas.casino;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileDeck implements DeckReader {

    private final Path path;

    public FileDeck(String file) {
        path = Paths.get(file);
    }

    public FileDeck(Path path) {
        this.path = path;
    }

    @Override
    public Deck readDeck() throws IOException {
        // Check if file exists
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            throw new IOException("File does not exist or is not a regular file.");
        }

        // Read one line from the file
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            line = reader.readLine();
        }

        // Check if line is not empty
        if (StringUtils.isBlank(line)) {
            throw new IOException("File is empty.");
        }

        // Check if the list has 52 items
        String[] split = line.split(",");
        if (split.length != Card.SIZE) {
            throw new IOException("File does not contain 52 items.");
        }

        // Check for right format and duplications
        List<Card> cards = new ArrayList<>(Card.SIZE);
        Set<Card> uniqueCards = new HashSet<>();
        for (String code : split) {
            try {
                Card card = Card.valueOf(code.toUpperCase());
                if (!uniqueCards.add(card)) {
                    throw new IOException("Duplicate card found: " + card.name());
                }
                cards.add(card);
            } catch (IllegalArgumentException ex) {
                throw new IOException("Illegal card code: " + code);
            }
        }

        return Deck.of(cards);
    }
}
