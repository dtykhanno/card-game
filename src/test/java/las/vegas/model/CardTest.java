package las.vegas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

    @DisplayName("Verify valueOf()")
    @Test
    void valueOf() {
        assertEquals(56, Card.values().length);
        assertEquals(Card.C1, Card.valueOf("C1"));
        assertEquals(10, Card.valueOf("C10").getRank());
        assertEquals(10, Card.valueOf("CJ").getRank());
        assertEquals(10, Card.valueOf("CD").getRank());
        assertEquals(10, Card.valueOf("CK").getRank());
        assertEquals(11, Card.valueOf("CA").getRank());
    }
}