package Jeu;

import java.io.IOException;

public interface Jouable {

    default void start() throws IOException {
        prepareJeu();
        GameCard gc = joue();
        resumePartie(gc);
    }

    void prepareJeu();

    GameCard joue();

    default void resumePartie(GameCard gc) throws IOException {
      int tentative = gc.getScore()-1;

      if (gc.isGagne()) {
          System.out.println("Le code a été trouvé en " +tentative+ " tentative(s)");
          System.out.println("");
        }

    else if (!gc.isGagne()) {
        System.out.println("Perdu en " + tentative + " tentative(s) ! ");
        System.out.println("");
    }
        System.out.println("Partie terminee");
        System.out.println("");
        System.out.println("Vous allez rejouer");
        System.out.println("------------------------------");
        Mastermind.main(new String[] {});
    }
}
