package tests_classes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jeu_composants.Carte;
import jeu_composants.Paquet;
import jeu_composants.Serie;

class testSerie {
	
	/**
	 * @brief Vérifie que la syntaxe du toString de Serie et toStringSeries()
	 * sont les bonnes
	 */
	@Test
	void testToStringSeries() {
		Paquet paquet = new Paquet();
		
		for (int i=1; i<5; ++i) {
			paquet.add(new Carte(1));	
		}
		
		Serie tabS[] = Serie.initialisationSeries(paquet);
		
		tabS[0].ajoute(5);
		tabS[0].ajoute(6);
		String s1 = "- série n° 1 : 1, 5 (2), 6";
		assertEquals(s1, tabS[0].toString());
		
		tabS[1].ajoute(10);
		tabS[1].ajoute(22);
		tabS[1].ajoute(50);
		String s2 = "- série n° 2 : 1, 10 (3), 22 (5), 50 (3)";
		assertEquals(s2, tabS[1].toString());

		tabS[2].ajoute(9);
		tabS[2].ajoute(90);
		tabS[2].ajoute(104);
		String s3 = "- série n° 3 : 1, 9, 90 (3), 104";
		assertEquals(s3, tabS[2].toString());

		tabS[3].ajoute(55);
		tabS[3].ajoute(67);
		tabS[3].ajoute(85);
		tabS[3].ajoute(90);
		String s4 = "- série n° 4 : 1, 55 (7), 67, 85 (2), 90 (3)";
		assertEquals(s4, tabS[3].toString());
		
		String series = s1 + System.lineSeparator() + s2 + System.lineSeparator()
						+ s3 + System.lineSeparator() + s4;
		
		assertEquals(series, Serie.toStringSeries(tabS));
	}

	
	/**
	 * @brief Vérifie que la méthode détermine bien si la série est pleine
	 */
	@Test
	void testEstPleine() {
		Paquet paquet = new Paquet();
		
		for (int i=1; i<5; ++i) {
			paquet.add(new Carte(1));	
		}
		
		Serie tabS[] = Serie.initialisationSeries(paquet);
		assertFalse(tabS[0].estPleine());
		
		tabS[0].ajoute(5);
		assertFalse(tabS[0].estPleine());
		
		tabS[0].ajoute(6);
		assertFalse(tabS[0].estPleine());
		
		tabS[0].ajoute(7);
		assertFalse(tabS[0].estPleine());
		
		tabS[0].ajoute(8);
		assertTrue(tabS[0].estPleine());
	}
	
	
	/**
	 * @brief Vérifie que le numéro renvoyé par la méthode est le bon
	 */
	@Test
	void testNCarteMax() {
		Paquet paquet = new Paquet();
		
		for (int i=1; i<5; ++i) {
			paquet.add(new Carte(1));	
		}

		Serie tabS[] = Serie.initialisationSeries(paquet);
		
		assertEquals(1, tabS[0].nCarteMax());
		
		tabS[0].ajoute(2);
		assertEquals(2, tabS[0].nCarteMax());
		
		tabS[0].ajoute(3);
		assertEquals(3, tabS[0].nCarteMax());
		
		tabS[0].ajoute(10);
		assertEquals(10, tabS[0].nCarteMax());
		
		tabS[0].ajoute(104);
		assertEquals(104, tabS[0].nCarteMax());
	}
	
	
	/**
	 * @brief Vérifie que le nombre de points renvoyé par la méthode est cohérant
	 */
	@Test
	void testNbPoints() {
		Paquet paquet = new Paquet();
		
		for (int i=1; i<5; ++i) {
			paquet.add(new Carte(1));	
		}

		Serie tabS[] = Serie.initialisationSeries(paquet);
		int nbPoints = 1;
		assertEquals(nbPoints, tabS[0].nbPoints());
		
		tabS[0].ajoute(5);
		nbPoints += 2;
		assertEquals(nbPoints, tabS[0].nbPoints());
		
		tabS[0].ajoute(10);
		nbPoints += 3;
		assertEquals(nbPoints, tabS[0].nbPoints());
		
		tabS[0].ajoute(22);
		nbPoints += 5;
		assertEquals(nbPoints, tabS[0].nbPoints());
		
		tabS[0].ajoute(55);
		nbPoints += 7;
		assertEquals(nbPoints, tabS[0].nbPoints());
	}
}
