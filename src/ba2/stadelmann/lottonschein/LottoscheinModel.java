package ba2.stadelmann.lottonschein;

import java.util.ArrayList;
import java.util.Arrays;

public class LottoscheinModel {

	private ArrayList<Integer> zahlen = new ArrayList();
	private int glückszahl;
	boolean glückszahlGetroffen = false;

	private ArrayList<Integer> tipp = new ArrayList();
	private int tippGlückszahl;

	private int ziehung = 0;
	private int zufallszahl;
	private int anzahlRichtige = 0;
	private String gewinnanzeige;

	public static void main(String[] args) {

		/**
		 * Die while-Schleife simuliert die Ziehung der 6 Lottozahlen. Dabei
		 * wird überprüft, ob ein Wert schon in der Array List vorhanden ist.
		 * Der Wert wird nur zur Liste hinzugefügt, wenn er noch nicht vorhanden
		 * ist. Dann wird der Counter erhöht.
		 */

		int gewinnzähler = 0;

		ArrayList<Integer> zahlen = new ArrayList();
		int glückszahl;

		ArrayList<Integer> tipp = new ArrayList();
		int tippGlückszahl;

		int ziehung = 0;
		int zufallszahl;

		while (ziehung < 6) {
			zufallszahl = (int) (Math.random() * 42) + 1;
			System.out.println(zufallszahl);
			if (zahlen.contains(zufallszahl)) {
				zufallszahl = (int) (Math.random() * 42 + 1);

			} else {
				zahlen.add(zufallszahl);
				ziehung++;
			}

			// if ziehung == 6 kann man mit Collections.sort(zahlen) die Liste
			// sortieren

		}

		glückszahl = (int) (Math.random() * 6) + 1;

		System.out.println(Arrays.toString(zahlen.toArray()));
		System.out.println(glückszahl);

	}

	public void prüfeRichtige(int i) {
		if (zahlen.contains(i)) {
			anzahlRichtige++;
		} else {

		}
	}

	public void gebeGewinnmeldung(int anzahlRichtige) {

		// gewinnanzeige = "gewinnanzeige";
		// switch (gewinnzähler) {
		// case 1: gewinnzähler = "January";
		// break;

		// Der Gewinnzähler zählt bei Glückszahl richtig eins nach oben.
		// Folgende Tabelle ergibt
		// Gewinnumrechnung: 1. Gewinnrang Jack

		/**
		 * 1. 6 1 Jackpot 6 richtige && Gewinnzahl (boolean !Gewinnzahl) 2. 6 0
		 * CHF 1'000'000.- * 6 richtige 3. 5 1 CHF 10'000.- 5 && Gewinnzahl 4. 5
		 * 0 CHF 1'000.- 5. 4 1 CHF 150.- 6. 4 0 CHF 75.- 7. 3 1 CHF 25.- 8. 3 0
		 * CHF 10.-
		 */

		if (anzahlRichtige == 6 && glückszahlGetroffen) {
			System.out.println("Jackpot");
		}

		if (anzahlRichtige == 6) {
			System.out.println("CHF 1'000'000.");
		}

		if (anzahlRichtige == 5 && glückszahlGetroffen) {
			System.out.println("CHF 10'000.-");
		}

	}

}
