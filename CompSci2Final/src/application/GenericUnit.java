package application;

public class GenericUnit { //Will become abstract | Figure out handling of unit destruction

	int hp;
	int attack;
	int movementRange;
	
	public GenericUnit(int hp, int attack, int movementRange) {
		this.hp = hp;
		this.attack = attack;
		this.movementRange = movementRange;
	}
	
	public int getHP() {
		return hp;
	}
	
	public void setHP(int newHP) { //Sets HP & will maybe handle unit destruction
		
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getMovementRange() {
		return movementRange;
	}
	
	public void move() { //Maybe need a move function here? | Don't think so though | Would become abstract
		
	}
	
	public void attack(GenericUnit target) { //Would be an abstract method
		
	}
	
	
		
	
	
}
