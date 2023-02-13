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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.logic.PortsEntity;
import src.logic.AllUsersEntity;
import javafx.scene.paint.Color;
import javafx.scene.web.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.lang.String.valueOf;

/**
 * The type Contact dialog.
 */
public class ContactDialog extends Application implements EventHandler<ActionEvent> {
    private GridPane grid;
    private Text formTitle, notification;

    private Label addressLabel, contactNumberLabel, vhfChannelLabel, bankAccountLabel;

    private Text streetNumberText, streetNameText, cityNameText, postCodeText, contactNumberText,vhfChannelText, bankAccountText;

    private WebEngine webEngine;
    private WebView webView;

    private Button accountButton, returnButton;

    private AllUsersEntity selectedUser;
    private Scene scene;
    private Stage contactStage;
    private String cssPath;

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
        contactStage = stage;
        selectedUser = user;
        stage.setTitle("Port: " + port.getPortName() + ", price list");

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        formTitle = new Text(port.getPortName().toString());
        formTitle.setWrappingWidth(300);
        formTitle.setId("formatTitle");
        grid.add(formTitle, 0, 0, 1, 2);

        accountButton = new Button("Account Details");
        accountButton.setPrefSize(150, 50);
        accountButton.setMinSize(150, 50);
        accountButton.setOnAction(this);

        grid.add(accountButton, 1, 0);
        grid.setHalignment(accountButton, HPos.RIGHT);

        addressLabel = new Label("Address: ");
        grid.add(addressLabel, 0, 2);

        streetNumberText = new Text(valueOf(port.getStreetNumber()));
        streetNameText = new Text(port.getStreetName());

        cityNameText = new Text(port.getCityName());
        postCodeText = new Text(port.getPostCode());

        Text comaText = new Text(",");
        Text countryText = new Text("Poland");
        Text[] address = {streetNameText, streetNumberText, comaText, postCodeText, cityNameText, countryText};

        Text portAdress = new Text();
        for (Text s : address) {
            portAdress.setText(portAdress.getText() + " " + s.getText());
        }
        grid.add(portAdress, 1, 2);

        contactNumberLabel = new Label("Contact Number: ");
        grid.add(contactNumberLabel, 0, 3);

        contactNumberText = new Text(valueOf(port.getPhoneNumber()));
        grid.add(contactNumberText, 1, 3);

        vhfChannelLabel = new Label("VHF Channel: ");
        grid.add(vhfChannelLabel, 0, 4);

        vhfChannelText = new Text(valueOf(port.getVhfChannel()));
        grid.add(vhfChannelText, 1, 4);

        bankAccountLabel = new Label("Bank account: ");
        grid.add(bankAccountLabel, 0, 5);

        bankAccountText = new Text(valueOf(port.getBankAccount()));
        grid.add(bankAccountText, 1, 5);

        webView = new WebView();
        webEngine = webView.getEngine();
        URL url = this.getClass().getResource("GoogleMap.html");
        webEngine.load(url.toString());

        webView.setPrefWidth(300);
        webView.setPrefHeight(350);

        grid.add(webView, 0,6, 2, 1);

        returnButton = new Button("Return");
        returnButton.setPrefSize(150, 50);
        returnButton.setMinSize(150, 50);
        returnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getSource().equals(returnButton)) {
                    PortDialog portDialog = new PortDialog();
                    portDialog.start(stage, port, user);
                }

            }
        });

        grid.add(returnButton, 1, 7);
        grid.setHalignment(returnButton, HPos.RIGHT);

        notification = new Text();
        notification.setId("notification");
        grid.add(notification, 0, 7);

        scene = new Scene(grid, 600, 575);
        cssPath = this.getClass().getResource("LoginDialog.css").toExternalForm();
        scene.getStylesheets().add(cssPath);
        contactStage.setScene(scene);
        contactStage.centerOnScreen();
        contactStage.setResizable(false);
        contactStage.show();
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
            accountDialog.start(contactStage, selectedUser);
        }
    }


}

