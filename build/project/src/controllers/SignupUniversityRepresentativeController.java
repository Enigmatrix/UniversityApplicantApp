package controllers;

import models.University;
import models.UniversityRepresentative;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SignupUniversityRepresentativeController extends
		SignupUserController {

	@FXML
	private TextField universityNameField;

	@Override
	public String getTitle() {
		return "Signup Uni Rep";
	}

	@Override
	public void loaded() {
		super.loaded();
		UniversityRepresentative universityRepresentative = (UniversityRepresentative) parameter;
		universityRepresentative.setUniversity(new University());
		universityNameField.textProperty().bindBidirectional(
				universityRepresentative.getUniversity().getNameProperty());
		
	}

}
