package application;

import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Map extends GridPane {

	private Tile[][] tileGrid;
	
	final public static int MAP_SIZE=15;
	
	/* 
	 * Initializes a GridPane with tileGrid, with all Tiles properly created & initial troop layout
	 */
	private Map() {
		
		tileGrid= new Tile[MAP_SIZE][MAP_SIZE];
		setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		setAlignment(Pos.CENTER);
		
	}
	
	//Makes either a random or blank map. True=random, False=blank
	public Map(boolean random) { 
		
		this();
		fill(random);
		
	}

	//Fills Map with one image
	public Map(Image image) { 
		
		this();
		fill(image);
		
	}
	
	//Fills Map with preset map
	public Map(int[][] types) {
		
		this();
		fill(types);
		
	}
	
	//If no parameters sent in, the map is filled with empty cyan tiles.
	private void fill() {
		
		fill(false);
		
	}
	
	//Fills with an image
	private void fill(Image image) {
		
		for(int y=0;y<MAP_SIZE;y++) {
			
			for(int x=0;x<MAP_SIZE;x++) {
				
				tileGrid[y][x] = new Tile(image,x,y);
				add(tileGrid[y][x],x,y);
				
			}
			
		}
		
	}
	
	//Fills either randomly or all empty. Send in a true parameter to make it not empty, false for empty.
	private void fill(boolean random) {
		
		int i=0;
		
		//Either fill empty or randomly
		if(random) {
			i++;
		}
		
		for(int y=0;y<MAP_SIZE;y++) {
			
			for(int x=0;x<MAP_SIZE;x++) {
				
				Random rand = new Random();
				tileGrid[y][x] = new Tile(rand.nextInt(5)*i,x,y);
				add(tileGrid[y][x],x,y);
				
			}
			
		}
		
	}
	
	//Sends in a bunch of integers, corresponding to tiles, get a map!
	private void fill(int[][] types) {
		
		for(int y=0;y<MAP_SIZE;y++) {
			
			for(int x=0;x<MAP_SIZE;x++) {
				
				tileGrid[y][x] = new Tile(types[y][x],x,y);
				add(tileGrid[y][x],x,y);
				
			}
			
		}
		
	}
	
	//If an operation below cannot be performed, the array is sent back as it is sent in
	
	//Sets a certain tile in a map to a specified type.
	public static int[][] setTile(int[][] tiles, int x, int y, int type) {
		
		if(x<MAP_SIZE && y<MAP_SIZE && x>0 && y>0) {
			
			tiles[y][x]=type;
			
		}
		
		return tiles; 
		
	}
	
	//Sets a row of tiles in a map to a specified type
	public static int[][] setRow(int[][] tiles, int row, int type) {
		
		if(row<MAP_SIZE && row>0) {
			
			for(int i=0;i<MAP_SIZE;i++) {
				
				tiles[row][i]=type;
				
			}
			
		}
		
		return tiles;
		
	}
	
	//Sets a column of tiles in a map to a specified type
	public static int[][] setCol(int[][] tiles, int col, int type) {
		
		if(col<MAP_SIZE && col>0) {
			
			for(int i=0;i<MAP_SIZE;i++) {
				
				tiles[i][col]=type;
				
			}
			
		}
		
		return tiles;
		
	}
	
	//Sets a rectangle of tiles in a map to a specified type. Boolean specifies filled or not filled
	public static int[][] setRect(int[][] tiles, int x, int y, int width, int height, boolean fill, int type) {
		
		if(x<MAP_SIZE && y<MAP_SIZE && x>=0 && y>=0 && (width+x)<=MAP_SIZE && (height+y)<=MAP_SIZE && width>0 && height>0) {
			
			for(int r=y;r<height+y;r++) {
				
				for(int c=x; c<width+x; c++) {
					
					if((r==y||r==height+y-1)||(c==x||c==width+x-1)||fill) {
						
						tiles[r][c]=type;
						
					}
					
				}
				
			}
			
		}
		
		return tiles;
		
	}
	
	//Sets Map Border of specified thickness to a certain type of tile
	public static int[][] setBorder(int[][] tiles, int thick, int type) {
		
		if(thick>=0 && thick<=MAP_SIZE) {
			for(int i=0;i<thick;i++) tiles=setRect(tiles,i,i,MAP_SIZE-i*2,MAP_SIZE-i*2,false,type);
		}
		
		return tiles;
		
	}
	
	//Sets entire Map to 1 type of tile
	public static int[][] setMap(int[][] tiles, int type) {
		
		tiles=setBorder(tiles,15,type);
		return tiles;
		
	}
	
}
