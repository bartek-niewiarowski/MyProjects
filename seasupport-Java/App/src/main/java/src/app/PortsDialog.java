package src.app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.logic.PortsEntity;
import src.logic.*;

import java.util.List;

/**
 * The type Ports dialog.
 */
public class PortsDialog extends Application implements EventHandler<MouseEvent> {
    private GridPane grid;
    private Text formTitle, notification;

    private Button accountButton;

    private ListView<PortsEntity> listView;

    private ObservableList<PortsEntity> data;
    private AllUsersEntity selectedUser;

    /**
     * Gets ports.
     *
     * @return the ports
     */
    List<PortsEntity> getPorts() {
        List<PortsEntity> ports = DataBase.getInstance().getPorts();
        return ports;
    }

    private Button mouseButton, settingsButton;
    private Scene scene;

    private String cssPath;
    @Override
    public void start(Stage stage) {
    }

    /**
     * Start.
     *
     * @param stage the stage
     * @param user  the user
     */
    public void start(Stage stage, AllUsersEntity user) {
        selectedUser = user;
        data = FXCollections.observableArrayList(getPorts());
        stage.setTitle("Ports Dialog");
        stage.getIcons().add(
                new Image(
                        WelcomeDialog.class.getResourceAsStream("Logo.png")));

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        formTitle = new Text("Choose Port");
        formTitle.setId("formatTitle");
        grid.add(formTitle, 0, 0);

        accountButton = new Button("Account Details");
        accountButton.setPrefSize(150, 50);
        accountButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getSource().equals(accountButton)) {
                    AccountDialog accountDialog = new AccountDialog();
                    accountDialog.start(stage, selectedUser);
                }

            }
        });

        grid.add(accountButton, 1, 0);
        grid.setHalignment(accountButton, HPos.RIGHT);

        listView = new ListView<PortsEntity>();
        listView.setItems(data);
        listView.setCellFactory(param -> new ListCell<PortsEntity>() {

            protected void updateItem(PortsEntity port, boolean empty) {
                super.updateItem(port, empty);

                if (empty || port == null || port.getPortName() == null) {
                    setText(null);
                } else {
                    setText(port.getPortName());
                }
            }
        });

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    PortsEntity currentItemSelected = listView.getSelectionModel()
                            .getSelectedItem();
                    //Parent parent = LoginDialog
                    //e->stage.setScene();
                    System.out.println(currentItemSelected.getPortName());
                    //CreateVisitDialog visit = new CreateVisitDialog();
                    //visit.start(stage, currentUser, currentItemSelected);
                    PortDialog portDialog = new PortDialog();
                    portDialog.start(stage, currentItemSelected, selectedUser);
                }
            }
        });

        listView.setPrefWidth(400);
        listView.setPrefHeight(500);

        grid.add(listView, 0, 1, 2, 1);

        notification = new Text();
        notification.setId("notification");
        grid.add(notification, 1, 6);

        scene = new Scene(grid, 600, 575);
        cssPath = this.getClass().getResource("PortsDialog.css").toExternalForm();
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
    public void handle(MouseEvent mouseEvent) {
    }
}
