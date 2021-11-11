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

public class Tile extends StackPane {
	
	private boolean isOccupied;
	private GenericUnit unit;
	private Color backGroundColor;
	
	/*
	 * Tile constructors below. Supports constructors for initializing a Tile using a
	 * color, integer, or image, with really only just changes the way the Tile's background looks.
	 * You can send in a GenericUnit with any of these paramters to make a tile start with a unit.
	 */
	
	//Creates a blank tile.
	public Tile() {
		
		isOccupied=false;
		getChildren().add(new ImageView("/Sprites/SkyTile.png"));
		
	}
	
	//Makes tile with an image background
	public Tile(Image image) {
		
		isOccupied=false;
		getChildren().add(new ImageView(image));
		
	}
	
	//Makes tile with an image background and puts a troop in it.
	public Tile(Image image, GenericUnit troop) {
		
		this(image);
		isOccupied=true;
		unit=troop;
		
	}
	
	//Makes a tile with a colored background.
	public Tile(int type) {
		
		isOccupied=false;
		
		if(type==0) {
			getChildren().add(new ImageView("/Sprites/SkyTile.png"));
		} else if(type==1) {
			getChildren().add(new ImageView("/Sprites/GrassTile.png"));
		} else if(type==2) {
			getChildren().add(new ImageView("/Sprites/RockTile.png"));
		} else if(type==3) {
			getChildren().add(new ImageView("/Sprites/WaterTile.png"));
		} else if(type==4) {
			getChildren().add(new ImageView("/Sprites/WoodTile.png"));
		}
		
	}
	
	//Makes tile with a background color and a troop within it.
	public Tile(int type, GenericUnit troop) {
		
		this(type);
		isOccupied=true;
		unit=troop;
		
	}
	
	/*
	 * The stuff below sets a tile to a certain color, and does stuff with the color.
	 * Useless now that all the Tile sprites are made. But its there.
	 */
	
	
	//Sets tile to a preset color.
	public Tile(Color color) {
		
		isOccupied=false;
		setPrefHeight(75);
		setPrefWidth(75);
		
		backGroundColor = color;
		setColor(color);
		
	}
	
	//Sets tile to a preset color and puts a troop in it
	public Tile(Color color, GenericUnit troop) {
		
		this(color);
		isOccupied=true;
		unit=troop;
		
	}
	/*
	 * End of all of the Tile Constructors
	 */
	
	public Color getColor() {
		
		return backGroundColor;
		
	}
	
	//Change color of tile
	public void setColor(Color color) {
		
		backGroundColor = color;
		//Sets properties of the border around tile.
		BorderStroke[] stroke =  {new BorderStroke(color.darker(),BorderStrokeStyle.SOLID,new CornerRadii(0),new BorderWidths(5.0,5.0,5.0,5.0))};
		
		setBackground(new Background(new BackgroundFill(color,null,null)));
		setBorder(new Border(stroke));
		
	}
	
	
	/*
	 * End of largely irrelevant Tile Color Stuff
	 */
	
	public boolean isOccupied() { //Returns whether a unit is inside of Tile
		return isOccupied;
	}
	
	public GenericUnit getUnit() { //Returns actual unit
		return unit;
	}
	
	public void setUnit(GenericUnit unit) { //Sets Unit
		this.unit = unit;
		if(!isOccupied) {
			getChildren().add(unit);
			isOccupied=true;
		}
	}
	
	public void moveUnit(Tile destination) { //Moves Unit from this tile, to destination Tile if it is empty ????
		
	}
}
