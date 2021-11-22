package application;

import java.util.ArrayList;
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
	
	public void setUnit(int x, int y, GenericUnit unit) {
		
		tileGrid[y][x].setUnit(unit);
		
	}
	
	/*
	 * WARNING: getSurroundingTiles is a HIGHLY DISGUSTING & COMPACT Method. It it to be
	 * eventually revised to look and function cleaner, but for now, it is NASTY
	 * Returns an ArrayList of the tiles surrounding a certain unit given a certain range
	 * Only includes tiles which can be moved on
	 * The integer is for if you are 1: moving, 2: attacking, 3,4,5...: whatever else we need
	 */
	
	public ArrayList<Tile> getAttackTilesWater(int range) {
		
		ArrayList<Tile> unitTiles = this.getUnitTiles();
		ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
		
		if(unitTiles.size()!=1) {
			
			return null;
			
		} else {
		
			Tile unitTile = unitTiles.get(0);
			//Unit's x & y coords
			
			
			for(int i=0;i<range;i++) {
				
				if(i==0) {
					
					int x = unitTile.getX();
					int y = unitTile.getY();
					
					//Algorithm to add 4 tiles around unit. used a basis for adding more and more tiles.
					//Adds directly to the left, right, up and down of the tile.
					//cos = 1 when i=0 or 2, cos = 0 when i=1 or 3. Opposite for sin.
					for(int step=0;step<4;step++) {
						
						int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
						int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
						
						System.out.println(spotX);
						System.out.println(spotY);
						
						if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
						if(tileGrid[spotY][spotX].getIsWater())
						//if(tileGrid[spotY][spotX].isOccupied())
						surroundingTiles.add(tileGrid[spotY][spotX]);
						
					}
					
				} else {
					
					/*
					 * Goes through every tile in surrounding tiles and attempts to add more tiles
					 * to every adjacent open space around them.
					 */
					
					ArrayList<Tile> currentTiles = new ArrayList<Tile>();
					
					for(Tile t : surroundingTiles) {
						
						currentTiles.add(t);
						
					}
					
					for(Tile t : currentTiles) {
					
						int x = t.getX();
						int y = t.getY();
						
						for(int step=0;step<4;step++) {
								
							int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
							int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
								
							if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
							if(tileGrid[spotY][spotX].getIsWater())
							//if(tileGrid[spotY][spotX].isOccupied())
							//Key difference between when i==0 and when i!=0 is that the tile checks
							//to make sure the tile it is about to add hasn't already been added.
							if(!surroundingTiles.contains(tileGrid[spotY][spotX]))
							if(!(spotX==unitTile.getX()&&spotY==unitTile.getY()))
							surroundingTiles.add(tileGrid[spotY][spotX]);
								
						}
							
					}
					
				}
				
			}
			
		}
		
		ArrayList<Tile> tileChecker = new ArrayList<Tile>();
		
		for(Tile t : surroundingTiles) {
			
			tileChecker.add(t);
			
		}
		
		for(Tile t : tileChecker) {
			
			if(!surroundingTiles.get(surroundingTiles.indexOf(t)).isOccupied()) {
				surroundingTiles.remove(t);
			}
			
		}
		
		return surroundingTiles;
		
	}
	
	//For units that can only attack 
	public ArrayList<Tile> getAttackTilesLandWater(int range) {
		
		ArrayList<Tile> unitTiles = this.getUnitTiles();
		ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
		
		if(unitTiles.size()!=1) {
			
			return null;
			
		} else {
		
			Tile unitTile = unitTiles.get(0);
			//Unit's x & y coords
			
			
			for(int i=0;i<range;i++) {
				
				if(i==0) {
					
					int x = unitTile.getX();
					int y = unitTile.getY();
					
					//Algorithm to add 4 tiles around unit. used a basis for adding more and more tiles.
					//Adds directly to the left, right, up and down of the tile.
					//cos = 1 when i=0 or 2, cos = 0 when i=1 or 3. Opposite for sin.
					for(int step=0;step<4;step++) {
						
						int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
						int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
						
						System.out.println(spotX);
						System.out.println(spotY);
						
						if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
						if(tileGrid[spotY][spotX].getTraversible()||tileGrid[spotY][spotX].getIsWater())
						//if(tileGrid[spotY][spotX].isOccupied())
						surroundingTiles.add(tileGrid[spotY][spotX]);
						
					}
					
				} else {
					
					/*
					 * Goes through every tile in surrounding tiles and attempts to add more tiles
					 * to every adjacent open space around them.
					 */
					
					ArrayList<Tile> currentTiles = new ArrayList<Tile>();
					
					for(Tile t : surroundingTiles) {
						
						currentTiles.add(t);
						
					}
					
					for(Tile t : currentTiles) {
					
						int x = t.getX();
						int y = t.getY();
						
						for(int step=0;step<4;step++) {
								
							int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
							int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
								
							if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
							if(tileGrid[spotY][spotX].getTraversible()||tileGrid[spotY][spotX].getIsWater())
							//if(tileGrid[spotY][spotX].isOccupied())
							//Key difference between when i==0 and when i!=0 is that the tile checks
							//to make sure the tile it is about to add hasn't already been added.
							if(!surroundingTiles.contains(tileGrid[spotY][spotX]))
							if(!(spotX==unitTile.getX()&&spotY==unitTile.getY()))
							surroundingTiles.add(tileGrid[spotY][spotX]);
								
						}
							
					}
					
				}
				
			}
			
		}
		
		ArrayList<Tile> tileChecker = new ArrayList<Tile>();
		
		for(Tile t : surroundingTiles) {
			
			tileChecker.add(t);
			
		}
		
		for(Tile t : tileChecker) {
			
			if(!surroundingTiles.get(surroundingTiles.indexOf(t)).isOccupied()) {
				surroundingTiles.remove(t);
			}
			
		}
		
		return surroundingTiles;
		
	}
	
	//For units that can only attack targets on land
	public ArrayList<Tile> getAttackTilesLand(int range) {
		
		ArrayList<Tile> unitTiles = this.getUnitTiles();
		ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
		
		if(unitTiles.size()!=1) {
			
			return null;
			
		} else {
		
			Tile unitTile = unitTiles.get(0);
			//Unit's x & y coords
			
			
			for(int i=0;i<range;i++) {
				
				if(i==0) {
					
					int x = unitTile.getX();
					int y = unitTile.getY();
					
					//Algorithm to add 4 tiles around unit. used a basis for adding more and more tiles.
					//Adds directly to the left, right, up and down of the tile.
					//cos = 1 when i=0 or 2, cos = 0 when i=1 or 3. Opposite for sin.
					for(int step=0;step<4;step++) {
						
						int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
						int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
						
						System.out.println(spotX);
						System.out.println(spotY);
						
						if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
						if(tileGrid[spotY][spotX].getTraversible())
						//if(tileGrid[spotY][spotX].isOccupied())
						surroundingTiles.add(tileGrid[spotY][spotX]);
						
					}
					
				} else {
					
					/*
					 * Goes through every tile in surrounding tiles and attempts to add more tiles
					 * to every adjacent open space around them.
					 */
					
					ArrayList<Tile> currentTiles = new ArrayList<Tile>();
					
					for(Tile t : surroundingTiles) {
						
						currentTiles.add(t);
						
					}
					
					for(Tile t : currentTiles) {
					
						int x = t.getX();
						int y = t.getY();
						
						for(int step=0;step<4;step++) {
								
							int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
							int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
								
							if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
							if(tileGrid[spotY][spotX].getTraversible())
							//if(tileGrid[spotY][spotX].isOccupied())
							//Key difference between when i==0 and when i!=0 is that the tile checks
							//to make sure the tile it is about to add hasn't already been added.
							if(!surroundingTiles.contains(tileGrid[spotY][spotX]))
							if(!(spotX==unitTile.getX()&&spotY==unitTile.getY()))
							surroundingTiles.add(tileGrid[spotY][spotX]);
								
						}
							
					}
					
				}
				
			}
			
		}
		
		ArrayList<Tile> tileChecker = new ArrayList<Tile>();
		
		for(Tile t : surroundingTiles) {
			
			tileChecker.add(t);
			
		}
		
		for(Tile t : tileChecker) {
			
			if(!surroundingTiles.get(surroundingTiles.indexOf(t)).isOccupied()) {
				surroundingTiles.remove(t);
			}
			
		}
		
		return surroundingTiles;
		
	}
	
	//For units that can only move on water
	public ArrayList<Tile> getMovementTilesWater(int range) {
		
		ArrayList<Tile> unitTiles = this.getUnitTiles();
		ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
		
		if(unitTiles.size()!=1) {
			
			return null;
			
		} else {
		
			Tile unitTile = unitTiles.get(0);
			//Unit's x & y coords
			
			
			for(int i=0;i<range;i++) {
				
				if(i==0) {
					
					int x = unitTile.getX();
					int y = unitTile.getY();
					
					//Algorithm to add 4 tiles around unit. used a basis for adding more and more tiles.
					//Adds directly to the left, right, up and down of the tile.
					//cos = 1 when i=0 or 2, cos = 0 when i=1 or 3. Opposite for sin.
					for(int step=0;step<4;step++) {
						
						int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
						int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
						
						System.out.println(spotX);
						System.out.println(spotY);
						
						if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
						if(tileGrid[spotY][spotX].getIsWater())
						if(!tileGrid[spotY][spotX].isOccupied())
						surroundingTiles.add(tileGrid[spotY][spotX]);
						
					}
					
				} else {
					
					/*
					 * Goes through every tile in surrounding tiles and attempts to add more tiles
					 * to every adjacent open space around them.
					 */
					
					ArrayList<Tile> currentTiles = new ArrayList<Tile>();
					
					for(Tile t : surroundingTiles) {
						
						currentTiles.add(t);
						
					}
					
					for(Tile t : currentTiles) {
					
						int x = t.getX();
						int y = t.getY();
						
						for(int step=0;step<4;step++) {
								
							int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
							int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
								
							if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
							if(tileGrid[spotY][spotX].getIsWater())
							if(!tileGrid[spotY][spotX].isOccupied())
							//Key difference between when i==0 and when i!=0 is that the tile checks
							//to make sure the tile it is about to add hasn't already been added.
							if(!surroundingTiles.contains(tileGrid[spotY][spotX]))
							surroundingTiles.add(tileGrid[spotY][spotX]);
								
						}
							
					}
					
				}
				
			}
			
		}
		
		return surroundingTiles;
		
	}
	//method for attacking land in straight line
	public ArrayList<Tile> getLineAttackTilesLand(int range){
		return null;
		
	}
//method for attacking land and water in a straight line
	public ArrayList<Tile> getLineAttackTilesLandWater(int range){
		return null;
		
	}
//method for hollow circle attack radius
	public ArrayList<Tile> getHollowAttackTilesLandWater(int range, int hole){

		ArrayList<Tile> unitTiles = this.getUnitTiles();
		ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
		
		if(unitTiles.size()!=1) {
			
			return null;
			
		} else {
		
			Tile unitTile = unitTiles.get(0);
			//Unit's x & y coords
			
			
			for(int i=0;i<range;i++) {
				
				if(i==0) {
					
					int x = unitTile.getX();
					int y = unitTile.getY();
					
					//Algorithm to add 4 tiles around unit. used a basis for adding more and more tiles.
					//Adds directly to the left, right, up and down of the tile.
					//cos = 1 when i=0 or 2, cos = 0 when i=1 or 3. Opposite for sin.
					for(int step=0;step<4;step++) {
						
						int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
						int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
						
						System.out.println(spotX);
						System.out.println(spotY);
						
						if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
						if(tileGrid[spotY][spotX].getTraversible())
						//if(tileGrid[spotY][spotX].isOccupied())
						surroundingTiles.add(tileGrid[spotY][spotX]);
						
					}
					
				} else {
					
					/*
					 * Goes through every tile in surrounding tiles and attempts to add more tiles
					 * to every adjacent open space around them.
					 */
					
					ArrayList<Tile> currentTiles = new ArrayList<Tile>();
					
					for(Tile t : surroundingTiles) {
						
						currentTiles.add(t);
						
					}
					
					for(Tile t : currentTiles) {
					
						int x = t.getX();
						int y = t.getY();
						
						for(int step=0;step<4;step++) {
								
							int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
							int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
								
							if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
							if(tileGrid[spotY][spotX].getTraversible())
							//if(tileGrid[spotY][spotX].isOccupied())
							//Key difference between when i==0 and when i!=0 is that the tile checks
							//to make sure the tile it is about to add hasn't already been added.
							if(!surroundingTiles.contains(tileGrid[spotY][spotX]))
							if(!(spotX==unitTile.getX()&&spotY==unitTile.getY()))
							surroundingTiles.add(tileGrid[spotY][spotX]);
								
						}
							
					}
					
						
				}
				
			}
			
			//test removing hollow circle
			for(int i=0;i<hole;i++) {
				
				if(i==0) {
					
					int x = unitTile.getX();
					int y = unitTile.getY();
					
					//Algorithm to add 4 tiles around unit. used a basis for adding more and more tiles.
					//Adds directly to the left, right, up and down of the tile.
					//cos = 1 when i=0 or 2, cos = 0 when i=1 or 3. Opposite for sin.
					for(int step=0;step<4;step++) {
						
						int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
						int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
						
						System.out.println(spotX);
						System.out.println(spotY);
						
						if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
						if(tileGrid[spotY][spotX].getTraversible())
						//if(tileGrid[spotY][spotX].isOccupied())
						surroundingTiles.remove(tileGrid[spotY][spotX]);
						
					}
					
				} else {
					
					/*
					 * Goes through every tile in surrounding tiles and attempts to add more tiles
					 * to every adjacent open space around them.
					 */
					
					ArrayList<Tile> currentTiles = new ArrayList<Tile>();
					
					for(Tile t : surroundingTiles) {
						
						currentTiles.add(t);
						
					}
					
					for(Tile t : currentTiles) {
					
						int x = t.getX();
						int y = t.getY();
						
						for(int step=0;step<4;step++) {
								
							int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
							int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
								
							if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
							if(tileGrid[spotY][spotX].getTraversible())
							//if(tileGrid[spotY][spotX].isOccupied())
							//Key difference between when i==0 and when i!=0 is that the tile checks
							//to make sure the tile it is about to add hasn't already been added.
							if(!surroundingTiles.contains(tileGrid[spotY][spotX]))
							if(!(spotX==unitTile.getX()&&spotY==unitTile.getY()))
							surroundingTiles.remove(tileGrid[spotY][spotX]);
								
						}
							
					}
					
						
				}
				
			}
			
			//end removing circle				
			
		}
		
		ArrayList<Tile> tileChecker = new ArrayList<Tile>();
		
		for(Tile t : surroundingTiles) {
			
			tileChecker.add(t);
			
		}
		
		for(Tile t : tileChecker) {
			
			if(!surroundingTiles.get(surroundingTiles.indexOf(t)).isOccupied()) {
				surroundingTiles.remove(t);
			}
			
		}
		
		return surroundingTiles;
		
		
	}

	//For units that can move on land and water (But not rocks or sky)
	public ArrayList<Tile> getMovementTilesLandWater(int range) {
		
		ArrayList<Tile> unitTiles = this.getUnitTiles();
		ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
		
		if(unitTiles.size()!=1) {
			
			return null;
			
		} else {
		
			Tile unitTile = unitTiles.get(0);
			//Unit's x & y coords
			
			
			for(int i=0;i<range;i++) {
				
				if(i==0) {
					
					int x = unitTile.getX();
					int y = unitTile.getY();
					
					//Algorithm to add 4 tiles around unit. used a basis for adding more and more tiles.
					//Adds directly to the left, right, up and down of the tile.
					//cos = 1 when i=0 or 2, cos = 0 when i=1 or 3. Opposite for sin.
					for(int step=0;step<4;step++) {
						
						int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
						int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
						
						System.out.println(spotX);
						System.out.println(spotY);
						
						if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
						if(tileGrid[spotY][spotX].getTraversible()||tileGrid[spotY][spotX].getIsWater())
						if(!tileGrid[spotY][spotX].isOccupied())
						surroundingTiles.add(tileGrid[spotY][spotX]);
						
					}
					
				} else {
					
					/*
					 * Goes through every tile in surrounding tiles and attempts to add more tiles
					 * to every adjacent open space around them.
					 */
					
					ArrayList<Tile> currentTiles = new ArrayList<Tile>();
					
					for(Tile t : surroundingTiles) {
						
						currentTiles.add(t);
						
					}
					
					for(Tile t : currentTiles) {
					
						int x = t.getX();
						int y = t.getY();
						
						for(int step=0;step<4;step++) {
								
							int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
							int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
								
							if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
							if(tileGrid[spotY][spotX].getTraversible()||tileGrid[spotY][spotX].getIsWater())
							if(!tileGrid[spotY][spotX].isOccupied())
							//Key difference between when i==0 and when i!=0 is that the tile checks
							//to make sure the tile it is about to add hasn't already been added.
							if(!surroundingTiles.contains(tileGrid[spotY][spotX]))
							surroundingTiles.add(tileGrid[spotY][spotX]);
								
						}
							
					}
					
				}
				
			}
			
		}
		
		return surroundingTiles;
		
	}
	
	//For units that can only move on land
	public ArrayList<Tile> getMovementTilesLand(int range) {
		
		ArrayList<Tile> unitTiles = this.getUnitTiles();
		ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
		
		if(unitTiles.size()!=1) {
			
			return null;
			
		} else {
		
			Tile unitTile = unitTiles.get(0);
			//Unit's x & y coords
			
			
			for(int i=0;i<range;i++) {
				
				if(i==0) {
					
					int x = unitTile.getX();
					int y = unitTile.getY();
					
					//Algorithm to add 4 tiles around unit. used a basis for adding more and more tiles.
					//Adds directly to the left, right, up and down of the tile.
					//cos = 1 when i=0 or 2, cos = 0 when i=1 or 3. Opposite for sin.
					for(int step=0;step<4;step++) {
						
						int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
						int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
						
						System.out.println(spotX);
						System.out.println(spotY);
						
						if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
						if(tileGrid[spotY][spotX].getTraversible())
						if(!tileGrid[spotY][spotX].isOccupied())
						surroundingTiles.add(tileGrid[spotY][spotX]);
						
					}
					
				} else {
					
					/*
					 * Goes through every tile in surrounding tiles and attempts to add more tiles
					 * to every adjacent open space around them.
					 */
					
					ArrayList<Tile> currentTiles = new ArrayList<Tile>();
					
					for(Tile t : surroundingTiles) {
						
						currentTiles.add(t);
						
					}
					
					for(Tile t : currentTiles) {
					
						int x = t.getX();
						int y = t.getY();
						
						for(int step=0;step<4;step++) {
								
							int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
							int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
								
							if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
							if(tileGrid[spotY][spotX].getTraversible())
							if(!tileGrid[spotY][spotX].isOccupied())
							//Key difference between when i==0 and when i!=0 is that the tile checks
							//to make sure the tile it is about to add hasn't already been added.
							if(!surroundingTiles.contains(tileGrid[spotY][spotX]))
							surroundingTiles.add(tileGrid[spotY][spotX]);
								
						}
							
					}
					
				}
				
			}
			
		}
		
		return surroundingTiles;
		
	}
	
	/*
	 * Used to make null buttons. Do not delete.
	 * gets EVERy tile surrounding a unit, regardless if that unit is occupied,
	 * water, traversible, etc.
	 */
	public ArrayList<Tile> getSurroundingTiles(int range) {
		
		ArrayList<Tile> unitTiles = this.getUnitTiles();
		ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
		
		if(unitTiles.size()!=1) {
			
			return null;
			
		} else {
		
			Tile unitTile = unitTiles.get(0);
			//Unit's x & y coords
			
			
			for(int i=0;i<range;i++) {
				
				if(i==0) {
					
					int x = unitTile.getX();
					int y = unitTile.getY();
					
					//Algorithm to add 4 tiles around unit. used a basis for adding more and more tiles.
					//Adds directly to the left, right, up and down of the tile.
					//cos = 1 when i=0 or 2, cos = 0 when i=1 or 3. Opposite for sin.
					for(int step=0;step<4;step++) {
						
						int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
						int spotY=(int)(y+Math.sin(step*(Math.PI/2)));

						
						if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
						surroundingTiles.add(tileGrid[spotY][spotX]);
						
					}
					
				} else {
					
					/*
					 * Goes through every tile in surrounding tiles and attempts to add more tiles
					 * to every adjacent open space around them.
					 */
					
					ArrayList<Tile> currentTiles = new ArrayList<Tile>();
					
					for(Tile t : surroundingTiles) {
						
						currentTiles.add(t);
						
					}
					
					for(Tile t : currentTiles) {
					
						int x = t.getX();
						int y = t.getY();
						
						for(int step=0;step<4;step++) {
								
							int spotX=(int)(x+Math.cos(step*(Math.PI/2)));
							int spotY=(int)(y+Math.sin(step*(Math.PI/2)));
								
							if(spotY>-1&&spotY<MAP_SIZE&&spotX>-1&&spotX<MAP_SIZE)
							if(!surroundingTiles.contains(tileGrid[spotY][spotX]))
							if(!(spotY==unitTile.getY()&&spotX==unitTile.getX()))
							surroundingTiles.add(tileGrid[spotY][spotX]);
								
						}
							
					}
					
				}
				
			}
			
		}
		
		return surroundingTiles;
		
	}
	
	//Gets the lines of tiles of a certain range surrounding a unit.
	public ArrayList<Tile> getSurroundingTileLine(int range) {
		
		ArrayList<Tile> unitTiles = this.getUnitTiles();
		ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
		
		if(unitTiles.size()!=1) {
			
			return null;
			
		} else {
		
			Tile unitTile = unitTiles.get(0);
			//Unit's x & y coords
			int x = unitTile.getX();
			int y = unitTile.getY();
			
			for(int i=0;i<4;i++) {
				//Algorithm to add 4 tiles around unit. used a basis for adding more and more tiles.
				//Adds directly to the left, right, up and down of the tile.
				//cos = 1 when i=0 or 2, cos = 0 when i=1 or 3. Opposite for sin.
				for(int step=1;step<=range;step++) {
					
					int shiftX=x+(int)(step*Math.cos(i*(Math.PI/2)));
					int shiftY=y+(int)(step*Math.sin(i*(Math.PI/2)));
					
					if(shiftY>-1&&shiftY<MAP_SIZE&&shiftX>-1&&shiftX<MAP_SIZE)
					surroundingTiles.add(tileGrid[shiftY][shiftX]);
					
				}
				
			}
			
		}
		
		return surroundingTiles;
		
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
		
	public Tile[][] getTiles() {
		
		return tileGrid;
		
	}
	
}
