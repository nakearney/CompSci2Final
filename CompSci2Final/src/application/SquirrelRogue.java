package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SquirrelRogue extends GenericUnit {
	
	public static final int COST = 1500;

	public SquirrelRogue(Player player) {
		super(3, 4, 4, 1, player);
		cost = COST;
		if(player.getPlayerNumber()==1) {
			this.setGraphic(new ImageView(new Image("/Sprites/RedTank.png")));
		} else {
			this.setGraphic(new ImageView(new Image("/Sprites/BlueTank.png")));
		}
	}
	
	public SquirrelRogue(int hp, int attack, int movementRange, int attackRange, Player player) {
		super(hp, attack, movementRange, attackRange, player);
	}

	@Override
	public ArrayList<Tile> attackStyle(Map field) {
		ArrayList<Tile> attackArea = field.getAttackTilesLand(getAttackRange());
		return attackArea;
	}

	@Override
	public ArrayList<Tile> nullStyle(Map field) {
		ArrayList<Tile> nullSpace = field.getSurroundingTiles(getAttackRange());
		return nullSpace;

	}

	@Override
	public ArrayList<Tile> moveStyle(Map field) {
		ArrayList<Tile> moveArea = field.getMovementTilesLand(getMovementRange());
		return moveArea;
	}

}
