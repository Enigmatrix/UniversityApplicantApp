package models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Course extends Entity {
	private StringProperty nameProperty;
	private ObjectProperty<University> universityProperty;
	private ObservableList<Qualification> preRequisites;
	private ObservableList<CourseApplication> courseApplications;

	public Course() {
		nameProperty = new SimpleStringProperty();
		preRequisites = FXCollections.observableArrayList();
		courseApplications = FXCollections.observableArrayList();
		universityProperty = new SimpleObjectProperty();
	}

	@JsonIgnore
	public StringProperty getNameProperty() {
		return nameProperty;
	}

	public String getName() {
		return nameProperty.get();
	}

	public void setName(String name) {
		nameProperty.set(name);
	}
	
	public University getUniversity(){
		return universityProperty.get();
	}
	
	public void setUniversity(University uni){
		universityProperty.set(uni);
	}

	public ObservableList<Qualification> getPreRequisites() {
		return preRequisites;
	}

	// for Jackson
	public void setPreRequisites(List<Qualification> preRequisites) {
		this.preRequisites.clear();
		this.preRequisites.addAll(preRequisites);
	}

	public ObservableList<CourseApplication> getCourseApplications() {
		return courseApplications;
	}

	// for Jackson
	public void setCourseApplications(List<CourseApplication> courseApplications) {
		this.courseApplications.clear();
		this.courseApplications.addAll(courseApplications);
	}

	@JsonIgnore
	public ObjectProperty<University> getUniversityProperty() {
		return universityProperty;
	}
}
