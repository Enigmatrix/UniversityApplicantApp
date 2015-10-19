package controllers;

import models.Course;
import models.Qualification;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class CourseDataController extends DialogController {

	@FXML
	public Text universityField;
	@FXML
	public Text courseNameField;
	@FXML
	private TableView<Qualification> preRequisitesTable;
	@FXML
	private TableColumn<Qualification, String> preRequisiteNameColumn;
	@FXML
	private TableColumn<Qualification, String> preRequisiteFieldColumn;
	
	@Override
	public String getTitle() {
		return "Course Data";
	}

	@Override
	public Object data() {
		return parameter;
	}
	
	@Override
	public void loaded(){
		Course course = (Course) parameter;
		courseNameField.textProperty().bindBidirectional(course.getNameProperty());
		universityField.textProperty().bindBidirectional(course.getUniversity().getNameProperty());

		preRequisitesTable.setItems(course.getPreRequisites());
		preRequisiteNameColumn.setCellValueFactory((q) -> q.getValue()
				.getNameProperty());
		preRequisiteFieldColumn.setCellValueFactory((q) -> q.getValue()
				.getFieldProperty());
	}
}
