package las.vegas.service;

import las.vegas.casino.FileDeck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class FileDeckTest {

    @Test
    @DisplayName("File is directory")
    void fileIsDirectory() {
        IOException ioException = assertThrowsExactly(IOException.class, () -> new FileDeck("").readDeck());
        assertEquals("File does not exist or is not a regular file.", ioException.getMessage());
    }

    @Test
    @DisplayName("File not exists")
    void fileNotExists() {
        IOException ioException = assertThrowsExactly(IOException.class, () -> new FileDeck("not-exists.txt").readDeck());
        assertEquals("File does not exist or is not a regular file.", ioException.getMessage());
    }

    @Test
    @DisplayName("Empty file")
    void emptyFile() {
        IOException ioException = assertThrowsExactly(IOException.class, () -> new FileDeck(toPath("./decks/empty.txt")).readDeck());
        assertEquals("File is empty.", ioException.getMessage());
    }

    @Test
    @DisplayName("File does not contain 52 items")
    void wrongCardsNumber() {
        IOException ioException = assertThrowsExactly(IOException.class, () -> new FileDeck(toPath("./decks/not-52.txt")).readDeck());
        assertEquals("File does not contain 52 items.", ioException.getMessage());
    }

    @Test
    @DisplayName("Wrong card form")
    void wrongFormat() {
        IOException ioException = assertThrowsExactly(IOException.class, () -> new FileDeck(toPath("./decks/wrong-format.txt")).readDeck());
        assertEquals("Illegal card code: D5D7", ioException.getMessage());
    }

    @Test
    @DisplayName("Duplicates")
    void duplicated() {
        IOException ioException = assertThrowsExactly(IOException.class, () -> new FileDeck(toPath("./decks/duplicates.txt")).readDeck());
        assertEquals("Duplicate card found: D5", ioException.getMessage());
    }

    @Test
    @DisplayName("Valid deck")
    void valid() {
        assertDoesNotThrow(()-> new FileDeck(toPath("./decks/valid.txt")));
    }
    @Test
    @DisplayName("Valid deck in low case")
    void validLowCase() {
        assertDoesNotThrow(()-> new FileDeck(toPath("./decks/valid-low-case.txt")));
    }


    private Path toPath(String fileName) throws URISyntaxException {
        URL resource = this.getClass().getResource(fileName);
        if (resource != null){
            return  Path.of(resource.toURI());
        }
        throw new IllegalArgumentException("Unknown resource");
    }
}