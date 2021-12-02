package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	
	public static Map map1;
	
	@Override
	public void start(Stage primaryStage) {
		
		Player player1 = new Player(1,true);
		Player player2 = new Player(2,false);
		
		//Creating Map Below
		int[][] Map1=new int[Map.MAP_SIZE][Map.MAP_SIZE];
		Map1=Map.setRect(Map1, 2, 2, 11, 11, true, 1);
		Map1=Map.setRow(Map1,7,3);
		Map1=Map.setTile(Map1, 4, 7, 4);
		Map1=Map.setTile(Map1, 5, 7, 4);
		Map1=Map.setTile(Map1, 10, 7, 4);
		Map1=Map.setTile(Map1, 9, 7, 4);
		Map1=Map.setTile(Map1, 3, 3, 2);
		Map1=Map.setTile(Map1, 3, 4, 2);
		Map1=Map.setTile(Map1, 4, 3, 2);
		Map1=Map.setBorder(Map1, 2, 0);
		//Process for making units is to initialize the map of tiles created above,
		//Then use the Map methods to add units.
		map1 = new Map(Map1);
		map1.setUnit(8, 4, new CatSoldier(player1));
		map1.setUnit(8, 10, new CatSoldier(player2));
		map1.setUnit(10,4, new Facility(player1));
		map1.setUnit(10,10, new Facility(player2));
		
		GameGUI game = new GameGUI(player1, player2, map1);
		Scene s = new Scene(game);
		
		primaryStage.setMaximized(true);
		primaryStage.setScene(s);
		primaryStage.setTitle("Scam Kids: Make Money");
		primaryStage.getIcons().add(new Image("/Sprites/TinyTank.png"));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
