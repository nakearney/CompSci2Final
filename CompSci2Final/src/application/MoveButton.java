package application;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MoveButton extends MapButton {

	Tile myTile,startPoint;
	GenericUnit myUnit;
	
	public MoveButton(Tile myTile, Tile startPoint, GenericUnit myUnit) {
		
		super(Color.BLUE);
		if(myUnit.getMoved()) format(Color.GREY);
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
		
		if(!myUnit.getMoved()) {
			
			myTile.setUnit(myUnit);
			startPoint.removeUnit(myUnit);
			myTile.getUnit().moved();
			Main.theMap.deselectUnits();
			Main.theMap.removeMapButtons();
			
		}
	}

}
