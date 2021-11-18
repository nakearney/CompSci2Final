package application;

import javafx.scene.paint.Color;

public class NullButton extends MapButton {

	public NullButton() {
		
		super(Color.GREY);
		
	}

	@Override
	public void action() {
		
		//Null Buttons don't do anything. They are purely visual.
		
	}

}
