package tictactoe;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tictactoemvc.Tile;

public class View {

	
	private Stage stage;
	private Client client;
	private Tile[][] board = new Tile[3][3];
	private Tile tile;
	

	protected View(Stage stage, Client client, Tile tile) {
		this.stage = stage;
		this.client = client;
		this.tile = tile;
		
		stage.setTitle("Tic Tac Toe");
		
		stage.setScene(new Scene(createContent()));
		
	}
	
	private Parent createContent() {
		Pane root = new Pane();
		root.setPrefSize(600, 600);
		
		for (int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				
			
			Tile tile = new Tile();
			tile.setTranslateX(j*200);
			tile.setTranslateY(i*200);
			
			root.getChildren().add(tile);
			
			board[j][i] = tile;
			}
		}
		
		return root;
	}
	

	public void start() {
		stage.show();
		
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
