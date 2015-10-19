package controllers;

import usercontrols.EditableTextControl;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Course;
import models.University;

public class UniversityEditController extends DialogController {

	@FXML
	public EditableTextControl nameField;
	@FXML
	public EditableTextControl locationField;
	@FXML
	public TableView<Course> courses;
	@FXML
	public TableColumn<Course, String> courseNameColumn;
	
	@Override
	public String getTitle() {
		return "Edit University";
	}

	@Override
	public Object data() {
		return parameter;
	}
	
	@Override
	public void loaded(){
		University university = (University)parameter;
		nameField.textProperty().bindBidirectional(university.getNameProperty());
		locationField.textProperty().bindBidirectional(university.getLocationProperty());
		courses.setItems(university.getCourses());
		courseNameColumn.setCellValueFactory(val -> val.getValue().getNameProperty());
		courses.setOnMouseClicked(val -> {
			if(val.getClickCount() == 2){
				//TODO navigate
				
			}
		});
	}
}
