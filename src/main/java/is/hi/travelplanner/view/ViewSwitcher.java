package is.hi.travelplanner.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class ViewSwitcher {
    private static Scene scene;

    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    public static void switchTo(View view){
        if (scene == null) {
            System.out.println("Error: Scene has not been set in App.java yet.");
            return;
        }

        try {
            // Updated to look in the absolute resources/fxml/ path
            Parent root = FXMLLoader.load(ViewSwitcher.class.getResource("/fxml/" + view.getFileName()));
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println("Failed to load: " + view.getFileName());
            e.printStackTrace();
        }
    }
}