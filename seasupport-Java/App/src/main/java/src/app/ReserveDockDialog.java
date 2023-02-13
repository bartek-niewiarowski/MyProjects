package src.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.logic.PortsEntity;

/**
 * The type Reserve dock dialog.
 */
public class ReserveDockDialog extends Application implements EventHandler<ActionEvent> {
    private GridPane grid;
    private Text formTitle, notification;

    private Label smallDockLabel, bigDockLabel;
    private Button loginButton, registrationButton;
    private Scene scene;
    private String cssPath;
    @Override
    public void start(Stage stage) { }

    /**
     * Start.
     *
     * @param stage the stage
     * @param port  the port
     * @param isBig the is big
     */
    public void start(Stage stage, PortsEntity port, Boolean isBig) {
        stage.setTitle("Reserve Dock Dialog");
        stage.getIcons().add(
                new Image(
                        WelcomeDialog.class.getResourceAsStream("Logo.png")));

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        formTitle = new Text(port.getPortName());
        formTitle.setId("formatTitle");
        grid.add(formTitle, 0, 0, 1,1);
        grid.setHalignment(formTitle, HPos.CENTER);

        loginButton = new Button("Sign In");
        loginButton.setPrefSize(400, 50);
        loginButton.setOnAction(this);
        grid.add(loginButton, 0, 3);
        grid.setHalignment(loginButton, HPos.CENTER);

        registrationButton = new Button("Create a free account");
        registrationButton.setPrefSize(400, 50);
        registrationButton.setOnAction(this);
        grid.add(registrationButton, 0, 4);
        grid.setHalignment(registrationButton, HPos.CENTER);

        notification = new Text();
        notification.setId("notification");
        grid.add(notification, 1, 6);

        scene = new Scene(grid, 600, 575);
        cssPath = this.getClass().getResource("LoginDialog.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * The entry point of class LoginDialog
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
    }
}

