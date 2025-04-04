package las.vegas.casino.game;

import las.vegas.casino.Deck;
import las.vegas.mortal.Client;
import las.vegas.mortal.Dealer;
import las.vegas.mortal.Player;
import las.vegas.mortal.PlayerState;

import java.util.List;

public class BlackJack implements OneToOne {

    private static final int MAX_CARDS = 2;

    public static BlackJack of() {
        return new BlackJack();
    }

    @Override
    public GameResult play(Client client, Dealer dealer, Deck deck) {
        List<Player> players = List.of(client, dealer);

        // initial hand
        for (int i = 0; i < MAX_CARDS; i++) {
            for (Player player : players) {
                player.playCard(deck.popCard());
            }
        }

        // checks after 'initial hand'
        GameResult.Builder rBuilder = GameResult.Builder.newBuilder();
        // client always wins
        if (client.getPlayerState() == PlayerState.WON) {
            printResults(players);
            return rBuilder.withWinner(client).withLoser(dealer).build();
        }

        // dealer wins, if both lose after 'initial hand'
        if (client.getPlayerState() == PlayerState.LOST && (dealer.getPlayerState() == PlayerState.LOST || dealer.getPlayerState() == PlayerState.WON)) {
            dealer.setPlayerState(PlayerState.WON);
            printResults(players);
            return rBuilder.withWinner(dealer).withLoser(client).build();
        }

        // client wants more
        while (client.getPlayerState() == PlayerState.DRAW && deck.hasNextCard()) {
            client.playCard(deck.popCard());
        }

        // Set the client's final score and re-check the dealer's state.
        // It's possible the dealer has already won.
        dealer.setClientScore(client.getScore());

        // dealer takes cards
        while (dealer.getPlayerState() == PlayerState.DRAW && deck.hasNextCard()) {
            dealer.playCard(deck.popCard());
        }

        // determine who has won
        if (dealer.getPlayerState() == PlayerState.WON) {
            return rBuilder.withWinner(dealer).withLoser(client).build();
        }
        return rBuilder.withWinner(client).withLoser(dealer).build();
    }

    private void printResults(List<Player> players) {
        players.forEach(p -> System.out.println(p.stateDetails()));
    }
}
