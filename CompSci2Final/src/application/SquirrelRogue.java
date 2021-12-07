package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SquirrelRogue extends GenericUnit {
	
	public static final int COST = 2000;

	public SquirrelRogue(Player player) {
		super(1, 5, 4, 1, player);
		cost = COST;
		if(player.getPlayerNumber()==1) {
			this.setGraphic(new ImageView(new Image("/Sprites/Squirrel_Rogue_Player1.png")));
		} else {
			this.setGraphic(new ImageView(new Image("/Sprites/Squirrel_Rogue_Player2.png")));
		}
	}
	
	public SquirrelRogue(int hp, int attack, int movementRange, int attackRange, Player player) {
		super(hp, attack, movementRange, attackRange, player);
	}

	@Override
	public ArrayList<Tile> attackStyle(Map field) {
		ArrayList<Tile> attackArea = field.getFightLand(getAttackRange());
		return attackArea;
	}

	@Override
	public ArrayList<Tile> moveStyle(Map field) {
		ArrayList<Tile> moveArea = field.getMoveLand(getMovementRange());
		return moveArea;
	}

}
