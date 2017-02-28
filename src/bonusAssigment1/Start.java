package bonusAssigment1;

import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application  {

	public static void main(String[] args) {
		
	
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Person person = new Person(primaryStage);
		
		ManageGuiC manageGuiC = new ManageGuiC(person);
		manageGuiC.show();
		
	}

}
