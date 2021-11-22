package application;

import java.util.ArrayList;

public class Docks extends GenericUnit {
	
	boolean canBuild;
	
	public Docks(Player player) {
		super(20,0,0,0,player);
		canBuild = true;
		player.subtractUnit();
	}

	public Docks(int hp, int attack, int movementRange, Player player) {
		super(20,0,0,0,player);
		canBuild = true;
		player.subtractUnit();
	}
	
	public void buildUnit(GenericUnit unit, Map field) { //Figure out why one tile is missing
		
		if(canBuild) {
			ArrayList<Tile> buildTiles = field.getMovementTilesWater(1);
			
			for(int i = 0; i<buildTiles.size(); i++) {
				if(buildTiles.get(i).isOccupied()) {
					buildTiles.remove(i);
					i--;
				}
			}
			
			if(buildTiles.size() == 0) {
				return;
			}
			
			int tileCount = buildTiles.size();
			int index = (int)Math.random()*(tileCount);
			
			if(unit instanceof AxolotlGod) {
				buildTiles.get(index).setUnit((AxolotlGod)unit);
			} else if(unit instanceof DuckWizard) {
				buildTiles.get(index).setUnit((DuckWizard)unit);
			} else if(unit instanceof FlamingoSniper) {
				buildTiles.get(index).setUnit((FlamingoSniper)unit);
			} 
			
			canBuild = false;
		}
	}
	
	public boolean getBuild() {
		return canBuild;
	}
	
	public void reset() {
		canBuild = true;
	}

	@Override
	public ArrayList<Tile> attackStyle(Map field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Tile> nullStyle(Map field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Tile> moveStyle(Map field) {
		// TODO Auto-generated method stub
		return null;
	}

}
