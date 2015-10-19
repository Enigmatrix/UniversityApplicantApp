package services;

import java.util.function.Predicate;

import repository.EntityRepository;
import repository.Repository;
import utils.Constants;
import views.Main;
import models.Applicant;
import models.UniversityRepresentative;
import models.User;

public class AuthManager {

	private static Repository repository = EntityRepository.getInstance();

	private static Predicate<Applicant> getApplicantMatchPredicate(
			String userName, String password) {
		return applicant -> applicant.getUserName().equals(userName)
				&& applicant.getUserPassword().equals(password);
	}

	private static Predicate<Applicant> getApplicantMatchPredicate(
			String userName) {
		return applicant -> applicant.getUserName().equals(userName);
	}

	private static Predicate<UniversityRepresentative> getUniversityRepresentativeMatchPredicate(
			String userName, String password) {
		return uniRep -> uniRep.getUserName().equals(userName)
				&& uniRep.getUserPassword().equals(password);
	}

	private static Predicate<UniversityRepresentative> getUniversityRepresentativeMatchPredicate(
			String userName) {
		return uniRep -> uniRep.getUserName().equals(userName);
	}

	public static Applicant authenticateApplicant(String userName,
			String password) {
		Applicant applicant = repository.getApplicants().stream()
				.filter(getApplicantMatchPredicate(userName, password))
				.findAny().orElse(null);
		currentUser = applicant;
		return applicant;
	}

	public static boolean applicantExists(String userName) {
		return repository.getApplicants().stream()
				.anyMatch(getApplicantMatchPredicate(userName));
	}

	public static boolean signUpApplicant(Applicant applicant) {
		if (applicantExists(applicant.getUserName()))
			return false;
		repository.getApplicants().add(applicant);
		return true;
	}

	private static User currentUser;

	public static User getLoggedInUser() {
		return currentUser;
	}
	public static void setLoggedInUser(User user) {
		currentUser = user;
	}

	public static void logout() {
		currentUser = null;
		Main.getInstance().navigate(Constants.LoginViewPath);
	}

	public static boolean universityRepresentativeExists(String userName) {
		return repository.getUniversityRepresentatives().stream()
				.anyMatch(getUniversityRepresentativeMatchPredicate(userName));
	}

	public static UniversityRepresentative authenticateUniversityRepresentative(
			String userName, String password) {
		UniversityRepresentative uniRep = repository
				.getUniversityRepresentatives()
				.stream()
				.filter(getUniversityRepresentativeMatchPredicate(userName,
						password)).findAny().orElse(null);
		currentUser = uniRep;
		return uniRep;
	}

	public static boolean signUpUniversityRepresentative(
			UniversityRepresentative uniRep) {
		if (universityRepresentativeExists(uniRep.getUserName()))
			return false;
		repository.getUniversityRepresentatives().add(uniRep);
		return true;
	}
}
