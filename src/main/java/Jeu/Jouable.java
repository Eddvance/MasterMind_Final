package Jeu;

public interface Jouable {

    default void start() {
        prepareJeu();
        GameCard gc = joue();
        resumePartie(gc);
    }
    void prepareJeu();

    GameCard joue();

    /**
     *
     * @param gc
     */
    default void resumePartie(GameCard gc) {
        //String resultat = "perdu";
        int tentative = gc.getScore()-1;

        if (gc.isGagne()) {
            System.out.println("Dommage !! Votre code a été trouvé en " +tentative+ " tentative(s)");
            System.out.println("L'ordinateur a été meilleur que vous !");
        }
        else if (!gc.isGagne())
            //resultat = "gagne";
        System.out.println("L'ordinateur a perdu en " + tentative + " tentative(s) \nFélicitation !! Votre code n'a pas été dechiffré !");
        //System.out.println("Félicitation !! Votre code n'a pas été dechiffré !"); dans ce cas cette phrase est affichee dans les 2 cas de figures Pourquoi ??
    }
}

