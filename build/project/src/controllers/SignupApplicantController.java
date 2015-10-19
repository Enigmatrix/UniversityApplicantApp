package controllers;


public class SignupApplicantController extends SignupUserController{

	
	@Override
	public String getTitle() {
		return "Signup Applicant";
	}
	
	@Override
	public void loaded(){
		super.loaded();
	}

}
