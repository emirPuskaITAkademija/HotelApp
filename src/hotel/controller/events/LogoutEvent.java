package hotel.controller.events;

import hotel.controller.Controller;
import hotel.controller.constants.Constants;
import hotel.gui.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class LogoutEvent implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent event) {
        Controller controller = Controller.getInstance();
        controller.setLoggedUser(null);
        LoginView loginView = new LoginView();
        controller.setLoginView(loginView);
        Scene scene = new Scene(loginView, 650, 180);
        controller.getPrimaryStage().setTitle(Constants.LOGIN_TITLE);
        controller.getPrimaryStage().setScene(scene);
    } 
}
