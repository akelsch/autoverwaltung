package de.htwsaar.prog3.carrental.controller;

import java.util.Optional;
import de.htwsaar.prog3.carrental.model.Employee;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

/**
 * This is the Controller for the "Edit Employee View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class EmployeeEditViewController {
    @Setter
    private Stage modalStage;

    @Getter
    private boolean applyClicked = false;

    private Employee employeeToEdit;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField positionTextField;

    /**
     * fills all the text fields with the given information from given employeeToEdit.
     * 
     * @param employeeToEdit given employee to be edit
     */
    public void setEmployee(Employee employeeToEdit) {
        this.employeeToEdit = employeeToEdit;

        firstNameTextField.setText(employeeToEdit.getFirstName());
        lastNameTextField.setText(employeeToEdit.getLastName());
        positionTextField.setText(employeeToEdit.getPosition());
    }

    /**
     * Handle Cancel Button clicked.
     */
    public void handleCancelButtonClicked() {
        Alert confirmationDialog = DialogUtil
                .createConfirmationDialog(I18nComponentsUtil.getDialogCancelConfirmationText());

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.orElse(null) == ButtonType.OK) {
            modalStage.close();
        }
    }

    /**
     * Handle Apply Button clicked.
     */
    public void handleApplyButtonClicked() {
        // TODO only update data that has changed?
        if (isInputValid()) {
            employeeToEdit.setFirstName(firstNameTextField.getText());
            employeeToEdit.setLastName(lastNameTextField.getText());
            employeeToEdit.setPosition(positionTextField.getText());

            applyClicked = true;
            modalStage.close();
        }
    }

    /**
     * Valid Data Check.
     *
     * @return true if every data is valid, false if at least one data is not valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameTextField.getText() == null
                || firstNameTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getEmployeeNoValidFirstName() + "\n";
        }

        if (lastNameTextField.getText() == null
                || lastNameTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getEmployeeNoValidLastName() + "\n";
        }

        if (positionTextField.getText() == null
                || positionTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getEmployeeNoValidPosition() + "\n";
        }

        if (!errorMessage.isEmpty()) {
            Alert alert = DialogUtil.createErrorDialog("Invalid Fields", "Please correct invalid fields", errorMessage);
            alert.showAndWait();

            return false;
        }

        return true;
    }
}
