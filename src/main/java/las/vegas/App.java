package las.vegas;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import las.vegas.service.Deck;
import las.vegas.service.DeckReader;
import las.vegas.service.FileDeck;
import las.vegas.service.Player;
import las.vegas.service.RandomDeck;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class App {
    @Parameter(
            names = "--file",
            description = "File name"
    )
    private String file;

    @Parameter(names = "--help", help = true)
    private boolean help;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        App app = new App();
        JCommander js = JCommander.newBuilder()
                .addObject(app)
                .build();

        js.parse(args);

        if (app.help) {
            js.usage();
        } else {
            app.start();
        }
    }

    private void start() {
        DeckReader fileDeck;
        if (StringUtils.isNotBlank(file)) {
            fileDeck = new FileDeck(file);
        }else {
            fileDeck = new RandomDeck();
        }
        Deck deck = fileDeck.readDeck();

        List<Player> players = List.of(Player.of("Sam", 1), Player.of("Dealer", 2));

        for (int i =0; i < 2; i++) {
            for (Player player : players) {
                player.playCard(deck.popCard());
            }
        }

        for (Player player : players) {
            player.playCard(deck.popCard());
        }

    }
}
