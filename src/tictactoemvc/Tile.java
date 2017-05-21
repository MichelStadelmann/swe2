package tictactoemvc;

import java.awt.Color;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Tile extends StackPane{
	private Text text = new Text();
	private Tile tile;
	
	public Tile(){
		Rectangle border = new Rectangle(200,200);
		border.setFill(null);
		border.setStroke(Color.BLACK);
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(border);
		
		
		
		
		
		
	}
	
	

}
