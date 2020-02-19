package Jeu;

import java.util.ArrayList;
import java.util.List;

public class Challenger extends Game implements Jouable {

    public Challenger(int combinaisonSize, int nbEssai, Joueur player) {
        super(combinaisonSize, nbEssai, player);
    }

        @Override
        public void prepareJeu () {
            combinaisonSecrete = new ArrayList<>();
            for (int i = 0; i < combinaisonSize; i++) {
                combinaisonSecrete.add(getRandomNumberInRange(0, 9));
            }
            if ("dev".equals(Mastermind.mode)) {
                System.out.println(combinaisonSecrete);
            }
        }

        @Override
        public GameCard joue () {
            GameCard gc = new GameCard(false, 1, player);
            while ((gc.getScore() <= nbEssai) && !gc.isGagne()) {
                gc = joueUnTour(gc);
            }
            return gc;
        }

    /**
     * Differente actions de jouer un tour
     * @param gc GameCard
     * @return GameCard
     */
    public GameCard joueUnTour (GameCard gc ){

            List<Integer> propo = saisieCombinaison();
            List<String> derniereReponse = getReponse(propo);
            System.out.println("validation :" + derniereReponse);
            gc.setGagne(isRight(derniereReponse));
            gc.setScore(gc.getScore() + 1);

            return gc;
    }
}