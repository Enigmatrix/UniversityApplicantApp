package controllers;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import com.guigarage.sdk.util.RoundImageView;

import services.AuthManager;
import models.Applicant;
import models.Course;
import models.CourseApplication;
import models.Qualification;
import usercontrols.EditableTextControl;
import utils.Constants;
import utils.JavaFXImageConversion;
import views.Main;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

public class ApplicantController extends ControllerBase {
	@FXML
	private EditableTextControl nameField;
	@FXML
	private EditableTextControl emailAddressField;
	@FXML
	private EditableTextControl phoneNumberField;
	@FXML
	private TableView<Qualification> qualificationsTable;
	@FXML
	private TableColumn<Qualification, String> qualificationNameColumn;
	@FXML
	private TableColumn<Qualification, String> qualificationFieldColumn;
	@FXML
	private TableView<CourseApplication> coursesTableView;
	@FXML
	private TableColumn<CourseApplication, String> nameColumn;
	@FXML
	private TableColumn<CourseApplication, String> universityColumn;
	@FXML
	private TableColumn<CourseApplication, Number> preRequisiteCountColumn;
	@FXML
	private TableColumn<CourseApplication, Boolean> acceptedColumn;
	@FXML
	private RoundImageView applicantImage;

	private Applicant applicant;

	@Override
	public void loaded() {
		// TODO remove later
		/*
		 * applicant = new Applicant();
		 * applicant.setUserName("Chandrasekaran Akash");
		 * applicant.setEmailAddress("enigmatrix2000@gmail.com");
		 * applicant.setPhoneNumber("96552532");
		 */
		applicant = (Applicant) AuthManager.getLoggedInUser();
		/*
		 * Qualification q1 = new Qualification();
		 * q1.setName("CS Bachelors Degree"); q1.setField("CS");
		 * applicant.getQualifications().add(q1);
		 * EntityRepository.getInstance().saveCurrentDataRepresentation();
		 */

		nameField.textProperty().bindBidirectional(
				applicant.getUserNameProperty());
		emailAddressField.textProperty().bindBidirectional(
				applicant.getEmailAddressProperty());
		phoneNumberField.textProperty().bindBidirectional(
				applicant.getPhoneNumberProperty());

		qualificationsTable.setItems(applicant.getQualifications());
		qualificationsTable.setEditable(true);
		qualificationsTable.getSelectionModel().setSelectionMode(
				SelectionMode.MULTIPLE);
		qualificationNameColumn.setCellFactory(TextFieldTableCell
				.forTableColumn());
		qualificationFieldColumn.setCellFactory(TextFieldTableCell
				.forTableColumn());
		qualificationNameColumn.setCellValueFactory((q) -> q.getValue()
				.getNameProperty());
		qualificationFieldColumn.setCellValueFactory((q) -> q.getValue()
				.getFieldProperty());
		qualificationNameColumn.setOnEditCommit((t) -> {
			t.getRowValue().setName(t.getNewValue());
		});
		qualificationFieldColumn.setOnEditCommit((t) -> {
			t.getRowValue().setField(t.getNewValue());
		});

		coursesTableView.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				main.navigateToDialog(Constants.CourseDataViewPath,
						coursesTableView.getSelectionModel().getSelectedItem()
								.getCourse());
			}
		});

		coursesTableView.setItems(applicant.getAppliedCourseApplications());
		nameColumn.setCellValueFactory(val -> val.getValue().getCourse()
				.getNameProperty());
		universityColumn
				.setCellValueFactory(val -> {
					if (val.getValue().getCourse().getUniversity() == null)
						return null;
					return val.getValue().getCourse().getUniversity()
							.getNameProperty();
				});
		preRequisiteCountColumn
				.setCellValueFactory(val -> new SimpleIntegerProperty(val
						.getValue().getCourse().getPreRequisites().size()));
		acceptedColumn.setCellValueFactory(val -> val.getValue()
				.getAcceptedProperty());
		acceptedColumn.setCellFactory(val -> CheckBoxTableCell.forTableColumn(
				val).call(val));

		
		applicant.getImageBytesProperty().addListener(listener -> {
			updateImage();
		});
		updateImage();
		
		applicantImage.setOnMouseClicked(e -> {
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
				applicant.setImageBytes(data);
		});
		
	}

	public void updateImage() {
		Byte[] bytes = applicant.getImageBytes();
		if (bytes != null) {
			Image newImg = JavaFXImageConversion
					.getJavaFXImage(JavaFXImageConversion
							.toPrimitiveByteArray(bytes));
			applicantImage.setImage(newImg);
		}else{
			//set a default image
			applicantImage.setImage(new Image(Main.class.getResourceAsStream("applicanticon.png")));
		}
	}

	@FXML
	public void addQualification(ActionEvent e) {
		Qualification q1 = new Qualification();
		q1.setName("QName");
		q1.setField("QF");
		applicant.getQualifications().add(q1);
	}

	@FXML
	public void removeQualification(ActionEvent e) {
		applicant.getQualifications().removeAll(
				qualificationsTable.getSelectionModel().getSelectedItems());
	}

	@FXML
	public void registerForCourse(ActionEvent e) {

		@SuppressWarnings("unchecked")
		ObservableList<Course> selectedCourses = (ObservableList<Course>) main
				.navigateToDialog(
						Constants.RegisterForCoursesViewPath,
						applicant.getAppliedCourseApplications().stream()
								.map(ca -> ca.getCourse())
								.collect(Collectors.toList()));

		// user cancelled registration for more courses
		if (selectedCourses == null)
			return;

		selectedCourses.forEach(course -> {
			CourseApplication courseApplication = new CourseApplication();
			courseApplication.setAccepted(false);
			courseApplication.setApplicant(applicant);
			courseApplication.setCourse(course);
			courseApplication.setShouldBeAccepted(applicant.getQualifications()
					.containsAll(course.getPreRequisites()));
			course.getCourseApplications().add(courseApplication);
			applicant.getAppliedCourseApplications().add(courseApplication);
		});
	}

	@FXML
	public void unregisterForCourse(ActionEvent e) {
		List<CourseApplication> selectedCoursesApplications = coursesTableView
				.getSelectionModel().getSelectedItems();
		selectedCoursesApplications.forEach(ca -> {
			ca.getCourse().getCourseApplications().remove(ca);
			ca.getApplicant().getAppliedCourseApplications().remove(ca);
		});
	}

	@FXML
	public void saveData(ActionEvent e) {
		main.getRepositoryStore().saveCurrentDataRepresentation();
	}

	@FXML
	public void logout(ActionEvent e) {
		AuthManager.logout();
	}
}
