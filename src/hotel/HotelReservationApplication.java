package hotel;

import hotel.controller.Controller;
import hotel.controller.constants.Constants;
import hotel.gui.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * 
 * 1. Glavni kontejner i kontejneri(JFrame, JPanel...)
 * <li>
 * 2. UI controls (JButton, JCheckBox, JRadioButton...)
 * <lI>
 * 3. LayoutManagers(BorderLayout, GridLayout, FlowLayout, SpringLayout, GridBagLayout,
 * BoxLayout...)
 * 
 * <p>
 * JavaFx
 * <li> 1. Kontejner i manager BorderPane(1 i 3 su stopljene u jednu), StackPane, GridPane
 * 
 * @author grupa 1
 */
public class HotelReservationApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        Controller.getInstance().setPrimaryStage(primaryStage);
        LoginView loginView = new LoginView();
        Controller.getInstance().setLoginView(loginView);
        
        Scene scene = new Scene(loginView, 650, 180);
        
        primaryStage.setTitle(Constants.LOGIN_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
