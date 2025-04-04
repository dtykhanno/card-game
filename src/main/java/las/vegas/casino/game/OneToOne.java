package las.vegas.casino.game;

import las.vegas.casino.Deck;
import las.vegas.mortal.Client;
import las.vegas.mortal.Dealer;

public interface OneToOne {

    GameResult play(Client client, Dealer dealer, Deck deck);
}
