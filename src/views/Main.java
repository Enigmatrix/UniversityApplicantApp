package views;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import repository.EntityRepository;
import repository.Repository;
import controllers.ControllerBase;
import controllers.DialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Main extends Application {

	private BorderPane rootLayout;
	private Stage primaryStage;

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void loadResources() {
		Locale locale = getRepositoryStore().getAppLocale();
		if (locale == null) {
			resourceBundle = ResourceBundle.getBundle("bundles.i18n");
		} else {
			resourceBundle = ResourceBundle.getBundle("bundles.i18n", locale);
		}
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			instance = this;

			loadResources();
			
			// Load root layout from fxml file.
			FXMLLoader loader = getFXMLLoader();
			loader.setLocation(Main.class.getResource("RootView.fxml"));
			rootLayout = (BorderPane) loader.load();
			initializeController((ControllerBase) loader.getController());

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("University Applications");
			primaryStage.show();

			this.primaryStage = primaryStage;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private FXMLLoader getFXMLLoader() {
		FXMLLoader loader = new FXMLLoader();
		loader.setResources(resourceBundle);
		return loader;
	}

	private static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public void navigate(String viewPath) {
		try {
			
			FXMLLoader loader = getFXMLLoader();
			loader.setLocation(Main.class.getResource(viewPath));
			rootLayout.setCenter(loader.load());
			initializeController((ControllerBase) loader.getController());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object navigateToDialog(String dialoString) {
		return navigateToDialog(dialoString, null);
	}

	private ResourceBundle resourceBundle;

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public Object navigateToDialog(String dialogViewPath, Object param) {
		try {
			FXMLLoader loader = getFXMLLoader();
			loader.setLocation(Main.class.getResource(dialogViewPath));
			Pane page = (Pane) loader.load();

			// Set the module into the controller.
			DialogController controller = loader.getController();
			Stage dialogStage = new Stage();
			controller.setDialogStage(dialogStage);
			controller.setParameter(param);

			dialogStage.setTitle(controller.getTitle());
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			scene.getStylesheets().add(
					getClass().getResource("application.css").toExternalForm());
			dialogStage.setScene(scene);

			initializeController(controller);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			return controller.data();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void initializeController(ControllerBase controller) {
		controller.setMain(this);
		controller.loaded();
	}

	public Repository getRepositoryStore() {
		return EntityRepository.getInstance();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
