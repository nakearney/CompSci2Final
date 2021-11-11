package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		//Creating Map Below
		int[][] Map1=new int[Map.MAP_SIZE][Map.MAP_SIZE];
		Map1=Map.setRect(Map1, 2, 2, 11, 11, true, 1);
		Map1=Map.setRow(Map1,7,3);
		Map1=Map.setTile(Map1, 4, 7, 4);
		Map1=Map.setTile(Map1, 5, 7, 4);
		Map1=Map.setTile(Map1, 10, 7, 4);
		Map1=Map.setTile(Map1, 9, 7, 4);
		Map1=Map.setBorder(Map1, 2, 0);
		
		Map map1 = new Map(Map1);
		
		GameGUI game = new GameGUI(map1);
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
