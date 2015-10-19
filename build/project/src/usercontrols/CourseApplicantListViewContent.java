package usercontrols;

import utils.Constants;
import application.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.CourseApplication;
import models.Qualification;

public class CourseApplicantListViewContent extends UserControl {

	@FXML
	private Label nameLabel;
	@FXML
	private Label qualificationLabel;
	@FXML
	private Button button;

	private CourseApplication cApplication;

	public CourseApplicantListViewContent(CourseApplication ca) {
		cApplication = ca;
		nameLabel.textProperty().bindBidirectional(
				ca.getApplicant().getUserNameProperty());
		populateQProp();
		button.setOnAction(e -> {
			cApplication.setAccepted(!cApplication.getAccepted());
		});
		cApplication.getAcceptedProperty().addListener(listener -> {
			updateButtonText();
		});
		updateButtonText();
	}

	public void updateButtonText() {
		if (cApplication.getAccepted()) {
			button.setText(Main.getInstance().getResourceBundle()
					.getString(Constants.REJECT));
			button.setStyle("-fx-background-color: red;");
		} else {
			button.setText(Main.getInstance().getResourceBundle()
					.getString(Constants.ACCEPT));
			button.setStyle("-fx-background-color: green;");
		}
	}

	public void populateQProp() {
		StringProperty qProp = new SimpleStringProperty();
		qualificationLabel.textProperty().bindBidirectional(qProp);
		int nq = getNumberOfExtraQualifications();
		if (nq < 0) {
			qProp.set(-nq + " " + Main.getInstance().getResourceBundle()
					.getString(Constants.PREREQUISITES_NOT_MET));
		} else if (nq == 0) {
			qProp.set(Main.getInstance().getResourceBundle()
					.getString(Constants.PREREQUISITES_MET_EXACTLY));
		} else {
			qProp.set(nq + " " + Main.getInstance().getResourceBundle()
					.getString(Constants.EXTRA_QUALIFICATIONS));
		}
	}

	public int getNumberOfExtraQualifications() {
		ObservableList<Qualification> qList = FXCollections
				.observableArrayList(cApplication.getApplicant()
						.getQualifications());
		qList.retainAll(cApplication.getCourse().getPreRequisites());
		if (qList.size() == cApplication.getCourse().getPreRequisites().size()) {
			// return extra q here
			return cApplication.getApplicant().getQualifications().size()
					- qList.size();
		} else {
			return qList.size()
					- cApplication.getCourse().getPreRequisites().size();
		}
	}
}
