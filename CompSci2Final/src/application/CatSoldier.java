package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CatSoldier extends GenericUnit {
	
	public static final int COST = 1000;

	public CatSoldier(Player player) {
		super(5, 3, 2, 3, player);
		cost = COST;
		if(player.getPlayerNumber()==1) {
			this.setGraphic(new ImageView(new Image("/Sprites/Cat_Soldier_Player1.png")));
		} else {
			this.setGraphic(new ImageView(new Image("/Sprites/Cat_Soldier_Player2.png")));
		}
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
