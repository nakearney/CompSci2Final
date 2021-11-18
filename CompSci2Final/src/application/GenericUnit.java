package application;

import java.util.ArrayList;

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

public abstract class GenericUnit extends Button { //Will become abstract 

	private String name;
	private int hp;
	private int attack;
	private int movementRange;
	private Player player;
	private boolean isDead;
	private boolean isSelected;
	private boolean hasMoved;
	private boolean hasAttacked;
	
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
					
					BorderStroke[] stroke;
					stroke = new BorderStroke[1];
					
					if(player.yourTurn()) {
						stroke[0] =  new BorderStroke(Color.GOLD,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0));
					} else {
						stroke[0] =  new BorderStroke(Color.GREY,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0));
					}
					
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
				
				if(player.yourTurn()) {
					
					Main.map1.removeMapButtons();
					
					isSelected=!isSelected;
					
					if(isSelected) {
						BorderStroke[] stroke =  {new BorderStroke(Color.PURPLE,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};
						b.setBorder(new Border(stroke));
					} else {
						BorderStroke[] stroke =  {new BorderStroke(Color.GOLD,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};
						b.setBorder(new Border(stroke));
					}
					
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
	
	public void moved() {
		
		hasMoved=true;
		
	}
	
	public void fought() {
		
		hasMoved=true;
		hasAttacked=true;
		
	}
	
	public boolean getMoved() {
		
		return hasMoved;
		
	}
	
	public boolean getFought() {
		
		return hasAttacked;
		
	}
	
	public void resetActions() {
		
		hasMoved=false;
		hasAttacked=false;
		
	}
	
	public void attack(GenericUnit target) { //Would be an abstract method though it will follow a similar formula 
		target.takeDamage(attack);
		if(target.isDead()) {
			target.getPlayer().subtractUnit();
		}
	}
	
	public abstract ArrayList<Tile> attackStyle(Map field);
	
}
