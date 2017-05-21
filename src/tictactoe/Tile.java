package tictactoe;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
	
	protected Text text = new Text();
	
	protected Tile(){
		Rectangle border = new Rectangle(200,200);
		border.setFill(null);
		border.setStroke(Color.BLACK);
		
		text.setFont(Font.font(72));
		
		setAlignment(Pos.CENTER);
		getChildren().addAll(border, text);
	}
	
	
	public void drawX(){
		text.setText("X");
	}
	
	public void drawO(){
		text.setText("O");
	}
	
	public Text getText(Text text){
		return text;
	}
	
	public Tile getTile (Tile tile){
		return tile;
	}

}
