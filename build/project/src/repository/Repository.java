package repository;

import java.util.Locale;

import models.Applicant;
import models.Course;
import models.University;
import models.UniversityRepresentative;
import javafx.collections.ObservableList;

public interface Repository {
	// impt funcs
	public void loadDataRepresentation();

	public void saveCurrentDataRepresentation();

	// helper funcs
	public ObservableList<Applicant> getApplicants();

	public ObservableList<Course> getCourses();

	// private ObservableList<Entity> getEntities();
	public ObservableList<University> getUniversities();

	public ObservableList<UniversityRepresentative> getUniversityRepresentatives();
	// private ObservableList<User> getUsers();
	public void setAppLocale(Locale locale);
	public Locale getAppLocale();
}
