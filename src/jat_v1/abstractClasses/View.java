package jat_v1.abstractClasses;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class View<M extends Model> {
	protected Stage stage;
	protected Scene scene;
	protected M model;
	
	
	protected View(Stage stage, M model){
		this.stage = stage;
		this.model = model;
		
		scene = create_GUI();
		stage.setScene(scene);
			
	}


	protected abstract Scene create_GUI();
	
	public void start(){
		stage.show();
	}
	
	public void stop(){
		stage.hide();
	}
	
	public Stage getStage(){
		return stage;
	}

}
