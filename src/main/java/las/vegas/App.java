package las.vegas;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import las.vegas.casino.Deck;
import las.vegas.casino.DeckReader;
import las.vegas.casino.FileDeck;
import las.vegas.casino.RandomDeck;
import las.vegas.casino.game.BlackJack;
import las.vegas.casino.game.GameResult;
import las.vegas.mortal.Client;
import las.vegas.mortal.Dealer;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class App {

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

        GameResult result = BlackJack.of().play(Client.of(SAM), Dealer.of(DEALER), deck);

        System.out.println(result);
    }

}
