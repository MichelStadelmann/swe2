package ba1.stadelmann.lotto;

import javafx.application.Application;
import javafx.stage.Stage;

public class Lotto extends Application {
	private LottoView view;
	private LottoController controller;
	private LottoModel model;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Optional. Program initialization can go here, for example, connection to
	 * a database, or a network server. The primaryStage is not yet available.
	 */
	@Override
	public void init() {
		// Nothing to do in this example
	}

	/**
	 * Note the dependencies between model, view and controller. Additionally,
	 * the view needs access to the primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {
		// Initialize the GUI
		model = new LottoModel();
		view = new LottoView(primaryStage, model);
		controller = new LottoController(model, view);

		// Display the GUI after all initialization is complete
		// Disabled einzelne Buttons, welche erst später enabled sein sollen
		view.start();
		view.btnZiehung.setDisable(true);
		view.btnKugelnRollen.setDisable(true);
		view.btnGewinn.setDisable(true);

		// die Ziehungs-Zahlen sollen nicht bearbeitbar sein
		for (int i = 0; i < 6; i++) {
			view.ziehung[i].setEditable(false);
		}

		// die Ziehungs-Glückszahl soll nicht bearbeitbar sein
		view.ziehungGlückszahl.setEditable(false);
	}

	/**
	 * Optional. An opportunity to clean house, for example, disconnecting from
	 * a database or network server, before the program ends.
	 */
	@Override
	public void stop() {
		if (view != null)
			view.stop();
	}
}