package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CourseApplication extends Entity {
	private ObjectProperty<Applicant> applicantProperty;
	private ObjectProperty<Course> courseProperty;
	private BooleanProperty acceptedProperty;
	private BooleanProperty shouldBeAcceptedProperty;

	public CourseApplication() {
		applicantProperty = new SimpleObjectProperty<>();
		courseProperty = new SimpleObjectProperty<>();
		acceptedProperty = new SimpleBooleanProperty();
		shouldBeAcceptedProperty = new SimpleBooleanProperty();
	}

	public Applicant getApplicant() {
		return applicantProperty.get();
	}

	public void setApplicant(Applicant applicant) {
		applicantProperty.set(applicant);
	}

	public Course getCourse() {
		return courseProperty.get();
	}

	public void setCourse(Course course) {
		courseProperty.set(course);
	}

	public boolean getAccepted() {
		return acceptedProperty.get();
	}

	public void setAccepted(boolean acc) {
		acceptedProperty.set(acc);
	}

	public boolean getShouldBeAccepted() {
		return shouldBeAcceptedProperty.get();
	}

	public void setShouldBeAccepted(boolean val) {
		shouldBeAcceptedProperty.set(val);
	}

	@JsonIgnore
	public ObjectProperty<Applicant> getApplicantProperty() {
		return applicantProperty;
	}

	@JsonIgnore
	public ObjectProperty<Course> getCourseProperty() {
		return courseProperty;
	}

	@JsonIgnore
	public BooleanProperty getAcceptedProperty() {
		return acceptedProperty;
	}

	@JsonIgnore
	public BooleanProperty getShouldBeAcceptedProperty() {
		return shouldBeAcceptedProperty;
	}

}
