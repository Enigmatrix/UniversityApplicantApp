package usercontrols;

import utils.Constants;
import application.Main;
import models.Course;
import javafx.scene.control.ListCell;

public class CourseListViewCell extends ListCell<Course>{
	 @Override
     public void updateItem(Course item, boolean empty) {
         super.updateItem(item, empty);
         if (item != null && !empty) {
        	 this.setOnMouseClicked(e ->{
        		 if(e.getClickCount() == 2){
        			 //TODO open a more detailed view of the course
        			 Main.getInstance().navigateToDialog(Constants.CourseDataViewPath, item);
        		 }
        	 });
        	 setGraphic(new CourseListViewContent(item));
         }
         else{
        	 setGraphic(null);
         }
     }
}
