package appli;

import java.io.File;

public class Erreur {
    private static final int NB_JOUEURS_MIN = 2;   // Nombre de joueurs minimum
    private static final int NB_JOUEURS_MAX = 10;  // Nombre de joueurs maximum
    
    /**
     * @brief Classe utilitaire : aucun constructeur
     */
    private Erreur() {
        throw new IllegalStateException("Classe Utilitaire");
    }
    
    
    /**
     * @brief Teste si le fichier de configuration existe, quitte le programme
     * avec un message si ce n'est pas le cas
     * @param f Fichier de Configuration
     */
    public static void testFichierExiste(File f) {
        if (!f.exists()) {
            System.out.println("Fichier " + f + " introuvable");
            System.exit(0);
        }
    }

    
    /**
     * @brief Teste si le nombre de joueurs est trop bas, quitte le programme
     * avec un message si c'est le cas
     * @param nbJoueurs Nombre de joueurs
     */
    public static void testNbJoueurTropBas(int nbJoueurs) {
        if (nbJoueurs < NB_JOUEURS_MIN) {
            System.out.println("Le nombre de joueurs est trop petit (" +
            nbJoueurs + " joueur, " + NB_JOUEURS_MIN + " minimum)");
            System.exit(0);
        }
    }

    
    /**
     * @brief Teste si le nombre de joueurs est trop haut, quitte le programme
     * avec un message si c'est le cas
     * @param nbJoueurs Nombre de joueurs
     */
    public static void testNbJoueursTropElevees(int nbJoueurs) {
        if (nbJoueurs > NB_JOUEURS_MAX) {
            System.out.println("Le nombre de joueurs est trop élevé (" +
            nbJoueurs + " joueurs, " + NB_JOUEURS_MAX + " maximum)");
            System.exit(0);
        }
    }
}
