package appli;

import java.io.File;
import java.io.FileNotFoundException;

public class Application {
    public static void main(String[] args) throws FileNotFoundException{
        Jeu jeu = new Jeu(new File("config.txt")); // Initialise la partie

        jeu.partie(); // Lance la partie
    }
}
