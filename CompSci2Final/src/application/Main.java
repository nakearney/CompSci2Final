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
	public static Stage theStage;
	//Holds the players. Accessible everywhere.
	public static Player player1;
	public static Player player2;
	//Holds the scene. Accessible everywhere
	public static Scene theScene;
	
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
		Button map5 = new Button("MAP 5");
		formatButton(map5,5);
		Button map6 = new Button("MAP 6");
		formatButton(map6,6);
		Button map7 = new Button("MAP 7");
		formatButton(map7,7);
		Button map8 = new Button("MAP 8");
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
			
			int[][] Map2=new int[Map.MAP_SIZE][Map.MAP_SIZE];
			Map2=Map.setMap(Map2,3);
			Map2=Map.setCol(Map2,7,4);
			Map2=Map.setRow(Map2,7,4);
			Map2=Map.setRect(Map2,1,1,13,13,false,3);
			//Island Rocks
			Map2=Map.setRect(Map2,4,8,2,6,false,2);
			Map2=Map.setRect(Map2,1,9,6,2,false,2);
			
	
			Map2=Map.setRect(Map2,8,4,6,2,false,2);
			Map2=Map.setRect(Map2,9,1,2,6,false,2);
			
			Map2=Map.setRect(Map2,2,2,11,11,false,4);
			//Corner Islands
			Map2=Map.setRect(Map2,1,1,2,2,true,1);
			Map2=Map.setRect(Map2,12,12,2,2,true,1);
			Map2=Map.setTile(Map2,1,3,1);
			Map2=Map.setTile(Map2,3,1,1);
			Map2=Map.setTile(Map2,13,11,1);
			Map2=Map.setTile(Map2,11,13,1);
			
			Map2=Map.setBorder(Map2, 1, 0);
			
			theMap = new Map(Map2);
			theMap.setUnit(2, 2, new CatSoldier(player2));
			theMap.setUnit(12, 12, new CatSoldier(player1));
			theMap.setUnit(1,1, new Facility(player2));
			theMap.setUnit(13,13, new Facility(player1));
			theMap.setUnit(4,4, new Carrier(player2));
			theMap.setUnit(10,10, new Carrier(player1));
			
		} else if(mapIndex==4) {
			
			
			
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
