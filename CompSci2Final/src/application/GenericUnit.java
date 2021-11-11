package application;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GenericUnit extends Button { //Will become abstract 

	private int hp;
	private int attack;
	private int movementRange;
	private Player player;
	private boolean isDead;
	private boolean isSelected;
	
	public GenericUnit(int hp, int attack, int movementRange, Player player) { // Add Image Parameter
		this.hp = hp;
		this.attack = attack;
		this.movementRange = movementRange;
		this.player = player;
		isDead = false;
		isSelected = false;
		if(player.getPlayerNumber()==1) {
			this.setGraphic(new ImageView(new Image("/Sprites/RedTank.png")));
		} else {
			this.setGraphic(new ImageView(new Image("/Sprites/BlueTank.png")));
		}
		this.setBackground(null);
		this.setMaxHeight(30);
		this.setMaxWidth(30);
		player.addUnit();
		Button b = this;
		
		this.setOnMouseEntered((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				if(isSelected) {
					BorderStroke[] stroke =  {new BorderStroke(Color.PURPLE,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};
					b.setBorder(new Border(stroke));
				} else {
					BorderStroke[] stroke =  {new BorderStroke(Color.GOLD,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};
					b.setBorder(new Border(stroke));
				}
			}
		});
		
		this.setOnMouseExited((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) {
				if(!isSelected) {
					b.setBorder(null);
				}
				
			}
		});
		
		
		this.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				isSelected = !isSelected;
				if(isSelected) {
					BorderStroke[] stroke =  {new BorderStroke(Color.PURPLE,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};
					b.setBorder(new Border(stroke));
				} else {
					BorderStroke[] stroke =  {new BorderStroke(Color.GOLD,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};
					b.setBorder(new Border(stroke));
				}
			}
			
		});

	}
	
	public int getHP() {
		return hp;
	}
	

	public void takeDamage(int damage) { //Nick has an idea for unit death
		hp-=damage;
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
