package usercontrols;

import utils.Constants;
import views.Main;
import models.Course;
import models.Qualification;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CourseListViewContent extends UserControl {

	@FXML
	private Label nameLabel;
	@FXML
	private Label universityLabel;
	@FXML
	private Label preRequisiteCountLabel;
	@FXML
	private Label preRequisiteLabel;
	
	private Course course;

	public CourseListViewContent(Course content) {
		course = content;
		nameLabel.textProperty().bindBidirectional(content.getNameProperty());
		universityLabel.textProperty().bindBidirectional(
				content.getUniversity().getNameProperty());
		StringProperty preReqCount = new SimpleStringProperty();
		content.getPreRequisites().addListener(
				(ListChangeListener.Change<? extends Qualification> l) -> {
					updatePreReq(preReqCount);
				});
		preRequisiteLabel.textProperty().bindBidirectional(preReqCount);
		updatePreReq(preReqCount);
	}
	
	public void updatePreReq(StringProperty preReqCount){
		if (course.getPreRequisites().size() == 0)
			preReqCount.set(Main.getInstance().getResourceBundle()
					.getString(Constants.NO_PREREQUISITES));
		else
			preReqCount.set(Integer.toString(course
					.getPreRequisites().size()) + " " + Main.getInstance().getResourceBundle()
							.getString(Constants.PREREQUISITES));
	}

	@FXML
	public void initialize() {

	}

}
