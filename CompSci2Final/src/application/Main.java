package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		GameGUI game = new GameGUI();
		Scene s = new Scene(game);
		
		primaryStage.setMaximized(true);
		primaryStage.setScene(s);
		primaryStage.setTitle("Gamers VS Society");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
