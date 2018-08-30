package de.htwsaar.prog3.carrental.gui;

import de.htwsaar.prog3.carrental.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.i18n.I18nStringsUtil;
import de.htwsaar.prog3.carrental.i18n.I18nUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Entry Point of the New Car Creation Dialog.
 *
 * @author Jens Thewes
 */
public class NewCarCreationView {

    /**
     * Start the New Car Creation Dialog in a modal Window.
     *
     * @param parentStage
     * @throws Exception
     */
    public void start(Stage parentStage) throws Exception {
        Stage modalWindow = new Stage();
        // Load FXML document for the car configuration view wit the needed resource bundle
        Parent scene =
                FXMLLoader.load(getClass().getResource(I18nStringsUtil.getNewCarCreationViewURL()),
                        I18nUtil.getResourceBundleComponents());
        modalWindow.setTitle(I18nComponentsUtil.getStageTitleString());
        // Apply styling described in the FXML document
        modalWindow.setScene(new Scene(scene));
        modalWindow.setMaximized(true);
        // set the Owner of the modal window and the Modality of the new Stage (Modal Window)
        modalWindow.initOwner(parentStage);
        modalWindow.initModality(Modality.WINDOW_MODAL);
        modalWindow.show();
    }
}