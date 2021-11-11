package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class GameGUI extends BorderPane {

	public Map field;
	public HBox buttons;
	public Label turnGUI;
	public Button attack;
	public Button move;
	public Button endTurn;
	public Player p1;
	public Player p2;
	
	
	//Initializes Map in Main Currently. Then passes it here.
	GameGUI(Player p1, Player p2, Map field) {
		
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
		
		buttons = new HBox();
		buttons.setPadding(new Insets(4.0,4.0,4.0,4.0));
		
		move = new Button("MOVE");
		formatButton(move,Color.BLUE);
		
		attack = new Button("ATTACK");
		formatButton(attack,Color.RED);
		
		endTurn = new Button("END TURN");
		formatButton(endTurn, Color.GRAY);
		
		endTurn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				if(p1.yourTurn() && p2.getUnitCount() == 0) {
					//player 1 win condition
				} else if(p2.yourTurn() && p1.getUnitCount() == 0) {
					//player 2 win condition
				}
				
				p1.switchTurn();
				p2.switchTurn();
				turnDisplay(p1,p2);
				
			}
			
		});
		
		buttons.getChildren().add(move);
		buttons.getChildren().add(attack);
		buttons.getChildren().add(endTurn);
		
		setTop(turnGUI);
		setBottom(buttons);
		
	}
	
	//Makes buttons have this game's unique style with changing colors
	private void formatButton(Button b, Color color) {
		
		BorderStroke[] stroke =  {new BorderStroke(Color.WHITESMOKE,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};

		b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		b.setBorder(new Border(stroke));
		b.setStyle("-fx-font-size: 32");
		b.setTextFill(Color.WHITESMOKE);
		b.setPrefWidth(1000);
		
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
				
				b.setBackground(new Background(new BackgroundFill(color,null,null)));
				
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
