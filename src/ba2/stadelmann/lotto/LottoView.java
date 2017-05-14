package ba2.stadelmann.lotto;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LottoView {
	private LottoModel model;
	private Stage stage;

	protected Label lblNumber;
	protected Button btnTipp;
	protected TextField[] lottoscheinZahlen = new TextField[42];
	private static final double HEIGHT = 600;
	private static final double WIDTH = 900;
	protected TextField[] tipp = new TextField[6];

	private Label lblTipp;
	private TextField zahl1;
	protected TextField[] ziehung = new TextField[6];
	protected Button btnZiehung;
	private Label lblZahlen;
	protected TextField tippGlückszahl;
	private Label lblZiehung;
	private Label lblGlückszahl;
	protected TextField ziehungGlückszahl;
	protected Button btnGewinn;
	private Label lblGewinn;

	public void setLblGewinn(Label lblGewinn) {
		this.lblGewinn = lblGewinn;
	}

	protected LottoView(Stage stage, LottoModel model) {
		this.stage = stage;
		this.model = model;

		stage.setTitle("Swiss Lotto – niemand macht mehr Millionäre");

		GridPane root = new GridPane();
		root.setPrefSize(WIDTH, HEIGHT);

		lblTipp = new Label();
		lblTipp.setText("Mache deinen Tipp:");
		root.add(lblTipp, 0, 0);

		lblZahlen = new Label();
		lblZahlen.setText("Zahlen");
		root.add(lblZahlen, 0, 1);

		lblGlückszahl = new Label();
		lblGlückszahl.setText("Glückszahl");
		root.add(lblGlückszahl, 7, 1);

		for (int i = 0; i < 6; i++) {
			tipp[i] = new TextField();
			tipp[i].setMaxWidth(40);
			root.add(tipp[i], i, 2);
		}

		tippGlückszahl = new TextField();
		root.add(tippGlückszahl, 7, 2);

		btnTipp = new Button();
		btnTipp.setText("Tipp abgeben");
		root.add(btnTipp, 0, 3);

		lblZiehung = new Label();
		lblZiehung.setText("Ziehung");
		root.add(lblZiehung, 0, 4);

		btnZiehung = new Button();
		btnZiehung.setText("Ziehung starten");
		root.add(btnZiehung, 0, 5);

		for (int i = 0; i < 6; i++) {
			ziehung[i] = new TextField();
			root.add(ziehung[i], i, 7);
		}

		lblZahlen = new Label();
		lblZahlen.setText("Zahlen");
		root.add(lblZahlen, 0, 6);

		lblGlückszahl = new Label();
		lblGlückszahl.setText("Glückszahl");
		root.add(lblGlückszahl, 7, 6);
		;

		ziehungGlückszahl = new TextField();
		root.add(ziehungGlückszahl, 7, 7);

		btnGewinn = new Button();
		btnGewinn.setText("Gewinn berechnen");
		root.add(btnGewinn, 0, 8);

		lblGewinn = new Label();
		lblGewinn.setText("???$$$$££££???");
		root.add(lblGewinn, 0, 9);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("Lotto.css").toExternalForm());
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

	public Label getLblTipp() {
		return lblTipp;
	}

	public void setLblTipp(Label lblTipp) {
		this.lblTipp = lblTipp;
	}

	public Label getLblZiehung() {
		return lblZiehung;
	}

	public void setLblZiehung(Label lblZiehung) {
		this.lblZiehung = lblZiehung;
	}

	public Label getLblGewinn() {
		return lblGewinn;
	}
}