package network;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application {

	private ClientModel model;
	private ClienView view;
	private ClientController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		//initialize the GUI
		model = new ClientModel();
		view = new ClienView(primaryStage,model);
		controller = new ClientController(model, view);
		
		view.start();
		
	}
	
	public static void main (String[] args){
		launch(args);
		
	}
	
	public void stop(){
		if (view !=null)
			view.stop();
	}

}
