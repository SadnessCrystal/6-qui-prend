package jeu_composants;

import java.util.ArrayList;

public class Paquet extends ArrayList<Carte>{
    


    /**
     * @brief Retourne la chaîne de caractère désignant le paquet
     * @return [La liste de cartes]
     */
    public String toStringPaquet(){
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<this.size(); ++i){
            sb.append(this.get(i));

            if (i < this.size()-1){
                sb.append(", ");
            }
        }

        return sb.toString();
    }
    

    /**
     * @brief Initialise et retourne une pioche avec NB_CARTES_JEU cartes
     * @return Pioche de NB_CARTES_JEU cartes
     */
    public static Paquet initialisationPioche() {
        Paquet pioche = new Paquet();
        
        for (int i=1; i<=Carte.getNbCartesJeu(); ++i) {
            pioche.add(new Carte(i));
        }

        return pioche;
    }
}
