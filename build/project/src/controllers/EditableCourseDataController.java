package controllers;

import usercontrols.EditableTextControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import models.Course;
import models.Qualification;

public class EditableCourseDataController extends DialogController{
	@FXML
	public EditableTextControl courseNameField;
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
	
	private Course course;
	
	@Override
	public void loaded(){
		course = (Course) parameter;
		courseNameField.textProperty().bindBidirectional(course.getNameProperty());

		preRequisitesTable.setItems(course.getPreRequisites());
		preRequisitesTable.setEditable(true);
		preRequisiteNameColumn.setCellValueFactory((q) -> q.getValue()
				.getNameProperty());
		preRequisiteFieldColumn.setCellValueFactory((q) -> q.getValue()
				.getFieldProperty());
		preRequisiteNameColumn.setCellFactory(TextFieldTableCell
				.forTableColumn());
		preRequisiteFieldColumn.setCellFactory(TextFieldTableCell
				.forTableColumn());
		preRequisiteFieldColumn.setOnEditCommit((t) -> {
			t.getRowValue().setName(t.getNewValue());
		});
		preRequisiteFieldColumn.setOnEditCommit((t) -> {
			t.getRowValue().setField(t.getNewValue());
		});
	}
	
	@FXML
	public void addPreReqClicked(ActionEvent e){
		Qualification q = new Qualification();
		q.setName("QName");
		q.setField("QF");
		course.getPreRequisites().add(q);
	}
	
	@FXML
	public void removePreReqClicked(ActionEvent e){
		course.getPreRequisites().removeAll(preRequisitesTable.getSelectionModel().getSelectedItems());
	}
}
