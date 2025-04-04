package las.vegas.casino.game;

import las.vegas.casino.Card;
import las.vegas.casino.Deck;
import las.vegas.mortal.Client;
import las.vegas.mortal.Dealer;
import las.vegas.mortal.Player;
import las.vegas.mortal.PlayerState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.of;
import static las.vegas.casino.Card.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BlackJackTest {

    public static final String NAME_TEST = "Test";
    public static final String NAME_DEALER = "Dealer";

    @Test
    @DisplayName("initial hand: C wins + D loses: C wins")
    void initialHandClientWins() {
        GameResult result = runGame(of(CA, Card.CK),
                of(Card.C2, Card.CD));


        Player winner = result.getWinner();
        assertEquals(NAME_TEST, winner.getName());
        assertEquals(PlayerState.WON, winner.getPlayerState());
    }

    @Test
    @DisplayName("initial hand: C loses + D loses: D wins")
    void initialHandBothMore22AndDealerWins() {
        GameResult result = runGame(of(CA, HA),
                of(DA, SA));

        Player winner = result.getWinner();
        assertEquals(NAME_DEALER, winner.getName());
        assertEquals(PlayerState.WON, winner.getPlayerState());
    }



    @Test
    @DisplayName("initial hand: C loses + D wins: D wins")
    void initialHandDealerWins() {
        GameResult result = runGame(
                of(CA, HA),
                of(DA, SD));

        Player winner = result.getWinner();
        assertEquals(NAME_DEALER, winner.getName());
        assertEquals(PlayerState.WON, winner.getPlayerState());
    }

    @Test
    @DisplayName("client 21 and dealer has 15")
    void client21Dealer17() {
        GameResult result = runGame(of(HD, H5, H6),
                of(HD, H7));

        Player winner = result.getWinner();
        assertEquals(NAME_TEST, winner.getName());
        assertEquals(PlayerState.WON, winner.getPlayerState());
    }

    @Test
    @DisplayName("client has less than dealer")
    void client19Dealer20() {
        GameResult result = runGame(of(C9, H2, D8),
                of(HD, CD));

        Player winner = result.getWinner();
        assertEquals(NAME_DEALER, winner.getName());
        assertEquals(PlayerState.WON, winner.getPlayerState());
    }

    @Test
    @DisplayName("client has 22")
    void client22Dealer20() {
        GameResult result = runGame(of(DD, H2, DK),
                of(HD, CD));

        Player winner = result.getWinner();
        assertEquals(NAME_DEALER, winner.getName());
        assertEquals(PlayerState.WON, winner.getPlayerState());
    }

    private GameResult runGame(List<Card> clientHand, List<Card> dealerHand) {
        GameResult result = BlackJack.of().play(createClient(), createDealer(),
                handsAsDeck(
                        clientHand,
                        dealerHand)
        );
        System.out.println(result);
        return result;
    }

    private Client createClient() {
        return Client.of(NAME_TEST);
    }

    private Dealer createDealer() {
        return Dealer.of(NAME_DEALER);
    }

    private Deck handsAsDeck(List<Card> clientHand, List<Card> dealerHand) {
        List<Card> mergedHand = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            if (i < clientHand.size()) {
                mergedHand.add(clientHand.get(i));
            }
            if (i < dealerHand.size()) {
                mergedHand.add(dealerHand.get(i));
            }
        }
        mergedHand.addAll(clientHand.subList(2, clientHand.size()));
        mergedHand.addAll(dealerHand.subList(2, dealerHand.size()));
        return new Deck(mergedHand);
    }
}