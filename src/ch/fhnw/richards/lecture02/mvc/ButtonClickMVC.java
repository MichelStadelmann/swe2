package ch.fhnw.richards.lecture02.mvc;

import javafx.application.Application;
import javafx.stage.Stage;

public class ButtonClickMVC extends Application {
	private ButtonClickView view;
	private ButtonClickController controller;
	private ButtonClickModel model;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Note the dependencies between model, view and controller. Additionally,
	 * the view needs the primaryStage created by JavaFX.
	 */
	@Override
	public void start(Stage primaryStage) {
		// Initialize the GUI
		model = new ButtonClickModel();
		view = new ButtonClickView(primaryStage, model);
		controller = new ButtonClickController(model, view);

		// Display the GUI after all initialization is complete
		view.start();
	}

	/**
	 * The stop method is the opposite of the start method. It provides an
	 * opportunity to close down the program gracefully, when the program has
	 * been closed.
	 */
	@Override
	public void stop() {
		if (view != null)
			view.stop();
	}
}
