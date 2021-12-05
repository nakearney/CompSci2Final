package application;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public abstract class GenericUnit extends Button {

	private int hp;
	private int attack;
	private int movementRange;
	private int attackRange;
	protected int cost;
	Player player;
	private boolean isDead;
	private boolean isSelected;
	private boolean hasMoved;
	private boolean hasAttacked;
	private HpDisplay hpDisp;
	
	public GenericUnit(int hp, int attack, int movementRange, int attackRange, Player player) { 
		
		this.hp = hp;
	
		this.attack = attack;
		this.movementRange = movementRange;
		this.attackRange = attackRange;
		this.player = player;
		this.hpDisp=new HpDisplay(this);
		cost = 0;
		isDead = false;
		isSelected = false;
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
						if(b instanceof Building) {
							Building build = (Building)b;
							if(build.getBuild()==true) {
								stroke[0] =  new BorderStroke(Color.GOLD,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0));
							} else {
								stroke[0] =  new BorderStroke(Color.GREY,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0));
							}
						} else {
							if(hasMoved&&hasAttacked) {
								stroke[0] =  new BorderStroke(Color.GREY,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0));
							} else {
								stroke[0] =  new BorderStroke(Color.GOLD,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0));
							}
						}	
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
					if(b instanceof Building) {
						
						Building build = (Building)b;
						if(build.getBuild()==true) {
							
							Main.theMap.removeMapButtons();
							
							isSelected=!isSelected;
							
							if(isSelected) {
								BorderStroke[] stroke =  {new BorderStroke(Color.PURPLE,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};
								b.setBorder(new Border(stroke));
							} else {
								BorderStroke[] stroke =  {new BorderStroke(Color.GOLD,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};
								b.setBorder(new Border(stroke));
							}
							
						}
						
					} else {
						
						if(!(hasMoved&&hasAttacked)) {
							
							Main.theMap.removeMapButtons();
							
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
					
				}
				
				
				
			}
			
		});

	}
	
	public int getHP() {
		return hp;
	}
	

	public void takeDamage(int damage) {
		hp-=damage;
		hpDisp.setHp(this);

		if(hp <= 0) {
			hp = 0;
			isDead = true;
		}
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getAttackRange() {
		return attackRange;
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
	
	public int getCost() {
		return cost;
	}
	public HpDisplay getHpDisp() {
		return hpDisp;
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
	
	public void attack(GenericUnit target) { 
		target.takeDamage(attack);
		
		if(target.isDead()) {
			AudioPlayer ap = new AudioPlayer("explosion.wav");
			ap.playSound();
			target.getPlayer().subtractUnit();
			this.getPlayer().addMoney(target.getCost()/2);
			GameGUI.turnDisplay(Main.player1, Main.player2);
		} else {
			AudioPlayer ap = new AudioPlayer("hit.mp3", 400);
			ap.playSound();
		}
	}
	
	public abstract ArrayList<Tile> attackStyle(Map field);
	
	public abstract ArrayList<Tile> moveStyle(Map field);
	
}
