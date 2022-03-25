package appli;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import jeu_composants.Joueur;
import jeu_composants.Paquet;
import jeu_composants.Serie;
import utils.Console;

public class Jeu {
    private static final String CHOIX = "Saisissez votre choix : ";
    private static final String CARTE_INCORRECTE = "Vous n'avez pas cette carte";
    private static final String SERIE_INCORRECTE = "Ce n'est pas une série valide";
    private static final String NOUVELLE_TENTATIVE = ", saisissez votre choix : ";
    
    private ArrayList<Joueur> tabJ; // Ensemble des joueurs
    private Serie[] tabS;           // Tableau des Séries
    
    
    /**
     * @brief Constructeur de Jeu, initialise les joueurs et les séries
     * @param f Fichier de configuration
     */
    public Jeu(File f) throws FileNotFoundException {
        Erreur.testFichierExiste(f); // Teste si le fichier n'existe pas

        Paquet pioche = Paquet.initialisationPioche();
        this.initialisationJoueur(pioche, f);
        this.tabS = Serie.initialisationSeries(pioche);
    }
    
    
    /**
     * @brief Constructeur de Joueurs
     * @param pioche Pioche dans laquelle piocher
     * @param f Fichier de configuration
     */
    private void initialisationJoueur(Paquet pioche, File f)
                    throws FileNotFoundException {
        Scanner scF = new Scanner(new FileInputStream(f));
        
        this.tabJ = new ArrayList<>();
        int nbJoueurs = 0;
        
        while (scF.hasNext()){
            // Teste si le nombre de joueurs n'est pas trop élevé
            Erreur.testNbJoueursTropElevees(++nbJoueurs);
            tabJ.add(new Joueur(scF.next(), pioche));
        }
        
        scF.close();
        
        // Teste si le nombre de joueur n'est pas trop bas
        Erreur.testNbJoueurTropBas(nbJoueurs);
    }
    
    /**
     * @brief Lance une partie
     */
    public void partie() {
        // Message de début de partie
        System.out.println(Joueur.debutPartie(tabJ));
        
        Scanner sc = new Scanner(System.in);

        // Tour de table
        for (int idxTour = 0; idxTour < Joueur.getNbCartesJoueurs(); ++idxTour) {
            // Tour du joueur : phase 1
            for (Joueur j : tabJ) {

                /**
                 * Annonce le joueur, fait une pause, affiche les séries,
                 * la main du joueur, et demande à faire un choix
                 */
                System.out.println(j.annonce());
                Console.pause();
                System.out.println(Serie.toStringSeries(tabS));
                System.out.println(j.toStringMain());
                System.out.print(CHOIX);
                
                // Choix de la carte à sélectionner
                while (true){
                    try  {
                        int nCarte = Integer.parseInt(sc.next());
                        
                        j.choisi(nCarte);
                        break;
                    }
                    
                    /**
                     * Si la carte n'est pas un nombre, ou si elle n'existe pas
                     * dans la main du joueur, refaire une demande
                     */
                    catch (java.lang.RuntimeException e){
                        System.out.print(CARTE_INCORRECTE + NOUVELLE_TENTATIVE);
                    }
                }
                
                Console.clearScreen();
            }

            boolean aucunJoueurADuChoisirSerie = true;

            // Tour du joueur : phase 2
            for (Joueur j : Joueur.copieTriee(tabJ)){

                /**
                 * Si la série n'est pas la bonne, demander au joueur d'entrer
                 * la série voulue
                 */
                if (!j.peutJouerDeSuite(tabS)) {

                    // Si c'est la première fois, afficher le message adéquat
                    if (aucunJoueurADuChoisirSerie) {
                        aucunJoueurADuChoisirSerie = false;
                        System.out.println(Joueur.prePosageCarte(tabJ));
                    }

                    System.out.println(j.debutChoixSerie());
                    System.out.println(Serie.toStringSeries(tabS));
                    System.out.print(CHOIX);
                    
                    // Choix de la série à sélectionner
                    while (true){
                        try  {
                            int nSerie = Integer.parseInt(sc.next());
                            
                            Serie serie = tabS[nSerie-1];
                            j.joueDans(serie);
                            break;
                        }
                        
                        /**
                         * Si le numéro demandé est incorrect, refaire une 
                         * demande
                         */
                        catch (java.lang.NumberFormatException|
                               java.lang.IndexOutOfBoundsException e){
                            System.out.print(SERIE_INCORRECTE + NOUVELLE_TENTATIVE);
                        }
                    }
                }
            }

            
            // Annonce les cartes jouées, affiche les séries et le score du tour
            System.out.println(Joueur.postPosageCarte(tabJ));
            System.out.println(Serie.toStringSeries(tabS));
            System.out.println(Joueur.scoreFinTour(tabJ));
        }
        sc.close();
        
        System.out.println(Joueur.scoreFinal(tabJ)); // Affiche le score final
    }
}
