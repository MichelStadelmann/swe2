package ba1.stadelmann.lotto;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.WindowEvent;

/**
 * Die Controller Klasse regelt das Event-Handling, welches durch die Buttons
 * Clicks gesteuert wird. Dabei wurden lambda-Expressions verwendet. Das enablen
 * und disablen der Buttons (resp. die Editierbarkeit der Textfelder) soll den
 * Benutzer führen und mögliche Fehlerquellen reduzieren. Zusätzlich werden
 * viele Eingaben über das Model validiert. Der Tipp wird in einer Array List
 * gespeichert und für weitere Operationen ans Model übergeben.
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

		// Wir registrieren uns für Buttons Clicks
		view.btnTipp.setOnAction(event -> {

			// Prüfung, ob es noch Felder ohne Eingabe hat
			if (view.tippGlückszahl.getText().isEmpty() || view.tipp[0].getText().isEmpty()
					|| view.tipp[1].getText().isEmpty() || view.tipp[2].getText().isEmpty()
					|| view.tipp[3].getText().isEmpty() || view.tipp[4].getText().isEmpty()
					|| view.tipp[5].getText().isEmpty() || view.tipp[5].getText().isEmpty()) {

				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Fehlermeldung");
				alert.setHeaderText("Tipp unvollständig");
				alert.setContentText("Bitten den Tipp vervollständigen: "
						+ "Bitte beim Tipp folgendes beachten: ganze Zahl zwischen 1 und 42, einmalig vorkommend! Die Glückszahl zwischen 1 und 6! wählen");

				alert.showAndWait();

			}

			/**
			 * Sind alle Felder befüllt, erfolgt die Verrechnung. Falls bereits
			 * ein Tipp gemacht wurde, wird die bestehende Liste geleert und neu
			 * angelegt. Während der Eingabe des Tipps wird gleich auf doppelt
			 * angelegte Werte geachtet.
			 */

			else {

				model.setDoppelteWerte(false);

				if (model.getTipp().isEmpty() == false) {

					model.getTipp().clear();
				}

				for (int i = 0; i < 6; i++) {
					int foo = Integer.parseInt(view.tipp[i].getText());
					if (tipp.contains(foo)) {

						model.setDoppelteWerte(true);
						tipp.add(foo);

					} else {
						tipp.add(foo);
					}
				}

				model.setTipp(tipp);

				int gZahl = Integer.parseInt(view.tippGlückszahl.getText());
				model.setGlückszahl(gZahl);

				// System.out.println(model.isPrüfungErfolgreich());

				model.überprüfeEingaben(tipp, gZahl);

				// System.out.println(model.isPrüfungErfolgreich());

				if (model.isPrüfungErfolgreich() == true) {

					for (int i = 0; i < 6; i++) {
						view.tipp[i].setEditable(false);
					}

					view.btnTipp.setDisable(true);

					view.tippGlückszahl.setEditable(false);

					view.btnZiehung.setDisable(false);
				}

				if (model.isPrüfungErfolgreich() == false) {

					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Fehlermeldung");
					alert.setHeaderText("Fehler bei Tipp-Eingabe");
					alert.setContentText(
							"Bitte beim Tipp folgendes beachten: ganze Zahl zwischen 1 und 42, einmalig vorkommend! Die Glückszahl zwischen 1 und 6! wählen");

					alert.showAndWait();

				}
			}

		});

		// löst die Berechnung der Ziehung durch das Model aus
		view.btnZiehung.setOnAction(event -> {

			view.btnZiehung.setDisable(true);
			model.spieleLotto();

			// hier wäre eine urspüngliche Variante, um alle Zahlen auf einmal
			// anzuzeigen:
			// for (int i = 0; i < 6; i++) {
			// String neuerText = Integer.toString(model.zahlen.get(i));
			// view.ziehung[i].setText(neuerText);
			// }

			// String Glückszahl = Integer.toString(model.getGlückszahl());
			// view.ziehungGlückszahl.setText(Glückszahl);

			view.btnKugelnRollen.setDisable(false);

		});

		// dieser Button simuliert die Ziehung
		// das Model hat die Kugel bereits berechnet, hier folgt die verzögerte
		// Anzeige
		view.btnKugelnRollen.setOnAction(event -> {

			if (counter < 6) {

				int i = counter;
				String neuerText = Integer.toString(model.zahlen.get(i));
				view.ziehung[i].setText(neuerText);

			}

			if (counter == 6) {

				String Glückszahl = Integer.toString(model.getGlückszahl());
				view.ziehungGlückszahl.setText(Glückszahl);

				view.btnKugelnRollen.setDisable(true);
				view.btnGewinn.setDisable(false);

			}

			counter++;

		});

		// löst die Berechnung der Ziehung durch das Model aus
		view.btnGewinn.setOnAction(event -> {

			view.btnGewinn.setDisable(true);
			model.prüfeRichtige(model.getZahlen(), model.getTipp());
			model.prüfeGlückszahl(model.getGlückszahl());
			model.gebeGewinnmeldung(model.getAnzahlRichtige());
			view.getLblAnzahlRichtige().setText("Anzahl Richtige: " + (Integer.toString(model.getAnzahlRichtige())));
			view.getLblGewinn().setText(model.getGewinnanzeige());

		});

		// man kann so beim Eingabefeld nur int-Werte eingeben
		// http://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
		view.tippGlückszahl.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					view.tippGlückszahl.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		// man kann so beim Eingabefeld nur int-Werte eingeben
		// http://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
		// Code kann hier noch verbessert werden, viele Wiederholungen. For loop
		// warf Fehler

		view.tipp[0].textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					view.tipp[0].setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		view.tipp[1].textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					view.tipp[1].setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		view.tipp[2].textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					view.tipp[2].setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		view.tipp[3].textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					view.tipp[3].setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		view.tipp[4].textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					view.tipp[4].setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

		view.tipp[5].textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					view.tipp[5].setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
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
