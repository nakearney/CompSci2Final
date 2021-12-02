package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AxolotlCaptain extends GenericUnit {
	
	public static final int COST = 2000;
	
	public AxolotlCaptain(Player player) {
		super(4, 3, 3, 3, player);
		cost = COST;
		if(player.getPlayerNumber()==1) {
			this.setGraphic(new ImageView(new Image("/Sprites/Axolotl_Sailor_Player1.png")));
		} else {
			this.setGraphic(new ImageView(new Image("/Sprites/Axolotl_Sailor_Player2.png")));
		}
	}
	
	public AxolotlCaptain(int hp, int attack, int movementRange, int attackRange, Player player) {
		super(hp, attack, movementRange, attackRange, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Tile> attackStyle(Map field) {
		ArrayList<Tile> attackArea = field.getAttackTilesLandWater(getAttackRange());
		return attackArea;
	}

	@Override
	public ArrayList<Tile> nullStyle(Map field) {
		ArrayList<Tile> nullSpace = field.getSurroundingTiles(getAttackRange());
		return nullSpace;
	}

	@Override
	public ArrayList<Tile> moveStyle(Map field) {
		ArrayList<Tile> moveArea=field.getMovementTilesWater(getMovementRange());
		return moveArea;
	}

	

}
