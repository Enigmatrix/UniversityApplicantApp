package usercontrols;

import javafx.scene.control.ListCell;
import models.Course;

public class EditableCourseListViewCell extends ListCell<Course>{
	
	 @Override
     public void updateItem(Course item, boolean empty) {
         super.updateItem(item, empty);
         if (item != null && !empty) {
        	 setGraphic(new EditableCourseListViewContent(item));
         }
         else{
        	 setGraphic(null);
         }
     }
}
