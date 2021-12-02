package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DuckWizard extends GenericUnit {
	
	public static final int COST = 3000;

	public DuckWizard(Player player) {
		super(3, 4, 4, 2, player);
		cost = COST;
		if(player.getPlayerNumber()==1) {
			this.setGraphic(new ImageView(new Image("/Sprites/Duck_Wizard_Player1.png")));
		} else {
			this.setGraphic(new ImageView(new Image("/Sprites/Duck_Wizard_Player2.png")));
		}
	}
	
	public DuckWizard(int hp, int attack, int movementRange, int attackRange, Player player) {
		super(hp, attack, movementRange, attackRange, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Tile> attackStyle(Map field) {
		ArrayList<Tile> attackArea = field.getLineAttackTilesLandWater(getAttackRange());
		return attackArea;
	}

	@Override
	public ArrayList<Tile> nullStyle(Map field) {
		ArrayList<Tile> nullSpace = field.getSurroundingTilesLine(getAttackRange());
		return nullSpace;
	}

	@Override
	public ArrayList<Tile> moveStyle(Map field) {
		ArrayList<Tile> moveArea = field.getMovementTilesLandWater(getMovementRange());
		return moveArea;
	}

}
