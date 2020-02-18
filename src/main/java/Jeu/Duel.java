package Jeu;

public class Duel implements Jouable {
    protected int combinaisonSize;
    protected int nbEssai;
    protected Joueur player;

    Defenseur def;
    Challenger chall;

    public Duel(int combinaisonSize, int nbEssai, Joueur player) {
        this.combinaisonSize = combinaisonSize;
        this.nbEssai = nbEssai;
        this.player = player;
    }

    @Override
    public void prepareJeu() {
        // initialiser un defenseur
        // initialiser un challenger
        def = new Defenseur(combinaisonSize,nbEssai,player);
        chall = new Challenger(combinaisonSize,nbEssai,player);
        def.prepareJeu();
        chall.prepareJeu();
    }

    @Override
    public GameCard joue() {
        GameCard gcDefenseur = new GameCard(false,1, player);
        GameCard gcChallenger = new GameCard(false,1, player);
        while (jeuPasFini(gcChallenger,gcDefenseur)){
            gcDefenseur = def.joueUnTour(gcDefenseur);
            gcChallenger=  chall.joueUnTour(gcChallenger);
        }
        return getGcGlobale(gcDefenseur,gcChallenger);
    }

    /**
     *
     * @param gcDefenseur
     * @param gcChallenger
     * @return
     */
    private GameCard getGcGlobale(GameCard gcDefenseur, GameCard gcChallenger) {
        // Todo evaluer le bon gc
        return gcDefenseur;
    }

    private boolean jeuPasFini(GameCard gcChallenger, GameCard gcDefenseur) {

        boolean pasFiniChallenger = (gcChallenger.getScore() <= nbEssai) && !gcChallenger.isGagne();
        boolean pasFiniDefenseur = (gcDefenseur.getScore() <= nbEssai) && !gcDefenseur.isGagne();

        return pasFiniChallenger && pasFiniDefenseur;
    }
}


