import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Controller c = new Controller();
		Scene scene = new Scene(c.getView());
		
		stage.setTitle("Dijkstra - path finder - algortime"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show(); // Display the stage
	}
}
