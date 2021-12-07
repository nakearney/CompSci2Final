package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BullMatador extends GenericUnit {
	
	public static final int COST = 5000;

	public BullMatador(Player player) {
		super(9, 5, 4, 3, player);
		cost = COST;
		if(player.getPlayerNumber()==1) {
			this.setGraphic(new ImageView(new Image("/Sprites/Matador_Bull_Player1.png")));
		} else {
			this.setGraphic(new ImageView(new Image("/Sprites/Matador_Bull_Player2.png")));
		}
	}
	
	public BullMatador(int hp, int attack, int movementRange, int attackRange, Player player) {
		super(hp, attack, movementRange, attackRange, player);
	}

	@Override
	public ArrayList<Tile> attackStyle(Map field) {
		ArrayList<Tile> attackArea = field.getFightLandLine(getAttackRange());
		return attackArea;
	}

	@Override
	public ArrayList<Tile> moveStyle(Map field) {
		ArrayList<Tile> moveArea = field.getMoveLandLine(getMovementRange());
		return moveArea;
	}

}
