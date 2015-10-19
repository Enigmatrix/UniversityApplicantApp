package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class UniversityRepresentative extends User {
	private ObjectProperty<University> universityProperty;

	public UniversityRepresentative() {
		universityProperty = new SimpleObjectProperty<University>();
	}

	public University getUniversity() {
		return universityProperty.get();
	}

	public void setUniversity(University uni) {
		universityProperty.set(uni);
	}

	@JsonIgnore
	public ObjectProperty<University> getUniversityProperty() {
		return universityProperty;
	}
}
