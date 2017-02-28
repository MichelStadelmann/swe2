package bonusAssigment1;

import javafx.stage.Stage;

public class Person {
	
	private Stage primaryStage = null;
	
	
	public Person(Stage primaryStage){
		this.primaryStage = primaryStage;
		
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

}
