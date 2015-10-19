package controllers;

import java.io.File;

import application.Main;

import com.guigarage.sdk.util.RoundImageView;

import models.Course;
import models.CourseApplication;
import models.UniversityRepresentative;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.AuthManager;
import usercontrols.CourseApplicantListViewCell;
import usercontrols.EditableCourseListViewCell;
import usercontrols.EditableTextControl;
import utils.Constants;
import utils.JavaFXImageConversion;

public class UniversityRepresentativeController extends ControllerBase {

	@FXML
	private EditableTextControl nameField;
	@FXML
	private EditableTextControl emailAddressField;
	@FXML
	private EditableTextControl phoneNumberField;
	@FXML
	private Button universityLink;
	@FXML
	private ListView<Course> coursesView;
	@FXML
	private TextField searchCourseField;
	@FXML
	private Pane noDataToShowPane;
	@FXML
	private Pane courseApplicationsPane;
	@FXML
	private ListView<CourseApplication> courseApplicationsView;
	@FXML
	private RoundImageView uniRepImage;

	private UniversityRepresentative universityRepresentative;

	@Override
	public void loaded() {
		universityRepresentative = (UniversityRepresentative) AuthManager
				.getLoggedInUser();

		nameField.textProperty().bindBidirectional(
				universityRepresentative.getUserNameProperty());
		emailAddressField.textProperty().bindBidirectional(
				universityRepresentative.getEmailAddressProperty());
		phoneNumberField.textProperty().bindBidirectional(
				universityRepresentative.getPhoneNumberProperty());
		universityLink.textProperty().bindBidirectional(
				universityRepresentative.getUniversity().getNameProperty());
		universityLink.setOnAction(e -> {
			main.navigateToDialog(Constants.UniversityEditViewPath,
					universityRepresentative.getUniversity());
		});

		FilteredList<Course> filteredList = universityRepresentative
				.getUniversity().getCourses().filtered(course -> true);
		coursesView.setItems(filteredList);

		coursesView.setCellFactory(val -> new EditableCourseListViewCell());

		searchCourseField.textProperty().addListener(
				(prop, oldVal, newVal) -> {
					filteredList.setPredicate(course -> {
						return course.getName().toLowerCase()
								.contains(newVal.toLowerCase());
					});
				});

		coursesView.getSelectionModel().selectedItemProperty()
				.addListener((prop, oldVal, newVal) -> {
					showCourseApplicationsData(newVal);
				});
		showCourseApplicationsData(null);
		
		universityRepresentative.getImageBytesProperty().addListener(listener -> {
			updateImage();
		});
		updateImage();
		
		uniRepImage.setOnMouseClicked(e -> {
			//let user change image
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Image File");                
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
                );
			File f = fileChooser.showOpenDialog(main.getPrimaryStage());
			Byte[] data = JavaFXImageConversion.fileToBytes(f);
			if(data != null)
				universityRepresentative.setImageBytes(data);
		});
	}

	public void updateImage() {
		Byte[] bytes = universityRepresentative.getImageBytes();
		if (bytes != null) {
			Image newImg = JavaFXImageConversion
					.getJavaFXImage(JavaFXImageConversion
							.toPrimitiveByteArray(bytes));
			uniRepImage.setImage(newImg);
		}else{
			//set a default image
			uniRepImage.setImage(new Image(Main.class.getResourceAsStream("universityicon.png")));
		}
	}

	private void showCourseApplicationsData(Course course) {
		FadeTransition noDataToShowPaneTransition = new FadeTransition();
		noDataToShowPaneTransition.setNode(noDataToShowPane);
		FadeTransition courseApplicationsPaneTransition = new FadeTransition();
		courseApplicationsPaneTransition.setNode(courseApplicationsPane);
		ParallelTransition transition = new ParallelTransition(
				noDataToShowPaneTransition, courseApplicationsPaneTransition);
		if (course == null) {
			noDataToShowPaneTransition.setToValue(1.0f);
			courseApplicationsPaneTransition.setToValue(0.0f);
			transition.play();
			// TEST STUFF HERE

			// Image img = new
			// Image(main.getClass().getResourceAsStream("university.jpg"));
			// byte[] dat = JavaFXImageConversion.getBytesFromJavaFXImage(img);
			// universityRepresentative.setImageBytes(JavaFXImageConversion.toByteArray(dat));

			return;
		}
		noDataToShowPaneTransition.setToValue(0.0f);
		courseApplicationsPaneTransition.setToValue(1.0f);

		// TODO attach course data stuff here
		courseApplicationsView.setItems(course.getCourseApplications());
		courseApplicationsView
				.setCellFactory(val -> new CourseApplicantListViewCell());

		transition.play();
	}

	@FXML
	public void addCourseClicked(ActionEvent e) {
		Course newCourse = new Course();
		newCourse.setName("Course Name");
		newCourse.setUniversity(universityRepresentative.getUniversity());
		universityRepresentative.getUniversity().getCourses().add(newCourse);
		main.getRepositoryStore().getCourses().add(newCourse);
	}

	@FXML
	public void removeCourseClicked(ActionEvent e) {
		Course courseToRemove = coursesView.getSelectionModel()
				.getSelectedItem();

		// cascade delete
		courseToRemove.getCourseApplications().forEach(ca -> {
			ca.getApplicant().getAppliedCourseApplications().remove(ca);
		});
		courseToRemove.getCourseApplications().clear();
		universityRepresentative.getUniversity().getCourses()
				.remove(courseToRemove);

		main.getRepositoryStore().getCourses().remove(courseToRemove);
	}

	@FXML
	public void saveClicked(ActionEvent e) {
		main.getRepositoryStore().saveCurrentDataRepresentation();
	}

	@FXML
	public void logoutClicked(ActionEvent e) {
		AuthManager.logout();
	}
}
