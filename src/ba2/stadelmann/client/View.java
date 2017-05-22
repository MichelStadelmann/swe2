package ba2.stadelmann.client;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class View {
	private Model model;
	private Stage stage;

	protected Label lblNumber;
	protected Button btnClick;

	protected View(Stage stage, Model model) {
		this.stage = stage;
		this.model = model;

		stage.setTitle("Button Click MVC");

		GridPane root = new GridPane();
		lblNumber = new Label();
		lblNumber.setText(Integer.toString(model.getValue()));
		root.add(lblNumber, 0, 0);

		btnClick = new Button();
		btnClick.setText("Click Me!");
		root.add(btnClick, 0, 1);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Client.css").toExternalForm());
		stage.setScene(scene);
		;
	}

	public void start() {
		stage.show();
	}

	/**
	 * Stopping the view - just make it invisible
	 */
	public void stop() {
		stage.hide();
	}

	/**
	 * Getter for the stage, so that the controller can access window events
	 */
	public Stage getStage() {
		return stage;
	}
}