package jat_v1.splashScreen;

import jat_v1.abstractClasses.View;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Splash_View extends View<Splash_Model> {
	ProgressBar progress;
	private Label lblStatus;

	public Splash_View(Stage stage, Splash_Model model) {
		super(stage,model);
		stage.initStyle(StageStyle.TRANSPARENT); //also undecorated
		
	}
	
	@Override
	protected Scene create_GUI() {
		BorderPane root = new BorderPane();
		root.setId("splash");
		
		lblStatus = new Label("Woof");
		root.setCenter(lblStatus);
		
		progress = new ProgressBar();
		HBox bottomBox = new HBox();
		bottomBox.setId("progressionbox");
		bottomBox.getChildren().add(progress);
		root.setBottom(bottomBox);
		
		Scene scene = new Scene(root, 300, 300, Color.TRANSPARENT);
		scene.getStylesheets().addAll(
				this.getClass().getResource("splash.css").toExternalForm());
		
		
		
		return scene;
	}
	
	
	

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

	

}
