package src.app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
import src.logic.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The type Add Owner dialog.
 */
public class AddOwnerDialog extends Application implements EventHandler<ActionEvent> {

    private GridPane grid;
    private Text formTitle, notification;
    private Label forenameLabel, surnameLabel, phoneLabel, emailLabel, peselLabel, ownerTypeLabel,
            companyLabel, nipLabel;
    private TextField  forenameField, surnameField, phoneField, emailField, peselField, companyField, nipField;

    private ComboBox<String> ownerTypeBox;

    private String ownerTypes [] = {  "private", "comercial" };

    private Button accountButton, registerButton, returnButton;

    private Scene scene;

    private Stage registrationStage;

    private Stage previousStage;

    private AllUsersEntity currentUser;

    private PortsEntity currentPort;

    private CaptainsEntity currentCaptain;

    private String cssPath;

    private List<String> messages=  List.of("Required fields are empty!"
             ,"Wrong format of phone number!", "Wrong format of email!", "Wrong format of pesel!",
            "Wrong format of NIP number","Successful action, you added new owner!", "Given email has already been used!",
            "Captain with this pesel already exists in the data base!");

    /**
     * Get text contents vector.
     *
     * @return the vector
     */
    public Vector<String> getTextContents(){
        Vector<String> data = new Vector<>();
        data.add(ownerTypeBox.getValue().toString());
        data.add(phoneField.getText());
        data.add(emailField.getText());
        if(data.get(0).equals("private")){
            data.add(forenameField.getText());
            data.add(surnameField.getText());
            data.add(peselField.getText());
        }
        else{
            data.add(companyField.getText());
            data.add(nipField.getText());
        }
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
     */
    public void start(Stage tempPreviousStage, AllUsersEntity user, PortsEntity port, CaptainsEntity captain) {

        previousStage = tempPreviousStage;

        Stage stage = new Stage();
        registrationStage = stage;

        previousStage.hide();

        currentUser = user;
        currentPort = port;
        currentCaptain = captain;
        stage.setTitle("Add Owner Dialog");
        stage.getIcons().add(
                new Image(
                        WelcomeDialog.class.getResourceAsStream("Logo.png")));

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.addEventFilter(KeyEvent.KEY_PRESSED, this::handleArrowNavigation);

        formTitle = new Text("Add Owner Form");
        formTitle.setId("formatTitle");
        grid.add(formTitle, 0, 0, 2, 1);

        accountButton = new Button("Account Details");
        accountButton.setPrefSize(150, 50);
        accountButton.setOnAction(this);

        grid.add(accountButton, 2, 0);
        grid.setHalignment(accountButton, HPos.RIGHT);

        ownerTypeLabel = new Label("Owner Type: ");
        grid.add(ownerTypeLabel, 0, 1);

        ownerTypeBox = new ComboBox<String>();
        ownerTypeBox.getItems().setAll(
                new String("private"),
                new String("commercial")
        );
        ownerTypeBox.getSelectionModel().selectFirst();
        grid.add(ownerTypeBox, 1, 1);

        phoneLabel = new Label("Phone number: ");
        grid.add(phoneLabel, 0, 2);

        phoneField = new TextField();
        grid.add(phoneField, 1, 2);

        emailLabel = new Label("Email: ");
        grid.add(emailLabel, 0, 3);

        emailField = new TextField();
        grid.add(emailField, 1, 3);



        forenameLabel = new Label("Forename: ");
        grid.add(forenameLabel, 0, 4);

        forenameField = new TextField();
        grid.add(forenameField, 1, 4);

        surnameLabel = new Label("Surname: ");
        grid.add(surnameLabel, 0, 5);

        surnameField = new TextField();
        grid.add(surnameField, 1, 5);

        peselLabel = new Label("Pesel: ");
        grid.add(peselLabel, 0, 6);

        peselField = new TextField();
        grid.add(peselField, 1, 6);


        companyLabel = new Label("Company name: ");
        grid.add(companyLabel, 0, 4);
        companyLabel.setVisible(false);

        companyField = new TextField();
        grid.add(companyField, 1, 4);
        companyField.setVisible(false);

        nipLabel = new Label("NIP: ");
        grid.add(nipLabel, 0, 5);
        nipLabel.setVisible(false);

        nipField = new TextField();
        grid.add(nipField, 1, 5);
        nipField.setVisible(false);

        ownerTypeBox.setOnAction(e -> {
            switch (ownerTypeBox.getValue()) {
                case "private":

                    forenameLabel.setVisible(true);
                    forenameField.setVisible(true);
                    surnameLabel.setVisible(true);
                    surnameField.setVisible(true);
                    peselLabel.setVisible(true);
                    peselField.setVisible(true);


                    companyLabel.setVisible(false);
                    companyField.setVisible(false);
                    nipLabel.setVisible(false);
                    nipField.setVisible(false);
                    break;
                case "commercial":

                    forenameLabel.setVisible(false);
                    forenameField.setVisible(false);
                    surnameLabel.setVisible(false);
                    surnameField.setVisible(false);
                    peselLabel.setVisible(false);
                    peselField.setVisible(false);


                    companyLabel.setVisible(true);
                    companyField.setVisible(true);
                    nipLabel.setVisible(true);
                    nipField.setVisible(true);
                    break;
            }
        });

        registerButton = new Button("Add Owner");
        registerButton.setPrefSize(150, 25);
        registerButton.setOnAction(this);

        grid.add(registerButton, 2, 7);
        registerButton.setPrefSize(150, 50);
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
        phoneField.requestFocus();
    }

    /**
     * The entry point of class AddOwnerDialog
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
        else if (event.getSource() == returnButton) {
            registrationStage.close();
            previousStage.show();
        }
        if (event.getSource() == registerButton) {

            registerButton.setText("Button pressed");
            VisitsWindowActions action = new VisitsWindowActions();
            int message_code = action.addOwner(getTextContents());
            notification.setText(messages.get(message_code));
            if(message_code == 5) {
                Vector<String> data = getTextContents();
                ShipOwnersEntity owner = new ShipOwnersEntity(data.get(2), data.get(3),
                        data.get(0), data.get(1), data.get(4));

                Stage stage = new Stage();
                AddShipDialog addShipDialog = new AddShipDialog();
                addShipDialog.start(stage, currentUser, currentPort, currentCaptain, owner);
                registrationStage.close();
            }
        }
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
