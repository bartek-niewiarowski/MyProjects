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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * The type Welcome dialog.
 */
public class WelcomeDialog extends Application implements EventHandler<ActionEvent> {
    private GridPane grid;
    private Text formTitle, notification;
    private Label whoAreYouLabel;
    private Button loginButton, registrationButton;
    private Scene scene;
    private Stage welcomeStage;
    private String cssPath;

    @Override
    public void start(Stage stage) {
        welcomeStage = new Stage();
        welcomeStage.setTitle("Welcome Dialog");
        welcomeStage.getIcons().add(
                new Image(
                        WelcomeDialog.class.getResourceAsStream("Logo.png")));

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        formTitle = new Text("Welcome");
        formTitle.setId("formatTitle");
        grid.add(formTitle, 0, 0, 1,1);
        grid.setHalignment(formTitle, HPos.CENTER);

        whoAreYouLabel = new Label("Join us!");
        grid.add(whoAreYouLabel, 0, 1);
        grid.setHalignment(whoAreYouLabel, HPos.CENTER);

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
        welcomeStage.setScene(scene);
        welcomeStage.setResizable(false);
        welcomeStage.show();
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
        if (event.getSource() == loginButton) {
            Stage stage = new Stage();
            LoginDialog loginDialog = new LoginDialog();
            loginDialog.start(welcomeStage, stage);
        }
        else if (event.getSource() == registrationButton) {
            Stage stage = new Stage();
            RegistrationDialog registrationDialog = new RegistrationDialog();
            registrationDialog.start(welcomeStage, stage);
        }
    }
}
