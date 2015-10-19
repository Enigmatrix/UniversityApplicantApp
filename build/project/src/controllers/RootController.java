package controllers;

import services.AuthManager;
import utils.Constants;
import javafx.fxml.FXML;

public class RootController extends ControllerBase {

	@Override
	@FXML
	public void loaded() {
		// navigate to LoginView
		//main.navigateToDialog(Constants.UniversityEditViewPath, main.getRepositoryStore().getUniversities().get(0));
		main.navigate(Constants.LoginViewPath);
		
		/*
		AuthManager.setLoggedInUser(main.getRepositoryStore().getApplicants().get(0));
		main.navigate(Constants.ApplicantViewPath);
		*/
	}
}
