package controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;

public abstract class SignupUserController extends DialogController {

	@FXML
	private TextField nameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField emailAddrField;
	@FXML
	private TextField phoneNumberField;
	@FXML
	private Button okButton;
	@FXML
	private Button cancelButton;

	
	protected boolean ok = false;

	@Override
	public Object data() {
		return ok ? parameter : null;
	}
	
	@Override
	public void loaded(){
		User user = (User)parameter;
		nameField.textProperty().bindBidirectional(user.getUserNameProperty());
		passwordField.textProperty().bindBidirectional(user.getUserPasswordProperty());
		emailAddrField.textProperty().bindBidirectional(user.getEmailAddressProperty());
		phoneNumberField.textProperty().bindBidirectional(user.getPhoneNumberProperty());
		okButton.setOnAction(e -> {
			ok = true;
			dialogStage.close();
		});
		cancelButton.setOnAction(e -> {
			ok = false;
			dialogStage.close();
		});
	}
	
}
