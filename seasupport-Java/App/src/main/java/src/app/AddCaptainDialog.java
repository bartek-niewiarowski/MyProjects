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
import src.appActions.VisitsWindowActions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import src.logic.*;

/**
 * The type add Captain dialog.
 */
public class AddCaptainDialog extends Application implements EventHandler<ActionEvent> {

    private GridPane grid;

    private Text formTitle, notification;

    private Label  forenameLabel, surnameLabel, peselLabel;

    private TextField forenameField, surnameField, peselField;

    private CheckBox showPass;
    private Button accountButton, registerButton, returnButton;

    private final String pattern = "dd/MM/yy";

    private Scene scene;

    private Stage registrationStage;

    private Stage previousStage;

    private AllUsersEntity currentUser;

    private PortsEntity currentPort;

    private ShipsEntity currentShip;

    private String cssPath;


    private List<String> messages=  List.of("Required fields are empty!", "Wrong format of pesel!",
            "Successful action, you added new Captain!", "Captain with this pesel already exists in the data base!");

    /**
     * Get text contents vector.
     *
     * @return the vector
     */
    public Vector<String> getTextContents(){
        Vector<String> data = new Vector<>();
        data.add(forenameField.getText());
        data.add(surnameField.getText());
        data.add(peselField.getText());
        return data;
    }

    @Override
    public void start(Stage stage) {
    }

    /**
     * Start.
     *
     * @param tempPreviousStage the temp previous stage
     * @param user              the user
     * @param port              the port
     * @param ship              the ship
     */
    public void start(Stage tempPreviousStage, AllUsersEntity user, PortsEntity port, ShipsEntity ship) {
        previousStage = tempPreviousStage;

        Stage stage = new Stage();
        registrationStage = stage;

        previousStage.hide();

        currentUser = user;
        currentPort = port;
        currentShip = ship;
        stage.setTitle("Add Captain Dialog");
        stage.getIcons().add(
                new Image(
                        WelcomeDialog.class.getResourceAsStream("Logo.png")));

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.addEventFilter(KeyEvent.KEY_PRESSED, this::handleArrowNavigation);

        formTitle = new Text("Add Captain Form");
        formTitle.setId("formatTitle");
        grid.add(formTitle, 0, 0, 2, 1);

        accountButton = new Button("Account Details");
        accountButton.setPrefSize(150, 50);
        accountButton.setOnAction(this);

        grid.add(accountButton, 2, 0);
        grid.setHalignment(accountButton, HPos.RIGHT);


        showPass = new CheckBox("I'm the Captain of the ship");
        grid.add(showPass, 0, 1);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e)
            {
                if (showPass.isSelected()) {
                    showPass.setVisible(false);
                    forenameField.setText(currentUser.getForename());
                    surnameField.setText(currentUser.getSurname());
                    peselField.setText(currentUser.getPesel());
                }
            }
        };
        showPass.setOnAction(event);

        forenameLabel = new Label("Forename: ");
        grid.add(forenameLabel, 0, 2);

        forenameField = new TextField();
        grid.add(forenameField, 1, 2);

        surnameLabel = new Label("Surname: ");
        grid.add(surnameLabel, 0, 3);

        surnameField = new TextField();
        grid.add(surnameField, 1, 3);

        peselLabel = new Label("Pesel: ");
        grid.add(peselLabel, 0, 4);

        peselField = new TextField();
        grid.add(peselField, 1, 4);


        registerButton = new Button("Add Captain");
        registerButton.setPrefSize(150, 50);
        registerButton.setOnAction(this);

        grid.add(registerButton, 2, 5);
        grid.setHalignment(registerButton, HPos.RIGHT);

        returnButton = new Button("Return");
        returnButton.setPrefSize(150, 50);
        returnButton.setOnAction(this);
        grid.add(returnButton, 2, 6);

        notification = new Text();
        notification.setId("notification");
        grid.add(notification, 1, 7);

        scene = new Scene(grid, 600, 575);
        cssPath = this.getClass().getResource("LoginDialog.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        forenameField.requestFocus();
    }

    /**
     * The entry point of class AddCaptainDialog
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
        else if (event.getSource() == returnButton) {
            registrationStage.close();
            previousStage.show();
        }
        else if (event.getSource() == registerButton) {
            registerButton.setText("Button pressed");
            VisitsWindowActions action = new VisitsWindowActions();
            int message_code = action.addCaptian(getTextContents());
            notification.setText(messages.get(message_code));
            if(message_code == 2) {

                Vector<String> data = getTextContents();
                CaptainsEntity newCaptain = new CaptainsEntity(data.get(0), data.get(1), data.get(2));

                Stage stage = new Stage();
                CreateVisitDialog createVisitDialog = new CreateVisitDialog();

                createVisitDialog.start(stage, currentUser, currentPort, currentShip, newCaptain);
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
