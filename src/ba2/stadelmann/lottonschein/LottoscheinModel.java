package ba2.stadelmann.lottonschein;

import java.util.ArrayList;
import java.util.Arrays;

public class LottoscheinModel {

	private ArrayList<Integer> zahlen = new ArrayList();
	private int gl�ckszahl;
	boolean gl�ckszahlGetroffen = false;

	private ArrayList<Integer> tipp = new ArrayList();
	private int tippGl�ckszahl;

	private int ziehung = 0;
	private int zufallszahl;
	private int anzahlRichtige = 0;
	private String gewinnanzeige;

	public static void main(String[] args) {

		/**
		 * Die while-Schleife simuliert die Ziehung der 6 Lottozahlen. Dabei
		 * wird �berpr�ft, ob ein Wert schon in der Array List vorhanden ist.
		 * Der Wert wird nur zur Liste hinzugef�gt, wenn er noch nicht vorhanden
		 * ist. Dann wird der Counter erh�ht.
		 */

		int gewinnz�hler = 0;

		ArrayList<Integer> zahlen = new ArrayList();
		int gl�ckszahl;

		ArrayList<Integer> tipp = new ArrayList();
		int tippGl�ckszahl;

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

		gl�ckszahl = (int) (Math.random() * 6) + 1;

		System.out.println(Arrays.toString(zahlen.toArray()));
		System.out.println(gl�ckszahl);

	}

	public void pr�feRichtige(int i) {
		if (zahlen.contains(i)) {
			anzahlRichtige++;
		} else {

		}
	}

	public void gebeGewinnmeldung(int anzahlRichtige) {

		// gewinnanzeige = "gewinnanzeige";
		// switch (gewinnz�hler) {
		// case 1: gewinnz�hler = "January";
		// break;

		// Der Gewinnz�hler z�hlt bei Gl�ckszahl richtig eins nach oben.
		// Folgende Tabelle ergibt
		// Gewinnumrechnung: 1. Gewinnrang Jack

		/**
		 * 1. 6 1 Jackpot 6 richtige && Gewinnzahl (boolean !Gewinnzahl) 2. 6 0
		 * CHF 1'000'000.- * 6 richtige 3. 5 1 CHF 10'000.- 5 && Gewinnzahl 4. 5
		 * 0 CHF 1'000.- 5. 4 1 CHF 150.- 6. 4 0 CHF 75.- 7. 3 1 CHF 25.- 8. 3 0
		 * CHF 10.-
		 */

		if (anzahlRichtige == 6 && gl�ckszahlGetroffen) {
			System.out.println("Jackpot");
		}

		if (anzahlRichtige == 6) {
			System.out.println("CHF 1'000'000.");
		}

		if (anzahlRichtige == 5 && gl�ckszahlGetroffen) {
			System.out.println("CHF 10'000.-");
		}

	}

}
