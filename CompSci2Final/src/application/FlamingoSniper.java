package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FlamingoSniper extends GenericUnit {
	public int hollow;
	public static final int COST = 4000;
	
	public FlamingoSniper(Player player) {
		super(3, 5, 4, 2, player);
		hollow=3;
		cost = COST;
		if(player.getPlayerNumber()==1) {
			this.setGraphic(new ImageView(new Image("/Sprites/Flamingo_Sniper_Player1.png")));
		} else {
			this.setGraphic(new ImageView(new Image("/Sprites/Flamingo_Sniper_Player2.png")));
		}
	}

	public FlamingoSniper(int hp, int attack, int movementRange, int attackRange, Player player) {
		super(hp, attack, movementRange, attackRange, player);
	}

	@Override
	public ArrayList<Tile> attackStyle(Map field) {
		ArrayList<Tile> attackArea = field.getHollowAttackTilesLandWater(getAttackRange(),hollow);
		return attackArea;
	}

	@Override
	public ArrayList<Tile> nullStyle(Map field) {
		ArrayList<Tile> nullSpace = field.getSurroundingTiles(getAttackRange());
		return nullSpace;

	}

	@Override
	public ArrayList<Tile> moveStyle(Map field) {
		ArrayList<Tile> moveArea = field.getMovementTilesLandWater(getMovementRange());
		return moveArea;
	}

}
