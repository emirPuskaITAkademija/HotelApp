package hotel.controller;

import hotel.business.model.User;
import hotel.controller.events.EventBus;
import hotel.gui.LoginView;
import hotel.gui.admin.AdminView;
import hotel.gui.user.UserView;
import javafx.stage.Stage;

/**
 * SINGLETON pattern.
 *
 *
 * @author grupa 1
 */
public class Controller {

    //MODEL 
    private User loggedUser;
    //VIEW
    private Stage primaryStage;
    private LoginView loginView;
    private UserView userView;
    private AdminView adminView;
    private final EventBus eventBus = new EventBus();

    private Controller() {

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void setAdminView(AdminView adminView) {
        this.adminView = adminView;
    }

    public AdminView getAdminView() {
        return adminView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public UserView getUserView() {
        return userView;
    }

    private static Controller INSTANCE;

    public static Controller getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }

}
