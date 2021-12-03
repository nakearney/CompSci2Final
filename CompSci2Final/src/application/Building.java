package application;

public abstract class Building extends GenericUnit {
	
	protected int canBuild;
	
	public Building(Player player) {
		super(1,0,0,0,player);
		canBuild = determineBuild();
		player.subtractUnit();
	}

	public Building(int hp, int attack, int movementRange, int attackRange, Player player) {
		super(1,0,0,0,player);
		canBuild = determineBuild();
		player.subtractUnit();
	}
	
	public int getBuild() {
		return canBuild;
	}
	
	public void reset() {
		canBuild = determineBuild();
	}
	
	public abstract void buildUnit(GenericUnit unit, Map field);
	
	int determineBuild() {
		
		if(player==Main.player1) {
			
			int num = player.getUnitCount()-Main.player2.getUnitCount();
			
			if(num<0) {
				return Math.abs(num);
			} else {
				return 1;
			}
			
		} else {
			
			int num = player.getUnitCount()-Main.player1.getUnitCount();
			
			if(num<0) {
				return Math.abs(num)+1;
			} else {
				return 1;
			}
			
		}
		
	}

}
