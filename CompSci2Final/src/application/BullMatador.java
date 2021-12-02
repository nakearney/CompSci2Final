package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BullMatador extends GenericUnit {
	
	public static final int COST = 5000;

	public BullMatador(Player player) {
		super(8, 4, 3, 4, player);
		cost = COST;
		if(player.getPlayerNumber()==1) {
			this.setGraphic(new ImageView(new Image("/Sprites/RedTank.png")));
		} else {
			this.setGraphic(new ImageView(new Image("/Sprites/BlueTank.png")));
		}
	}
	
	public BullMatador(int hp, int attack, int movementRange, int attackRange, Player player) {
		super(hp, attack, movementRange, attackRange, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Tile> attackStyle(Map field) {
		ArrayList<Tile> attackArea = field.getLineAttackTilesLand(getAttackRange());
		return attackArea;
	}

	@Override
	public ArrayList<Tile> nullStyle(Map field) {
		ArrayList<Tile> nullSpace = field.getSurroundingTilesLine(getAttackRange());
		return nullSpace;
	}

	@Override
	public ArrayList<Tile> moveStyle(Map field) {
		ArrayList<Tile> moveArea = field.getMovementTilesLand(getMovementRange());
		return moveArea;
	}

}
