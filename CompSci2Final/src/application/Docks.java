package application;

import java.util.ArrayList;

public class Docks extends Building {
	
	public Docks(Player player) {
		super(player);
	}
	
	public Docks(int hp, int attack, int movementRange, int attackRange, Player player) {
		this(player);
	}
	
	public void buildUnit(GenericUnit unit, Map field) { 
		
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
