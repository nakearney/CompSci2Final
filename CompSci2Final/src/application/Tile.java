package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Tile extends StackPane {
	
	boolean isOccupied;
	GenericUnit unit;
	Color backGroundColor;
	
	public Tile() {
		
		isOccupied=false;
		setPrefHeight(75);
		setPrefWidth(75);
		
		backGroundColor = Color.BLACK;
		setColor(backGroundColor);
		
	}
	
	public Tile(Image image) {
		
		getChildren().add(new ImageView(image));
		
	}
	
	public Tile(int type) {
		
		isOccupied=false;
		setPrefHeight(75);
		setPrefWidth(75);
		
		if(type==0) {
			backGroundColor = Color.BLACK;
		} else if(type==1) {
			backGroundColor = Color.GREEN;
		} else if(type==2) {
			backGroundColor = Color.GREY;
		} else if(type==3) {
			backGroundColor = Color.BLUE;
		} else if(type==4) {
			backGroundColor = Color.SADDLEBROWN;
		}
		
		setColor(backGroundColor);
		
	}
	
	public Tile(int type, GenericUnit troop) {
		
		isOccupied=false;
		unit=troop;
		setPrefHeight(75);
		setPrefWidth(75);
		
		if(type==0) {
			backGroundColor = Color.BLACK;
		} else if(type==1) {
			backGroundColor = Color.GREEN;
		} else if(type==2) {
			backGroundColor = Color.GREY;
		} else if(type==3) {
			backGroundColor = Color.BLUE;
		} else if(type==4) {
			backGroundColor = Color.SADDLEBROWN;
		}
		
		setColor(backGroundColor);
		
	}
	
	public Tile(Color color) {
		
		isOccupied=false;
		setPrefHeight(75);
		setPrefWidth(75);
		
		backGroundColor = color;
		setColor(color);
		
	}
	
	public Tile(Color color, GenericUnit troop) {
		
		isOccupied=true;
		unit=troop;
		setPrefHeight(75);
		setPrefWidth(75);
		
		backGroundColor = color;
		setColor(color);
		
	}
	
	public Color getColor() { //Change return type for this method
		
		return backGroundColor;
		
	}
	
	public void setColor(Color color) { //Changes color of Tile
		
		backGroundColor = color;
		BorderStroke[] stroke =  {new BorderStroke(color.darker(),BorderStrokeStyle.SOLID,new CornerRadii(0),new BorderWidths(5.0,5.0,5.0,5.0))};
		
		setBackground(new Background(new BackgroundFill(color,null,null)));
		setBorder(new Border(stroke));
		
	}
	
	public boolean isOccupied() { //Returns whether a unit is inside of Tile
		return isOccupied;
	}
	
	public GenericUnit getUnit() { //Returns actual unit
		return unit;
	}
	
	public void setUnit() { //Sets Unit
		
	}
	
	public void moveUnit(Tile destination) { //Moves Unit from this tile, to destination Tile if it is empty ????
		
	}
}
