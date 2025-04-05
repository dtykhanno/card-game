package las.vegas;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import las.vegas.mortal.Client;
import las.vegas.mortal.Dealer;
import las.vegas.equipment.Deck;
import las.vegas.equipment.DeckReader;
import las.vegas.equipment.FileDeck;
import las.vegas.mortal.Player;
import las.vegas.equipment.PlayerState;
import las.vegas.equipment.RandomDeck;

import java.io.IOException;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class App {
    private static final int MAX_CARDS = 2;
    public static final String SAM = "Sam";
    public static final String DEALER = "Dealer";
    private static final int ERROR = 1;

    @Parameter(
            names = "--file",
            description = "File name"
    )
    private String file;

    @Parameter(names = "--help", help = true)
    private boolean help;

    public static void main(String[] args) {
        App app = new App();
        JCommander js = JCommander.newBuilder()
                .addObject(app)
                .build();
        js.parse(args);
        if (app.help) {
            js.usage();
        } else {
            try {
                app.start();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                System.exit(ERROR);
            }
        }
    }


    private void start() throws IOException {
        DeckReader fileDeck = isNotBlank(file) ? new FileDeck(file) : new RandomDeck();

        Deck deck = fileDeck.readDeck();

        Client client = Client.of(SAM);
        Dealer dealer = Dealer.of(DEALER);
        List<Player> players = List.of(client, dealer);

        for (int i = 0; i < MAX_CARDS; i++) {
            for (Player player : players) {
                player.playCard(deck.popCard());
            }
        }

        if (client.getPlayerState() == PlayerState.WON) {
            printResults(players);
            return;
        }

        if (client.getPlayerState() == PlayerState.LOST && dealer.getPlayerState() == PlayerState.LOST) {
            dealer.setPlayerState(PlayerState.WON);
            printResults(players);
            return;
        }

        while (client.getPlayerState() == PlayerState.DRAW && deck.hasNextCard()) {
            client.playCard(deck.popCard());
        }

        while (dealer.getPlayerState() == PlayerState.DRAW && deck.hasNextCard()) {
            dealer.playCard(deck.popCard());
        }

        printResults(players);
    }

    private void printResults(List<Player> players) {
        players.forEach(System.out::println);
    }
}
