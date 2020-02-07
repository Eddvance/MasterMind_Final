package Jeu;

public class GameCard {

    private Joueur player;

    private boolean gagne;
    private int score;

    /**
     * @param gagne
     * @param score
     * @param player
     */
    public GameCard(boolean gagne, int score, Joueur player) {
        this.gagne = gagne;
        this.score = score;
    }

    /**
     * @return
     */
    public boolean isGagne() {
        return gagne;
    }

    public void setGagne(boolean gagne) {
        this.gagne = gagne;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

