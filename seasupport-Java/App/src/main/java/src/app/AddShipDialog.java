package src.app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.tinylog.Logger;
import src.appActions.LoginWindowActions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.appActions.VisitsWindowActions;

import java.util.List;
import java.util.Vector;
import javafx.util.StringConverter;
import org.hibernate.annotations.Check;
import src.logic.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The type Add Ship Dialog.
 */
public class AddShipDialog extends Application implements EventHandler<ActionEvent> {

    private GridPane grid;

    private Text formTitle, notification;

    private Label callSignLabel, shipNameLabel, typeLabel, shipLenghtLabel, ownerIdLabel, newOwnerLabel;

    private TextField callSignField, shipNameField, lenghtField, ownerIdField;

    private ComboBox shipTypeBox;

    private Button accountButton, registerButton, newOwnerButton, returnButton;

    private final String pattern = "dd/MM/yy";

    private Scene scene;

    private Stage registrationStage;

    private Stage previousStage;

    private AllUsersEntity currentUser;
    private PortsEntity currentPort;

    private CaptainsEntity currentCaptain;

    private ShipOwnersEntity currentOwner;

    private ShipsEntity newShip;

    private String cssPath;


    private List<String> messages=  List.of("Required fields are empty!", "Call Sign is not available!",
            "Incorrect type of ship!", "Length of the ship should be a number",
            "Ship is to long you can not booked place in this port",
            "Successful action, you added new ship into your account!",
            "Incorrect owner Id.");

    /**
     * Get text contents vector.
     *
     * @return the vector
     */
    public Vector<String> getTextContents(){
        Vector<String> data = new Vector<>();
        data.add(callSignField.getText());
        data.add(shipNameField.getText());
        data.add(shipTypeBox.getValue().toString());
        data.add(lenghtField.getText());
        data.add(ownerIdField.getText());
        return data;
    }
    @Override
    public void start(Stage stage) { }

    /**
     * Start.
     *
     * @param tempPreviousStage the temp previous stage
     * @param user              the user
     * @param port              the port
     * @param captain           the captain
     * @param owner             the owner
     */
    public void start(Stage tempPreviousStage, AllUsersEntity user, PortsEntity port, CaptainsEntity captain, ShipOwnersEntity owner) {
        previousStage = tempPreviousStage;

        Stage stage = new Stage();
        registrationStage = stage;

        previousStage.hide();

        currentUser = user;
        currentPort = port;
        currentCaptain = captain;
        currentOwner = owner;

        stage.setTitle("Registration Dialog");
        stage.getIcons().add(
                new Image(
                        WelcomeDialog.class.getResourceAsStream("Logo.png")));

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.addEventFilter(KeyEvent.KEY_PRESSED, this::handleArrowNavigation);

        accountButton = new Button("Account Details");
        accountButton.setPrefSize(150, 50);
        accountButton.setOnAction(this);

        grid.add(accountButton, 2, 0);
        grid.setHalignment(accountButton, HPos.RIGHT);

        formTitle = new Text("Add Ship");
        formTitle.setId("formatTitle");
        grid.add(formTitle, 0, 0, 2, 1);

        callSignLabel = new Label("Call Sign: ");
        grid.add(callSignLabel, 0, 1);

        //callSignLabel.setVisible(false);

        callSignField = new TextField();
        grid.add(callSignField, 1, 1);

        //callSignField.setVisible(false);

        shipNameLabel = new Label("Ship Name: ");
        grid.add(shipNameLabel, 0, 2);

        shipNameField = new TextField();
        grid.add(shipNameField, 1, 2);

        //showPass = new CheckBox("Show password");
        //grid.add(showPass, 1, 3);

        typeLabel = new Label("Ship Type: ");
        grid.add(typeLabel, 0, 3);

        shipTypeBox = new ComboBox();
        shipTypeBox.getItems().addAll(
            "sailing_boat",
                "motor_boat"
        );
        grid.add(shipTypeBox, 1, 3);

        shipLenghtLabel = new Label("Ship Length: ");
        grid.add(shipLenghtLabel, 0, 4);

        lenghtField = new TextField();
        grid.add(lenghtField, 1, 4);

        newOwnerLabel = new Label("If you have to add owner: ");
        grid.add(newOwnerLabel, 1, 5);

        newOwnerButton = new Button("Add new owner");
        newOwnerButton.setPrefSize(150, 25);
        newOwnerButton.setOnAction(this);

        grid.add(newOwnerButton, 2, 5);
        grid.setHalignment(newOwnerButton, HPos.RIGHT);

        ownerIdLabel = new Label("Ship Owner Id: ");
        if(currentOwner.getShipOwnerId() != null)
            ownerIdField.setText(Integer.toString(currentOwner.getShipOwnerId()));
        grid.add(ownerIdLabel, 0, 6);

        ownerIdField = new TextField();
        grid.add(ownerIdField, 1, 6);

        registerButton = new Button("Add Ship");
        registerButton.setPrefSize(150, 50);
        registerButton.setOnAction(this);

        grid.add(registerButton, 2, 7);
        grid.setHalignment(registerButton, HPos.RIGHT);


        returnButton = new Button("Return");
        returnButton.setPrefSize(150, 50);
        returnButton.setOnAction(this);
        grid.add(returnButton, 2, 8);

        notification = new Text();
        notification.setId("notification");
        grid.add(notification, 1, 9);

        scene = new Scene(grid, 600, 575);
        cssPath = this.getClass().getResource("LoginDialog.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        callSignField.requestFocus();
    }

    /**
     * The entry point of class AddShipDialog
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == accountButton) {
            //notification.setText("account button pressed");
            @Deprecated
            AccountDialog accountDialog = new AccountDialog();
            accountDialog.start(registrationStage, currentUser);
        }
        else if (event.getSource() == newOwnerButton) {
            //Stage stage = new Stage();
            AddOwnerDialog ownerDialog = new AddOwnerDialog();
            ownerDialog.start(registrationStage, currentUser, currentPort, currentCaptain);
            //registrationStage.close();
        }
        else if (event.getSource() == returnButton) {
            registrationStage.close();
            previousStage.show();
        }
        if (event.getSource() == registerButton) {

            VisitsWindowActions action = new VisitsWindowActions();
            int message_code = action.addShip(getTextContents());
            notification.setText(messages.get(message_code));
            if(message_code == 5) {

                Logger.info("{} added new ship", currentUser.getLogin());
                Vector<String> data = getTextContents();
                ShipOwnersEntity owner = DataBase.getInstance().getOwner(data.get(4));
                short len = Short.valueOf(data.get(3));
                ShipsEntity ship = new ShipsEntity(data.get(0), data.get(1), data.get(2), len, owner);
                Stage stage = new Stage();
                newShip = ship;
                CreateVisitDialog createVisitDialog = new CreateVisitDialog();

                createVisitDialog.start(stage, currentUser, currentPort, newShip, currentCaptain);
                registrationStage.close();
            }
        }
    }

    /**
     * Show password.
     *
     * @param field the field
     * @param text  the text
     * @param box   the box
     */
    public void showPassword(PasswordField field, TextField text, CheckBox box) {
        text.managedProperty().bind(box.selectedProperty());
        text.visibleProperty().bind(box.selectedProperty());

        field.managedProperty().bind(box.selectedProperty().not());
        field.visibleProperty().bind(box.selectedProperty().not());

        text.textProperty().bindBidirectional(field.textProperty());
    }


    /**
     * Gets nodes by coordinate.
     *
     * @param row    the row
     * @param column the column
     * @return the nodes by coordinate
     */
    public List<Node> getNodesByCoordinate(Integer row, Integer column) {
        List<Node> matchingNodes = new ArrayList<>();
        for (Node node : grid.getChildren()) {
            if(grid.getRowIndex(node) == row && grid.getColumnIndex(node) == column && (node instanceof TextField ||
                    node instanceof CheckBox || node instanceof DatePicker || node instanceof ComboBox) ) {
                matchingNodes.add(node);
            }
        }
        return matchingNodes;
    }


    /**
     * Handle arrow navigation.
     *
     * @param event the event
     */
    public void handleArrowNavigation(KeyEvent event) {
        Node source = (Node) event.getSource(); // the GridPane
        Node focused = source.getScene().getFocusOwner();
        if (event.getCode().isArrowKey() && focused.getParent() == source) {

            int row = grid.getRowIndex(focused);
            int col = grid.getColumnIndex(focused);
            // Switch expressions were standardized in Java 14
            switch (event.getCode()) {
                case LEFT: {
                    if (col < grid.getColumnCount() - 1) {
                        List<Node> newFocused = getNodesByCoordinate(row, col + 1);
                        if(newFocused.size() > 0)
                            newFocused.get(0).requestFocus();
                    }
                }
                break;
                case RIGHT: {
                    if (col > 0) {
                        List<Node> newFocused = getNodesByCoordinate(row, col - 1);
                        if(newFocused.size() > 0)
                            newFocused.get(0).requestFocus();
                    }
                }
                break;
                case UP: {
                    if (row > 0) {
                        List<Node> newFocused = getNodesByCoordinate(row - 1, col);
                        if(newFocused.size() > 0)
                            newFocused.get(0).requestFocus();
                    }
                }
                break;
                case DOWN: {
                    if (row < grid.getRowCount() - 1) {
                        List<Node> newFocused = getNodesByCoordinate(row + 1, col);
                        if(newFocused.size() > 0)
                            newFocused.get(0).requestFocus();
                    }
                }break;
            }
            event.consume();
        }
    }

}
