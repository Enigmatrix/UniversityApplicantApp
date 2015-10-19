package models;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Applicant extends User {
	private ObservableList<Qualification> qualifications;
	private ObservableList<CourseApplication> appliedCourseApplications;

	// applied univerities courses?

	public Applicant() {
		qualifications = FXCollections.observableArrayList();
		appliedCourseApplications = FXCollections.observableArrayList();
	}

	public ObservableList<Qualification> getQualifications() {
		return qualifications;
	}

	// for Jackson
	public void setQualifications(List<Qualification> qualifications) {
		this.qualifications.clear();
		this.qualifications.addAll(qualifications);
	}

	public ObservableList<CourseApplication> getAppliedCourseApplications() {
		return appliedCourseApplications;
	}

	// for jackson
	public void setAppliedCourseApplications(
			List<CourseApplication> appliedCourseApplication) {
		this.appliedCourseApplications.clear();
		this.appliedCourseApplications.addAll(appliedCourseApplication);
	}
}
