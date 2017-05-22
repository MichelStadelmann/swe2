package ba2.stadelmann.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class Controller {
	
	
	//final; why protected
	final private Model model;
	final private View view;
	
	protected Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		
		// register ourselves to listen for button clicks
		view.btnClick.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
		        model.incrementValue();
		        String newText = Integer.toString(model.getValue());
		        view.lblNumber.setText(newText);        
			}
		});

		// register ourselves to handle window-closing event
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				view.stop();
				Platform.exit();
			}
		});
	}
}
