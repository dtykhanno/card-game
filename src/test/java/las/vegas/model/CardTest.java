package las.vegas.model;

import las.vegas.casino.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

    @DisplayName("Verify Card enumeration")
    @Test
    void verifyCardEnumeration() {
        assertEquals(Card.SIZE, Card.values().length);
        assertEquals(Card.C2, Card.valueOf("C2"));
        assertEquals(10, Card.valueOf("C10").getRank());
        assertEquals(10, Card.valueOf("CJ").getRank());
        assertEquals(10, Card.valueOf("CD").getRank());
        assertEquals(10, Card.valueOf("CK").getRank());
        assertEquals(11, Card.valueOf("CA").getRank());
    }
}