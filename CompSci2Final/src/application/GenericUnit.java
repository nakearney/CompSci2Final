package application;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class GenericUnit extends Button { //Will become abstract 

	private int hp;
	private int attack;
	private int movementRange;
	private Player player;
	private boolean isDead;
	private boolean isSelected;
	//Add Image Variable
	
	public GenericUnit(int hp, int attack, int movementRange, Player player) { // Add Image Parameter
		this.hp = hp;
		this.attack = attack;
		this.movementRange = movementRange;
		this.player = player;
		isDead = false;
		isSelected = false;
		player.addUnit();
		this.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				isSelected = !isSelected;
			}
			
		});
	}
	
	public int getHP() {
		return hp;
	}
	
	public void takeDamage(int damage) { 
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
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public void move() { //Maybe need a move function here? | Don't think so though | Would become abstract
		
	}
	
	public void attack(GenericUnit target) { //Would be an abstract method though it will follow a similar formula 
		target.takeDamage(attack);
		if(target.isDead()) {
			target.getPlayer().subtractUnit();
			//Destroy unit
		}
	}
	
	
		
	
	
}
