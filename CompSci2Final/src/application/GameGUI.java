package application;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameGUI extends BorderPane {

	private Map field;
	private HBox buttons;
	private VBox econGUI;
	private ScrollPane infoGUI;
	private Label turnGUI;
	private Button attack;
	private Button move;
	private Button endTurn;
	private Player p1;
	private Player p2;
	private Player empty = new Player(0,false);
	private double sideWidth=225.0;
	
	//Initialize Images here
	private Image catImage = new Image("/Sprites/RedTank.png");
	private Image squirrelImage = new Image("/Sprites/RedTank.png");
	private Image axolotlImage = new Image("/Sprites/RedTank.png");;
	private Image duckImage = new Image("/Sprites/RedTank.png");;
	private Image flamingoImage = new Image("/Sprites/RedTank.png");;
	private Image armadilloImage = new Image("/Sprites/RedTank.png");;
	private Image bullImage = new Image("/Sprites/RedTank.png");;
	
	//Initializes Map in Main Currently. Then passes it here.
	public GameGUI(Player p1, Player p2, Map field) {
		
		setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		
		ScrollPane scroller = new ScrollPane();
		scroller.setPannable(true);
		scroller.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		scroller.setMaxHeight(600*1.81);
		scroller.setMaxWidth(600*1.81);
		scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		this.field = field;
		scroller.setContent(field);
		setCenter(scroller);
		
		turnGUI = new Label();
		turnGUI.setTextFill(Color.WHITESMOKE);
		turnGUI.setFont(new Font("Arial",20));
		turnGUI.setTextAlignment(TextAlignment.CENTER);
		turnDisplay(p1,p2);
		
		initInfoGUI();
		
		econGUI = new VBox();
		econGUI.setPrefWidth(sideWidth);
		
		Button cat = new Button("Cat Soldier: $" + CatSoldier.COST);
		formatEconButton(cat, CatSoldier.COST, p1, p2);
		cat.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() { 
			@Override
			public void handle(MouseEvent event) {
				
				ArrayList<Tile> unitList = field.getUnitTiles();
				if(unitList.size()==1 && unitList.get(0).getUnit() instanceof Facility) { //Checks for facility selected
					
					Facility f = (Facility)unitList.get(0).getUnit();
					
					if(p1.yourTurn() && p1 == f.getPlayer() && p1.getMoney() >= CatSoldier.COST && f.getBuild()) { //Player 1 is valid
						
						p1.subtractMoney(CatSoldier.COST);
						f.buildUnit(new CatSoldier(p1), field);
						
					} else if(p2.yourTurn() && p2 == f.getPlayer() && p2.getMoney() >= CatSoldier.COST && f.getBuild()) {
						
						p2.subtractMoney(CatSoldier.COST);
						f.buildUnit(new CatSoldier(p2), field);
						
					}
					
					
				} 
				
				turnDisplay(p1,p2);
				field.deselectUnits();
			}
		});
		
		Button squirrel = new Button("Squirrel Rogue: $" + SquirrelRogue.COST);
		formatEconButton(squirrel, SquirrelRogue.COST, p1, p2);
		squirrel.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				ArrayList<Tile> unitList = field.getUnitTiles();
				if(unitList.size()==1 && unitList.get(0).getUnit() instanceof Facility) { //Checks for facility selected
					
					Facility f = (Facility)unitList.get(0).getUnit();
					
					if(p1.yourTurn() && p1 == f.getPlayer() && p1.getMoney() >= SquirrelRogue.COST && f.getBuild()) { //Player 1 is valid
						
						p1.subtractMoney(SquirrelRogue.COST);
						f.buildUnit(new SquirrelRogue(p1), field);
						
					} else if(p2.yourTurn() && p2 == f.getPlayer() && p2.getMoney() >= SquirrelRogue.COST && f.getBuild()) {
						
						p2.subtractMoney(SquirrelRogue.COST);
						f.buildUnit(new SquirrelRogue(p2), field);
						
					}
					
					
				} 
				
				turnDisplay(p1,p2);
				field.deselectUnits();
			}
		});
		
		Button axolotl = new Button("Axolotl God: $" + AxolotlGod.COST);
		formatEconButton(axolotl, AxolotlGod.COST, p1, p2);
		axolotl.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				ArrayList<Tile> unitList = field.getUnitTiles();
				if(unitList.size()==1 && unitList.get(0).getUnit() instanceof Docks) { //Checks for docks selected
					
					Docks d = (Docks)unitList.get(0).getUnit();
					
					if(p1.yourTurn() && p1 == d.getPlayer() && p1.getMoney() >= AxolotlGod.COST && d.getBuild()) { //Player 1 is valid
						
						p1.subtractMoney(AxolotlGod.COST);
						d.buildUnit(new AxolotlGod(p1), field);
						
					} else if(p2.yourTurn() && p2 == d.getPlayer() && p2.getMoney() >= AxolotlGod.COST && d.getBuild()) {
						
						p2.subtractMoney(AxolotlGod.COST);
						d.buildUnit(new AxolotlGod(p2), field);
						
					}
					
					
				} 
				
				turnDisplay(p1,p2);
				field.deselectUnits();
			}
		});
		
		Button duck = new Button("Duck Wizard: $" + DuckWizard.COST);
		formatEconButton(duck, DuckWizard.COST, p1, p2);
		duck.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				ArrayList<Tile> unitList = field.getUnitTiles();
				if(unitList.size()==1 && unitList.get(0).getUnit() instanceof Facility) { //Checks for facility selected
					
					Facility f = (Facility)unitList.get(0).getUnit();
					
					if(p1.yourTurn() && p1 == f.getPlayer() && p1.getMoney() >= DuckWizard.COST && f.getBuild()) { //Player 1 is valid
						
						p1.subtractMoney(DuckWizard.COST);
						f.buildUnit(new DuckWizard(p1), field);
						
					} else if(p2.yourTurn() && p2 == f.getPlayer() && p2.getMoney() >= DuckWizard.COST && f.getBuild()) {
						
						p2.subtractMoney(DuckWizard.COST);
						f.buildUnit(new DuckWizard(p2), field);
						
					}
					
					
				} else if(unitList.size()==1 && unitList.get(0).getUnit() instanceof Docks) { //Checks for docks selected
					
					Docks d = (Docks)unitList.get(0).getUnit();
					
					if(p1.yourTurn() && p1 == d.getPlayer() && p1.getMoney() >= DuckWizard.COST && d.getBuild()) { //Player 1 is valid
						
						p1.subtractMoney(DuckWizard.COST);
						d.buildUnit(new DuckWizard(p1), field);
						
					} else if(p2.yourTurn() && p2 == d.getPlayer() && p2.getMoney() >= DuckWizard.COST && d.getBuild()) {
						
						p2.subtractMoney(DuckWizard.COST);
						d.buildUnit(new DuckWizard(p2), field);
						
					}
				}
				
				turnDisplay(p1,p2);
				field.deselectUnits();
			}
		});
		
		Button flamingo = new Button("Flamingo Sniper: $" + FlamingoSniper.COST);
		formatEconButton(flamingo, FlamingoSniper.COST, p1, p2);
		flamingo.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				ArrayList<Tile> unitList = field.getUnitTiles();
				if(unitList.size()==1 && unitList.get(0).getUnit() instanceof Facility) { //Checks for facility selected
					
					Facility f = (Facility)unitList.get(0).getUnit();
					
					if(p1.yourTurn() && p1 == f.getPlayer() && p1.getMoney() >= FlamingoSniper.COST && f.getBuild()) { //Player 1 is valid
						
						p1.subtractMoney(FlamingoSniper.COST);
						f.buildUnit(new FlamingoSniper(p1), field);
						
					} else if(p2.yourTurn() && p2 == f.getPlayer() && p2.getMoney() >= FlamingoSniper.COST && f.getBuild()) {
						
						p2.subtractMoney(FlamingoSniper.COST);
						f.buildUnit(new FlamingoSniper(p2), field);
						
					}
					
					
				} else if(unitList.size()==1 && unitList.get(0).getUnit() instanceof Docks) { //Checks for docks selected
					
					Docks d = (Docks)unitList.get(0).getUnit();
					
					if(p1.yourTurn() && p1 == d.getPlayer() && p1.getMoney() >= FlamingoSniper.COST && d.getBuild()) { //Player 1 is valid
						
						p1.subtractMoney(FlamingoSniper.COST);
						d.buildUnit(new FlamingoSniper(p1), field);
						
					} else if(p2.yourTurn() && p2 == d.getPlayer() && p2.getMoney() >= FlamingoSniper.COST && d.getBuild()) {
						
						p2.subtractMoney(FlamingoSniper.COST);
						d.buildUnit(new FlamingoSniper(p2), field);
						
					}
				}
				
				turnDisplay(p1,p2);
				field.deselectUnits();
			}
		});
		
		Button armadillo = new Button("Armadillo Tank: $" + ArmadilloTank.COST);
		formatEconButton(armadillo, ArmadilloTank.COST, p1, p2);
		armadillo.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				ArrayList<Tile> unitList = field.getUnitTiles();
				if(unitList.size()==1 && unitList.get(0).getUnit() instanceof Facility) { //Checks for facility selected
					
					Facility f = (Facility)unitList.get(0).getUnit();
					
					if(p1.yourTurn() && p1 == f.getPlayer() && p1.getMoney() >= ArmadilloTank.COST && f.getBuild()) { //Player 1 is valid
						
						p1.subtractMoney(ArmadilloTank.COST);
						f.buildUnit(new ArmadilloTank(p1), field);
						
					} else if(p2.yourTurn() && p2 == f.getPlayer() && p2.getMoney() >= ArmadilloTank.COST && f.getBuild()) {
						
						p2.subtractMoney(ArmadilloTank.COST);
						f.buildUnit(new ArmadilloTank(p2), field);
						
					}
					
					
				} 
				
				turnDisplay(p1,p2);
				field.deselectUnits();
			}
		});
		
		Button bull = new Button("Bull Matador: $" + BullMatador.COST);
		formatEconButton(bull, BullMatador.COST, p1, p2);
		bull.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				ArrayList<Tile> unitList = field.getUnitTiles();
				if(unitList.size()==1 && unitList.get(0).getUnit() instanceof Facility) { //Checks for facility selected
					
					Facility f = (Facility)unitList.get(0).getUnit();
					
					if(p1.yourTurn() && p1 == f.getPlayer() && p1.getMoney() >= BullMatador.COST && f.getBuild()) { //Player 1 is valid
						
						p1.subtractMoney(BullMatador.COST);
						f.buildUnit(new BullMatador(p1), field);
						
					} else if(p2.yourTurn() && p2 == f.getPlayer() && p2.getMoney() >= BullMatador.COST && f.getBuild()) {
						
						p2.subtractMoney(BullMatador.COST);
						f.buildUnit(new BullMatador(p2), field);
						
					}
					
					
				} 
				
				turnDisplay(p1,p2);
				field.deselectUnits();
			}
		});
		
		econGUI.getChildren().add(cat);
		econGUI.getChildren().add(squirrel);
		econGUI.getChildren().add(axolotl);
		econGUI.getChildren().add(duck);
		econGUI.getChildren().add(flamingo);
		econGUI.getChildren().add(armadillo);
		econGUI.getChildren().add(bull);
		
		this.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(turnGUI.getText() != "Player 1 Wins" && turnGUI.getText() != "Player 2 Wins") {
					turnDisplay(p1,p2);
				}
			}
		});
		
		buttons = new HBox();
		buttons.setPadding(new Insets(4.0,4.0,4.0,4.0));
		
		move = new Button("MOVE");
		formatButton(move,Color.BLUE);
		
		move.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) { //Temporary testing
				
				field.removeMapButtons();
				ArrayList<Tile> unitList = field.getUnitTiles();
				
				if(unitList.size()==1 && unitList.get(0).getUnit().getPlayer().yourTurn()) {
					
					GenericUnit unit = unitList.get(0).getUnit();
					ArrayList<Tile> moveArea = unit.moveStyle(field);
					
					for(Tile t : moveArea) {
				
						t.setMyButton(new MoveButton(t,unitList.get(0),unit));
						
					}
				}
			}
			
		});
		
		attack = new Button("ATTACK");
		formatButton(attack,Color.RED);
		
		attack.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) { //Temporary testing
				
				field.removeMapButtons();
				ArrayList<Tile> unitList = field.getUnitTiles();
				
				if(unitList.size()==1 && unitList.get(0).getUnit().getPlayer().yourTurn()) {
		
					GenericUnit unit = unitList.get(0).getUnit();
					ArrayList<Tile> attackArea = unit.attackStyle(field);
					ArrayList<Tile> nullSpace = unit.nullStyle(field);
					
					for(Tile t : nullSpace) {
						
						if(attackArea.contains(t)) {
							t.setMyButton(new AttackButton(t,unitList.get(0),unit));
						} else {
							t.setMyButton(new NullButton());
						}
						
					}
					
				}
				
			}
			
		});
		
		endTurn = new Button("END TURN");
		formatButton(endTurn, Color.GRAY);
		
		endTurn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				field.removeMapButtons();
				field.deselectUnits();
				
				if(p1.yourTurn()) {
					p2.addMoney(3000);
				} else if(p2.yourTurn()) {
					p1.addMoney(3000);
				}
				
				if(p1.yourTurn() && field.getBuildingTiles(p2).size() == 0) {
					turnGUI.setText("Player 1 Wins");
				} else if(p2.yourTurn() && field.getBuildingTiles(p1).size() == 0) {
					turnGUI.setText("Player 2 Wins");
				} else {
					p1.switchTurn();
					p2.switchTurn();
					turnDisplay(p1,p2);
				}
	
				Tile[][] tiles = field.getTiles();
				
				for(Tile[] T : tiles) {
					
					for(Tile t : T) {
						
						GenericUnit unit = t.getUnit();
						
						if(unit!=null) {
							
							unit.resetActions();
							
						}
						
						if(unit instanceof Facility) { //Reset Builds
							Facility f = (Facility)t.getUnit();
							f.reset();
						}
						
					}
					
				}
				
			}
			
		});
		
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().add(move);
		buttons.getChildren().add(attack);
		buttons.getChildren().add(endTurn);
		
		setTop(turnGUI);
		BorderPane.setAlignment(turnGUI, Pos.CENTER);
		setBottom(buttons);
		setLeft(infoGUI);
		setRight(econGUI);
		
	}
	
	//Makes buttons have this game's unique style with changing colors
	private void formatButton(Button b, Color color) {
		
		BorderStroke[] stroke =  {new BorderStroke(Color.WHITESMOKE,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};

		b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		b.setBorder(new Border(stroke));
		b.setStyle("-fx-font-size: 32");
		b.setTextFill(Color.WHITESMOKE);
		b.setPrefWidth(362);
		
		b.setOnMouseEntered((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(color.darker().darker(),null,null)));
				
			}
			
		});
			
		b.setOnMouseExited((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
				
			}
			
		});
		
		b.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
				
			}
			
		});
			
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
				
			}
			
		});
		
	}
	
	private void turnDisplay(Player p1, Player p2) {
		if(p1.yourTurn()) {
			turnGUI.setText("Player 1: You have " + p1.getUnitCount() + " units and $" + p1.getMoney() + " remaining");
		} else if(p2.yourTurn()) {
			turnGUI.setText("Player 2: You have " + p2.getUnitCount() + " units and $" + p2.getMoney() + " remaining");
		} else {
			turnGUI.setText("Turn Error");
		}
	}
	
	private void formatEconButton(Button b, int unitCost, Player p1, Player p2) { 
		
		BorderStroke[] stroke =  {new BorderStroke(Color.WHITESMOKE,BorderStrokeStyle.SOLID,new CornerRadii(4.0),new BorderWidths(6.0,6.0,6.0,6.0))};

		b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		b.setBorder(new Border(stroke));
		b.setStyle("-fx-font-size: 16");
		b.setTextFill(Color.WHITESMOKE);
		b.setPrefWidth(sideWidth);
		
		b.setOnMouseEntered((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				if((p1.getMoney() >= unitCost && p1.yourTurn()) || (p2.getMoney() >= unitCost && p2.yourTurn())) {	
					b.setBackground(new Background(new BackgroundFill(Color.GREEN.darker().darker(),null,null)));
				} else {
					b.setBackground(new Background(new BackgroundFill(Color.RED.darker().darker(),null,null)));
				}
			}
			
		});
			
		b.setOnMouseExited((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
				
			}
			
		});
		
		b.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
				
			}
			
		});
			
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
				
			}
			
		});
		
	}
	
	public void initInfoGUI() {
		
		infoGUI = new ScrollPane();
		infoGUI.setPrefWidth(sideWidth);
		infoGUI.setPannable(true);
		
		VBox infoList = new VBox(); 
		
		formatInfoBlock(catImage, new CatSoldier(empty), infoList);
		formatInfoBlock(squirrelImage, new SquirrelRogue(empty), infoList);
		formatInfoBlock(axolotlImage, new AxolotlGod(empty), infoList);
		formatInfoBlock(duckImage, new DuckWizard(empty), infoList);
		formatInfoBlock(flamingoImage, new FlamingoSniper(empty), infoList);
		formatInfoBlock(armadilloImage, new ArmadilloTank(empty), infoList);
		formatInfoBlock(bullImage, new BullMatador(empty), infoList);
		
		infoGUI.setContent(infoList);
	}
	
	public void formatInfoBlock(Image image, GenericUnit unit, VBox infoList) {
		
		VBox infoBlock = new VBox();
		Text title = new Text("");
		HBox imageStats = new HBox();
		Text statsField = new Text(String.format("HP: %d%nAttack: %d%nSpeed: %d%nRange: %d", unit.getHP(), unit.getAttack(), unit.getMovementRange(), unit.getAttackRange()));
		Text blurb = new Text("");
		
		if(unit instanceof CatSoldier) {
			title.setText("Cat Soldier");
			blurb.setText(String.format("Not really sure how we managed to %n"
					+ "get these lazy furballs onto the %n"
					+ "battlefield. But man do they rock %n"
					+ "the uniform."));
		} else if(unit instanceof SquirrelRogue) {
			title.setText("Squirrel Rogue");
			blurb.setText("Squirrel Words");
		} else if(unit instanceof AxolotlGod) {
			title.setText("Axolotl Captain");
			blurb.setText("Axolotl Words");
		} else if(unit instanceof DuckWizard) {
			title.setText("Duck Wizard");
			blurb.setText("Duck Words");
		} else if(unit instanceof FlamingoSniper) {
			title.setText("Flamingo Sniper");
			blurb.setText("Flamingo Words");
		} else if(unit instanceof ArmadilloTank) {
			title.setText("Armadillo Tank");
			blurb.setText("Armadillo Words");
		} else if(unit instanceof BullMatador) {
			title.setText("Bull Matador");
			blurb.setText("Bull Words");
		} 
		
		imageStats.setSpacing(5);
		imageStats.getChildren().add(new ImageView(image));
		imageStats.getChildren().add(statsField);
		
		infoBlock.setAlignment(Pos.CENTER_LEFT);
		infoBlock.getChildren().add(title);
		infoBlock.getChildren().add(imageStats);
		infoBlock.getChildren().add(blurb);
		
		infoBlock.setPadding(new Insets(10,0,10,0));
		
		infoList.getChildren().add(infoBlock);
	}
	
}
