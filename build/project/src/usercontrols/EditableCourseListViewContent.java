package usercontrols;

import utils.Constants;
import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Course;
import models.Qualification;

public class EditableCourseListViewContent extends UserControl {
	@FXML
	private Label nameLabel;
	@FXML
	private Label preRequisiteLabel;

	private Course course;

	public EditableCourseListViewContent(Course content) {
		course = content;
		nameLabel.textProperty().bindBidirectional(content.getNameProperty());
		StringProperty preReqWord = new SimpleStringProperty();
		content.getPreRequisites().addListener(
				(ListChangeListener.Change<? extends Qualification> l) -> {
					updatePreRequisiteProperty(preReqWord, content);
				});
		updatePreRequisiteProperty(preReqWord, content);

		preRequisiteLabel.textProperty().bindBidirectional(preReqWord);
	}

	public void updatePreRequisiteProperty(StringProperty property,
			Course course) {
		if (course.getPreRequisites().size() == 0) {
			property.set(Main.getInstance().getResourceBundle()
					.getString(Constants.NO_PREREQUISITES));
		} else if (course.getPreRequisites().size() == 1) {
			property.set("1 "
					+ Main.getInstance().getResourceBundle()
							.getString(Constants.PREREQUISITE));
		} else {
			property.set(Integer.toString(course.getPreRequisites().size())
					+ " "
					+ Main.getInstance().getResourceBundle()
							.getString(Constants.PREREQUISITES));
		}
	}

	@FXML
	public void editClicked(ActionEvent e) {
		// TODO CHANGE THIS SHIT
		Main.getInstance().navigateToDialog(
				Constants.EditableCourseDataViewPath, course);
	}
}
