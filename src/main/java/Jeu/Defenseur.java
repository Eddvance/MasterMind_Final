package Jeu;

import java.util.ArrayList;
import java.util.List;

class Defenseur extends Game implements Jouable {

    List<String> derniereReponse;
    List<Integer> derniereProposition;


    public Defenseur(int combinaisonSize, int nbEssai, Joueur player) {
        super(combinaisonSize, nbEssai, player);
        //List<String> derniereReponse = new ArrayList<>();
       // List<Integer> derniereProposition = new ArrayList<>();
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

    public GameCard joueUnTour(GameCard gc) {//////

        System.out.println("Le systeme joue");
        List<Integer> propo = generateProposition(derniereReponse, derniereProposition);
        System.out.println("sa proposition :" + propo);
        derniereProposition = propo;
        derniereReponse = getReponse(propo);
        System.out.println("validation :" + derniereReponse);
        gc.setGagne(isRight(derniereReponse));
        gc.setScore(gc.getScore() + 1);

        return gc;
    }

    private List<Integer> generateProposition(List<String> derniereReponse, List<Integer> derniereProposition) {
        List<Integer> propo = new ArrayList<>();
        if (derniereReponse == null || derniereProposition.isEmpty()) {
            // c'est la premiere tentative
            // all random : easy
            for (int i = 0; i < combinaisonSecrete.size(); i++) {
                propo.add(getRandomNumberInRange(0, 9));
            }
        } else {
            // j'ai une reponse il faut l'utilser
            int i = 0;
            for (Integer propoValue : derniereProposition) {
                String repValue = derniereReponse.get(i);
                i++;
                if ("=".equals(repValue)) {
                    propo.add(propoValue);
                } else if ("+".equals(repValue)) {
                    // si c'est + la bonne reponse est entre 0 et ma derniere propo - 1
                    propo.add(getRandomNumberInRange(0, propoValue - 1));
                } else if ("-".equals(repValue)) {
                    // si c'est - la bonne reponse est entre ma derniere propo + 1 et 9
                    propo.add(getRandomNumberInRange(propoValue + 1, 9));
                }
            }
        }
        return propo;
    }
}

