package usercontrols;

import com.guigarage.sdk.util.MaterialDesignButton;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;

public class EditableTextControl extends UserControl {
	@FXML
    private TextField editTextField;
	@FXML
	private Label viewTextField;
	@FXML
	private MaterialDesignButton switchButton;
	
	private BooleanProperty _editingProperty;
	private StringProperty _textProperty;
	private ObjectProperty<Font> _fontProperty;
	
	public BooleanProperty editingProperty(){
		return _editingProperty;
	}
	
	public StringProperty textProperty(){
		return _textProperty;
	}
	
    public Font getFont(){
        return _fontProperty.getValue();
    }
    public void setFont(Font font){ 
        _fontProperty.setValue(font); 
    }
	
	public EditableTextControl() {
		_editingProperty = new SimpleBooleanProperty(false);
		_textProperty = new SimpleStringProperty();
		_fontProperty = new SimpleObjectProperty(Font.getDefault());

		switchButton.setText("\uE104");
		
		editTextField.visibleProperty().bind(_editingProperty);
		viewTextField.visibleProperty().bind(_editingProperty.not());
		
		viewTextField.fontProperty().bindBidirectional(_fontProperty);
		
		editTextField.textProperty().bindBidirectional(_textProperty);
		viewTextField.textProperty().bindBidirectional(_textProperty);
		
		
		//change icon
		_editingProperty.addListener((prop, oldVal, newVal) -> {
			//we are editing
			if(!newVal)
				//the edit icon in Microsoft Segoe UI Symbol
				switchButton.setText("\uE104");
			else
				//the tick icon in Microsoft Segoe UI Symbol
				switchButton.setText("\uE10B");
		});
		
		
		//change edit mode when button pressed
		switchButton.setOnAction(e -> {
			_editingProperty.set(!_editingProperty.get());
		});
		//or when enter is pressed
		editTextField.setOnKeyPressed(key -> {
			if(key.getCode() == KeyCode.ENTER){
				_editingProperty.set(!_editingProperty.get());
			}
		});
	}
}
