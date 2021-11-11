package application;

public class GenericUnit { //Will become abstract 

	int hp;
	int attack;
	int movementRange;
	Player player;
	boolean isDead;
	//Add Image Variable
	
	public GenericUnit(int hp, int attack, int movementRange, Player player) { // Add Image Parameter
		this.hp = hp;
		this.attack = attack;
		this.movementRange = movementRange;
		this.player = player;
		isDead = false;
		player.addUnit();
	}
	
	public int getHP() {
		return hp;
	}
	
	public void takeDamage(int damage) { //Nick has an idea for unit death
		hp = hp - damage;
		if(hp <= 0) {
			hp = 0;
			isDead = true;
		}
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getMovementRange() {
		return movementRange;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public void move() { //Maybe need a move function here? | Don't think so though | Would become abstract
		
	}
	
	public void attack(GenericUnit target) { //Would be an abstract method though it will follow a similar formula 
		target.takeDamage(attack);
		if(target.isDead()) {
			target.getPlayer().subtractUnit();
			//Remove unit from field
		}
	}
	
	
		
	
	
}
