package application;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class AttackButton extends MapButton {

	private Tile myTile,startPoint;
	private GenericUnit myUnit;
	
	public AttackButton(Tile myTile, Tile startPoint, GenericUnit myUnit) {
		super(Color.RED);
		if(myUnit.getFought()) format(Color.GREY);
		this.myTile=myTile;
		this.startPoint=startPoint;
		this.myUnit=myUnit;
		
		this.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				action();
				
			}
			
		});
		
	}

	@Override
	public void action() {
		
		if(!myUnit.getFought()) {
			
			myUnit.attack(myTile.getUnit());
			if(myTile.getUnit().isDead()) {
				
				myTile.removeUnit(myTile.getUnit());
				
			}
			myUnit.fought();
			Main.theMap.deselectUnits();
			Main.theMap.removeMapButtons();
			
		}
		
	}

}
