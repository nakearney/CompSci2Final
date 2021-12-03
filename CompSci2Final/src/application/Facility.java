package application;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Facility extends Building {
	
	public Facility(Player player) {
		super(player);
		
		if(player.getPlayerNumber()==1) {
			this.setGraphic(new ImageView(new Image("/Sprites/Barracks_Player1.png")));
		} else {
			this.setGraphic(new ImageView(new Image("/Sprites/Barracks_Player2.png")));
		}
		
	}
	
	public Facility(int hp, int attack, int movementRange, int attackRange, Player player) {
		this(player);
	}
	
	public void buildUnit(GenericUnit unit, Map field) { 
		
		if(canBuild!=0) {
			ArrayList<Tile> buildTiles = field.getMoveLand(1);
			
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
			
			if(unit instanceof CatSoldier) {
				buildTiles.get(index).setUnit((CatSoldier)unit);
			} else if(unit instanceof SquirrelRogue) {
				buildTiles.get(index).setUnit((SquirrelRogue)unit);
			} else if(unit instanceof DuckWizard) {
				buildTiles.get(index).setUnit((DuckWizard)unit);
			} else if(unit instanceof FlamingoSniper) {
				buildTiles.get(index).setUnit((FlamingoSniper)unit);
			} else if(unit instanceof ArmadilloTank) {
				buildTiles.get(index).setUnit((ArmadilloTank)unit);
			} else if(unit instanceof BullMatador) {
				buildTiles.get(index).setUnit((BullMatador)unit);
			}
			
			canBuild--;
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
