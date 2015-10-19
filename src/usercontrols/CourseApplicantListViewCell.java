package usercontrols;

import javafx.scene.control.ListCell;
import models.CourseApplication;

public class CourseApplicantListViewCell extends ListCell<CourseApplication>{
	 @Override
     public void updateItem(CourseApplication item, boolean empty) {
         super.updateItem(item, empty);
         if (item != null && !empty) {
        	 setGraphic(new CourseApplicantListViewContent(item));
         }
         else{
        	 setGraphic(null);
         }
     }
}
