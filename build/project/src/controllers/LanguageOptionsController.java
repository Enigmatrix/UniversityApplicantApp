package controllers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;



import repository.Repository;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class LanguageOptionsController extends DialogController {

	@FXML
	public Button okButton;
	@FXML
	public ListView<String> langs;
	
	@Override
	public String getTitle() {
		return "Language Options";
	}

	@Override
	public Object data() {
		return null;
	}
	
	private Map<String, Locale> nameToLocale = new HashMap<>();
	
	@Override
	public void loaded(){
		
		nameToLocale.put("Default", null);
		nameToLocale.put("French", Locale.FRANCE);
		
		okButton.setOnAction(e -> {
			//set locale and save
			String chosenOption = langs.getSelectionModel().getSelectedItem();
			Repository repository = main.getRepositoryStore();
			repository.setAppLocale(nameToLocale.get(chosenOption));
			repository.saveCurrentDataRepresentation();
			try {
				Thread.sleep(500);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		});
		langs.getItems().addAll(nameToLocale.keySet());
	}

}
