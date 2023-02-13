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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.appActions.LoginWindowActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javafx.util.StringConverter;
import org.hibernate.annotations.Check;
import src.logic.AllUsersEntity;
import src.logic.PortsEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The type Registration dialog.
 */
public class RegistrationDialog extends Application implements EventHandler<ActionEvent> {

    private GridPane grid;

    private Text formTitle, notification;

    private Label userLoginLabel, userPassLabel, userPassConfLabel, userTypeLabel, forenameLabel, surnameLabel, numberLabel, peselLabel, birthdayLabel;

    private TextField userLoginField, forenameField, surnameField, numberField, peselField;
    private TextField userPassVisibleField, userPassVisibleConfField;

    private DatePicker birthdayPicker;
    private final String pattern = "dd/MM/yy";
    private PasswordField userPassField, userPassConfField;

    private CheckBox showPass, showPassConf;

    private ComboBox userTypeBox;
    private Button registerButton;
    private Scene scene;
    private Stage registrationStage;

    private Stage welcomeStage;

    private String cssPath;

    private List<String> messages = List.of("Required fields are empty!", "Username is not available!", "Passwords are different!",
            "Incorrect type of user!", "Wrong format of phone number!", "Wrong format of pesel!", "Wrong format of birthdate!",
            "Successful registration, go to login!");

    /**
     * Gets text contents.
     *
     * @return the text contents
     */
    public Vector<String> getTextContents() {
        Vector<String> data = new Vector<>();
        data.add(userLoginField.getText());
        data.add(userPassField.getText());
        data.add(userPassConfField.getText());
        data.add(userTypeBox.getValue().toString());
        data.add(forenameField.getText());
        data.add(surnameField.getText());
        data.add(numberField.getText());
        data.add(peselField.getText());
        data.add(birthdayPicker.toString());
        return data;
    }

    @Override
    public void start (Stage stage) {}

    /**
     * Start.
     *
     * @param previousStage the previous stage
     * @param stage         the stage
     */
    public void start(Stage previousStage, Stage stage) {
        registrationStage = stage;
        welcomeStage = previousStage;
        stage.setTitle("Registration Dialog");

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.addEventFilter(KeyEvent.KEY_PRESSED, this::handleArrowNavigation);

        formTitle = new Text("Registration Form");
        formTitle.setId("formatTitle");
        grid.add(formTitle, 0, 0, 2, 1);

        userLoginLabel = new Label("Login: ");
        grid.add(userLoginLabel, 0, 1);

        userLoginField = new TextField();
        grid.add(userLoginField, 1, 1);

        showPass = new CheckBox("Show password");
        grid.add(showPass, 1, 3);

        userPassLabel = new Label("Password: ");
        grid.add(userPassLabel, 0, 2);

        userPassVisibleField = new TextField();
        userPassVisibleField.setManaged(false);
        userPassVisibleField.setVisible(false);

        userPassField = new PasswordField();
        showPassword(userPassField, userPassVisibleField, showPass);

        grid.add(userPassField, 1, 2);
        grid.add(userPassVisibleField, 1, 2);


        showPassConf = new CheckBox("Show confirmation password");
        grid.add(showPassConf, 1, 5);

        userPassConfLabel = new Label("Confirm password: ");
        grid.add(userPassConfLabel, 0, 4);

        userPassVisibleConfField = new TextField();
        userPassVisibleConfField.setManaged(false);
        userPassVisibleConfField.setVisible(false);

        userPassConfField = new PasswordField();
        showPassword(userPassConfField, userPassVisibleConfField, showPassConf);

        grid.add(userPassConfField, 1, 4);
        grid.add(userPassVisibleConfField, 1, 4);

        userTypeLabel = new Label("Type of user: ");

        grid.add(userTypeLabel, 0, 6);

        userTypeBox = new ComboBox<String>();
        userTypeBox.getItems().addAll(
                "normal",
                "admin"
        );
        grid.add(userTypeBox, 1, 6);

        forenameLabel = new Label("Forename: ");
        grid.add(forenameLabel, 0, 7);

        forenameField = new TextField();
        grid.add(forenameField, 1, 7);

        surnameLabel = new Label("Surname: ");
        grid.add(surnameLabel, 0, 8);

        surnameField = new TextField();
        grid.add(surnameField, 1, 8);

        numberLabel = new Label("Phone Number: ");
        grid.add(numberLabel, 0, 9);

        numberField = new TextField();
        grid.add(numberField, 1, 9);

        birthdayLabel = new Label("Date of Birth: ");
        grid.add(birthdayLabel, 0, 10);

        Locale.setDefault(Locale.ENGLISH);
        birthdayPicker = new DatePicker();
        birthdayPicker.setConverter(createStringConverter());
        birthdayPicker.setPromptText(pattern.toLowerCase());

        grid.add(birthdayPicker, 1, 10);

        peselLabel = new Label("Pesel: ");
        grid.add(peselLabel, 0, 11);

        peselField = new TextField();
        grid.add(peselField, 1, 11);


        registerButton = new Button("Register");
        registerButton.setPrefSize(150, 25);
        registerButton.setOnAction(this);

        grid.add(registerButton, 1, 13);
        grid.setHalignment(registerButton, HPos.RIGHT);

        notification = new Text();
        notification.setId("notification");
        grid.add(notification, 1, 12);

        scene = new Scene(grid, 600, 575);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                    registerButton.setText("Register button pressed");
                    LoginWindowActions action = new LoginWindowActions();
                    int message_code = action.checkRegData(getTextContents());
                    notification.setText(messages.get(message_code));
                    if (message_code == 7) {
                        action.register(getTextContents(), birthdayPicker.getValue());
                        LoginDialog loginDialog = new LoginDialog();
                        loginDialog.start(welcomeStage, registrationStage);
                    }
            }
        });
        cssPath = this.getClass().getResource("LoginDialog.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        welcomeStage.setScene(scene);
        welcomeStage.setResizable(false);
        welcomeStage.show();
        userLoginField.requestFocus();
    }

    /**
     * The entry point of class RegistrationDialog
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == registerButton) {

            registerButton.setText("Register button pressed");
            LoginWindowActions action = new LoginWindowActions();
            int message_code = action.checkRegData(getTextContents());
            notification.setText(messages.get(message_code));
            if (message_code == 7) {
                action.register(getTextContents(), birthdayPicker.getValue());
                LoginDialog loginDialog = new LoginDialog();
                loginDialog.start(welcomeStage, registrationStage);
                welcomeStage.close();
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