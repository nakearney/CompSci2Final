package application;

public class Player {
	
	private int number;
	private int unitCount;
	private int money;
	private boolean yourTurn;
	private boolean firstTurn;
	
	public Player(int number, boolean yourTurn) {
		this.number = number;
		unitCount = 0;
		this.yourTurn = yourTurn;
		money = 150;
		firstTurn = true;
	}
	
	public int getPlayerNumber() {
		return number;
	}
	
	public int getUnitCount() {
		return unitCount;
	}
	
	public void addUnit() {
		unitCount++;
	}
	
	public void subtractUnit() {
		unitCount--;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void addMoney(int gain) {
		money = money + gain;
	}
	
	public void subtractMoney(int loss) {
		money = money - loss;
		if(money < 0) {
			money = 0;
		}
	}
	
	public boolean yourTurn() {
		return yourTurn;
	}
	
	public boolean getFirstTurn() {
		return firstTurn;
	}
	
	public void setFirstTurn(boolean first) {
		firstTurn = first;
	}
	
	public void switchTurn() {
		yourTurn = !yourTurn;
	}
 
}
