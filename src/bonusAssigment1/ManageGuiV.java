package bonusAssigment1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageGuiV {
	
	//Variablen gekapselt
	
	private Scene scene;
	
	private Label nameLB = new Label();
	private TextField name = new TextField();
	
	private Label genderLB = new Label();
	 
	//Combo Box with an observable list http://docs.oracle.com/javafx/2/ui_controls/combo-box.htm 
	 ObservableList<String> options = 
			 FXCollections.observableArrayList(
			 "male",
	 		 "female"
	 		 
	 		 );
	 
	final ComboBox gender = new ComboBox(options);
	
	
	private Label motherLB = new Label();
	private TextField mother = new TextField();
	private Label fatherLB = new Label();
	private TextField father = new TextField();
	
	private Button create = new Button("create");
	private Button read = new Button("read");
	private Button update = new Button("update");
	private Button delete = new Button("delete");


	public ManageGuiV(){
		
		//Layout
		VBox vbox = new VBox();
		
		//Überschrift http://blog.axxg.de/model-view-controller-mit-javafx/
		
		//Name
		nameLB = new Label("Name");
		vbox.getChildren().add(nameLB);
		name = new TextField();
		vbox.getChildren().add(name);
		
		 Scene scene = new Scene(vbox,500,300);
		
		
	}
			  
	
	public void show(Stage stage){
		stage.setTitle("Test");
		stage.setScene(scene);
		stage.show();
		
	
	}

}
