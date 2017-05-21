package exercises07.WebServer_v1;

import java.util.logging.Logger;
import java.util.logging.Level;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Sending files, rather than just echoing requests
 *
 */



public class WebServer_v1 extends Application {
	private WebServerView view;
	private WebServerController controller;
	private WebServerModel model;

	public static void main(String[] args) {
		launch(args);
		

	}
	
	/**
	 * Note the dependencies between model, view and controller. Addionally,
	 * the view needs the primaryStage created by JavaFX.
	 */
	
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Part of the GUI will contain log output from our own handler
		TextAreaHandler textAreaHandler = new TextAreaHandler();
		textAreaHandler.setLevel(Level.INFO);
		Logger defaultLogger = Logger.getLogger("");
		defaultLogger.addHandler(textAreaHandler);
		
		// Initialize the GUI
		model = new WebServerModel();
		view = new WebServerView(primaryStage, model, textAreaHandler.getTextArea());
		controller = new WebServerController(model, view);
		
		// Display the GUI after all initialization is complete
		view.start();
		
	}
	
	/**
	 * The stop method is the opposite of the start method. It provides an
	 * opportunity to close down the programm gracefully, when the program has
	 * been closed.
	 */
	
	public void stop(){
		if (view != null)
			view.stop();
		
	}

}
