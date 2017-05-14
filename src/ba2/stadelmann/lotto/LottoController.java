package ba2.stadelmann.lotto;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class LottoController {

	private LottoModel model;
	private LottoView view;
	private ArrayList<Integer> tipp = new ArrayList();

	protected LottoController(LottoModel model, LottoView view) {
		this.model = model;
		this.view = view;

		// register ourselves to listen for button clicks
		view.btnTipp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// model.incrementValue();
				// String newText = Integer.toString(model.getValue());
				// view.lblNumber.setText(newText);
			}
		});

		view.btnTipp.setOnAction(event -> {
			// speichert den Tipp in einer ArrayList um ihn ans Model zu
			// �bergeben
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

		view.btnZiehung.setOnAction(event -> {
			// l�st die Berechnung der Ziehung durch das Model aus

			view.btnZiehung.setDisable(true);
			model.spieleLotto();

			for (int i = 0; i < 6; i++) {
				String neuerText = Integer.toString(model.zahlen.get(i));
				view.ziehung[i].setText(neuerText);
			}

			String Gl�ckszahl = Integer.toString(model.getGl�ckszahl());
			view.ziehungGl�ckszahl.setText(Gl�ckszahl);
			view.btnGewinn.setDisable(false);

		});

		view.btnGewinn.setOnAction(event -> {
			// l�st die Berechnung der Ziehung durch das Model aus

			view.btnGewinn.setDisable(true);
			model.pr�feRichtige(model.getZahlen(), model.getTipp());
			model.pr�feGl�ckszahl(model.getGl�ckszahl());
			model.gebeGewinnmeldung(model.getAnzahlRichtige());
			view.getLblGewinn().setText(model.getGewinnanzeige());

		});

		// register ourselves to handle window-closing event
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				view.stop();
				Platform.exit();
			}
		});
	}

}
