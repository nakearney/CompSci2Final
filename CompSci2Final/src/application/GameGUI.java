package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
	public Button attack;
	public Button move;
	
	GameGUI() {
		
		setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		
		ScrollPane scroller = new ScrollPane();
		scroller.setPannable(true);
		scroller.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		scroller.setMaxHeight(600*1.81);
		scroller.setMaxWidth(600*1.81);
		scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		field = new Map(true);
		scroller.setContent(field);
		setCenter(scroller);
		
		buttons = new HBox();
		buttons.setPadding(new Insets(4.0,4.0,4.0,4.0));
		
		move = new Button("MOVE");
		formatButton(move,Color.BLUE);
		
		attack = new Button("ATTACK");
		formatButton(attack,Color.RED);
		
		buttons.getChildren().add(move);
		buttons.getChildren().add(attack);
		
		setBottom(buttons);
		
	}
	
	GameGUI(Map field) {
		
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
		
		buttons = new HBox();
		buttons.setPadding(new Insets(4.0,4.0,4.0,4.0));
		
		move = new Button("MOVE");
		formatButton(move,Color.BLUE);
		
		attack = new Button("ATTACK");
		formatButton(attack,Color.RED);
		
		buttons.getChildren().add(move);
		buttons.getChildren().add(attack);
		
		setBottom(buttons);
		
	}
	
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
	
}
