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
import src.appActions.LoginWindowActions;

import java.util.List;
import java.util.Vector;
import javafx.util.StringConverter;
import org.hibernate.annotations.Check;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import src.appActions.VisitsWindowActions;
import src.logic.*;

/**
 * The type CreateVisitDialog.
 */
public class CreateVisitDialog extends Application implements EventHandler<ActionEvent> {

    private GridPane grid;

    private Text formTitle, notification;

    private Label numberLabel, beginLabel, endLabel, captainLabel;

    private Label newShipLabel, newCapitanLabel;

    private TextField  numberField, captainField;

    private DatePicker beginPicker, endPicker;
    private final String pattern = "dd/MM/yy";
    private Button accountButton, registerButton, newShipButton, newCaptainButton, returnButton;

    private Scene scene;

    private Stage registrationStage;

    private Stage previousStage;

    private String cssPath;

    private AllUsersEntity currentUser;

    private PortsEntity currentPort;

    private CaptainsEntity currentCaptain;

    private ShipsEntity currentShip;

    private List<String> messages=  List.of("Required fields are empty!", "Wrong format of pesel!",
            "Visit can not start in that day!", "Visit can not end in that day!",
            "Wrong call sign of ship!", "Wrong Captain id!","Successful operation, you added new visit in this port!",
            "There is no available places in this port.", "Select the start and end date of your visit!",
            "You've already booked a place for this date.", "On that day the ship will be in a different port.");

    /**
     * Get text contents vector.
     *
     * @return the vector
     */
    public Vector<String> getTextContents(){
        Vector<String> data = new Vector<>();
        data.add(beginPicker.toString());
        data.add(endPicker.toString());
        data.add(numberField.getText());
        data.add(captainField.getText());
        return data;
    }

    @Override
    public void start(Stage stage) {
    }

    /**
     * Start.
     *
     * @param stage   the stage
     * @param user    the user
     * @param port    the port
     * @param ship    the ship
     * @param captain the captain
     */
    public void start(Stage stage, AllUsersEntity user, PortsEntity port, ShipsEntity ship, CaptainsEntity captain) {
        registrationStage = stage;

        currentUser = user;
        currentPort = port;
        currentCaptain = captain;
        currentShip = ship;
        stage.setTitle("New Visit Dialog");
        stage.getIcons().add(
                new Image(
                        WelcomeDialog.class.getResourceAsStream("Logo.png"))
        );
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.addEventFilter(KeyEvent.KEY_PRESSED, this::handleArrowNavigation);


        formTitle = new Text("New Visit Form");
        formTitle.setId("formatTitle");
        grid.add(formTitle, 0, 0, 2, 1);

        accountButton = new Button("Account Details");
        accountButton.setPrefSize(150, 50);
        accountButton.setOnAction(this);

        grid.add(accountButton, 2, 0);
        grid.setHalignment(accountButton, HPos.RIGHT);

        beginLabel = new Label("Start date of the visit: ");
        grid.add(beginLabel, 0, 1);

        Locale.setDefault(Locale.ENGLISH);
        beginPicker = new DatePicker();
        beginPicker.setConverter(createStringConverter());
        beginPicker.setPromptText(pattern.toLowerCase());

        grid.add(beginPicker, 1, 1);

        endLabel = new Label("End date of the visit: ");
        grid.add(endLabel, 0, 2);

        Locale.setDefault(Locale.ENGLISH);
        endPicker = new DatePicker();
        endPicker.setConverter(createStringConverter());
        endPicker.setPromptText(pattern.toLowerCase());

        grid.add(endPicker, 1, 2);

        newShipLabel = new Label("If you have to add ship: ");
        grid.add(newShipLabel, 1, 3);

        newShipButton = new Button("Add New Ship");
        newShipButton.setPrefSize(150,25);
        newShipButton.setOnAction(this);

        grid.add(newShipButton, 2, 3);
        grid.setHalignment(newShipButton, HPos.RIGHT);

        numberLabel = new Label("Ship Call Sign: ");
        grid.add(numberLabel, 0, 4);

        numberField = new TextField();
        if (ship.getCallSign() != null)
            numberField.setText(ship.getCallSign());
        grid.add(numberField, 1, 4);

        newCapitanLabel = new Label("If you have to add capitan: ");
        grid.add(newCapitanLabel, 1, 5);

        newCaptainButton = new Button("Add New Captian");
        newCaptainButton.setPrefSize(150, 25);
        newCaptainButton.setOnAction(this);

        grid.add(newCaptainButton, 2, 5);
        grid.setHalignment(newCaptainButton, HPos.RIGHT);

        captainLabel = new Label("Visit's Captian Id: ");
        grid.add(captainLabel, 0, 6);

        captainField = new TextField();
        if (captain.getCaptainId() != null)
            captainField.setText(Integer.toString(captain.getCaptainId()));
        grid.add(captainField, 1, 6);

        registerButton = new Button("Create Visit");
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
        beginPicker.requestFocus();
    }

    /**
     * The entry point of class Create Visit Dialog
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
            accountDialog.start(registrationStage, currentUser);
        }

        if (event.getSource() == newCaptainButton) {
            AddCaptainDialog capDialog = new AddCaptainDialog();
            capDialog.start(registrationStage, currentUser, currentPort, currentShip);
        }

        if (event.getSource() == newShipButton) {
            AddShipDialog shipDialog = new AddShipDialog();
            ShipOwnersEntity owner = new ShipOwnersEntity();
            shipDialog.start(registrationStage, currentUser, currentPort, currentCaptain, owner);
        }

        if (event.getSource() == returnButton) {
            Stage next = new Stage();
            OpenDocksDialog dial = new OpenDocksDialog();
            dial.start(next, currentPort, currentUser);
            registrationStage.close();
        }

        if (event.getSource() == registerButton) {

            registerButton.setText("Add visit button pressed");
            VisitsWindowActions action = new VisitsWindowActions();
            int message_code = action.addVisit(getTextContents(), beginPicker.getValue(), endPicker.getValue(), currentPort, currentUser);
            notification.setText(messages.get(message_code));
            if(message_code == 5) {
                PortDialog portDialog = new PortDialog();
                portDialog.start(registrationStage, currentPort, currentUser);
            }
        }
    }

    /**
     * Create string converter string converter.
     *
     * @return the string converter
     */
    public StringConverter createStringConverter() {
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

        return converter;
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
