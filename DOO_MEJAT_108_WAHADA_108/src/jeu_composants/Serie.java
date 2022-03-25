package jeu_composants;

import java.util.Collections;
import java.util.Random;

public class Serie {
    private static final int NB_CARTES_MAX_SERIE = 5;
    private static final int NB_SERIES = 4;
    
    private int numeroSerie;        // Numéro de la série
    private Paquet paq;             // Paquet de carte
    

    /**
     * @brief Constructeur de Serie, initialise le numéro et sa première carte
     * @param n Numéro de la série
     * @param p Paquet dans lequel piocher
     */
    private Serie(int n, Paquet p) {
        this.numeroSerie = n;
        initialisationPaquet(p);
    }
    
    
    /**
     * @void Insère une carte dans la série
     * @param p Paquet dans lequel piocher
     */
    private void initialisationPaquet(Paquet p) {
        this.paq = new Paquet();

        int idxCarte = new Random().nextInt(p.size());
        Carte c = p.get(idxCarte);
        
        this.paq.add(c);
        p.remove(idxCarte);
    }
    

    /**
     * @brief Vide la série
     */
    public void vider() {
        this.paq.clear();
    }
    

    /**
     * @brief Vérifie si la série est pleine
     * @return true si la taille de la série est égale à
     * {@value NB_CARTES_MAX_SERIE}, false sinon
     */
    public boolean estPleine() {
        return this.paq.size() == NB_CARTES_MAX_SERIE;
    }
    

    /**
     * @brief Renvoie le numéro de la dernière carte
     * @pre La série n'est pas vide
     * @return Numéro de la dernière carte de la série
     */
    public int nCarteMax() throws RuntimeException{
        if (this.paq.size() == 0){
            throw new RuntimeException("Une série n'est pas censée rester vide");
        }

        int idxCarteMax = this.paq.size()-1;
        Carte carteMax = this.paq.get(idxCarteMax);
        return carteMax.getNumCarte();
    }

    
    /**
     * @brief Retourne le numéro de série ainsi que ses cartes
     * @return Série n° [numéro de la série] : [paquet de carte de la série]
     */
    public String toString(){
        return "- série n° " + numeroSerie + " : " + paq;
    }
    
    
    /**
     * @brief Renvoie le nombre de points de la série
     * @return Nombre de points de la série
     */
    public int nbPoints() {
        int nbTeteDeBoeuf = 0;
        
        for (Carte c : this.paq) {
            nbTeteDeBoeuf += c.getNumTeteDeBoeufs();
        }
        
        return nbTeteDeBoeuf;
    }

    
    /**
     * @brief Renvoie l'ensemble des séries sous forme de chaîne de caractère
     * @return Ensemble des séries
     */
    public static String toStringSeries(Serie[] tabS) {
        StringBuilder sb = new StringBuilder();
        
        for (int idxS = 0; idxS<tabS.length; ++idxS) {
            sb.append(tabS[idxS]);
            
            if (idxS < tabS.length-1) {
                sb.append(System.lineSeparator());
            }
        }
        
        return sb.toString();
    }


    /**
     * @brief Ajoute une carte à la série
     * @param c Carte à ajouter
     * @pre La série n'est pas pleine et le numéro de la carte est supérieur à
     * l'actuelle carte de plus haute valeur
     */
    public void ajoute(int c) throws RuntimeException{
        if (this.estPleine()){
            throw new RuntimeException("Série " + this + "est déjà pleine");
        }

        else if (this.paq.size() != 0 && this.nCarteMax() >= c){
            throw new RuntimeException("Carte n°" + c + "inférieure ou égale à "
                                        + this.nCarteMax());
        }

        this.paq.add(new Carte(c));
    }
    

    /**
     * @brief Compare Les 2 cartes maximales de 2 séries
     * @param s Série dont la plus grande carte est à comparer
     * @return true si la série de gauche a un numéro de carte maximale plus
     * élevée que la série de droite
     */
    public boolean aCarteMaxPlusEleveeQue(Serie s){
        return this.nCarteMax() > s.nCarteMax();
    }


    /**
     * @brief Trie le paquet de carte de la série
     */
    public void tri(){
        Collections.sort(this.paq, Carte::compareCarte);
    }


    /**
     * @brief Constructeur de 4 Séries
     * @param pioche Paquet dans lequel piocher les cartes
     */
    public static Serie[] initialisationSeries(Paquet pioche){
        Serie[] tabS = new Serie[NB_SERIES];
        
        for (int i=0; i<NB_SERIES; ++i){
            tabS[i] = new Serie(i+1, pioche);
        }

        return tabS;
    }
}
