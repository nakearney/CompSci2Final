package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FlamingoSniper extends GenericUnit {
	public int hollow;
	public static final int COST = 120;
	
	public FlamingoSniper(Player player) {
		super(3, 5, 2, 4, player);
		hollow=2;
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
		ArrayList<Tile> attackArea = field.getFightLandWaterHollow(getAttackRange(),hollow);
		return attackArea;
	}

	@Override
	public ArrayList<Tile> moveStyle(Map field) {
		ArrayList<Tile> moveArea = field.getMoveLandWater(getMovementRange());
		return moveArea;
	}

}
