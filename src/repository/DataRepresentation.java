package repository;

import java.util.List;
import java.util.Locale;

import models.Applicant;
import models.Course;
import models.University;
import models.UniversityRepresentative;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//when we store the data into the JSON file, we serialize/deserialize
//this GIANT object. So when a person presses save, sadly he has to suffer
//the process of SERIALIZING / DESERIALIZING THIS ENTIRE OBJECT THEN SAVING IT
// (IM SO SORRY FOR THE LAGS)

//we can try to use multithreading here and get a Future, so that we can know when
//it has finished, but for simplicity im not doing that
public class DataRepresentation {
	private ObservableList<Applicant> applicants = FXCollections
			.observableArrayList();
	private ObservableList<Course> courses = FXCollections
			.observableArrayList();
	private ObservableList<University> universities = FXCollections
			.observableArrayList();
	private ObservableList<UniversityRepresentative> universityRepresentatives = FXCollections
			.observableArrayList();
	private Locale appLocale;
	
	public ObservableList<UniversityRepresentative> getUniversityRepresentatives() {
		return universityRepresentatives;
	}

	public void setUniversityRepresentatives(List<UniversityRepresentative> urs) {
		universityRepresentatives.clear();
		universityRepresentatives.addAll(urs);
	}

	public ObservableList<University> getUniversities() {
		return universities;
	}

	public void setUniversities(List<University> us) {
		universities.clear();
		universities.addAll(us);
	}

	public ObservableList<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> cs) {
		courses.clear();
		courses.addAll(cs);
	}

	public ObservableList<Applicant> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<Applicant> as) {
		applicants.clear();
		applicants.addAll(as);
	}

	public Locale getAppLocale() {
		return appLocale;
	}

	public void setAppLocale(Locale appLocale) {
		this.appLocale = appLocale;
	}
}
