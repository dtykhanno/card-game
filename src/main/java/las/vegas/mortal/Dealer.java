package las.vegas.mortal;

public class Dealer extends Player {
    private int clientScore;

    public Dealer(String name) {
        super(name);
    }

    public void setPlayerState(PlayerState state) {
        playerState = state;
    }

    public void setClientScore(int clientScore) {
        this.clientScore = clientScore;
        setPlayerState(applyRules(getScore()));
    }

    public static Dealer of(String name) {
        return new Dealer(name);
    }

    @Override
    protected PlayerState applyRules(int score) {
        if (score > 21 || clientScore == 21) {
            return PlayerState.LOST;
        }
        if (clientScore < score || clientScore > 21) {
            return PlayerState.WON;
        }

        return PlayerState.DRAW;
    }
}
