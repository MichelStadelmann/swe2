package ba2.stadelmann.lottonschein;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class LottoscheinController {

	final private LottoscheinModel model;
	final private LottoscheinView view;

	protected LottoscheinController(LottoscheinModel model, LottoscheinView view) {
		this.model = model;
		this.view = view;

		// register ourselves to listen for button clicks
		view.btnClick.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// model.incrementValue();
				// String newText = Integer.toString(model.getValue());
				// view.lblNumber.setText(newText);
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
