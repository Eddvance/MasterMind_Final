package Jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Game {

    protected int combinaisonSize;
    protected int nbEssai;
    protected Joueur player;
    protected List<Integer> combinaisonSecrete;

    public Game(int combinaisonSize, int nbEssai, Joueur player) {
        this.combinaisonSize = combinaisonSize;
        this.nbEssai = nbEssai;
        this.player = player;
    }

    final static Logger log = LogManager.getLogger(Game.class.getName());

    /**
     * Definition et regles de saisie correcte de combinaison
     * @return combi
     */
    protected List<Integer> saisieCombinaison() {
        boolean okSize = false;
        boolean okInt = false;
        List<Integer> combi = new ArrayList<>();
        String saisie = null;

        while (!okSize || !okInt) {
            System.out.println("veuillez choisir votre combinaison  : ");
            System.out.println("Rappel taille combinaison : " + combinaisonSize);
            saisie = Mastermind.enter.nextLine();

            int saisieToInt = 0;
            log.info(saisie.length());
            if (saisie.length() != combinaisonSize) {
                okSize = false;
                System.out.println("Erreur de saisie : il faut " + combinaisonSize + " chiffres");
                System.out.println("Ex : 2578");
            }
            else{
                okSize = true;
            }
            try {
                saisieToInt = Integer.parseInt(saisie);
                okInt = true;
            } catch (NumberFormatException nbe) {
                okInt = false;
            }
        }

        String[] tabChar = saisie.split("");
        for (String caractere : tabChar) {
            combi.add(Integer.parseInt(caractere));
        }
        log.info("combi :" + combi);
        return combi;
    }

    /**
     * Comparaison de la porposition a la combinaison secrete
     * @param proposition La propostion du joueur
     * @return reponse a la proposition
     */
    protected List<String> getReponse(List<Integer> proposition) {
        List<String> reponse = new ArrayList<>();

        for (int i = 0; i < combinaisonSecrete.size(); i++) {
            Integer secretValue = combinaisonSecrete.get(i);
            Integer propoValue = proposition.get(i);
            if (secretValue.compareTo(propoValue) > 0) {
                reponse.add("-");
            }
            if (secretValue.compareTo(propoValue) < 0) {
                reponse.add("+");
            }
            if (secretValue.compareTo(propoValue) == 0) {
                reponse.add("=");
            }
        }
        return reponse;
    }

    /**
     * Indication de bonne ou de mauvaise reponse
     * @param responses Un tableau de reponse
     * @return isRight Bonne reponse ou non
     */
    protected boolean isRight(List<String> responses) {
        boolean isRight = true;
        for (String rep : responses) {
            if (!"=".equals(rep)) {
                isRight = false;
                break;
            }
        }
        return isRight;
    }

    /**
     * Gestion de la bonne generation du Random
     * @param min
     * @param max
     * @return le nombre correct genere
     */
    protected int getRandomNumberInRange(int min, int max) {

        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random random = new Random();
        int generated = random.nextInt(10);
        boolean ok = false;

        while (!ok) {
            if (generated < min || generated > max) {
                generated = random.nextInt(10);
            } else {
                ok = true;
            }
        }
        return generated;
    }
}