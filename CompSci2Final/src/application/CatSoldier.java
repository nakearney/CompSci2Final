package application;

import java.util.ArrayList;

public class CatSoldier extends GenericUnit {

	public CatSoldier(Player player) {
		super(5, 3, 3, 2, player);
	}

	@Override
	public ArrayList<Tile> attackStyle(Map field) {
		
		ArrayList<Tile> attackArea = field.getAttackTilesLand(getAttackRange());
		return attackArea;
		
	}
	
	public ArrayList<Tile> moveStyle(Map field) {
		
		ArrayList<Tile> moveArea = field.getMovementTilesLand(getMovementRange());
		return moveArea;
		
	}

	@Override
	public ArrayList<Tile> nullStyle(Map field) {
		
		ArrayList<Tile> nullSpace = field.getSurroundingTiles(getAttackRange());
		return nullSpace;
		
	}

}
