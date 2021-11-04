package application;

import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Map extends GridPane {

	Tile[][] tileGrid;
	
	/* 
	 * Initializes a GridPane with tileGrid, with all Tiles properly created & initial troop layout
	 */
	public Map(boolean random) { 
		
		tileGrid= new Tile[15][15];
		setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		setHgap(3.0);
		setVgap(3.0);
		setAlignment(Pos.CENTER);
		fill(random);
		
	}
	
	public Map(Image image) { 
		
		tileGrid= new Tile[15][15];
		setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		setHgap(0.0);
		setVgap(0.0);
		setAlignment(Pos.CENTER);
		fill(image);
		
	}
	
	public Map(int[][] types) {
		
		tileGrid= new Tile[15][15];
		setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		setHgap(3.0);
		setVgap(3.0);
		setAlignment(Pos.CENTER);
		fill(types);
		
	}
	
	//If no parameters sent in, the map is filled with empty cyan tiles.
	void fill() {
		
		fill(false);
		
	}
	
	void fill(Image image) {
		
		for(int y=0;y<15;y++) {
			
			for(int x=0;x<15;x++) {
				
				tileGrid[y][x] = new Tile(image);
				add(tileGrid[y][x],x,y);
				
			}
			
		}
		
	}
	
	//Fills either randomly or all empty. Send in a true parameter to make it not empty, false for empty.
	void fill(boolean random) {
		
		int i=0;
		
		//Either fill empty or randomly
		if(random) {
			i++;
		}
		
		for(int y=0;y<15;y++) {
			
			for(int x=0;x<15;x++) {
				
				Random rand = new Random();
				tileGrid[y][x] = new Tile(rand.nextInt(5)*i);
				add(tileGrid[y][x],x,y);
				
			}
			
		}
		
	}
	
	//Sends in a bunch of integers, corresponding to tiles, get a map!
	void fill(int[][] types) {
		
		for(int y=0;y<15;y++) {
			
			for(int x=0;x<15;x++) {
				
				tileGrid[y][x] = new Tile(types[y][x]);
				add(tileGrid[y][x],x,y);
				
			}
			
		}
		
	}
	
}
