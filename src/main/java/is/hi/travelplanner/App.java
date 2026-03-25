package is.hi.travelplanner;

import is.hi.travelplanner.view.View;
import is.hi.travelplanner.view.ViewSwitcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new Pane(), 800, 600);

        ViewSwitcher.setScene(scene);

        ViewSwitcher.switchTo(View.MAIN);

        primaryStage.setTitle("trip planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}