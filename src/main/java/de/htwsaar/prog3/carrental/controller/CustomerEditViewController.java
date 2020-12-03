package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.repository.CustomerRepository;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.I18nUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This is the Controller for the "Edit Customer View" of the Carrental Application.
 *
 * @author Jens Thewes
 */
@Component
public class CustomerEditViewController extends GenericEditViewController<Customer> {

    private final CustomerRepository customerRepository;

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

    @Autowired
    public CustomerEditViewController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void initialize(Customer customer) {
        entity = customer;

        firstNameTextField.setText(entity.getFirstName());
        lastNameTextField.setText(entity.getLastName());
        emailAddressTextField.setText(entity.getEmailAddress());
        phoneNumberTextField.setText(entity.getPhoneNumber());
        dateOfBirthTextField.setText(entity.getDateOfBirth());
        streetTextField.setText(entity.getStreet());
        houseNumberTextField.setText(entity.getHouseNumber());
        cityTextField.setText(entity.getCity());
        zipCodeTextField.setText(Integer.toString(entity.getZipCode()));
        idNumberTextField.setText(entity.getIdNumber());
        driverLicenseIdTextField.setText(entity.getDriverLicenseId());
    }

    @Override
    public void handleApplyButtonClicked() {
        if (isInputValid()) {
            entity.setFirstName(firstNameTextField.getText());
            entity.setLastName(lastNameTextField.getText());
            entity.setEmailAddress(emailAddressTextField.getText());
            entity.setPhoneNumber(phoneNumberTextField.getText());
            entity.setDateOfBirth(dateOfBirthTextField.getText());
            entity.setStreet(streetTextField.getText());
            entity.setHouseNumber(houseNumberTextField.getText());
            entity.setCity(cityTextField.getText());
            entity.setZipCode(Integer.parseInt(zipCodeTextField.getText()));
            entity.setIdNumber(idNumberTextField.getText());
            entity.setDriverLicenseId(driverLicenseIdTextField.getText());

            applyClicked = true;
            modalStage.close();
        }
    }

    @Override
    boolean isInputValid() {
        StringBuilder sb = new StringBuilder();

        if (firstNameTextField.getText() == null || firstNameTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCustomerNoValidFirstName());
            sb.append(System.lineSeparator());
        }

        if (lastNameTextField.getText() == null || lastNameTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCustomerNoValidLastName());
            sb.append(System.lineSeparator());
        }

        if (emailAddressTextField.getText() == null || emailAddressTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCustomerNoValidEmailAddress());
            sb.append(System.lineSeparator());
        }

        if (phoneNumberTextField.getText() == null || phoneNumberTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCustomerNoValidPhoneNumber());
            sb.append(System.lineSeparator());
        }

        if (dateOfBirthTextField.getText() == null || dateOfBirthTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCustomerNoValidDateOfBirth());
            sb.append(System.lineSeparator());
        }

        if (streetTextField.getText() == null || streetTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCustomerNoValidStreetName());
            sb.append(System.lineSeparator());
        }

        if (houseNumberTextField.getText() == null || houseNumberTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCustomerNoValidHouseNumber());
            sb.append(System.lineSeparator());
        }

        if (cityTextField.getText() == null || cityTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCustomerNoValidCityName());
            sb.append(System.lineSeparator());
        }

        if (zipCodeTextField.getText() == null || zipCodeTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCustomerNoValidZipCode());
            sb.append(System.lineSeparator());
        } else {
            try {
                Integer.parseInt(zipCodeTextField.getText());
            } catch (NumberFormatException e) {
                sb.append(I18nUtils.getCustomerNoValidZipCode());
                sb.append(" ");
                sb.append(I18nUtils.getDialogInvalidNumberText());
                sb.append(System.lineSeparator());
            }
        }

        if (idNumberTextField.getText() == null || idNumberTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCustomerNoValidIdNumber());
            sb.append(System.lineSeparator());
        } else {
            if (customerRepository.existsByIdNotAndIdNumber(entity.getId(), idNumberTextField.getText())) {
                sb.append(I18nUtils.getCustomerNoValidIdNumberDuplicate());
                sb.append(System.lineSeparator());
            }
        }

        if (driverLicenseIdTextField.getText() == null || driverLicenseIdTextField.getText().trim().isEmpty()) {
            sb.append(I18nUtils.getCustomerNoValidDriverLicence());
            sb.append(System.lineSeparator());
        } else {
            if (customerRepository.existsByIdNotAndDriverLicenseId(entity.getId(), driverLicenseIdTextField.getText())) {
                sb.append(I18nUtils.getCustomerNoValidDriverLicenceDuplicate());
                sb.append(System.lineSeparator());
            }
        }

        String errorMessage = sb.toString();
        if (!errorMessage.isEmpty()) {
            Alert alert = DialogUtil.createErrorDialog(I18nUtils.getDialogErrorInvalidFieldsTitle(),
                    I18nUtils.getDialogErrorInvalidFieldsText(), errorMessage);
            alert.showAndWait();

            return false;
        }

        return true;
    }
}
