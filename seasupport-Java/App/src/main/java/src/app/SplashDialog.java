package src.app;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import src.logic.PortsEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Splash dialog.
 */
public class SplashDialog extends Application {

    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;

    private Scene scene;
    private Stage stage;
    private String cssPath;
    private static final int SPLASH_WIDTH = 676;
    private static final int SPLASH_HEIGHT = 227;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        launch(args);
    }


    /**
     * Gets ports names.
     *
     * @return the ports names
     */
    List<String> getPortsNames() {
        List<PortsEntity> ports = DataBase.getInstance().getPorts();
        List<String> names = new ArrayList<String>();
        for (PortsEntity port: ports) {
            names.add(port.getPortName());
        }
        return names;
    }
    @Override
    public void init() {
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
        progressText = new Label("Will find ports . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);

        cssPath = this.getClass().getResource("LoginDialog.css").toExternalForm();
        splashLayout.getStylesheets().add(cssPath);

        splashLayout.setEffect(new DropShadow());
    }

    @Override
    public void start(final Stage initStage) throws Exception {
        initStage.getIcons().add(
                new Image(
                        LoginDialog.class.getResourceAsStream("Logo.png")));

        final Task<ObservableList<String>> friendTask = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException {
                ObservableList<String> foundFriends =
                        FXCollections.<String>observableArrayList();
                ObservableList<String> availableFriends =
                        FXCollections.observableArrayList(
                                getPortsNames()
                        );

                updateMessage("Finding ports . . .");
                for (int i = 0; i < availableFriends.size(); i++) {
                    Thread.sleep(400);
                    updateProgress(i + 1, availableFriends.size());
                    String nextFriend = availableFriends.get(i);
                    foundFriends.add(nextFriend);
                    updateMessage("Finding ports . . . found " + nextFriend);
                }
                Thread.sleep(400);
                updateMessage("All ports found.");

                return foundFriends;
            }
        };
        init();
        showSplash(
                initStage,
                friendTask,
                () -> showMainStage()
        );
        new Thread(friendTask).start();
    }

    private void showMainStage() {
        stage = new Stage();

        WelcomeDialog welcomeDialog = new WelcomeDialog();
        welcomeDialog.start(stage);


    }

    private void showSplash(
            final Stage initStage,
            Task<?> task,
            InitCompletionHandler initCompletionHandler
    ) {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            } // todo add code to gracefully handle other task states.
        });

        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        initStage.show();
    }

    /**
     * The interface Init completion handler.
     */
    public interface InitCompletionHandler {
        /**
         * Complete.
         */
        void complete();
    }
}

