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

public abstract class MapButton extends Button {
	
	public MapButton(Color color) {
		
		format(color);
		
	}
	
	void setColor(Color color) {
		
		format(color);
		
	}
	
	public abstract void action();
	
	void format(Color color) {
		
		BorderStroke[] stroke =  {new BorderStroke(color,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};
		this.setBackground(null);
		this.setBorder(new Border(stroke));
		this.setTextFill(null);
		this.setPrefWidth(76);
		this.setPrefHeight(76);
		
		Button b = this;
		
		this.setOnMouseEntered((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				BorderStroke[] st =  {new BorderStroke(color.desaturate().brighter(),BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(8.0,8.0,8.0,8.0))};
				b.setBorder(new Border(st));
				
			}
			
		});
		
		this.setOnMouseExited((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				b.setBorder(new Border(stroke));
				
			}
			
		});
		
	}
	
}
