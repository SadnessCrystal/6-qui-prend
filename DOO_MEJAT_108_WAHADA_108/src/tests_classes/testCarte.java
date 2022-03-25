package tests_classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu_composants.Carte;


class testCarte {
    /**
     * @brief Teste attributionTeteBoeuf()
     */
    @Test
    void testAttributionTeteboeuf() {
        // Vérifier que la carte n°55 donne 7 têtes de boeufs
        assertEquals(7, new Carte(55).getNumTeteDeBoeufs());
        
        // Vérifier qu'une carte se terminant par 5 donne 2 têtes de boeufs
        assertEquals(2, new Carte(5).getNumTeteDeBoeufs());
        assertEquals(2, new Carte(15).getNumTeteDeBoeufs());
        assertEquals(2, new Carte(95).getNumTeteDeBoeufs());
        
        // Vérifier qu'une carte se terminant par 0 donne 3 têtes de boeufs
        assertEquals(3, new Carte(10).getNumTeteDeBoeufs());
        assertEquals(3, new Carte(20).getNumTeteDeBoeufs());
        assertEquals(3, new Carte(100).getNumTeteDeBoeufs());
        
        /** 
         * Vérifier qu'une carte comportant deux chiffres égaux (sauf 55) donne
         * 5 têtes de boeufs
         */
        assertEquals(5, new Carte(11).getNumTeteDeBoeufs());
        assertEquals(5, new Carte(22).getNumTeteDeBoeufs()); 
        assertEquals(5, new Carte(44).getNumTeteDeBoeufs()); 
        
        /**
         * Vérifier que n'importe quelle carte ne remplissant pas les conditions
         * testés ci dessus donne 1 tête de boeuf
         */
        assertEquals(1, new Carte(1).getNumTeteDeBoeufs());
        assertEquals(1, new Carte(2).getNumTeteDeBoeufs());
        assertEquals(1, new Carte(12).getNumTeteDeBoeufs());
    }
    
    
    /**
     * @brief Teste toString()
     */
    @Test
    void testToString() {
        assertEquals("55 (7)", new Carte(55).toString());
        assertEquals("25 (2)", new Carte(25).toString());
        assertEquals("20 (3)", new Carte(20).toString());
        assertEquals("44 (5)", new Carte(44).toString());
        assertEquals("12", new Carte(12).toString());
    }
    

    /**
     * @brief Teste la méthode equals()
     */
    @Test
    void testCarteEgale(){
    	// 2 Cartes de même valeurs sont égales :
    	assertEquals(new Carte(1), new Carte(1));
    	assertEquals(new Carte(55), new Carte(55));
    	assertEquals(new Carte(10), new Carte(10));
    	
    	/** 
         * 2 cartes de valeurs différentes mais au même nombre de têtes de
         * boeufs sont différentes
         */ 
    	assertNotEquals(new Carte(2), new Carte(1));
    	assertNotEquals(new Carte(10), new Carte(20));
    	assertNotEquals(new Carte(25), new Carte(65));
    	
    	/**
         * 2 cartes de valeurs et au nombre de têtes de boeuf différentes sont
         * différentes
         */
    	assertNotEquals(new Carte(55), new Carte(1));
    	assertNotEquals(new Carte(55), new Carte(10));
    	assertNotEquals(new Carte(55), new Carte(25));
    	
    	// Comparer avec un objet qui n'est pas une carte ne renvoie pas d'erreur
    	assertNotEquals(new Carte(55), 1);
    	
    	// Comparer avec un objet nul ne renvoie pas d'erreur
    	assertNotEquals(new Carte(1), null);
    }
    
    /**
     * @brief Teste la méthode compareCarte()
     */
    @Test
    void testCompareCarte(){
    	assertEquals(Carte.compareCarte(new Carte(2), new Carte(1)), 1);
    	assertEquals(Carte.compareCarte(new Carte(55), new Carte(55)), 0);
    }
}
