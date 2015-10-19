package controllers;

import views.Main;
import javafx.fxml.FXML;

// base class for all controllers
public abstract class ControllerBase {

	protected Main main;

	@FXML
	public void initialize() {

	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void loaded() {

	}
	
	protected Object parameter;
	
	public void setParameter(Object parameter){
		this.parameter = parameter;
	}
}
