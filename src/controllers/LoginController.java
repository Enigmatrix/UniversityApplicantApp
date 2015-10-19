package controllers;

import com.guigarage.sdk.util.RoundImageView;

import models.Applicant;
import models.UniversityRepresentative;
import services.AuthManager;
import utils.Constants;
import views.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LoginController extends ControllerBase {
	
	private final String APPLICANT = "Applicant";
	private final String UNI_REP = "University_Representative";

	@FXML
	public PasswordField userPasswordField;
	@FXML
	public TextField userNameField;
	@FXML
	public Button loginButton;
	@FXML
	public Button signUpButton;
	@FXML
	public Button switchButton;
	@FXML
	public ImageView backgroundImage;
	@FXML
	public AnchorPane pane;
	@FXML
	public RoundImageView img;
	@FXML
	public Label typeLabel;
	@FXML
	public Button languageOptionsButton;

	private String type = APPLICANT;
	private StringProperty otherType = new SimpleStringProperty(
			UNI_REP);

	private void swapType() {
		String temp = type;
		type = otherType.get();
		otherType.set(temp);
	}

	@Override
	public void loaded() {
		// Login was pressed
		loginButton.setOnAction(e -> {
			processLogin();
		});
		otherType
				.addListener((prop, oldVal, newVal) -> {
					if (type.equals(UNI_REP)) {
						switchViewToUniversityRepresentativeMode();
					} else {
						switchViewToApplicantMode();
					}
				});
		
		backgroundImage.fitHeightProperty().bind(pane.heightProperty());
		backgroundImage.fitWidthProperty().bind(pane.widthProperty());
		
		switchButton.setOnAction(e -> {
			swapType();
		});

		signUpButton.setOnAction(e -> {
			processSignup();
		});
		
		languageOptionsButton.setOnAction(e -> {
			main.navigateToDialog(Constants.LangaugeOptionsView);
		});
		
		setLanguageConstants();
		switchViewToApplicantMode();
	}
	
	private void setLanguageConstants() {
		userPasswordField.setPromptText(main.getResourceBundle().getString(Constants.PASSWORD));
		loginButton.setText(main.getResourceBundle().getString(Constants.LOGIN));
		signUpButton.setText(main.getResourceBundle().getString(Constants.SIGNUP));
	}

	public void switchViewToApplicantMode(){
		switchButton.setText(main.getResourceBundle().getString(Constants.SWITCH_TO_UNI_REP));
		userNameField.setPromptText(main.getResourceBundle().getString(Constants.APPLICANT_USER_NAME));
		backgroundImage.setImage(new Image(Main.class
				.getResourceAsStream(Constants.ApplicantPicturePath)));
		img.setImage(new Image(
				Main.class
				.getResourceAsStream(Constants.ApplicantIconPicturePath)));
		typeLabel.setText(main.getResourceBundle().getString(APPLICANT));
	}

	public void switchViewToUniversityRepresentativeMode(){
		switchButton.setText(main.getResourceBundle().getString(Constants.SWITCH_TO_APPLICANT));
		userNameField.setPromptText(main.getResourceBundle().getString(Constants.UNI_REP_USER_NAME));
		backgroundImage.setImage(new Image(
				Main.class
						.getResourceAsStream(Constants.UniversityPicturePath)));
		img.setImage(new Image(
				Main.class
				.getResourceAsStream(Constants.UniversityIconPicturePath)));
		typeLabel.setText(main.getResourceBundle().getString(UNI_REP));
	}
	
	public void processSignup() {
		if (type.equals(APPLICANT)) {
			processApplicantSignup();
		} else if (type.equals(UNI_REP)) {
			processUniversityRepresentativeSignup();
		}
	}

	private void processUniversityRepresentativeSignup() {
		UniversityRepresentative uniRep = (UniversityRepresentative) main
				.navigateToDialog(
						Constants.SignupUniversityRepresentativeViewPath,
						new UniversityRepresentative());
		if (uniRep != null)
			AuthManager.signUpUniversityRepresentative(uniRep);
	}

	private void processApplicantSignup() {
		Applicant applicant = (Applicant) main.navigateToDialog(
				Constants.SignupApplicantViewPath, new Applicant());
		if (applicant != null)
			AuthManager.signUpApplicant(applicant);
	}

	private String userName;
	private String password;

	public void processLogin() {
		userName = userNameField.getText();
		password = userPasswordField.getText();
		if (type.equals(APPLICANT)) {
			processStudentLogin();
		} else if (type.equals(UNI_REP)) {
			processUniversityRepresentativeLogin();
		}
	}

	public void processStudentLogin() {
		Applicant result = AuthManager
				.authenticateApplicant(userName, password);
		if (result == null) {
			// failure

			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Invalid user name or password");
			alert.show();
		} else {
			// success
			main.navigate(Constants.ApplicantViewPath);
		}
	}

	public void processUniversityRepresentativeLogin() {
		UniversityRepresentative result = AuthManager
				.authenticateUniversityRepresentative(userName, password);
		if (result == null) {
			// failure
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Invalid user name or password");
			alert.show();
		} else {
			// success
			main.navigate(Constants.UniversityRepresentativeViewPath);
		}
	}
}
