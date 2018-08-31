package de.htwsaar.prog3.carrental.gui;

import java.io.IOException;
import java.util.Locale;

import de.htwsaar.prog3.carrental.util.EntityManagerUtil;
import de.htwsaar.prog3.carrental.util.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.util.I18nStringsUtil;
import de.htwsaar.prog3.carrental.util.I18nUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point of the car rental application. Definition of primary stage and
 * building of the environment.
 *
 * @author Lukas Raubuch
 */
public class CarTableView extends Application {

	// Has to be static to enable modal dialogs
    private static Stage primaryStage;
    private static Parent carScene;
    private static Parent employeeScene;
    private static Parent rentalScene;
    private static Parent customerScene;

    /**
     * Entry Point for the main view of the program.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        // Load FXML-document for the main view with the needed resource bundle
        initScenes();
        primaryStage.setTitle(I18nComponentsUtil.getStageTitleString());
        // Apply styling described in the FXML-document
        primaryStage.setScene(new Scene(carScene));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void initScenes() throws IOException {
		carScene = FXMLLoader.load(getClass().getResource(I18nStringsUtil.getCarTableViewURL()),
                I18nUtil.getResourceBundleComponents());
		//TODO: Correct
		//employeeScene = FXMLLoader.load(getClass().getResource(I18nStringsUtil.getEmployeeTableViewURL()),
        //        I18nUtil.getResourceBundleComponents());
		
	}

	/**
     * This method is used to execute operations when the application is closed by
     * the user. It's primary purpose is to close the database connection
     */
    @Override
    public void stop() {
        EntityManagerUtil.closeEntityManagerFactory();
    }

    /**
     * Main method of this Application.
     *
     * @param args commandline arguments
     */
    public static void main(String[] args) {
    	Locale.setDefault(new Locale("en"));
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setPrimaryStage(Stage primaryStage) {
        CarTableView.primaryStage = primaryStage;
    }

	public static void setEmployeeView() {
		primaryStage.setScene(new Scene(employeeScene));
	}
}
