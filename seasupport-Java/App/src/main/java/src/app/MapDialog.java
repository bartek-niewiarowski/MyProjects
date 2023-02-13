package src.app;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.logic.AllUsersEntity;
import src.logic.PortsEntity;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * The type Map dialog.
 */
public class MapDialog extends Application implements EventHandler<ActionEvent> {
    private GridPane grid;
    private Text formTitle, notification;
    private Image map;
    private ImageView view;
    private Button accountButton, returnButton;

    private AllUsersEntity selectedUser;
    private Scene scene;
    private Stage mapStage;
    private String cssPath;

    @Override
    public void start(Stage stage) { }

    /**
     * Start.
     *
     * @param stage the stage
     * @param port  the port
     * @param user  the user
     * @throws IOException the io exception
     */
    public void start(Stage stage, PortsEntity port, AllUsersEntity user) throws IOException {
        mapStage = stage;
        selectedUser = user;
        stage.setTitle("Port: " + port.getPortName() + ", price list");

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        formTitle = new Text(port.getPortName().toString());
        formTitle.setWrappingWidth(400);
        formTitle.setId("formatTitle");
        grid.add(formTitle, 0, 0, 1, 2);

        accountButton = new Button("Account Details");
        accountButton.setPrefSize(150, 50);
        accountButton.setOnAction(this);

        grid.add(accountButton, 1, 0);
        grid.setHalignment(accountButton, HPos.RIGHT);

        map = new Image(WelcomeDialog.class.getResourceAsStream("ExamplePortMap.jpg"));
        view = new ImageView(map);
        view.setX(10);
        view.setY(10);
        view.setFitWidth(575);
        view.setFitWidth(500);
        view.setPreserveRatio(true);
        grid.add(view, 0, 2, 2, 1);

        returnButton = new Button("Return");
        returnButton.setPrefSize(150, 50);
        returnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getSource().equals(returnButton)) {
                    PortDialog portDialog = new PortDialog();
                    portDialog.start(stage, port, user);
                }

            }
        });

        grid.add(returnButton, 1, 3);
        grid.setHalignment(returnButton, HPos.RIGHT);

        notification = new Text();
        notification.setId("notification");
        grid.add(notification, 0, 6);

        scene = new Scene(grid, 600, 575);
        cssPath = this.getClass().getResource("LoginDialog.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        mapStage.setScene(scene);
        mapStage.centerOnScreen();
        stage.setResizable(false);
        mapStage.show();
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
        if (event.getSource() == accountButton) {
            notification.setText("account button pressed");
            @Deprecated
            AccountDialog accountDialog = new AccountDialog();
            accountDialog.start(mapStage, selectedUser);
        }
    }

}
