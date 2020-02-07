package Jeu;

import java.util.ArrayList;
import java.util.List;

class Defenseur extends Game implements Jouable {

    List<String> derniereReponse;
    List<Integer> derniereProposition;


    public Defenseur(int combinaisonSize, int nbEssai, Joueur player) {
        super(combinaisonSize, nbEssai, player);
    }

    @Override
    public void prepareJeu() {
        combinaisonSecrete = saisieCombinaison();
    }

    @Override
    public GameCard joue() {
        GameCard gc = new GameCard(false, 1, player);

        while ((gc.getScore() <= nbEssai) && !gc.isGagne()) {
            gc = joueUnTour(gc);
        }
        return gc;
    }

    /**
     *
     * @param gc
     * @return
     */
    public GameCard joueUnTour(GameCard gc) {

        System.out.println("Le systeme joue");
        List<Integer> proposition = generateProposition(derniereReponse, derniereProposition);
        System.out.println("sa proposition :" + proposition);
        derniereProposition = proposition;
        derniereReponse = getReponse(proposition);
        System.out.println("validation :" + derniereReponse);
        gc.setGagne(isRight(derniereReponse));
        gc.setScore(gc.getScore() + 1);

        return gc;
    }

    /**
     * Genere une proposition random
     * @param derniereReponse La derniere reponse
     * @param derniereProposition La derniere proposition
     * @return la proposition complete
     */
    private List<Integer> generateProposition(List<String> derniereReponse, List<Integer> derniereProposition) {

        List<Integer> proposition = new ArrayList<>();
        if (derniereReponse == null || derniereProposition.isEmpty()) {
            for (int i = 0; i < combinaisonSecrete.size(); i++) {
                proposition.add(getRandomNumberInRange(0, 9));
            }
        } else {
            // j'ai une reponse il faut l'utilser
            int i = 0;
            for (Integer propositionValeur : derniereProposition) {
                String repValue = derniereReponse.get(i);
                i++;
                if ("=".equals(repValue)) {
                    proposition.add(propositionValeur);
                } else if ("+".equals(repValue)) {
                    // si c'est + la bonne reponse est entre 0 et ma derniere propo - 1
                    proposition.add(getRandomNumberInRange(0, propositionValeur - 1));
                } else if ("-".equals(repValue)) {
                    // si c'est - la bonne reponse est entre ma derniere propo + 1 et 9
                    proposition.add(getRandomNumberInRange(propositionValeur + 1, 9));
                }
            }
        }
        return proposition;
    }
}