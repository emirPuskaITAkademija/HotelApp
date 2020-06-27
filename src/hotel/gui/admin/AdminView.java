package hotel.gui.admin;

import hotel.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AdminView extends BorderPane {

    private final ToggleButton userToggleButton = new ToggleButton("Uposlenici");
    private final ToggleButton roomToggleButton = new ToggleButton("Sobe");
    private final ToggleButton logoutToggleButton = new ToggleButton();

    private UserAdminPanel userAdminPanel;
    private RoomAdminPanel roomAdminPanel;

    public AdminView() {
        userAdminPanel = new UserAdminPanel();
        setCenter(userAdminPanel);

        ToggleGroup toggleGroup = new ToggleGroup();
        userToggleButton.setToggleGroup(toggleGroup);
        roomToggleButton.setToggleGroup(toggleGroup);
        userToggleButton.setSelected(true);
        logoutToggleButton.setText("Odjava(" + Controller.getInstance().getLoggedUser().getName() + ")");
        logoutToggleButton.setOnAction(Controller.getInstance().getEventBus().getLogoutEvent());
        userToggleButton.setOnAction(this::showUserAdminPanel);
        roomToggleButton.setOnAction(this::showRoomAdminPanel);

        HBox mainMenuBox = new HBox();
        mainMenuBox.setSpacing(5);
        mainMenuBox.setPadding(new Insets(10));
        mainMenuBox.getChildren().addAll(userToggleButton, roomToggleButton);

        HBox logoutBox = new HBox(logoutToggleButton);
        logoutBox.setAlignment(Pos.CENTER_RIGHT);
        logoutBox.setPadding(new Insets(10));

        GridPane topPane = new GridPane();
        topPane.add(mainMenuBox, 0, 0);
        topPane.add(logoutBox, 1, 0);
        setTop(topPane);
    }

    private void showUserAdminPanel(ActionEvent event) {
        userAdminPanel = new UserAdminPanel();
        setCenter(userAdminPanel);
    }
    
    private void showRoomAdminPanel(ActionEvent event){
        roomAdminPanel = new RoomAdminPanel();
        setCenter(roomAdminPanel);
    }

    public UserAdminPanel getUserAdminPanel() {
        return userAdminPanel;
    }

    public void setUserAdminPanel(UserAdminPanel userAdminPanel) {
        this.userAdminPanel = userAdminPanel;
    }

    public RoomAdminPanel getRoomAdminPanel() {
        return roomAdminPanel;
    }

    public void setRoomAdminPanel(RoomAdminPanel roomAdminPanel) {
        this.roomAdminPanel = roomAdminPanel;
    }
}
