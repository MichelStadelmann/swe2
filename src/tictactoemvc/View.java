package tictactoemvc;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class View {

	private Stage stage;
	private Model model;
	private Tile tile;
	

	protected View(Stage primaryStage, Model model, Tile tile) {
		this.stage = stage;
		this.model = model;
		this.tile = tile;
		
		
		
		Pane root = new Pane();
		root.setPrefSize(600, 600);
		
		for (int i = 0; i < 3; i++){
			for(int j =0; j <3; j++){
				Tile tileBoard = new Tile();
				tileBoard.setTranslateX(j*200);
				tileBoard.setTranslateY(i*200);
				
				root.getChildren().add(tile);
			}
		}
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Tic Tac Toe");
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
