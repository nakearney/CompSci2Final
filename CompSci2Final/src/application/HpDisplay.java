package application;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HpDisplay extends Group {
	
	private Text health = new Text("5");
	private StackPane graphic;
	private Rectangle rect;

	public HpDisplay() {
		graphic = new StackPane();
		graphic.setBackground(null);
		this.getChildren().add(graphic);
		this.getChildren().add(health);

	}

	public HpDisplay(GenericUnit unit) {
		graphic = new StackPane();
		rect = new Rectangle();

		Font font = Font.font("Verdana", FontWeight.BOLD, 10);

		rect.setWidth(20);
		rect.setHeight(20);
		rect.setFill(Color.WHITE);
		rect.setArcHeight(7);
		rect.setArcWidth(7);
		graphic.setMaxSize(20, 20);

		int hold = unit.getHP();
		String h = String.format("%d", hold);
		health.setText(h);
		health.setStrokeWidth(3);
		health.setFont(font);
		if (unit.getPlayer().getPlayerNumber() == 1) {
			health.setFill(Color.RED);
			rect.setStroke(Color.RED);
		} else {
			health.setFill(Color.BLUE);
			rect.setStroke(Color.BLUE);
		}
		health.setScaleY(1.2);
		health.setScaleX(1.2);
		graphic.getChildren().add(rect);
		graphic.getChildren().add(health);
		this.getChildren().add(graphic);

	}

	public void setHp(GenericUnit unit) {
		int hold = unit.getHP();
		String h = String.format("%d", hold);
		health.setText(h);
	}

	public void remove() {
		graphic.getChildren().remove(graphic);
		graphic.getChildren().remove(health);
	}

}
