package hotel.gui.admin;

import hotel.business.FacadeFactory;
import hotel.business.model.Privilege;
import hotel.business.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class UserAdminPanel extends VBox {

    //TITLE
    private final Label titleLabel = new Label("Administracija korisnika");
    //TABELA
    private TableView<User> userTable = new TableView<User>();
    private ObservableList<User> observableUserList;
    //FORMA
    private TextField usernameTextField = new TextField();
    private PasswordField passwordField = new PasswordField();
    private TextField nameTextField = new TextField();
    private TextField surnameTextField = new TextField();
    private ChoiceBox<Privilege> privilegeChoiceBox = new ChoiceBox<>();
    private Button addUserButton = new Button("Dodaj");
    private Button deleteUserButton = new Button("Izbriši");

    public UserAdminPanel() {
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        try {
            observableUserList = FXCollections.observableArrayList(FacadeFactory.getFacade().getAllUsers());
        } catch (Exception exception) {
            observableUserList = FXCollections.observableArrayList();
        }
        addUserButton.setOnAction(this::addUser);
        TableColumn usernameColumn = new TableColumn("Korisničko ime");
        usernameColumn.setMinWidth(150);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));

        TableColumn nameColumn = new TableColumn("Ime");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));

        TableColumn surnameColumn = new TableColumn("Prezime");
        surnameColumn.setMinWidth(100);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));

        TableColumn privilegeColumn = new TableColumn("Privilegija");
        privilegeColumn.setMinWidth(150);
        privilegeColumn.setCellValueFactory(new PropertyValueFactory<User, String>("idPrivilege"));

        userTable.setItems(observableUserList);
        userTable.getColumns().addAll(usernameColumn, nameColumn, surnameColumn, privilegeColumn);

        getChildren().addAll(titleLabel, userTable, getForm());

    }

    private void addUser(ActionEvent event) {
        User user = new User();
        user.setUsername(usernameTextField.getText());
        user.setPassword(passwordField.getText());
        user.setName(nameTextField.getText());
        user.setSurname(surnameTextField.getText());
        user.setIdPrivilege(privilegeChoiceBox.getSelectionModel().getSelectedItem());
        FacadeFactory.getFacade().saveUser(user);
        observableUserList.add(user);
        clearInputs();
    }
    
    private void clearInputs(){
        usernameTextField.setText("");
    }

    private HBox getForm() {
        HBox hBox = new HBox();
        hBox.setSpacing(3);
        hBox.setPadding(new Insets(10));

        usernameTextField.setMaxWidth(75);
        usernameTextField.setPromptText("Kor.ime..");

        passwordField.setMaxWidth(75);
        passwordField.setPromptText("Lozinka..");

        nameTextField.setMaxWidth(100);
        nameTextField.setPromptText("Ime..");

        surnameTextField.setMaxWidth(100);
        surnameTextField.setPromptText("Prezime..");
        List<Privilege> privileges = FacadeFactory.getFacade().getAllPrivileges();
        ObservableList<Privilege> observablePrivilegeList = FXCollections.observableArrayList(privileges);
        privilegeChoiceBox.setItems(observablePrivilegeList);
        privilegeChoiceBox.getSelectionModel().select(0);
        privilegeChoiceBox.setMaxWidth(150);

        hBox
                .getChildren()
                .addAll(usernameTextField,
                        passwordField,
                        nameTextField,
                        surnameTextField,
                        privilegeChoiceBox,
                        addUserButton,
                        deleteUserButton);
        return hBox;
    }

}
