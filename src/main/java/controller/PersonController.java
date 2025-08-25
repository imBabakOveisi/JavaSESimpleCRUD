package controller;

import controller.exception.PersonNotFoundByFirstNameAndLastNameException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import lombok.extern.log4j.Log4j;
import model.entity.Person;
import model.entity.enums.PersonRole;
import model.service.PersonService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Log4j
public class PersonController implements Initializable {

    @FXML
    private TextField personIdText, firstNameText, lastNameText, searchFirstNameText, searchLastNameText;

    @FXML
    private DatePicker birthDate;

    @FXML
    private ComboBox<PersonRole> roleCombo;

    @FXML
    private ToggleGroup statusToggle;

    @FXML
    private RadioButton enableRadio, disableRadio;

    @FXML
    private Button saveButton, updateButton, deleteButton;

    @FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, Integer> personIdColumn;

    @FXML
    private TableColumn<Person, String> firstNameColumn, lastNameColumn;

    @FXML
    private TableColumn<Person, LocalDate> birthDateColumn;

    @FXML
    private TableColumn<Person, PersonRole> roleColumn;

    @FXML
    private TableColumn<Person, Boolean> statusColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            resetForm();
        } catch (Exception e) {
            log.error("Error Loading Person Form " + e.getMessage());
            Alert loadingFormAlert = new Alert(Alert.AlertType.ERROR, "Error Loading Person Form", ButtonType.OK);
            loadingFormAlert.show();
        }

        saveButton.setOnAction(event -> {
            try {
                Person person = Person
                        .builder()
                        .firstName(firstNameText.getText())
                        .lastName(lastNameText.getText())
                        .birthDate(birthDate.getValue())
                        .role(roleCombo.getSelectionModel().getSelectedItem())
                        .active(enableRadio.isSelected())
                        .build();

                PersonService.getService().save(person);
                log.info(String.format("Person Saved Successfully: %s", person));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("Person Saved Successfully.%n%s", person), ButtonType.OK);
                alert.show();
                try {
                    resetForm();
                } catch (Exception e) {
                    log.error("Error Loading Person Form");
                    Alert loadingFormAlert = new Alert(Alert.AlertType.ERROR, "Error Loading Person Form", ButtonType.OK);
                    loadingFormAlert.show();
                }
            } catch (Exception e) {
                log.error("Person Save Failed! " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Person Save Failed!%n%s", e.getMessage()), ButtonType.OK);
                alert.show();
            }
        });

        updateButton.setOnAction(event -> {
            try {
                Person person = Person
                        .builder()
                        .personId(Integer.parseInt(personIdText.getText()))
                        .firstName(firstNameText.getText())
                        .lastName(lastNameText.getText())
                        .birthDate(birthDate.getValue())
                        .role(roleCombo.getSelectionModel().getSelectedItem())
                        .active(enableRadio.isSelected())
                        .build();

                PersonService.getService().update(person);
                log.info(String.format("Person Updated Successfully: %s", person));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("Person Updated Successfully.%n%s", person), ButtonType.OK);
                alert.show();
                try {
                    resetForm();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                log.error("Person Update Failed! " + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Person Update Failed!%n%s", e.getMessage()), ButtonType.OK);
                alert.show();
            }
        });

        deleteButton.setOnAction(event -> {
            try {
                String personId = personIdText.getText();
                PersonService.getService().deleteById(Integer.valueOf(personId));
                log.info(String.format("Person Delete by id=%s Successfully.", personId));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, String.format("Person Deleted by id=%s Successfully.", personId), ButtonType.OK);
                alert.show();
                try {
                    resetForm();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                log.error(String.format("Person Delete by id=%s Failed! ", personIdText.getText()) + e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR, String.format("Person Delete by id=%s Failed!%n%s", personIdText.getText(), e.getMessage()), ButtonType.OK);
                alert.show();
            }
        });

        personTable.setOnMouseReleased(event -> {
            try {
                Person person = personTable.getSelectionModel().getSelectedItem();
                if (person == null) {
                    person = personTable.getItems().get(0);
                }
                selectFromTable(person);
            } catch (Exception e) {
                log.error("Error Loading Person Form " + e.getMessage());
                Alert loadingDataFromTableAlert = new Alert(Alert.AlertType.ERROR, "Error Loading Person Form", ButtonType.OK);
                loadingDataFromTableAlert.show();
            }
        });

        personTable.setOnKeyReleased(event -> {
            try {
                if (event.getCode() == KeyCode.TAB) {
                    Person person = personTable.getItems().get(0);
                    selectFromTable(person);
                } else if (event.getCode() == KeyCode.UP ||
                        event.getCode() == KeyCode.DOWN ||
                        event.getCode() == KeyCode.LEFT ||
                        event.getCode() == KeyCode.RIGHT) {
                    Person person = personTable.getSelectionModel().getSelectedItem();
                    selectFromTable(person);
                }
            } catch (Exception e) {
                log.error("Error Loading Person Form " + e.getMessage());
                Alert loadingDataFromTableAlert = new Alert(Alert.AlertType.ERROR, "Error Loading Person Form", ButtonType.OK);
                loadingDataFromTableAlert.show();

            }
        });

        searchFirstNameText.setOnKeyReleased(event -> {
            try {
                findByFirstNameAndLastName();
            } catch (Exception e) {
                log.error("Error Searching data " + e.getMessage());
                Alert searchByFirstNameAndLastNameAlert = new Alert(Alert.AlertType.ERROR, "Error Searching data", ButtonType.OK);
                searchByFirstNameAndLastNameAlert.show();
            }
        });

        searchLastNameText.setOnKeyReleased(event -> {
            try {
                findByFirstNameAndLastName();
            } catch (Exception e) {
                log.error("Error Searching data " + e.getMessage());
                Alert searchByFirstNameAndLastNameAlert = new Alert(Alert.AlertType.ERROR, "Error Searching data", ButtonType.OK);
                searchByFirstNameAndLastNameAlert.show();
            }
        });
    }

    private void resetForm() throws Exception {
        personIdText.clear();
        firstNameText.clear();
        lastNameText.clear();

        birthDate.setValue(LocalDate.now());

        roleCombo.getItems().clear();
        for (PersonRole role : PersonRole.values()) {
            roleCombo.getItems().add(role);
        }
        roleCombo.getSelectionModel().select(0);

        enableRadio.setSelected(true);

        showDataOnTableView(PersonService.getService().findAll());
    }

    private void showDataOnTableView(List<Person> personList) {
        ObservableList<Person> personObservableList = FXCollections.observableList(personList);

        personIdColumn.setCellValueFactory(new PropertyValueFactory<>("personId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("active"));

        personTable.setItems(personObservableList);
    }

    private void selectFromTable(Person person) {
        personIdText.setText(String.valueOf(person.getPersonId()));
        firstNameText.setText(person.getFirstName());
        lastNameText.setText(person.getLastName());
        birthDate.setValue(person.getBirthDate());
        roleCombo.getSelectionModel().select(person.getRole());
        enableRadio.setSelected(person.isActive());
        disableRadio.setSelected(!person.isActive());
    }

    private void findByFirstNameAndLastName() throws Exception {
        showDataOnTableView(PersonService.getService().findByFirstNameAndLastName(searchFirstNameText.getText(), searchLastNameText.getText()));
    }
}
