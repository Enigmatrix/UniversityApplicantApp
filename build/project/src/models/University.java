package models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class University extends Entity {
	private StringProperty nameProperty;
	private StringProperty locationProperty;
	private IntegerProperty rankingProperty;
	private ObservableList<Course> courses;

	public University() {
		nameProperty = new SimpleStringProperty();
		locationProperty = new SimpleStringProperty();
		rankingProperty = new SimpleIntegerProperty();
		courses = FXCollections.observableArrayList();
	}

	public String getName() {
		return nameProperty.get();
	}

	public void setName(String name) {
		nameProperty.set(name);
	}

	public String getLocation() {
		return locationProperty.get();
	}

	public void setLocation(String loc) {
		locationProperty.set(loc);
	}

	public int getRanking() {
		return rankingProperty.get();
	}

	public void setRanking(int ranking) {
		rankingProperty.set(ranking);
	}

	public ObservableList<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses.clear();
		this.courses.addAll(courses);
	}

	@JsonIgnore
	public StringProperty getNameProperty() {
		return nameProperty;
	}

	@JsonIgnore
	public StringProperty getLocationProperty() {
		return locationProperty;
	}

	@JsonIgnore
	public IntegerProperty getRankingProperty() {
		return rankingProperty;
	}
}
