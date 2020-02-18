package Jeu;

public class GameCard {

    private Joueur player;

    private boolean gagne;
    private int score;

    public GameCard(boolean gagne, int score, Joueur player) {
        this.gagne = gagne;
        this.score = score;
    }

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