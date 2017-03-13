package jat_v1.splashScreen;

import jat_v1.JavaFX_App_Template;
import jat_v1.abstractClasses.Controller;
import javafx.concurrent.Worker;

public class Splash_Controller extends Controller<Splash_Model, Splash_View> {

	public Splash_Controller(JavaFX_App_Template main, Splash_Model model,
			Splash_View view) {
		super(model,view);
	
		view.progress.progressProperty().bind(model.initializer.progressProperty());
		
		
		model.initializer.stateProperty().addListener(
				(overvable, oldValue, newValue) -> {
					if (newValue == Worker.State.SUCCEEDED)
						main.startApp();
				});
		
		
		
	}

}
