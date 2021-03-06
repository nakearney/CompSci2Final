package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Carrier extends Building {
	
	public Carrier(Player player) {
		super(player);
		if(player.getPlayerNumber()==1) {
			this.setGraphic(new ImageView(new Image("/Sprites/Aircraft_Carrier_Player1.png")));
		} else {
			this.setGraphic(new ImageView(new Image("/Sprites/Aircraft_Carrier_Player2.png")));
		}
	}
	
	public Carrier(int hp, int attack, int movementRange, int attackRange, Player player) {
		this(player);
	}
	
	public void buildUnit(GenericUnit unit, Map field) { 
		
		if(canBuild) {
			ArrayList<Tile> buildTiles = field.getMoveWater(1);
			
			for(int i = 0; i<buildTiles.size(); i++) {
				if(buildTiles.get(i).isOccupied()) {
					buildTiles.remove(i);
					i--;
				}
			}
			
			if(buildTiles.size() == 0) {
				return;
			}
			
			int tileCount = buildTiles.size();
			int index = (int)(Math.random()*(tileCount));
			
			if(unit instanceof AxolotlCaptain) {
				buildTiles.get(index).setUnit((AxolotlCaptain)unit);
			} else if(unit instanceof DuckWizard) {
				buildTiles.get(index).setUnit((DuckWizard)unit);
			} else if(unit instanceof FlamingoSniper) {
				buildTiles.get(index).setUnit((FlamingoSniper)unit);
			} 
			
			canBuild=false;
			
		}
	}

	@Override
	public ArrayList<Tile> attackStyle(Map field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Tile> moveStyle(Map field) {
		// TODO Auto-generated method stub
		return null;
	}

}
