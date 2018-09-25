package de.htwsaar.prog3.carrental.controller;

import de.htwsaar.prog3.carrental.model.Customer;
import de.htwsaar.prog3.carrental.service.CustomerService;
import de.htwsaar.prog3.carrental.util.DialogUtil;
import de.htwsaar.prog3.carrental.util.i18n.I18nComponentsUtil;
import de.htwsaar.prog3.carrental.view.CustomerEditView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.RollbackException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller for CustomerTableView.
 *
 * @author Lukas Raubuch, Jens Thewes
 */
public class CustomerTableViewController extends GenericTableViewController<Customer> implements Initializable {
    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<Customer, Integer> id;
    @FXML
    private TableColumn<Customer, String> city;
    @FXML
    private TableColumn<Customer, String> dateOfBirth;
    @FXML
    private TableColumn<Customer, String> driverLicenseId;
    @FXML
    private TableColumn<Customer, String> emailAddress;
    @FXML
    private TableColumn<Customer, String> firstName;
    @FXML
    private TableColumn<Customer, Integer> houseNumber;
    @FXML
    private TableColumn<Customer, String> idNumber;
    @FXML
    private TableColumn<Customer, String> lastName;
    @FXML
    private TableColumn<Customer, String> phoneNumber;
    @FXML
    private TableColumn<Customer, String> street;
    @FXML
    private TableColumn<Customer, String> zipCode;

    public CustomerTableViewController() {
        service = new CustomerService();
        entities = FXCollections.observableArrayList(service.findAll());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        city.setCellValueFactory(new PropertyValueFactory<>("City"));
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        driverLicenseId.setCellValueFactory(new PropertyValueFactory<>("DriverLicenseId"));
        emailAddress.setCellValueFactory(new PropertyValueFactory<>("EmailAddress"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        houseNumber.setCellValueFactory(new PropertyValueFactory<>("HouseNumber"));
        idNumber.setCellValueFactory(new PropertyValueFactory<>("IdNumber"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        street.setCellValueFactory(new PropertyValueFactory<>("Street"));
        zipCode.setCellValueFactory(new PropertyValueFactory<>("ZipCode"));

        customerTableView.setItems(entities);
    }

    @Override
    public void handleNewButtonClicked() {
        Customer customer = new Customer();

        boolean applyClicked = new CustomerEditView().start(app.getPrimaryStage(), customer);
        if (applyClicked) {
            service.persist(customer);
            entities.setAll(service.findAll());
        }
    }

    @Override
    public void handleEditButtonClicked() {
        Customer customer = customerTableView.getSelectionModel().getSelectedItem();

        if (customer != null) {
            boolean applyClicked = new CustomerEditView().start(app.getPrimaryStage(), customer);
            if (applyClicked) {
                service.update(customer);
                entities.setAll(service.findAll());
            }
        }
    }

    @Override
    public void handleDeleteButtonClicked() {
        Customer customer = customerTableView.getSelectionModel().getSelectedItem();

        if (null == customer) {
            Alert info = DialogUtil.createInformationDialog(I18nComponentsUtil.getDialogDeleteNoSelectionText());
            info.show();
            return;
        }

        Alert confirmation = DialogUtil.createConfirmationDialog(I18nComponentsUtil.getDialogDeleteConfirmationText());
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.orElse(null) == ButtonType.OK) {
            try {
                service.delete(customer);
                entities.setAll(service.findAll());
            } catch (RollbackException e) {
                // TODO i18n
                Alert error = DialogUtil.createErrorDialog("Invalid Action", "Can't delete this customer",
                        "You must first delete the rental");
                error.showAndWait();
            }
        }
    }
}
