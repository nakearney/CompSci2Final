package application;

public class GenericUnit { //Will become abstract 

	int hp;
	int attack;
	int movementRange;
	boolean isAlive;
	//Add Image Variable
	
	public GenericUnit(int hp, int attack, int movementRange) { // Add Image Parameter
		this.hp = hp;
		this.attack = attack;
		this.movementRange = movementRange;
	}
	
	public int getHP() {
		return hp;
	}
	
	public void takeDamage(int damage) { //Nick has an idea for unit death
		hp = hp - damage;
		if(hp <= 0) {
			isAlive = false;
		}
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getMovementRange() {
		return movementRange;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void move() { //Maybe need a move function here? | Don't think so though | Would become abstract
		
	}
	
	public void attack(GenericUnit target) { //Would be an abstract method
		target.takeDamage(attack);
	}
	
	
		
	
	
}
