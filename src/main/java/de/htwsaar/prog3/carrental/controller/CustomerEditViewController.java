package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This is the Controller for the "Edit Customer View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
public class CustomerEditViewController {
    @Setter
    private Stage modalStage;

    @Getter
    private boolean applyClicked = false;

    private Customer customerToEdit;
    private CustomerService service = new CustomerService();

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailAddressTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField dateOfBirthTextField;

    @FXML
    private TextField streetTextField;

    @FXML
    private TextField houseNumberTextField;

    @FXML
    private TextField cityTextField;

    @FXML
    private TextField zipCodeTextField;

    @FXML
    private TextField idNumberTextField;

    @FXML
    private TextField driverLicenseIdTextField;

    /**
     * fills all the text fields with the given information from given customerToEdit.
     *
     * @param customerToEdit given customer to be edit
     */
    public void setCustomer(Customer customerToEdit) {
        this.customerToEdit = customerToEdit;

        firstNameTextField.setText(customerToEdit.getFirstName());
        lastNameTextField.setText(customerToEdit.getLastName());
        emailAddressTextField.setText(customerToEdit.getEmailAddress());
        phoneNumberTextField.setText(customerToEdit.getPhoneNumber());
        dateOfBirthTextField.setText(customerToEdit.getDateOfBirth());
        streetTextField.setText(customerToEdit.getStreet());
        houseNumberTextField.setText(customerToEdit.getHouseNumber());
        cityTextField.setText(customerToEdit.getCity());
        zipCodeTextField.setText(Integer.toString(customerToEdit.getZipCode()));
        idNumberTextField.setText(customerToEdit.getIdNumber());
        driverLicenseIdTextField.setText(customerToEdit.getDriverLicenseId());
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
            customerToEdit.setFirstName(firstNameTextField.getText());
            customerToEdit.setLastName(lastNameTextField.getText());
            customerToEdit.setEmailAddress(emailAddressTextField.getText());
            customerToEdit.setPhoneNumber(phoneNumberTextField.getText());
            customerToEdit.setDateOfBirth(dateOfBirthTextField.getText());
            customerToEdit.setStreet(streetTextField.getText());
            customerToEdit.setHouseNumber(houseNumberTextField.getText());
            customerToEdit.setCity(cityTextField.getText());
            customerToEdit.setZipCode(Integer.parseInt(zipCodeTextField.getText()));
            customerToEdit.setIdNumber(idNumberTextField.getText());
            customerToEdit.setDriverLicenseId(driverLicenseIdTextField.getText());

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
        List<Customer> customers;

        if (firstNameTextField.getText() == null
                || firstNameTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getCustomerNoValidFirstName() + "\n";
        }

        if (lastNameTextField.getText() == null
                || lastNameTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getCustomerNoValidLastName() + "\n";
        }

        if (emailAddressTextField.getText() == null
                || emailAddressTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getCustomerNoValidEmailAdress() + "\n";
        }

        if (phoneNumberTextField.getText() == null
                || phoneNumberTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getCustomerNoValidPhoneNumber() + "\n";
        }

        if (dateOfBirthTextField.getText() == null
                || dateOfBirthTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getCustomerNoValidDateOfBirth() + "\n";
        }

        if (streetTextField.getText() == null || streetTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getCustomerNoValidStreetName() + "\n";
        }

        if (houseNumberTextField.getText() == null
                || houseNumberTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getCustomerNoValidHouseNumber() + "\n";
        }

        if (cityTextField.getText() == null || cityTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getCustomerNoValidCityName() + "\n";
        }

        if (zipCodeTextField.getText() == null || zipCodeTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getCustomerNoValidZipCode() + "\n";
        } else {
            try {
                Integer.parseInt(zipCodeTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += I18nComponentsUtil.getCustomerNoValidZipCode() + " "
                        + I18nComponentsUtil.getCustomerNoValidInteger() + "\n";
            }
        }

        if (idNumberTextField.getText() == null
                || idNumberTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getCustomerNoValidIdNumber() + "\n";
        } else {
            customers = service.filter(I18nComponentsUtil.getCustomerIdNumberLabel(), "=", idNumberTextField.getText())
                    .stream()
                    .filter(c -> !c.getId().equals(customerToEdit.getId()))
                    .collect(Collectors.toList());
            if (!customers.isEmpty()) {
                errorMessage += "There is already a customer with this id number \n";
            }
        }

        if (driverLicenseIdTextField.getText() == null
                || driverLicenseIdTextField.getText().trim().length() == 0) {
            errorMessage += I18nComponentsUtil.getCustomerNoValidDriverLicence() + "\n";
        } else {
            customers = service.filter(I18nComponentsUtil.getCustomerDriverLicenseIdLabel(),
                    "=", driverLicenseIdTextField.getText())
                    .stream()
                    .filter(c -> !c.getId().equals(customerToEdit.getId()))
                    .collect(Collectors.toList());
            if (!customers.isEmpty()) {
                errorMessage += "There is already a customer with this driver license id \n";
            }
        }

        if (!errorMessage.isEmpty()) {
            Alert alert = DialogUtil.createErrorDialog("Invalid Fields", "Please correct invalid fields", errorMessage);
            alert.showAndWait();

            return false;
        }

        return true;
    }
}
