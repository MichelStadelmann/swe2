package ba2.stadelmann.lotto;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

/**
 * Die Controller Klasse regelt das Event-Handling, welches durch die Buttons
 * Clicks gesteuert wird. Dabei wurden lambda-Expressions verwendet. Das enablen
 * und disablen der Buttons soll den Benutzer f�hren und m�gliche Fehlerquellen
 * reduzieren.
 * 
 * @author mosta
 *
 */

public class LottoController {

	private LottoModel model;
	private LottoView view;
	private ArrayList<Integer> tipp = new ArrayList();
	private int counter = 0;

	protected LottoController(LottoModel model, LottoView view) {
		this.model = model;
		this.view = view;

		// Wir registrieren uns, um auf Buttons Clicks zu registerien
		// speichert den Tipp in einer ArrayList um ihn ans Model zu �bergeben
		view.btnTipp.setOnAction(event -> {

			view.btnTipp.setDisable(true);

			for (int i = 0; i < 6; i++) {
				int foo = Integer.parseInt(view.tipp[i].getText());
				tipp.add(foo);
			}
			model.setTipp(tipp);

			int gZahl = Integer.parseInt(view.tippGl�ckszahl.getText());
			model.setGl�ckszahl(gZahl);

			view.btnZiehung.setDisable(false);

		});

		// l�st die Berechnung der Ziehung durch das Model aus
		view.btnZiehung.setOnAction(event -> {

			view.btnZiehung.setDisable(true);
			model.spieleLotto();

			// hier w�re eine ursp�ngliche Variante, um alle Zahlen auf einmal
			// anzuzeigen:
			// for (int i = 0; i < 6; i++) {
			// String neuerText = Integer.toString(model.zahlen.get(i));
			// view.ziehung[i].setText(neuerText);
			// }

			// String Gl�ckszahl = Integer.toString(model.getGl�ckszahl());
			// view.ziehungGl�ckszahl.setText(Gl�ckszahl);

			view.btnKugelnRollen.setDisable(false);

		});

		// dieser Button simuliert die Ziehung
		// das Model hat die Kugel bereits berechnet, hier folgt die verz�gerte
		// Anzeige
		view.btnKugelnRollen.setOnAction(event -> {

			if (counter < 6) {

				int i = counter;
				String neuerText = Integer.toString(model.zahlen.get(i));
				view.ziehung[i].setText(neuerText);

			}

			if (counter == 6) {

				String Gl�ckszahl = Integer.toString(model.getGl�ckszahl());
				view.ziehungGl�ckszahl.setText(Gl�ckszahl);

				view.btnKugelnRollen.setDisable(true);
				view.btnGewinn.setDisable(false);

			}

			counter++;

		});

		// l�st die Berechnung der Ziehung durch das Model aus
		view.btnGewinn.setOnAction(event -> {

			view.btnGewinn.setDisable(true);
			model.pr�feRichtige(model.getZahlen(), model.getTipp());
			model.pr�feGl�ckszahl(model.getGl�ckszahl());
			model.gebeGewinnmeldung(model.getAnzahlRichtige());
			view.getLblAnzahlRichtige().setText("Anzahl Richtige: " + (Integer.toString(model.getAnzahlRichtige())));
			view.getLblGewinn().setText(model.getGewinnanzeige());

		});

		// Event-Handling um Fenster zu schliessen
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				view.stop();
				Platform.exit();
			}
		});
	}

}
