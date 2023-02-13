package src.app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Pair;
import src.appActions.VisitsWindowActions;
import src.logic.AllUsersEntity;
import src.logic.CaptainsEntity;
import src.logic.PortsEntity;
import src.logic.ShipsEntity;

import java.sql.Date;
import java.util.Calendar;

/**
 * The type Open docks dialog.
 */
public class OpenDocksDialog extends Application implements EventHandler<ActionEvent> {
    private GridPane grid;
    private Text formTitle, notification;
    private Label yachtLength, smallShipsLabel, bigShipsLabel;

    private Text smallShipsText, bigShipsText;
    private Button accountButton, smallShipsButton, bigShipsButton, returnButton;

    private PortsEntity selectedPort;

    private AllUsersEntity selectedUser;

    private Stage openDocksStage;
    private Scene scene;
    private String cssPath;

    private final ObservableList<PortsEntity> data =
            FXCollections.observableArrayList(
                    new PortsEntity()
            );

    @Override
    public void start(Stage stage) {
    }

    /**
     * Start.
     *
     * @param stage the stage
     * @param port  the port
     * @param user  the user
     */
    public void start(Stage stage, PortsEntity port, AllUsersEntity user) {
        selectedUser = user;
        selectedPort = port;
        openDocksStage = stage;
        stage.setTitle("Port: " + port.getPortName() + ",open docks");
        stage.getIcons().add(
                new Image(
                        WelcomeDialog.class.getResourceAsStream("Logo.png")));
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.getColumnConstraints().add(new ColumnConstraints(250));
        grid.getColumnConstraints().add(new ColumnConstraints(50));
        grid.getColumnConstraints().add(new ColumnConstraints(200));

        formTitle = new Text(port.getPortName());
        formTitle.setWrappingWidth(350);
        formTitle.setId("formatTitle");
        grid.add(formTitle, 0, 0, 1, 2);

        accountButton = new Button("Account Details");
        accountButton.setPrefSize(150, 50);
        accountButton.setOnAction(this);

        grid.add(accountButton, 2, 0);
        grid.setHalignment(accountButton, HPos.CENTER);

        yachtLength = new Label("Places for yacht with hull length:");
        grid.add(yachtLength, 0, 2);

        smallShipsLabel = new Label("Up to 18 meters");
        grid.add(smallShipsLabel, 0,3);

        VisitsWindowActions action = new VisitsWindowActions();
        Date today = new Date(Calendar.getInstance().getTime().getTime());
        Pair<Integer, Integer> places = action.getBookedPlacesNow(selectedPort, today);
        smallShipsText = new Text(Integer.toString(selectedPort.getPlacesShipsSmall() - places.getKey()));
        smallShipsText.setTextAlignment(TextAlignment.CENTER);
        grid.add(smallShipsText, 1, 3);

        smallShipsButton = new Button("Reserve");
        smallShipsButton.setPrefSize(175, 25);

        smallShipsButton.setOnAction(actionEvent -> {
            if(!(selectedPort.getPlacesShipsSmall() > 0)) {
                notification.setText("All small docks are booked!");
                return;
            }
            smallShipsText.setText(Integer.toString(selectedPort.getPlacesShipsSmall() - places.getValue()));
            CreateVisitDialog dial = new CreateVisitDialog();
            ShipsEntity ship = new ShipsEntity();

            CaptainsEntity captain = new CaptainsEntity();
            dial.start(openDocksStage, selectedUser, selectedPort, ship, captain);
        });

        grid.add(smallShipsButton, 2, 3);
        grid.setHalignment(smallShipsButton, HPos.CENTER);

        bigShipsLabel = new Label("Over 18 meters");
        grid.add(bigShipsLabel, 0,4);

        bigShipsText = new Text(Integer.toString(selectedPort.getPlacesShipsBig()));
        bigShipsText.setTextAlignment(TextAlignment.CENTER);
        grid.add(bigShipsText, 1, 4);

        bigShipsButton = new Button("Reserve");
        bigShipsButton.setPrefSize(175, 25);

        bigShipsButton.setOnAction(actionEvent -> {
            if(!(selectedPort.getPlacesShipsBig() > 0)) {
                notification.setText("All big docks are booked!");
                return;
            }
            bigShipsText.setText(Integer.toString(selectedPort.getPlacesShipsBig()));
            CreateVisitDialog dial = new CreateVisitDialog();
            ShipsEntity ship = new ShipsEntity();

            CaptainsEntity captain = new CaptainsEntity();
            dial.start(openDocksStage, selectedUser, selectedPort, ship, captain);
        });

        grid.add(bigShipsButton, 2, 4);
        grid.setHalignment(bigShipsButton, HPos.CENTER);

        returnButton = new Button("Return");
        returnButton.setPrefSize(150, 50);
        returnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getSource().equals(returnButton)) {
                    PortDialog portDialog = new PortDialog();
                    portDialog.start(stage, port, selectedUser);
                }

            }
        });

        grid.add(returnButton, 2, 6);
        returnButton.setPrefSize(150, 50);
        grid.setHalignment(returnButton, HPos.CENTER);

        notification = new Text();
        notification.setId("notification");
        grid.add(notification, 2, 7);

        scene = new Scene(grid, 600, 575);
        cssPath = this.getClass().getResource("LoginDialog.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        stage.setScene(scene);
        stage.centerOnScreen();
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
        if (event.getSource() == accountButton) {
            notification.setText("account button pressed");
            @Deprecated
            AccountDialog accountDialog = new AccountDialog();
            accountDialog.start(openDocksStage, selectedUser);
        }
    }
}