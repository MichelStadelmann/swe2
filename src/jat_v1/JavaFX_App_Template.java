package jat_v1;

import java.util.logging.Logger;

import jat_v1.appClasses.App_Controller;
import jat_v1.appClasses.App_Model;
import jat_v1.appClasses.App_View;
import jat_v1.splashScreen.Splash_Controller;
import jat_v1.splashScreen.Splash_Model;
import jat_v1.splashScreen.Splash_View;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class JavaFX_App_Template extends Application {
	private static JavaFX_App_Template mainProgram; //singleton
	private Splash_View splashView;
	private App_View view;
	
	
	private ServiceLocator serviceLocator;		//resources, after initialisation

	public static void main(String[] args) {
		launch(args);

	}
	
	
	//stellt sich, dass nur eine Instanz läuft im JVM, weil unsere initialisierte Ressource ein Singleton ist und
	//bei zwei Instanzen die Ressourcen überschrieben würden
	
	public void init() {
		if (mainProgram == null) {
			mainProgram = this;
		} else {
			Platform.exit();
		}
		
		
	}
	
	
	

	@Override
	public void start(Stage primaryStage) {
		// Create and display the splash screen and model
		Splash_Model splashModel = new Splash_Model();
		splashView = new Splash_View(primaryStage, splashModel);
		new Splash_Controller(this, splashModel, splashView);
		splashView.start();
		
	
		
		//Display the splash screen and beginn the intialisation
		splashModel.initialize();
		
	}
	
	
	public void startApp(){
		Stage appStage = new Stage();
		
		App_Model model = new App_Model();
		view = new App_View(appStage, model);
		new App_Controller(model, view);
		
		//Ressources are now initialized
		serviceLocator = ServiceLocator.getServiceLocator();
		
		splashView.stop();
		splashView = null;
		
		view.start();
		
		
	}
	
	public void stop(){
		if (view!=null){
			//Make the view invisible
			view.stop();
		}
	
	
		serviceLocator.getLogger().info("Application terminated");
		
	}
		
	//static getter for a reference to the main program object
		protected static JavaFX_App_Template getMainProgram(){
			return mainProgram;
		
		
		
	}

}
