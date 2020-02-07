package Jeu;

public interface Jouable {

    default void start(){
        prepareJeu();
        GameCard gc = joue();
        resumePartie(gc);
    }
    void prepareJeu();

    GameCard joue();

    default void resumePartie(GameCard gc) {
        String resultat = "perdu";
        if (gc.isGagne()) {
            resultat = "gagne";
        }
        int tentative = gc.getScore()-1;
        System.out.println("Vous avez " + resultat + " en " + tentative + " tentatives");
    }
}

