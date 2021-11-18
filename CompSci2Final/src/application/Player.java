package application;

public class Player {
	
	private int number;
	private int unitCount;
	private int money;
	private boolean yourTurn;
	
	public Player(int number, boolean yourTurn) {
		this.number = number;
		unitCount = 0;
		this.yourTurn = yourTurn;
		money = 5000;
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
	
	public void switchTurn() {
		yourTurn = !yourTurn;
	}
 
}
