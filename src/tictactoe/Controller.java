package tictactoe;

import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;

public class Controller {

	private Client client;
	private View view;
	private Tile tile;
	

	public Controller(Client client, View view) {
		this.client = client;
		this.view = view;
	}
	
	Text text = new Text(tile.text.getText()); 
	view.tile.setOnMouseClicked(event ->{
		if(event.getButton() == MouseButton.PRIMARY){
			text.setText("X");
		}
	}
	

}
