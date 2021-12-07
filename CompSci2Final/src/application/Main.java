package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Main extends Application {
	
	
	//Holds the map. Accessible everywhere.
	public static Map theMap;
	//Holds the primaryStage. Accessible everywhere.
	private static Stage theStage;
	//Holds the players. Accessible everywhere.
	public static Player player1;
	public static Player player2;
	//Holds the scene. Accessible everywhere
	private static Scene theScene;
	
	@Override
	public void start(Stage primaryStage) {
		
		theStage=primaryStage;
		primaryStage.setMaximized(true);
		startScreen();
		primaryStage.setTitle("Scam Kids: Make Money");
		primaryStage.getIcons().add(new Image("/Sprites/Duck_Wizard_Player1.png"));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void startScreen() {
		
		BorderPane startScreen = new BorderPane();
		startScreen.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		
		//Contains the title of the game and instructions to select a map
		VBox text = new VBox();
		text.setAlignment(Pos.CENTER);
		text.setMinWidth(400);
		text.setSpacing(10.0);
		
		Label title = new Label("GAMERS vs. SOCIETY");
		title.setTextAlignment(TextAlignment.CENTER);
		title.setFont(new Font("Arial",48));
		title.setTextFill(Color.WHITESMOKE);
		
		Label instr = new Label("Select a Map");
		instr.setTextAlignment(TextAlignment.CENTER);
		instr.setFont(new Font("Arial",24));
		instr.setTextFill(Color.GREY);
		
		text.getChildren().add(title);
		text.getChildren().add(instr);
		
		//Contains Buttons to access Maps
		ScrollPane scroller = new ScrollPane();
		scroller.setFitToHeight(true);
		scroller.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		scroller.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scroller.setVbarPolicy(ScrollBarPolicy.NEVER);
		
		//Contains all the buttons. A flowpane is used to make a nice wrapping effect
		FlowPane buttons = new FlowPane(Orientation.VERTICAL);
		buttons.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		buttons.setPrefWrapLength(200);
		buttons.setMinHeight(205);
		buttons.setVgap(20);
		buttons.setHgap(20);
		buttons.setColumnHalignment(HPos.LEFT);
		Button map1 = new Button("RIVER CROSSING");
		formatButton(map1,1);
		Button map2 = new Button("OPEN SEA");
		formatButton(map2,2);
		Button map3 = new Button("BOARDWALK");
		formatButton(map3,3);
		Button map4 = new Button("TWIN PEAKS");
		formatButton(map4,4);
		Button map5 = new Button("HOURGLASS LAKE");
		formatButton(map5,5);
		Button map6 = new Button("WATER-LOCKED");
		formatButton(map6,6);
		Button map7 = new Button("ROCK MAZE");
		formatButton(map7,7);
		Button map8 = new Button("TIC TAC TOE");
		formatButton(map8,8);
		Button map9 = new Button("MAP 9");
		formatButton(map9,9);
		
		buttons.getChildren().add(map1);
		buttons.getChildren().add(map2);
		buttons.getChildren().add(map3);
		buttons.getChildren().add(map4);
		buttons.getChildren().add(map5);
		buttons.getChildren().add(map6);
		buttons.getChildren().add(map7);
		buttons.getChildren().add(map8);
		buttons.getChildren().add(map9);
		
		
		scroller.setFitToHeight(true);
		scroller.setContent(buttons);
		
		startScreen.setCenter(text);
		startScreen.setBottom(scroller);
		
		/*
		 * The if statement is to fix a weird bug on Will's Computer where the screen, when maximized,
		 * would not properly size the nodes on screen.
		 */
		Scene s;
		if(theScene!=null) {
			s = new Scene(startScreen,theScene.getWidth(),theScene.getHeight());
		} else {
			s = new Scene(startScreen,600*1.81,600);
		}
		theStage.setScene(s);
		theStage.setMaximized(true);
		theScene=s;
		
	}
	
	//Call this message to change a map. mapIndex is an ID for the map you are accessing
	private static void setMap(int mapIndex) {
		
		player1 = new Player(1,true);
		player2 = new Player(2,false);
		GameGUI game = new GameGUI(player1, player2, getMap(mapIndex), mapIndex);
		Scene s = new Scene(game,theScene.getWidth(),theScene.getHeight());
		theStage.setScene(s);
		theScene=s;
		
	}
	
	//Maps are all made in here. Returns the map with the id you give it.
	private static Map getMap(int mapIndex) {
		
		if(mapIndex==1) {
			
			//Creating Map Below
			int[][] Map1=new int[Map.MAP_SIZE][Map.MAP_SIZE];
			Map1=Map.setRect(Map1, 2, 2, 11, 11, true, 1);
			Map1=Map.setRow(Map1,7,3);
			Map1=Map.setTile(Map1, 4, 7, 4);
			Map1=Map.setTile(Map1, 5, 7, 4);
			Map1=Map.setTile(Map1, 10, 7, 4);
			Map1=Map.setTile(Map1, 9, 7, 4);
			Map1=Map.setBorder(Map1, 2, 0);
			//Process for making units is to initialize the map of tiles created above,
			//Then use the Map methods to add units.
			theMap = new Map(Map1);
			theMap.setUnit(5, 4, new CatSoldier(player1));
			theMap.setUnit(9, 10, new CatSoldier(player2));
			theMap.setUnit(4,4, new Facility(player1));
			theMap.setUnit(10,10, new Facility(player2));
			
		} else if(mapIndex==2) {
			
			//Creating Map Below
			int[][] Map2=new int[Map.MAP_SIZE][Map.MAP_SIZE];
			Map2=Map.setMap(Map2,3);
			Map2=Map.setBorder(Map2, 2, 0);
			Map2=Map.setTile(Map2,6,7,2);
			Map2=Map.setTile(Map2,8,7,2);
			Map2=Map.setTile(Map2,9,7,2);
			Map2=Map.setRect(Map2,2,2,2,2,true,2);
			Map2=Map.setTile(Map2,2,4,2);
			Map2=Map.setTile(Map2,4,10,2);
			Map2=Map.setTile(Map2,11,12,2);
			//Process for making units is to initialize the map of tiles created above,
			//Then use the Map methods to add units.
			theMap = new Map(Map2);
			theMap.setUnit(7, 4, new AxolotlCaptain(player1));
			theMap.setUnit(7, 10, new AxolotlCaptain(player2));
			theMap.setUnit(7,3, new Carrier(player1));
			theMap.setUnit(7,11, new Carrier(player2));
			
		} else if(mapIndex==3) {

			int[][] Map3=new int[Map.MAP_SIZE][Map.MAP_SIZE];
			Map3=Map.setMap(Map3,3);
			Map3=Map.setCol(Map3,7,4);
			Map3=Map.setRow(Map3,7,4);
			Map3=Map.setRect(Map3,1,1,13,13,false,3);
			//Island Rocks
			Map3=Map.setRect(Map3,4,8,2,6,false,2);
			Map3=Map.setRect(Map3,1,9,6,2,false,2);
			
	
			Map3=Map.setRect(Map3,8,4,6,2,false,2);
			Map3=Map.setRect(Map3,9,1,2,6,false,2);
			
			Map3=Map.setRect(Map3,2,2,11,11,false,4);
			//Corner Islands
			Map3=Map.setRect(Map3,1,1,2,2,true,1);
			Map3=Map.setRect(Map3,12,12,2,2,true,1);
			Map3=Map.setTile(Map3,1,3,1);
			Map3=Map.setTile(Map3,3,1,1);
			Map3=Map.setTile(Map3,13,11,1);
			Map3=Map.setTile(Map3,11,13,1);
			
			Map3=Map.setBorder(Map3, 1, 0);
			
			theMap = new Map(Map3);
			theMap.setUnit(2, 2, new CatSoldier(player2));
			theMap.setUnit(12, 12, new CatSoldier(player1));
			theMap.setUnit(1,1, new Facility(player2));
			theMap.setUnit(13,13, new Facility(player1));
			theMap.setUnit(4,4, new Carrier(player2));
			theMap.setUnit(10,10, new Carrier(player1));
			
		} else if(mapIndex==4) {
			
			
		}
		
		if(mapIndex==5) {
			
			int[][] Map5=new int[Map.MAP_SIZE][Map.MAP_SIZE];
			Map5=Map.setMap(Map5,1);
			
			Map5=Map.setRect(Map5,6,2,3,11,true,3);
			Map5=Map.setRect(Map5,4,3,7,3,true,3);
			Map5=Map.setRect(Map5,4,9,7,3,true,3);
			
			Map5=Map.setTile(Map5,6,7,1);
			Map5=Map.setTile(Map5,8,7,1);
			Map5=Map.setTile(Map5,7,7,4);
			
			Map5=Map.setBorder(Map5,1,0);
			
			theMap = new Map(Map5);
			
			theMap.setUnit(10,7, new CatSoldier(player1));
			theMap.setUnit(12,7, new Facility(player1));
			theMap.setUnit(12,2, new Facility(player1));
			theMap.setUnit(12,12, new Facility(player1));
			theMap.setUnit(9,4, new Carrier(player1));
			theMap.setUnit(9,10, new Carrier(player1));
			
			theMap.setUnit(4,7, new CatSoldier(player2));
			theMap.setUnit(2,7, new Facility(player2));
			theMap.setUnit(2,2, new Facility(player2));
			theMap.setUnit(2,12, new Facility(player2));
			theMap.setUnit(5,4, new Carrier(player2));
			theMap.setUnit(5,10, new Carrier(player2));
		
			
		} else if(mapIndex==6) {
			
			int[][] Map6=new int[Map.MAP_SIZE][Map.MAP_SIZE];
			Map6=Map.setMap(Map6,1);
			
			Map6=Map.setBorder(Map6,3,3);
			Map6=Map.setBorder(Map6,2,1);
			Map6=Map.setBorder(Map6,1,0);
			
			Map6=Map.setRect(Map6,7,5,1,5,true,3);
			Map6=Map.setRect(Map6,3,5,4,1,true,3);
			Map6=Map.setRect(Map6,7,9,6,1,true,3);
			
			Map6=Map.setTile(Map6,7,7,4);
			Map6=Map.setTile(Map6,5,5,4);
			Map6=Map.setTile(Map6,4,5,4);
			Map6=Map.setTile(Map6,9,9,4);
			Map6=Map.setTile(Map6,10,9,4);
			
			theMap = new Map(Map6);
			theMap.setUnit(2,2, new AxolotlCaptain(player1));
			theMap.setUnit(12,2, new AxolotlCaptain(player1));
			theMap.setUnit(12,12, new AxolotlCaptain(player2));
			theMap.setUnit(2,12, new AxolotlCaptain(player2));
			theMap.setUnit(10,4, new Facility(player1));
			theMap.setUnit(4,4, new Facility(player1));
			theMap.setUnit(4,10, new Facility(player2));
			theMap.setUnit(10,10, new Facility(player2));
			
		} else if(mapIndex==7) {
			
			int[][] Map7=new int[Map.MAP_SIZE][Map.MAP_SIZE];
			Map7=Map.setMap(Map7,1);
			
			Map7=Map.setRect(Map7,4,4,3,1,true,2);
			Map7=Map.setRect(Map7,8,8,2,2,true,2);
			Map7=Map.setRect(Map7,9,5,2,1,true,2);
			Map7=Map.setRect(Map7,3,8,2,1,true,2);
			Map7=Map.setRect(Map7,3,10,1,2,true,2);
			Map7=Map.setRect(Map7,7,11,4,1,true,2);
			Map7=Map.setRect(Map7,11,4,1,3,true,2);
			Map7=Map.setRect(Map7,8,3,1,3,true,2);
			
			Map7=Map.setTile(Map7,6,2,2);
			Map7=Map.setTile(Map7,5,5,2);
			Map7=Map.setTile(Map7,4,7,2);
			Map7=Map.setTile(Map7,6,9,2);
			Map7=Map.setTile(Map7,5,10,2);
			Map7=Map.setTile(Map7,6,7,2);
			Map7=Map.setTile(Map7,11,9,2);
			
			Map7=Map.setBorder(Map7, 2,2);
			Map7=Map.setBorder(Map7,1,0);
			
			theMap = new Map(Map7);
			theMap.setUnit(5,11, new Facility(player1));
			theMap.setUnit(3,2, new Facility(player1));
			theMap.setUnit(2,6, new Facility(player1));
			theMap.setUnit(9,3, new Facility(player2));
			theMap.setUnit(11,12, new Facility(player2));
			theMap.setUnit(12,8, new Facility(player2));
			
		} else if(mapIndex==8) {
			
			int[][] Map8=new int[Map.MAP_SIZE][Map.MAP_SIZE];
			
			Map8=Map.setBorder(Map8,6,3);
			Map8=Map.setBorder(Map8,5,1);
			Map8=Map.setBorder(Map8,2,3);
			Map8=Map.setBorder(Map8,1,0);
			
			Map8=Map.setRect(Map8,6,6,3,3,true,1);
			Map8=Map.setRect(Map8,5,1,5,13,false,3);
			Map8=Map.setRect(Map8,1,5,13,5,false,3);
			
			Map8=Map.setTile(Map8,3,5,4);
			Map8=Map.setTile(Map8,11,9,4);
			Map8=Map.setTile(Map8,9,3,4);
			Map8=Map.setTile(Map8,5,11,4);
			Map8=Map.setTile(Map8,5,7,4);
			Map8=Map.setTile(Map8,7,5,4);
			Map8=Map.setTile(Map8,9,7,4);
			Map8=Map.setTile(Map8,7,9,4);
			
			theMap = new Map(Map8);
			
			theMap.setUnit(3,11, new Facility(player1));
			theMap.setUnit(11,3, new Facility(player1));
			theMap.setUnit(3,3, new Facility(player2));
			theMap.setUnit(11,11, new Facility(player2));
			theMap.setUnit(5,5, new Carrier(player1));
			theMap.setUnit(9,9, new Carrier(player1));
			theMap.setUnit(5,9, new Carrier(player2));
			theMap.setUnit(9,5, new Carrier(player2));
			
		}
		
		return theMap;
		
	}
	
	//Gives buttons their formatting, and assigns each one a map to call when pressed
	private static void formatButton(Button b, int action) {
		
		BorderStroke[] stroke =  {new BorderStroke(Color.WHITESMOKE,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};
		Color backColor = Color.BISQUE;
		
		b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		b.setBorder(new Border(stroke));
		b.setStyle("-fx-font-size: 30");
		b.setTextFill(Color.WHITESMOKE);
		b.setPrefWidth(362);
		b.setMinWidth(362);
		
		b.setOnMouseEntered((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(backColor.darker().darker(),null,null)));
				
			}
			
		});
			
		b.setOnMouseExited((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
				
			}
			
		});
		
		b.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
				setMap(action);
				
			}
			
		});
			
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
				
			}
			
		});
		
	}
	
}
