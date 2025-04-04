package las.vegas.service;

public class Dealer extends Player {
    public Dealer(String name) {
        super(name);
    }

    public void setPlayerState(PlayerState state) {
        playerState = state;
    }

    public static Dealer of(String name) {
        return new Dealer(name);
    }

    @Override
    protected PlayerState applyRules(int score) {
        return null;
    }
}
