package src.app;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import src.logic.AllUsersEntity;
import src.logic.PortsEntity;
import src.logic.PriceListEntity;
import src.appActions.*;

import java.net.Inet4Address;
import java.util.*;

/**
 * The type Price list dialog.
 */
public class PriceListDialog extends Application implements EventHandler<ActionEvent> {
    private GridPane grid;
    private Text formTitle, notification;
    private TableView<Pair<String, String>> priceListView;
    private Button accountButton, returnButton;
    private AllUsersEntity selectedUser;
    private Scene scene;
    private Stage priceListStage;
    private String cssPath;

    private PriceListEntity priceList;

    /**
     * Get list price list entity.
     *
     * @param listId the list id
     * @return the price list entity
     */
    PriceListEntity getList(int listId){
        PriceListEntity current_list = DataBase.getInstance().getPriceList(listId);
        return current_list;
    }

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
        priceListStage = stage;
        selectedUser = user;
        stage.setTitle("Port: " + port.getPortName() + ", price list");
        stage.getIcons().add(
                new Image(
                        WelcomeDialog.class.getResourceAsStream("Logo.png")));
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        priceList = port.getPriceListEntity();

        formTitle = new Text("Price List");
        formTitle.setId("formatTitle");
        grid.add(formTitle, 0, 0, 2, 1);

        accountButton = new Button("Account Details");
        accountButton.setPrefSize(150, 50);
        accountButton.setOnAction(this);

        grid.add(accountButton, 1, 0);
        grid.setHalignment(accountButton, HPos.RIGHT);

        returnButton = new Button("Return");
        returnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getSource().equals(returnButton)) {
                    PortDialog portDialog = new PortDialog();
                    portDialog.start(stage, port, user);
                }

            }
        });

        grid.add(returnButton, 1, 2);
        returnButton.setPrefSize(150, 50);
        grid.setHalignment(returnButton, HPos.RIGHT);

        PortInformationsActions action = new PortInformationsActions();

        priceListView = new TableView<Pair<String, String>>();
        priceListView.getItems().addAll(
                action.getPrices(priceList).get(0),
                action.getPrices(priceList).get(1),
                action.getPrices(priceList).get(2),
                action.getPrices(priceList).get(3),
                action.getPrices(priceList).get(4),
                action.getPrices(priceList).get(5),
                action.getPrices(priceList).get(6),
                action.getPrices(priceList).get(7),
                action.getPrices(priceList).get(8),
                action.getPrices(priceList).get(9)



                /*
            new Pair<String, Short>("laundry", priceList.getLaundry()),
            new Pair<String, Short>("drying room", priceList.getDryingRoom()),
            new Pair<String, Short>("water", priceList.getWater()),
            new Pair<String, Short>("shower", priceList.getShower()),
            new Pair<String, Short>("sauna", priceList.getSauna()),
            new Pair<String, Short>("place shorter than 7m", priceList.getPlaceLess7M()),
            new Pair<String, Short>("place between 7m and 12m", priceList.getPlace712M()),
            new Pair<String, Short>("place between 12m and 17m", priceList.getPlace1217M()),
            new Pair<String, Short>("place between 17m and 20m", priceList.getPlace1720M()),
            new Pair<String, Short>("place longer than 20m", priceList.getPlaceMore20M())*/
            );


        TableColumn<Pair<String, String>, String> keyCol = new TableColumn<>("Name of service");
        keyCol.setMinWidth(260);
        keyCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey()));

        TableColumn<Pair<String, String>, String> valCol = new TableColumn<>("Price (ZL)");
        valCol.setMinWidth(160);
        valCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getValue().toString()));

        priceListView.getColumns().addAll(keyCol, valCol);

        priceListView.getSelectionModel().setCellSelectionEnabled(true);
        priceListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        priceListView.setFixedCellSize(25);
        priceListView.prefHeightProperty().bind(priceListView.fixedCellSizeProperty().multiply(Bindings.size(priceListView.getItems()).add(1.01)));
        priceListView.minHeightProperty().bind(priceListView.prefHeightProperty());
        priceListView.maxHeightProperty().bind(priceListView.prefHeightProperty());
        priceListView.setMaxWidth(420);
        priceListView.setMinWidth(420);
        priceListView.prefWidthProperty().bind(stage.widthProperty());


        grid.add(priceListView, 0, 1, 2, 1);

        priceListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    //String currentItemSelected = priceListView.getSelectionModel()
                    //        .getSelectedItem();
                    //Parent parent = LoginDialog
                    //e->stage.setScene();
                    //System.out.println(currentItemSelected);
                    //PortDialog portDialog = new PortDialog();
                    //portDialog.start(stage, currentItemSelected);

                }
            }
        });


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
    public void handle(ActionEvent event) {
        if (event.getSource() == accountButton) {
            notification.setText("account button pressed");
            @Deprecated
            AccountDialog accountDialog = new AccountDialog();
            accountDialog.start(priceListStage, selectedUser);
        }
    }

}
