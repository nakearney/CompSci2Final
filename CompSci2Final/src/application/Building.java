package application;

public abstract class Building extends GenericUnit {
	
	protected boolean canBuild;
	
	public Building(Player player) {
		super(20,0,0,0,player);
		canBuild = true;
		player.subtractUnit();
	}

	public Building(int hp, int attack, int movementRange, int attackRange, Player player) {
		super(20,0,0,0,player);
		canBuild = true;
		player.subtractUnit();
	}
	
	public boolean getBuild() {
		return canBuild;
	}
	
	public void reset() {
		canBuild = true;
	}
	
	public abstract void buildUnit(GenericUnit unit, Map field);

}
