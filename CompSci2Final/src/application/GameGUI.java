package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GameGUI extends BorderPane {

	private Map field;
	private HBox buttons;
	private VBox econGUI;
	private VBox econGUI2;
	private Label turnGUI;
	private Button attack;
	private Button move;
	private Button endTurn;
	private Player p1;
	private Player p2;
	
	
	//Initializes Map in Main Currently. Then passes it here.
	public GameGUI(Player p1, Player p2, Map field) {
		
		setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		
		ScrollPane scroller = new ScrollPane();
		scroller.setPannable(true);
		scroller.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		scroller.setMaxHeight(600*1.81);
		scroller.setMaxWidth(600*1.81);
		scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		this.field = field;
		scroller.setContent(field);
		setCenter(scroller);
		
		turnGUI = new Label("Player 1: You have " + p1.getUnitCount() + " units remaining");
		turnGUI.setTextFill(Color.WHITESMOKE);
		turnGUI.setFont(new Font("Arial",20));
		turnGUI.setTextAlignment(TextAlignment.CENTER);
		
		econGUI = new VBox();
		Label text = new Label("Words words words");
		econGUI.getChildren().add(text);
		
		buttons = new HBox();
		buttons.setPadding(new Insets(4.0,4.0,4.0,4.0));
		
		move = new Button("MOVE");
		formatButton(move,Color.BLUE);
		
		move.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) { //Temporary testing
				
				field.removeMapButtons();
				ArrayList<Tile> unitList = field.getUnitTiles();
				
				if(unitList.size()==1 && unitList.get(0).getUnit().getPlayer().yourTurn()) {
					
					ArrayList<Tile> moveArea = field.getMovementTilesLand(unitList.get(0).getUnit().getMovementRange());
					
					for(Tile t : moveArea) {
				
						t.setMyButton(new MoveButton(t,unitList.get(0),unitList.get(0).getUnit()));
						
					}
				}
			}
			
		});
		
		attack = new Button("ATTACK");
		formatButton(attack,Color.RED);
		
		attack.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) { //Temporary testing
				
				field.removeMapButtons();
				ArrayList<Tile> unitList = field.getUnitTiles();
				
				if(unitList.size()==1 && unitList.get(0).getUnit().getPlayer().yourTurn()) {
		
					ArrayList<Tile> attackArea = field.getAttackTilesLandWater(2);
					ArrayList<Tile> nullSpace = field.getSurroundingTiles(2);
					
					for(Tile t : nullSpace) {
						
						if(attackArea.contains(t)) {
							t.setMyButton(new AttackButton(t,unitList.get(0),unitList.get(0).getUnit()));
						} else {
							t.setMyButton(new NullButton());
						}
						
					}
					
				}
				
			}
			
		});
		
		endTurn = new Button("END TURN");
		formatButton(endTurn, Color.GRAY);
		
		endTurn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				field.removeMapButtons();
				field.deselectUnits();
				
				if(p1.yourTurn() && p2.getUnitCount() == 0) {
					turnGUI.setText("Player 1 Wins");
				} else if(p2.yourTurn() && p1.getUnitCount() == 0) {
					turnGUI.setText("Player 2 Wins");
				} else {
					p1.switchTurn();
					p2.switchTurn();
					turnDisplay(p1,p2);
				}
				
				Tile[][] tiles = field.getTiles();
				
				for(Tile[] T : tiles) {
					
					for(Tile t : T) {
						
						GenericUnit unit = t.getUnit();
						
						if(unit!=null) {
							
							unit.resetActions();
							
						}
						
					}
					
				}
				
			}
			
		});
		
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().add(move);
		buttons.getChildren().add(attack);
		buttons.getChildren().add(endTurn);
		
		setTop(turnGUI);
		BorderPane.setAlignment(turnGUI, Pos.CENTER);
		setBottom(buttons);
		setRight(econGUI);
		
	}
	
	//Makes buttons have this game's unique style with changing colors
	private void formatButton(Button b, Color color) {
		
		BorderStroke[] stroke =  {new BorderStroke(Color.WHITESMOKE,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};

		b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		b.setBorder(new Border(stroke));
		b.setStyle("-fx-font-size: 32");
		b.setTextFill(Color.WHITESMOKE);
		b.setPrefWidth(362);
		
		b.setOnMouseEntered((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(color.darker().darker(),null,null)));
				
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
				
			}
			
		});
			
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
				
			}
			
		});
		
	}
	
	private void turnDisplay(Player p1, Player p2) {
		if(p1.yourTurn()) {
			turnGUI.setText("Player 1: You have " + p1.getUnitCount() + " units remaining");
		} else if(p2.yourTurn()) {
			turnGUI.setText("Player 2: You have " + p2.getUnitCount() + " units remaining");
		} else {
			turnGUI.setText("Turn Terror");
		}
	}
	
}
