package tictactoemvc;

import javafx.application.Application;
import javafx.stage.Stage;

public class TicTacToe extends Application{

	
	
	public static void main(String[] args) {
		launch(args);

	}

	private Model model;
	private Controller controller;
	private View view;
	private Tile tile;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Model model = new Model();
		View view = new View(primaryStage, model, tile);
		Controller controller = new Controller(model, view);
		
		view.start();
		
	}
	
	public void stop() {
		if (view !=null)
			view.stop();
	}
	

}
