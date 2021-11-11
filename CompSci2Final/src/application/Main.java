package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		GameGUI game = new GameGUI();
		Scene s = new Scene(game);
		
		primaryStage.setMaximized(true);
		primaryStage.setScene(s);
		primaryStage.setTitle("Scam Kids: Make Money");
		primaryStage.getIcons().add(new Image("/Sprites/HastilyMadeTank.png"));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
