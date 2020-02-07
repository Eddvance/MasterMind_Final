package Jeu;

public class Joueur {

    private String nom;
    private int modeJeu;

    public Joueur(String nom, int modeJeu) {
        this.nom = nom;
        this.modeJeu = modeJeu;
    }

    public String getNom() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

    public int getModeJeu() {
        return modeJeu;
    }

    public void setModeJeu(int modeJeu) {

        this.modeJeu = modeJeu;
    }

}