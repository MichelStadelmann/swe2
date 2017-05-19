package ba2.stadelmann.lottonschein;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LottoscheinView {
	private LottoscheinModel model;
	private Stage stage;

	protected Label lblNumber;
	protected Button btnClick;
	protected TextField[] lottoscheinZahlen = new TextField[42];
	private static final double HEIGHT = 600;
	private static final double WIDTH = 400;
	private TextField[] zahlen = new TextField[42];
	private TextField zahl;

	protected LottoscheinView(Stage stage, LottoscheinModel model) {
		this.stage = stage;
		this.model = model;

		stage.setTitle("Swiss Lotto – niemand macht mehr Millionäre");

		GridPane root = new GridPane();
		root.setPrefSize(WIDTH, HEIGHT);

		// lblNumber = new Label();
		// lblNumber.setText(Integer.toString(model.getValue()));
		// root.add(lblNumber, 0, 0);

		// for (int i = 0; i < 41; i++) {
		//
		// zahl = new TextField();
		//
		// // String TextInteger.toString(i);
		// // btnClick.setText("1");
		// // root.add(btnClick, 0, 0);
		//
		// }

		Scene scene = new Scene(root);
		// scene.getStylesheets().add(getClass().getResource("ButtonClickMVC.css").toExternalForm());
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