package las.vegas.casino.game;

import las.vegas.mortal.Player;

public class GameResult {
    Player winner;
    Player loser;

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }

    @Override
    public String toString() {
        return winner.getName() + "\n" +
                winner + "\n" +
                loser;
    }

    private GameResult(Builder builder) {
        winner = builder.winner;
        loser = builder.loser;
    }

    public static final class Builder {
        private Player winner;
        private Player loser;

        public Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder withWinner(Player val) {
            winner = val;
            return this;
        }

        public Builder withLoser(Player val) {
            loser = val;
            return this;
        }

        public GameResult build() {
            return new GameResult(this);
        }
    }
}
