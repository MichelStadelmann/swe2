package ba2.stadelmann.lotto;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Das Model führt vielfältige Operationen aus: die Ziehung der Lottozahlen, die
 * Überprüfung des Gewinns, Überprüfung Anzahl Richtige, überprüft die Eingaben
 * und berechnet einen Zufallsjackpot. Zur Speicherung der Lottozahlen werden
 * Array Listen verwendet.
 * 
 * @author mosta
 *
 */

public class LottoModel {

	protected ArrayList<Integer> zahlen = new ArrayList();

	private int glückszahl;

	boolean glückszahlGetroffen = false;

	private ArrayList<Integer> tipp = new ArrayList();

	private int tippGlückszahl;

	private int ziehung = 0;
	private int zufallszahl;
	private int anzahlRichtige = 0;

	private int jackpot = 0;

	private String gewinnanzeige = new String();

	private boolean prüfungErfolgreich = false;

	private boolean doppelteWerte = false;

	/**
	 * Diese Methode berechnet die 6 Lottozahlen und die Glückszahl. Die
	 * Steuerung geschieht über den Counter Ziehung. Die while-Schleife
	 * simuliert die Ziehung der 6 Lottozahlen. Dabei wird überprüft, ob ein
	 * Wert schon in der Array List vorhanden ist. Der Wert wird nur zur Liste
	 * hinzugefügt, wenn er noch nicht vorhanden ist. Dann wird der Counter
	 * erhöht.
	 */

	public void spieleLotto() {
		while (ziehung < 6) {
			zufallszahl = (int) (Math.random() * 42) + 1;
			System.out.println(zufallszahl);
			if (zahlen.contains(zufallszahl)) {
				zufallszahl = (int) (Math.random() * 42 + 1);

			} else {
				zahlen.add(zufallszahl);
				ziehung++;
			}

		}

		glückszahl = (int) (Math.random() * 6) + 1;

	}

	public void prüfeRichtige(ArrayList zahlen, ArrayList tipp) {
		this.zahlen = zahlen;
		this.tipp = tipp;

		for (int i = 0; i < 6; i++) {
			if (zahlen.contains(tipp.get(i))) {
				anzahlRichtige++;
			} else {

			}
		}
		// System.out.println("Anzahl Richtige:" + anzahlRichtige);
	}

	public void gebeGewinnmeldung(int anzahlRichtige) {
		/**
		 * Die Gewinnberechnung gemäss Swiss Lotto: 1. 6 1 Jackpot 6 richtige &&
		 * Gewinnzahl (boolean !Gewinnzahl) 2. 6 0 CHF 1'000'000.- * 6 richtige
		 * 3. 5 1 CHF 10'000.- 5 && Gewinnzahl 4. 5 0 CHF 1'000.- 5. 4 1 CHF
		 * 150.- 6. 4 0 CHF 75.- 7. 3 1 CHF 25.- 8. 3 0 CHF 10.-
		 */

		if (anzahlRichtige == 6 && glückszahlGetroffen) {
			// System.out.println("Jackpot");
			gewinnanzeige = "Sie haben den Jackpot geknackt!!! Der Gewinn beträgt: " + getJackpot();
		}

		if (anzahlRichtige == 6) {
			// System.out.println("CHF 1'000'000.");
			gewinnanzeige = " Gewinn: CHF 1'000'000.-";
		}

		if (anzahlRichtige == 5 && glückszahlGetroffen) {
			// System.out.println("CHF 10'000.-");
			gewinnanzeige = " Gewinn: CHF 10'000.-";
		}

		if (anzahlRichtige == 5) {
			// System.out.println("CHF 1'000.-");
			gewinnanzeige = " Gewinn: CHF 1'000.-";
		}

		if (anzahlRichtige == 4 && glückszahlGetroffen) {
			// System.out.println("CHF 150.-");
			gewinnanzeige = " Gewinn: CHF 150.-";
		}

		if (anzahlRichtige == 4) {
			// System.out.println("CHF 75.-");
			gewinnanzeige = " Gewinn: CHF 75.-";
		}

		if (anzahlRichtige == 3 && glückszahlGetroffen) {
			// System.out.println("CHF 25.-");
			gewinnanzeige = " Gewinn: CHF 25.-";
		}

		if (anzahlRichtige == 3) {
			// System.out.println("CHF 10.-");
			gewinnanzeige = " Gewinn: CHF 10.-";
		}

		if (anzahlRichtige < 3) {
			// System.out.println("Kein Gewinn");
			gewinnanzeige = "Leider kein Gewinn";
		}

	}

	// definiert möglichen Jackpot zwischen 1.5 Mio und 60 Mio
	public void berechneJackpot() {
		jackpot = (int) (Math.random() * 60000000) + 1500000;
	}

	public void prüfeGlückszahl(int glückszahl) {
		this.glückszahl = glückszahl;

		if (glückszahl == tippGlückszahl) {
			glückszahlGetroffen = true;
		} else {

		}

	}

	/**
	 * Die Methode überprüft die eingegebenen Zahlenwerte auf ihre Korrektheit.
	 * Dazu wird der Zähler "controller" nach oben gezählt und sobald alle
	 * Zahlen bestätigt sind der boolean Wert prüfung Erfolgreich auf True
	 * gesetzt
	 * 
	 * @param tipp
	 * @param tippGlückszahl
	 */

	public void überprüfeEingaben(ArrayList tipp, int tippGlückszahl) {
		this.tipp = tipp;
		this.tippGlückszahl = tippGlückszahl;

		int controller = 0;

		// System.out.println(Integer.toString(getTippGlückszahl()));

		if (getTippGlückszahl() <= 6 && getTippGlückszahl() >= 1) {
			controller++;

		}

		// System.out.println(Arrays.toString(tipp.toArray()));

		for (int i = 0; i < 6; i++) {

			int test;

			test = (int) tipp.get(i);

			// System.out.println(Integer.toString(test));

			if (test <= 42 && test >= 1) {

				controller++;

			}

		}

		if (doppelteWerte == true) {
			controller--;
		}

		if (controller == 7) {
			prüfungErfolgreich = true;
		}

	}

	// getter und setter

	public int getZiehung() {
		return ziehung;
	}

	public void setZiehung(int ziehung) {
		this.ziehung = ziehung;
	}

	public int getGlückszahl() {
		return glückszahl;
	}

	public void setGlückszahl(int glückszahl) {
		this.glückszahl = glückszahl;
		System.out.println(glückszahl);
	}

	public ArrayList<Integer> getTipp() {
		return tipp;
	}

	public void setTipp(ArrayList<Integer> tipp) {
		this.tipp = tipp;
		System.out.println(Arrays.toString(tipp.toArray()));
	}

	public ArrayList<Integer> getZahlen() {
		return zahlen;
	}

	public void setZahlen(ArrayList<Integer> zahlen) {
		this.zahlen = zahlen;
	}

	public int getAnzahlRichtige() {
		return anzahlRichtige;
	}

	public void setAnzahlRichtige(int anzahlRichtige) {
		this.anzahlRichtige = anzahlRichtige;
	}

	public String getGewinnanzeige() {
		return gewinnanzeige;
	}

	public void setGewinnanzeige(String gewinnanzeige) {
		this.gewinnanzeige = gewinnanzeige;
	}

	public int getJackpot() {
		return jackpot;
	}

	public void setJackpot(int jackpot) {
		this.jackpot = jackpot;
	}

	public boolean isPrüfungErfolgreich() {
		return prüfungErfolgreich;
	}

	public void setPrüfungErfolgreich(boolean prüfungErfolgreich) {
		this.prüfungErfolgreich = prüfungErfolgreich;
	}

	public int getTippGlückszahl() {
		return tippGlückszahl;
	}

	public void setTippGlückszahl(int tippGlückszahl) {
		this.tippGlückszahl = tippGlückszahl;
	}

	public boolean isDoppelteWerte() {
		return doppelteWerte;
	}

	public void setDoppelteWerte(boolean doppelteWerte) {
		this.doppelteWerte = doppelteWerte;
	}
}