package jat_v1.appClasses;

import jat_v1.ServiceLocator;
import jat_v1.abstractClasses.View;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App_View extends View<App_Model> {
	ServiceLocator serviceLocator;
	Button btnClick;
	Label lblNumber;

	public App_View(Stage stage, App_Model model) {
		super(stage, model);
		stage.setTitle("JavaFX Application Template");
		
		serviceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application view initialized");
		
	}
	
	protected Scene create_GUI(){
		
		GridPane root = new GridPane();
		lblNumber = new Label();
		lblNumber.setText(Integer.toString(model.getValue()));
		lblNumber.setMinWidth(200);
		lblNumber.setAlignment(Pos.BASELINE_CENTER);
		root.add(lblNumber,0,0);
		
		btnClick = new Button();
		btnClick.setText("Click Me!");
		btnClick.setMinWidth(200);
		root.add(btnClick, 0,1);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(
				getClass().getResource("app.css").toExternalForm());
		
		return scene;
		
		
		
	}

	


}
