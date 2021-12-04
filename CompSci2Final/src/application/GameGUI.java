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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameGUI extends BorderPane {


	//Contains Move, Attack, and EndTurn
	private HBox buttons;
	//Contains purchasing buttons
	private VBox econGUI;
	private Box hpDisp;
	//Contains info on troops
	private ScrollPane infoGUI;
	//Displays current turn
	private Label turnGUI;
	private Button attack;
	private Button move;
	private Button endTurn;
	private Player p1;
	private Player p2;
	private Player empty = new Player(0,false);
	private AudioPlayer ap;
	//Sets hp rect dimentions;
	private Rectangle OutsideRect=new Rectangle();
	private Rectangle hpBar=new Rectangle();

	//Width of the left and right elements in GameGUI (the info & turnGUI)
	private double sideWidth=225.0;
	
	//Initialize Images here
	private Image catImage = new Image("/Sprites/Cat_Soldier_Player1.png");
	private Image squirrelImage = new Image("/Sprites/Squirrel_Rogue_Player2.png");
	private Image axolotlImage = new Image("/Sprites/Axolotl_Sailor_Player1.png");;
	private Image duckImage = new Image("/Sprites/Duck_Wizard_Player2.png");;
	private Image flamingoImage = new Image("/Sprites/Flamingo_Sniper_Player1.png");;
	private Image armadilloImage = new Image("/Sprites/Armadillo_Tank_Player2.png");;
	private Image bullImage = new Image("/Sprites/Matador_Bull_Player1.png");;
	
	//Initializes Map in Main Currently. Then passes it here.
	public GameGUI(Player p1, Player p2, Map field, int trackIndex) {
		
		switch(trackIndex) {
			case 1:
				ap = new AudioPlayer("battletrack1.mp3");
				break;
			case 2: 
				ap = new AudioPlayer("battletrack1.mp3");
				break;
			case 3: 
				ap = new AudioPlayer("battletrack1.mp3");
				break;
			case 4:
				ap = new AudioPlayer("battletrack1.mp3");
				break;
			case 5: 
				ap = new AudioPlayer("battletrack1.mp3");
				break;
			case 6: 
				ap = new AudioPlayer("battletrack1.mp3");
				break;
			case 7:
				ap = new AudioPlayer("battletrack1.mp3");
				break;
			case 8: 
				ap = new AudioPlayer("battletrack1.mp3");
				break;
			case 9: 
				ap = new AudioPlayer("battletrack1.mp3");
				break;
		}
		
		
		
		ap.playTrack();
		
		setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		
		ScrollPane scroller = new ScrollPane();
		scroller.setPannable(true);
		scroller.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		scroller.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
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
		formatEconButton(cat, new CatSoldier(empty), CatSoldier.COST, p1, p2, field);
		Button squirrel = new Button("Squirrel Rogue: $" + SquirrelRogue.COST);
		formatEconButton(squirrel, new SquirrelRogue(empty), SquirrelRogue.COST, p1, p2, field);
		Button axolotl = new Button("Axolotl Captain: $" + AxolotlCaptain.COST);
		formatEconButton(axolotl, new AxolotlCaptain(empty), AxolotlCaptain.COST, p1, p2, field);
		Button duck = new Button("Duck Wizard: $" + DuckWizard.COST);
		formatEconButton(duck, new DuckWizard(empty), DuckWizard.COST, p1, p2, field);
		Button flamingo = new Button("Flamingo Sniper: $" + FlamingoSniper.COST);
		formatEconButton(flamingo, new FlamingoSniper(empty), FlamingoSniper.COST, p1, p2, field);
		Button armadillo = new Button("Armadillo Tank: $" + ArmadilloTank.COST);
		formatEconButton(armadillo, new ArmadilloTank(empty), ArmadilloTank.COST, p1, p2, field);
		Button bull = new Button("Bull Matador: $" + BullMatador.COST);
		formatEconButton(bull, new BullMatador(empty), BullMatador.COST, p1, p2, field);
		
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
		
		Button move = new Button("MOVE");
		formatButton(move,Color.BLUE);
		
		move.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) { //Temporary testing
				
				field.removeMapButtons();
				ArrayList<Tile> unitList = field.getUnitTiles();
				
				if(unitList.size()==1 && unitList.get(0).getUnit().getPlayer().yourTurn()) {
					
					GenericUnit unit = unitList.get(0).getUnit();
					ArrayList<Tile> moveArea = unit.moveStyle(field);
					
					if(moveArea==null) {
						field.deselectUnits();
						return;
					}
					
					for(Tile t : moveArea) {
				
						t.setMyButton(new MoveButton(t,unitList.get(0),unit));
						
					}
				}
			}
			
		});
		
		Button attack = new Button("ATTACK");
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
					
					if(nullSpace==null) {
						field.deselectUnits();
						return;
					}
					
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
		
		Button endTurn = new Button("END TURN");
		formatButton(endTurn, Color.GRAY);
		
		endTurn.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				field.removeMapButtons();
				field.deselectUnits();
				
				if(p1.yourTurn() && !p2.getFirstTurn()) {
					p2.addMoney(3000);
				} else if(p2.yourTurn()) {
					p1.addMoney(3000);
				} else {
					p2.setFirstTurn(false);
				}
				
				if(p1.yourTurn() && field.getBuildingTiles(p2).size() == 0) {
					turnGUI.setText("Player 1 Wins");
					gameOver();
				} else if(p2.yourTurn() && field.getBuildingTiles(p1).size() == 0) {
					turnGUI.setText("Player 2 Wins");
					gameOver();
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
						
						if(unit instanceof Building) { //Reset Builds
							Building f = (Building)t.getUnit();
							f.reset();
						}
						
					}
					
				}
				
			}
			
		});
		
		buttons.setAlignment(Pos.BASELINE_CENTER);
		buttons.getChildren().add(move);
		buttons.getChildren().add(attack);
		buttons.getChildren().add(endTurn);
		buttons.setMinWidth(100);
		
		setTop(turnGUI);
		BorderPane.setAlignment(turnGUI, Pos.CENTER);
		setLeft(infoGUI);
		setRight(econGUI);
		setBottom(buttons);
		
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
	
	private void formatEconButton(Button b, GenericUnit unit, int unitCost, Player p1, Player p2, Map field) { 
		
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
			
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
				
			}
			
		});
		
		b.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() { 
			@Override
			public void handle(MouseEvent event) {
				
				b.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
				
				ArrayList<Tile> unitList = field.getUnitTiles();
				
				Player currentPlayer;
				
				if(p1.yourTurn()) {
					currentPlayer = p1;
				} else if(p2.yourTurn()) {
					currentPlayer = p2;
				} else {
					currentPlayer = empty;
				}
				
				if(!(unit instanceof AxolotlCaptain)) {
					if(unitList.size()==1 && unitList.get(0).getUnit() instanceof Facility) { //Checks for facility selected
						
						Facility f = (Facility)unitList.get(0).getUnit();

						if(currentPlayer == f.getPlayer() && currentPlayer.getMoney() >= unitCost && f.getBuild()) { 
							
							currentPlayer.subtractMoney(unitCost);
							
							if(unit instanceof CatSoldier) {
								f.buildUnit(new CatSoldier(currentPlayer), field);
							} else if(unit instanceof SquirrelRogue) {
								f.buildUnit(new SquirrelRogue(currentPlayer), field);
							} else if(unit instanceof DuckWizard) {
								f.buildUnit(new DuckWizard(currentPlayer), field);
							} else if(unit instanceof FlamingoSniper) {
								f.buildUnit(new FlamingoSniper(currentPlayer), field);
							} else if(unit instanceof ArmadilloTank) {
								f.buildUnit(new ArmadilloTank(currentPlayer), field);
							} else if(unit instanceof BullMatador) {
								f.buildUnit(new BullMatador(currentPlayer), field);
							}
						} 
					} 
				}
				
				if(unit instanceof AxolotlCaptain || unit instanceof DuckWizard || unit instanceof FlamingoSniper) {
					
					if(unitList.size()==1 && unitList.get(0).getUnit() instanceof Carrier) { //Checks for facility selected
						
						Carrier c = (Carrier)unitList.get(0).getUnit();

						if(currentPlayer == c.getPlayer() && currentPlayer.getMoney() >= unitCost && c.getBuild()) { 
							
							currentPlayer.subtractMoney(unitCost);
							
							if(unit instanceof AxolotlCaptain) {
								c.buildUnit(new AxolotlCaptain(currentPlayer), field);
							} else if(unit instanceof DuckWizard) {
								c.buildUnit(new DuckWizard(currentPlayer), field);
							} else if(unit instanceof FlamingoSniper) {
								c.buildUnit(new FlamingoSniper(currentPlayer), field);
							} 
						} 
					} 
				}
				
				

				turnDisplay(p1,p2);
				field.deselectUnits();
			}
		});
		
	}
	
	private void initInfoGUI() {
		
		infoGUI = new ScrollPane();
		infoGUI.setPrefWidth(sideWidth);
		infoGUI.setPannable(true);
		
		VBox infoList = new VBox(); 
		
		formatInfoBlock(catImage, new CatSoldier(empty), infoList);
		formatInfoBlock(squirrelImage, new SquirrelRogue(empty), infoList);
		formatInfoBlock(axolotlImage, new AxolotlCaptain(empty), infoList);
		formatInfoBlock(duckImage, new DuckWizard(empty), infoList);
		formatInfoBlock(flamingoImage, new FlamingoSniper(empty), infoList);
		formatInfoBlock(armadilloImage, new ArmadilloTank(empty), infoList);
		formatInfoBlock(bullImage, new BullMatador(empty), infoList);
		
		infoGUI.setContent(infoList);
	}
	
	private void formatInfoBlock(Image image, GenericUnit unit, VBox infoList) {
		
		VBox infoBlock = new VBox();
		Background Background = new Background( new BackgroundFill( Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY ) );
		infoBlock.setBackground(Background);
		Text title = new Text("");
		title.setFill(Color.WHITE);
		title.setScaleY(1.5);
		title.setScaleX(1.5);
		title.setScaleZ(1.5);	
		HBox imageStats = new HBox();
		Text statsField = new Text(String.format("HP: %d%nAttack: %d%nSpeed: %d%nRange: %d", unit.getHP(), unit.getAttack(), unit.getMovementRange(), unit.getAttackRange()));
		Text blurb = new Text("");
		blurb.setFill(Color.WHITE);
		statsField.setFill(Color.WHITE);
		
		if(unit instanceof CatSoldier) {
			title.setText("Cat Soldier");
			blurb.setText(String.format("Not really sure how we managed to %n"
										+ "get these lazy furballs onto the %n"
										+ "battlefield. But man do they rock %n"
										+ "the uniform."));
		} else if(unit instanceof SquirrelRogue) {
			title.setText("Squirrel Rogue");
			blurb.setText("Squirrel Words");
		} else if(unit instanceof AxolotlCaptain) {
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
		
		infoBlock.setPadding(new Insets(10,10,10,20));
		
		infoList.getChildren().add(infoBlock);
	}

	private void gameOver() {
		
		Button gameOver = new Button("OK");
		formatButton(gameOver,Color.BISQUE);
		BorderPane.setAlignment(gameOver, Pos.CENTER);
		
		gameOver.setOnMouseReleased((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				ap.stop();
				Main.startScreen();
				
			}
			
		});
		
		this.setBottom(gameOver);
	}
	
	
	
}
