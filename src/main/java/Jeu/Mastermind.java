package Jeu;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.ConfigManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Mastermind {

public static Scanner enter = new Scanner(System.in);

    public static String mode;
    private static int combiSize;
    private static int nbEssai;
    final static Logger log = LogManager.getLogger(Mastermind.class.getName());

    public static void main (String[]args) throws IOException {

        ArrayList<String> params;
        //Mastermind app = new Mastermind();
        params=getPropValues();
        combiSize = Integer.parseInt(params.get(0));
        nbEssai = Integer.parseInt(params.get(1));
        Joueur player = initJoueur();
        Jouable jeu = null;

            switch (player.getModeJeu()) {
                case 1:
                    jeu = new Defenseur(combiSize, nbEssai, player);
                    break;
                case 2:
                    jeu = new Challenger(combiSize, nbEssai, player);
                    break;
                case 3:
                    jeu = new Duel(combiSize, nbEssai, player);
                    break;
                default:
                    System.out.println("Aucun mode n'a ete choisi, veuillez recommencer");
            }
            jeu.start();
        }
        static InputStream inputStream;

    /**
     * Chargement de la configuration en fonction des parametres choisis
     * Recuperation des proprietes du jeu et gestion (catch) d'exception
     * @return result La configuration choisie
     * @throws IOException
     */
    public static ArrayList<String> getPropValues () throws IOException {
        ArrayList<String> result = new ArrayList<>();
        InputStream inputStream = null;
            try {
                Properties prop = new Properties();
                String propFileName = "config.properties";
                inputStream = Mastermind.class.getClassLoader().getResourceAsStream(propFileName);
                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }
                // get the property value and print it out
                result.add(prop.getProperty("game.nb.chiffre.combinaison"));
                result.add(prop.getProperty("game.nb.essai"));
                result.add(prop.getProperty("mode"));
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
                inputStream.close();
            }
            return result;
    }
        /**
         * Chargement de la configuration en fonction des parametres choisis
         * Recuperation des proprietes du jeu et gestion (catch) d'exception
         * @return resultat de configuration choisie
         */
   public static ArrayList<String> loadConfig () {

      ArrayList<String> resultat = new ArrayList<>();
       try {
          Properties prop = new Properties();
          String propFileName = "config.properties";
          inputStream = new FileInputStream(propFileName);
          Mastermind.class.getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
             throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
         }
         resultat.add(prop.getProperty("game.nb.chiffre.combinaison"));
         resultat.add(prop.getProperty("game.nb.essai"));
         resultat.add(prop.getProperty("mode"));

      } catch (Exception e) {
          System.out.println("Exception: " + e);
      }
       return resultat;
   }

  /**
  * Initialisation du joueur, affichage des regles du jeu
  * @return player Un joueur
  */
  public static Joueur initJoueur () {

     System.out.println("Bonjour, Veuillez saisir votre nom SVP");
     System.out.println("_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
     String nom = enter.nextLine();
     System.out.println("");
     System.out.println(String.join("\n",
                "Très bien " + nom + "! C'est parti ! ",
               "                                ",
               "--------------------------------",
               "Bienvenue dans le 'MASTERMIND' !",
               "--------------------------------",
               "Mode DEFENSEUR : L'ordinateur va tenter de trouver votre combinaison.",
               "Mode CHALLENGEUR : Vous tentez de trouver la combinaison de l'ordinateur.",
               "Mode DUEL : vous jouez contre l'ordinateur et l'ordinateur joue contre vous, à tour de rôle.",
               ""));

       int mode = chooseMode();
       Joueur player = new Joueur(nom, mode);
       return player;
   }

    /**
     * Choix du mode de jeu et controle de la saisie d'entier
     * @return retour Mode de jeu choisi
    */
    private static int chooseMode () {

      int retour = 0;

      while (retour == 0) {
           System.out.println("         Veuillez donc choisir votre mode de jeu :");
           System.out.println("");
           System.out.println("1 - Mode DEFENSEUR");
           System.out.println("2 - Mode CHALLENGER");
           System.out.println("3 - Mode DUEL");

          if (enter.hasNextInt()) {
              retour = enter.nextInt();
               enter.nextLine();
               if (retour < 0 || retour > 3) {
                System.out.println("Erreur de saisie : mode entre 1 et 3");
                 retour = -1;
              }
          } else {
             enter.nextLine();
              System.out.println("Erreur de saisie : entier uniquement");
           }
      }
      return retour;
    }
}