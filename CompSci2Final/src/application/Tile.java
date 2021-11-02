package application;

import javafx.scene.layout.StackPane;

public class Tile extends StackPane {
	
	boolean isOccupied;
	GenericUnit unit;
	//Variable for keeping track of Tile color???
	
	public Tile() {
		
	}
	
	public void getColor() { //Change return type for this method
		
	}
	
	public void setColor() { //Changes color of Tile
		
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
