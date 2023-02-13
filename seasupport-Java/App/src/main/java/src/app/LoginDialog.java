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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.appActions.LoginWindowActions;
import src.logic.AllUsersEntity;
import src.logic.PortsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class LoginDialog
 *
 * @author ewa.miazga
 */
public class LoginDialog extends Application implements EventHandler<ActionEvent> {
    private GridPane grid;

    private Text formTitle, notification;
    private Label userLoginLabel, userPassLabel;
    private TextField userLoginField, userPassVisibleField;
    private PasswordField userPassField;
    private CheckBox showPass;
    private Button signInButton;
    private Scene scene;
    private Stage loginStage;
    private Stage welcomeStage;
    private String cssPath;
    @Override
    public void start(Stage stage) { }

    /**
     * Start.
     *
     * @param previousStage the previous stage
     * @param stage         the stage
     */
    public void start(Stage previousStage, Stage stage) {
        loginStage = stage;
        welcomeStage = previousStage;
        loginStage.setTitle("Login Dialog");
        loginStage.getIcons().add(
                new Image(
                        LoginDialog.class.getResourceAsStream("Logo.png")));

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.addEventFilter(KeyEvent.KEY_PRESSED, this::handleArrowNavigation);

        formTitle = new Text("Login Form");
        formTitle.setId("formatTitle");
        grid.add(formTitle, 0, 0, 2, 1);

        userLoginLabel = new Label("Login: ");
        grid.add(userLoginLabel, 0, 1);

        userLoginField = new TextField();
        grid.add(userLoginField, 1, 1);

        userPassLabel = new Label("Password: ");
        grid.add(userPassLabel, 0, 2);

        userPassVisibleField = new TextField();
        userPassVisibleField.setManaged(false);
        userPassVisibleField.setVisible(false);
        userPassField = new PasswordField();

        grid.add(userPassField, 1, 2);
        grid.add(userPassVisibleField, 1, 2);

        showPass = new CheckBox("Show password");
        showPass.setAlignment(Pos.CENTER_RIGHT);
        grid.add(showPass, 1, 3);

        userPassVisibleField.managedProperty().bind(showPass.selectedProperty());
        userPassVisibleField.visibleProperty().bind(showPass.selectedProperty());

        userPassField.managedProperty().bind(showPass.selectedProperty().not());
        userPassField.visibleProperty().bind(showPass.selectedProperty().not());

        userPassVisibleField.textProperty().bindBidirectional(userPassField.textProperty());

        signInButton = new Button("Login");
        signInButton.setPrefSize(150, 25);
        signInButton.setOnAction(this);

        signInButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getSource() == signInButton) {
                    notification.setText("Sign in button pressed");
                    LoginWindowActions action = new LoginWindowActions();
                    AllUsersEntity currentUser = action.login(userLoginField.getText(), userPassField.getText());
                    if(currentUser != null){
                        PortsEntity port = action.userInPort(currentUser);
                        if(port != null){
                            PortDialog portDialog = new PortDialog();
                            portDialog.start(loginStage, port, currentUser);
                            welcomeStage.close();
                        }
                        else{
                            PortsDialog portsDialog = new PortsDialog();
                            portsDialog.start(loginStage, currentUser);
                            welcomeStage.close();
                        }
                    }
                    else{
                        notification.setText("Invalid login or password");
                    }
                }

            }
        });

        grid.add(signInButton, 1, 4);
        grid.setHalignment(signInButton, HPos.RIGHT);

        notification = new Text();
        notification.setId("notification");
        grid.add(notification, 1, 6);

        scene = new Scene(grid, 300, 275);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                System.out.println("A key was pressed");
                notification.setText("Sign in button pressed");
                LoginWindowActions action = new LoginWindowActions();
                AllUsersEntity currentUser = action.login(userLoginField.getText(), userPassField.getText());
                if(currentUser != null){
                    PortsEntity port = action.userInPort(currentUser);
                    if(port != null){
                        PortDialog portDialog = new PortDialog();
                        portDialog.start(loginStage, port, currentUser);
                        welcomeStage.close();
                    }
                    else{
                        PortsDialog portsDialog = new PortsDialog();
                        portsDialog.start(loginStage, currentUser);
                        welcomeStage.close();
                    }
                }
                else{
                    notification.setText("Invalid login or password");
                }
            }
        });

        cssPath = this.getClass().getResource("LoginDialog.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        loginStage.setScene(scene);
        loginStage.centerOnScreen();
        loginStage.setResizable(false);
        loginStage.show();
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
        //TODO:
        //Write function, which will check if login and password are equal from those from
        //database @michał
        /*if (event.getSource() == signInButton) {
            notification.setText("Sign in button pressed");
            LoginWindowActions action = new LoginWindowActions();
            AllUsersEntity currentUser = action.login(userLoginField.getText(), userPassField.getText());
            if(currentUser != null){
                PortsEntity port = action.userInPort(currentUser);
                if(port != null){
                    PortDialog portDialog = new PortDialog();
                    portDialog.start(loginStage, port, currentUser);
                    welcomeStage.hide();
                }
                else{
                    PortsDialog portsDialog = new PortsDialog();
                    portsDialog.start(loginStage, currentUser);
                    welcomeStage.hide();
                }
            }
            else{
                notification.setText("Invalid login or password");
            }
        }*/
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
            if(grid.getRowIndex(node) == row && grid.getColumnIndex(node) == column && (node instanceof TextField || node instanceof CheckBox || node instanceof DatePicker)){
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
                        if (newFocused.size() > 0)
                            newFocused.get(0).requestFocus();
                    }
                }
                break;
                case RIGHT: {
                    if (col > 0) {
                        List<Node> newFocused = getNodesByCoordinate(row, col - 1);
                        if (newFocused.size() > 0)
                            newFocused.get(0).requestFocus();
                    }
                }
                break;
                case UP: {
                    if (row > 0) {
                        List<Node> newFocused = getNodesByCoordinate(row - 1, col);
                        if (newFocused.size() > 0)
                            newFocused.get(0).requestFocus();
                    }
                }
                break;
                case DOWN: {
                    if (row < grid.getRowCount() - 1) {
                        List<Node> newFocused = getNodesByCoordinate(row + 1, col);
                        if (newFocused.size() > 0)
                            newFocused.get(0).requestFocus();
                    }
                }
                break;
            }
            event.consume();
        }
    }
}
