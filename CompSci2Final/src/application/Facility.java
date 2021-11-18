package application;

import java.util.ArrayList;

public class Facility extends GenericUnit {

	public Facility(int hp, int attack, int movementRange, Player player) {
		super(20,0,0,0,player);
	}
	
	public void buildUnit(GenericUnit unit) {
		if(unit instanceof CatSoldier) {
			
		} else if(unit instanceof SquirrelRogue) {
			
		} else if(unit instanceof AxolotlGod) {
			
		} else if(unit instanceof DuckWizard) {
			
		} else if(unit instanceof FlamingoSniper) {
			
		} else if(unit instanceof ArmadilloTank) {
			
		} else if(unit instanceof BullMatador) {
			
		}
	}

	@Override
	public ArrayList<Tile> attackStyle(Map field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Tile> nullStyle(Map field) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Tile> moveStyle(Map field) {
		// TODO Auto-generated method stub
		return null;
	}

}
