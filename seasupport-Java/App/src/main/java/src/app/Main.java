package src.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import src.logic.CaptainsEntity;
import src.logic.ShipOwnersEntity;
import src.logic.ShipsEntity;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Class Main
 *
 * @author ewa.miazga
 */
public class Main extends Application {

    /**
     * primary Stage
     */
    /**+
     * The func that run application
     *
     * @param stage
     */
    public void start(Stage stage) {
        stage = new Stage();
        SplashDialog splashDialog = new SplashDialog();
        try {
            splashDialog.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*stage = new Stage();

        WelcomeDialog welcomeDialog = new WelcomeDialog();
        welcomeDialog.start(stage);*/
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        DataBase.getInstance(); // connecting with database
        launch(args);
        DataBase.disableConnection(); // disconnecting with database
    }
}