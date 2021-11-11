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
	int x,y;
	
	/*
	 * Tile constructors below. Supports constructors for initializing a Tile using a
	 * color, integer, or image, with really only just changes the way the Tile's background looks.
	 * You can send in a GenericUnit with any of these parameters to make a tile start with a unit.
	 */
	
	//Creates a blank tile.
	public Tile(int x, int y) {
		
		isOccupied=false;
		getChildren().add(new ImageView("/Sprites/SkyTile.png"));
		setCoords(x,y);
		
	}
	
	//Makes tile with an image background
	public Tile(Image image, int x, int y) {
		
		isOccupied=false;
		getChildren().add(new ImageView(image));
		setCoords(x,y);
		
	}
	
	//Makes tile with an image background and puts a troop in it.
	public Tile(Image image, GenericUnit troop, int x, int y) {
		
		this(image,x,y);
		unit=troop;
		setUnit(troop);
		isOccupied=true;
		
	}
	
	//Makes a tile with a colored background.
	public Tile(int type, int x, int y) {
		
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
		
		setCoords(x,y);
		
	}
	
	//Makes tile with a background color and a troop within it.
	public Tile(int type, GenericUnit troop, int x, int y) {
		
		this(type,x,y);
		unit=troop;
		setUnit(troop);
		isOccupied=true;
		
	}
	
	/*
	 * The stuff below sets a tile to a certain color, and does stuff with the color.
	 * Useless now that all the Tile sprites are made. But its there.
	 */
	
	
	//Sets tile to a preset color.
	public Tile(Color color,int x, int y) {
		
		isOccupied=false;
		setPrefHeight(75);
		setPrefWidth(75);
		
		backGroundColor = color;
		setColor(color);
		
		setCoords(x,y);
		
	}
	
	//Sets tile to a preset color and puts a troop in it
	public Tile(Color color, GenericUnit troop, int x, int y) {
		
		this(color,x,y);
		unit=troop;
		setUnit(troop);
		isOccupied=true;
		
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
	
	void setCoords(int x, int y) {
		
		this.x=x;
		this.y=y;
		
	}
	
	int getX() {
		
		return x;
		
	}
	
	int getY() {
		
		return y;
		
	}
	
	public void moveUnit(Tile destination) { //Moves Unit from this tile, to destination Tile if it is empty ????
		
	}
}
