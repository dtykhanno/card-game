package las.vegas.mortal;

public class Client extends Player {
    private Client(String name) {
        super(name);
    }

    public static Client of(String name) {
        return new Client(name);
    }

    @Override
    protected PlayerState applyRules(int score) {
        if (score > 21) {
            return PlayerState.LOST;
        }
        if (score == 21) {
            return PlayerState.WON;
        }
        if (score < 17) {
            return PlayerState.DRAW;
        }
        return PlayerState.STAND;
    }
}
