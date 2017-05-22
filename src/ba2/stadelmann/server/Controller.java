package ba2.stadelmann.server;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class Controller {

	final private Model model;
	final private View view;

	protected Controller(Model model, View view) {
		this.model = model;
		this.view = view;

		// register ourselves to listen for button clicks
		view.btnGo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

			}
		});

		// register ourselves to handle window-closing event
		view.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				view.stop(); // closes the GUI
				Platform.exit(); // ends any JavaFX activities
				System.exit(0); // end all activities (our server task) - not
								// good code
			}
		});
	}
}
