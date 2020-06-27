package hotel.controller.events;

import hotel.business.FacadeFactory;
import hotel.business.model.User;
import hotel.controller.Controller;
import hotel.controller.constants.Constants;
import hotel.gui.LoginView;
import hotel.gui.admin.AdminView;
import hotel.gui.user.UserView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class LoginEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        Controller controller = Controller.getInstance();
        LoginView loginView = controller.getLoginView();
        String username = loginView.getUsernameTextField().getText();
        String password = loginView.getPasswordField().getText();
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            loginView.getMessageLabel().setText(Constants.EMPTY_USERNAME_PASSWORD);
            return;
        }
        User user = FacadeFactory.getFacade().login(username, password);
        if (user != null) {
            //admin, user
            controller.setLoggedUser(user);
            String fullNameOfUser = user.getName() + " " + user.getSurname();
            BorderPane view;
            if ("admin".equals(user.getIdPrivilege().getName())) {
                view = new AdminView();
                controller.setAdminView((AdminView) view);
                controller.getPrimaryStage().setTitle("Admin panel: " + fullNameOfUser);
            } else {
                view = new UserView();
                controller.setUserView((UserView) view);
                controller.getPrimaryStage().setTitle("Employee panel:" + fullNameOfUser);
            }
            Scene scene = new Scene(view, 650, 300);
            controller.getPrimaryStage().setScene(scene);
        } else {
            loginView.getMessageLabel().setText(Constants.BAD_USERNAME_PASSWORD_COMBINATION);
        }
    }

}
