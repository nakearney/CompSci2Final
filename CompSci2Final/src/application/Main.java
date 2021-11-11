package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		Player player1 = new Player(1,true);
		Player player2 = new Player(2,false);
		
		GameGUI game = new GameGUI(player1, player2);
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
