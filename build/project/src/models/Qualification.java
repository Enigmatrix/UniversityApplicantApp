package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Qualification extends Entity {
	private StringProperty nameProperty;
	private StringProperty fieldProperty;

	public Qualification() {
		nameProperty = new SimpleStringProperty();
		fieldProperty = new SimpleStringProperty();
	}

	public String getName() {
		return nameProperty.get();
	}

	public void setName(String name) {
		nameProperty.set(name);
	}

	public String getField() {
		return fieldProperty.get();
	}

	public void setField(String field) {
		fieldProperty.set(field);
	}

	@JsonIgnore
	public StringProperty getNameProperty() {
		return nameProperty;
	}

	@JsonIgnore
	public StringProperty getFieldProperty() {
		return fieldProperty;
	}
	
	@Override
	public boolean equals(Object other){
		Qualification qualification = (Qualification)other;
		return other != null && qualification.getName().equals(getName()) && qualification.getField().equals(getField());
	}
}
