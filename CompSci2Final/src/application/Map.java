package application;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
		
		tiles=setRect(tiles,0,0,MAP_SIZE,MAP_SIZE,true,type);
		return tiles;
		
	}
	
	public void setUnit(int x, int y, GenericUnit unit) {
		
		tileGrid[y][x].setUnit(unit);
		
	}
	
	/*
	 * Code for collecting all the tiles to place move & attack buttons on
	 */
	public ArrayList<Tile> getFightLandWaterHollow(int range, int blind) {
		
		return getActHollow(true,true,true,range,blind);
		
	}
	
	public ArrayList<Tile> getFightWaterLine(int range) {
		
		return getActLine(false,true,true,range);
		
	}
	
	public ArrayList<Tile> getFightLandWaterLine(int range) {
		
		return getActLine(true,true,true,range);
		
	}
	
	public ArrayList<Tile> getFightLandLine(int range) {
		
		return getActLine(true,false,true,range);
		
	}
	
	public ArrayList<Tile> getMoveWaterLine(int range) {
		
		return getActLine(false,true,false,range);
		
	}
	
	public ArrayList<Tile> getMoveLandWaterLine(int range) {
		
		return getActLine(true,true,false,range);
		
	}
	
	public ArrayList<Tile> getMoveLandLine(int range) {
		
		return getActLine(true,false,false,range);
		
	}
	
	public ArrayList<Tile> getFightWater(int range) {
		
		return getActTiles(false,true,true,range);
		
	}
	
	public ArrayList<Tile> getFightLandWater(int range) {
		
		return getActTiles(true,true,true,range);
		
	}
	
	public ArrayList<Tile> getFightLand(int range) {
		
		return getActTiles(true,false,true,range);
		
	}
	
	//For units that can only move on water
	public ArrayList<Tile> getMoveWater(int range) {
			
		return getActTiles(false,true,false,range);
			
	}

	//For units that can move on land and water (But not rocks or sky)
	public ArrayList<Tile> getMoveLandWater(int range) {
		
		return getActTiles(true,true,false,range);
		
	}
	
	//For units that can only move on land
	public ArrayList<Tile> getMoveLand(int range) {
		
		return getActTiles(true,false,false,range);
		
	}
	
	
	/*
	 * Gets a circle of tiles around a unit. Used for getting move & attack tiles (the red & 
	 * blue squares seen in game). Functions by starting with the single tile 
	 * a selected unit is on, putting it into an array list, adding four tiles around 
	 * it to the array list, then repeatedly going through each tile in the 
	 * growing array list and adding tiles around them a *range* number of times.
	 * The boolean values specify if a unit can act on land and water, and takes that into
	 * account when adding tiles. If the method is meant to return tiles meant for attacking, then
	 * fight is true and taken into account. Otherwise it is false.
	 */
	public ArrayList<Tile> getActTiles(boolean land, boolean water, boolean fight, int range) {
		
		ArrayList<Tile> unitTiles = this.getUnitTiles();
		ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
		
		//Method only works when 1 unit is selected
		if(unitTiles.size()!=1) {
			
			return null;
			
		} else {
			
			//Tile with selected unit
			Tile unitTile = unitTiles.get(0);
			
			ArrayList<Tile> currentTiles = new ArrayList<Tile>();
			currentTiles.add(unitTile);
			surroundingTiles.add(unitTile);
			
			for(int i=0;i<range;i++) {
				
				//Goes through each tile amassed from the surrounding unit and
				//attempts to branch them off in all 4 cardinal directions.
				for(Tile t : currentTiles) {
					
					int x = t.getX();
					int y = t.getY();
					
					/*
					 * For loop uses "step" variable to determine "spotX" and "spotY." Depending on if
					 * step is 0,1,2, or 3, spotX and spotY will be the coordinates of a tile directly
					 * to the left, right, up, or down of a tile. The code will attempt to add a tile
					 * to (spotY, spotX). If successful, this tile will be added to our arrayList.
					 * (Doesn't actually add a tile, but that's how this method is ultimately used)
					 */
					for(int step=0;step<4;step++) {
						
						int spotX=(int)Math.round(x+Math.cos(step*(Math.PI/2)));
						int spotY=(int)Math.round(y+Math.sin(step*(Math.PI/2)));
						
						if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE) {
							
							if((land&&tileGrid[spotY][spotX].getTraversible())||(water&&tileGrid[spotY][spotX].getIsWater())) {
								
								if(!tileGrid[spotY][spotX].isOccupied()||fight) {
									
									if(!surroundingTiles.contains(tileGrid[spotY][spotX])) {
										
										surroundingTiles.add(tileGrid[spotY][spotX]);
										
									}
									
								}
								
							}
							
						}
						
					}
					
				}
				
				//Adds all the new tiles we amassed to currentTiles.
				for(Tile t : surroundingTiles) {
					
					currentTiles.add(t);
					
				}
				
			}
			
		}
		
		//Removes the tile our unit is on.
		surroundingTiles.remove(0);
		return surroundingTiles;
		
	}
	
	/*
	 * Similar to getActTiles, except the algorithm is very different. To determine
	 * the tiles to place move/attack tiles on, it starts checking the tiles to the right
	 * of a unit in a line until it hits a bad tile, then goes up, then left, then down.
	 * Brings back 4 lines of tiles or less.
	 */
	public ArrayList<Tile> getActLine(boolean land, boolean water, boolean fight, int range) {
		
		ArrayList<Tile> unitTiles = this.getUnitTiles();
		ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
		
		if(unitTiles.size()!=1) {
			
			return null;
			
		} else {
			
			Tile unitTile = unitTiles.get(0);
			surroundingTiles.add(unitTile);
			
			int x=unitTile.getX();
			int y=unitTile.getY();
			
			for(int step=0;step<4;step++) {
				/*
				 * For loops work similarly to getActTiles, except this time, instead of amassing
				 * more and more tiles by adding tiles around each tile in an array list, the code
				 * simply goes through each cardinal direction, and adds tiles until the range is reached
				 * or it runs into a tile that a unit can't act on.
				 */
				for(int i=1;i<=range;i++) {
					
					int spotX=(int)Math.round(x+i*Math.cos(step*(Math.PI/2)));
					int spotY=(int)Math.round(y+i*Math.sin(step*(Math.PI/2)));
					
					if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE) {
						
						if((land&&tileGrid[spotY][spotX].getTraversible())||(water&&tileGrid[spotY][spotX].getIsWater())) {
							
							if(!tileGrid[spotY][spotX].isOccupied()||fight) {
								
								if(tileGrid[spotY][spotX].isOccupied()&&tileGrid[spotY][spotX].getUnit().getPlayer()==unitTile.getUnit().getPlayer()) break;
								
								surroundingTiles.add(tileGrid[spotY][spotX]);
								
								if(tileGrid[spotY][spotX].isOccupied()) break;
							
							//Break statements ensure a line stops being constructed as soon as a road block is hit.
							} else {
								break;
							}
							
						} else {
							break;
						}
						
					} else {
						break;
					}
					
				}
				
			}
			
		}
		
		surroundingTiles.remove(0);
		return surroundingTiles;
		
	}
	
	/*
	 * Cleaned up Hollow Circle Method. Simply Uses the Act Tiles command to get 
	 * an inner and outer circle, then removes the inner circle from the outer one.
	 */
	public ArrayList<Tile> getActHollow(boolean land, boolean water, boolean fight, int range, int blind) {
		
		ArrayList<Tile> outer = getActTiles(land,water,fight,range);
		ArrayList<Tile> inner = getActTiles(land,water,fight,range-blind);
		
		for(Tile t : inner) {
			
			if(outer.contains(t)) {
				
				outer.remove(t);
				
			}
			
		}
		
		return outer;
		
	}
	
	//Finds all tiles with SELECTED units in them
	public ArrayList<Tile> getUnitTiles() {
		
		ArrayList<Tile> unitTiles = new ArrayList<Tile>();
		
		for(Tile[] tR : tileGrid) {
			
			for(Tile tC : tR) {
				
				if(tC.getUnit()!=null&&tC.getUnit().isSelected()) {
					
					unitTiles.add(tC);
					
				}
				
			}
			
		}
		
		return unitTiles;
		
	}
	
	public ArrayList<Tile> getBuildingTiles(Player p) {
		
		ArrayList<Tile> buildingTiles = new ArrayList<Tile>();
		
		for(Tile[] tR : tileGrid) {
			
			for(Tile tC : tR) {
				
				if(tC.getUnit()!=null&&tC.getUnit() instanceof Building&&tC.getUnit().getPlayer()==p) {
					
					buildingTiles.add(tC);
					
				}
				
			}
			
		}
		
		return buildingTiles;
		
	}
	
	public void removeMapButtons() {
		
		for(Tile[] T : tileGrid) {
			
			for(Tile t : T) {
				
				t.removeMyButton();
				
			}
			
		}
		
	}
	
	public void deselectUnits() {
		
		for(Tile[] T : tileGrid) {
			
			for(Tile t : T) {
				
				t.deselectUnit();
				
			}
			
		}
		
	}
	
	//Remove everything from the Map.
	public void removeAll() {
		
		this.removeMapButtons();
		this.deselectUnits();
		
		ArrayList<Node> all = new ArrayList<Node>();
		
		for(Tile[] tt : tileGrid) {
			
			for(Tile t : tt) {
				
				if(t.getUnit()!=null)t.removeUnit(t.getUnit());
				all.add(t);
				
			}
			
		}
		
		this.getChildren().removeAll(all);
		
	}
		
	public Tile[][] getTiles() {
		
		return tileGrid;
		
	}
	
}
