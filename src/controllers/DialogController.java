package controllers;

import javafx.stage.Stage;

public abstract class DialogController extends ControllerBase {
	public abstract String getTitle();
	
	protected Stage dialogStage;
	
	public void setDialogStage(Stage dialogStage){
		this.dialogStage = dialogStage;
	}

	public abstract Object data();
}
