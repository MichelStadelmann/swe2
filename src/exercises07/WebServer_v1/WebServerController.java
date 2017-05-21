package exercises07.WebServer_v1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class WebServerController {
	
	final private WebServerModel model;
	final private WebServerView view;

	public WebServerController(WebServerModel model, WebServerView view) {
		this.model = model;
		this.view = view;
	
	
	//register ourselves to listen for button clicks
	view.btnGo.setOnAction(new EventHandler<ActionEvent>(){
		
		public void handle(ActionEvent event){
			Integer port = new Integer(view.txtPort.getText());
			model.serveContent(port);
		}
		
	});
	
	//register ourselves to handle window-closing event
	view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>(){
		
		public void handle(WindowEvent event){
			view.stop(); //close the GUI
			Platform.exit(); // ends any JavaFX activies
			System.exit(0); //end all avtivites (our server task)
		}
	});

	}
}
	
