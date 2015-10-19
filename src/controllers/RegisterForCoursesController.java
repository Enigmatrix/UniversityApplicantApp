package controllers;

import java.util.List;
import usercontrols.CourseListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import models.Course;

public class RegisterForCoursesController extends DialogController {

	@FXML
	public ListView<Course> coursesListView;

	@Override
	public String getTitle() {
		return "Registered Courses";
	}

	private boolean ok = false;

	@Override
	public Object data() {
		return ok ? coursesListView.getSelectionModel().getSelectedItems()
				: null;
	}

	@FXML
	private TextField searchText;

	private ObservableList<Course> coursesThatCanBeRegisteredFor;

	@Override
	public void loaded() {

		// remove the courses that we already registered for
		ObservableList<Course> allCourses = main.getRepositoryStore()
				.getCourses();
		List<Course> alreadyRegisteredCourses = (List<Course>) this.parameter;
		coursesThatCanBeRegisteredFor = FXCollections
				.observableArrayList(allCourses);
		
		coursesThatCanBeRegisteredFor.removeAll(alreadyRegisteredCourses);

		coursesListView.getSelectionModel().setSelectionMode(
				SelectionMode.MULTIPLE);
		coursesListView.setCellFactory(val -> new CourseListViewCell());
		FilteredList<Course> filteredList = coursesThatCanBeRegisteredFor.filtered(course -> true);
		coursesListView.setItems(filteredList);
		
		
		searchText.textProperty().addListener((prop, oldVal, newVal) -> {
			filteredList.setPredicate(course -> {
				return course.getName().toLowerCase()
						.contains(newVal.toLowerCase());
			});
		});
		
		/*
		 * TEST CODE
		 * 
		 * Qualification qualification = new Qualification();
		 * qualification.setName("High School Physics");
		 * qualification.setField("PHY");
		 * 
		 * Qualification qualification1 = new Qualification();
		 * qualification1.setName("High School Physics Olympiad");
		 * qualification.setField("PO");
		 * 
		 * Course course = new Course(); course.setName("Nuclear Physics 101");
		 * course.getPreRequisites().addAll(qualification, qualification1);
		 * 
		 * University uni = main.getRepositoryStore().getUniversities().get(0);
		 * 
		 * uni.getCourses().add(course); course.setUniversity(uni);
		 * 
		 * main.getRepositoryStore().getCourses().add(course);
		 * //main.getRepositoryStore().saveCurrentDataRepresentation();(
		 */

		/*
		 * Applicant randomGuy = new Applicant();
		 * randomGuy.setUserName("Enigmatrix Atheros");
		 * randomGuy.setUserPassword("lol");
		 * randomGuy.setEmailAddress("enigmatrix2000@gmail.com");
		 * randomGuy.setPhoneNumber("12345678");
		 * main.getRepositoryStore().getApplicants().add(randomGuy);
		 * main.getRepositoryStore().saveCurrentDataRepresentation();
		 */

	}

	@FXML
	public void okButtonPressed(ActionEvent e) {
		ok = true;
		dialogStage.close();
	}

	@FXML
	public void cancelButtonPressed(ActionEvent e) {
		ok = false;
		dialogStage.close();
	}
}
