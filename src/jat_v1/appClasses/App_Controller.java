package jat_v1.appClasses;

import jat_v1.ServiceLocator;
import jat_v1.abstractClasses.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class App_Controller extends Controller<App_Model, App_View> {
	ServiceLocator serviceLocator;

	public App_Controller(App_Model model, App_View view) {
		super(model, view);
		
		//register ourselves to listen for button clicks
		view.btnClick.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				buttonClick();
				
			}
			
			
		});
		
		//register ourselveles to listen for window-closing event
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>(){
			
			public void handle(WindowEvent event){
				Platform.exit();
			}
		});
		
		serviceLocator = ServiceLocator.getServiceLocator();
		serviceLocator.getLogger().info("Application controller intialized");
		
		
	}
	
	
	private void buttonClick() {
		model.incrementValue();
		String newText = Integer.toString(model.getValue());
		
		view.lblNumber.setText(newText);
		
	}
	
	
	

}
