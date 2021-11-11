package application;

public class Player {
	
	private int number;
	private int unitCount;
	private boolean yourTurn;
	
	public Player(int number, boolean yourTurn) {
		this.number = number;
		unitCount = 0;
		this.yourTurn = yourTurn;
	}
	
	public int getPlayerNumber() {
		return number;
	}
	
	public int getUnitCount() {
		return unitCount;
	}
	
	public boolean yourTurn() {
		return yourTurn;
	}
	
	public void addUnit() {
		unitCount++;
	}
	
	public void subtractUnit() {
		unitCount--;
	}
	
	public void switchTurn() {
		yourTurn = !yourTurn;
	}
 
}
